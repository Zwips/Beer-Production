package logic.mes;

/** Represents a processing plant.
 * @author Asmus
 * @param addMachine Method for adding a machine to the list of machines.
 * @param checkForMachine Method for checking if a machine exists in the list of machines.
 * @param removeMachine Method for removing a machine from the list of machines.
 */

import acquantiance.*;
import acquantiance.IMesMachine;
import com.prosysopc.ua.ServiceException;
import logic.mes.mesacquantiance.IPlantSchedulerFacade;
import logic.mes.scheduler.PlantSchedulerFacade;

import java.time.LocalDate;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProcessingPlant {

    private IPlantSchedulerFacade scheduler;
    private HashMap<String, IMesMachine> machines;
    private Set<String> idleMachines;
    private String plantID;
    private int nextBatchID;
    private ProcessingCapacity capacity;
    private Map<String, BlockingQueue<IProductionOrder>> machineSchedule;


    /**
     * processing plants containing a Map with Machines
     * @param plantID the String identifier for the plant
     */
    ProcessingPlant(String plantID) {
        this(plantID, MESOutFacade.getInstance().getMachines(plantID));
    }

    ProcessingPlant(String plantID, List<IMachineConnectionInformation> machines) {
        this.plantID = plantID;
        this.machines = new HashMap<>();
        this.scheduler = new PlantSchedulerFacade();
        this.machineSchedule = new HashMap<>();
        this.idleMachines = new HashSet<>();

        this.initialiseBatchID();
        this.initialiseMachines(machines);
        this.initialiseProcessingCapacity();

    }

    private void initialiseProcessingCapacity() {
        this.capacity = new ProcessingCapacity();
    }

    void initialiseMachines(List<IMachineConnectionInformation> machines) {
        for (IMachineConnectionInformation machineInfo : machines ) {
            this.addMachine(machineInfo.getMachineID(), machineInfo.getMachineIP(), machineInfo.getMachineUsername(), machineInfo.getMachinePassword());
        }
    }

    boolean addMachine(String machineName, String IPAddress, String userID, String password){
        Machine machine = new Machine(machineName, IPAddress, userID, password,plantID);

        try {
            machine.subscribeToCurrentState(new MachinestateCompleteSubscriber(machineName, this));
        } catch (ServiceException e) {
            e.printStackTrace();
            return false;
        }

        if(machine.isConnected()) {
            machines.put(machineName, machine);
            this.machineSchedule.put(machineName, new LinkedBlockingQueue<>());

            //TODO change to specifications
            switch ((int) machine.readCurrentState()) {
                case 3:
                case 6:
                case 10:
                case 11:
                    break;
                default:
                    this.idleMachines.add(machineName);
            }

            return true;
        } else{
            return false;
        }
    }

    boolean checkForMachine(String machineName) {
        return machines.containsKey(machineName);
    }

    boolean removeMachine(String machineName) {
        return machines.remove(machineName, machines.get(machineName));
    }

    boolean executeNextOrder(String machineID){
        BlockingQueue<IProductionOrder> queue = this.machineSchedule.get(machineID);
        IProductionOrder order = null;

        synchronized(queue){
            int queueSize = queue.size();
            if (queueSize > 0) {
                order = queue.poll();
            }
        }



       if(order != null){
            boolean started = this.machines.get(machineID).executeOrder(order, this.nextBatchID++);
            if (started) {
                this.idleMachines.remove(machineID);
            }
            return started;
        } else{
            this.idleMachines.add(machineID);
            //this.machines.get(machineID).reset();
            return false;
        }
    }

    /** Method for initialising the next batchID
     *
     */
    private void initialiseBatchID(){
        nextBatchID = MESOutFacade.getInstance().getNextBatchID(this.plantID)+1;
    }

    ProcessingCapacity addOrders(List<IProductionOrder> orders){
        Set<String> startMachines = new HashSet<>();

        for (IProductionOrder order : orders) {
            Set<Map.Entry<String, List<IProductionOrder>>> destinations = this.scheduler.schedule(order, this.machines.values()).entrySet();
            for (Map.Entry<String, List<IProductionOrder>> destination : destinations) {
                this.machineSchedule.get(destination.getKey()).addAll(destination.getValue());
                startMachines.add(destination.getKey());
            }
        }

        MESOutFacade.getInstance().saveOrders(orders);

        startMachines.retainAll(this.idleMachines);
        for (String startMachine : startMachines) {
            this.executeNextOrder(startMachine);
        }

        return new ProcessingCapacity();
    }

    List<IProductionOrder> getAllProductionOrders(){

        List<IProductionOrder> orders = new ArrayList<>();

        for (Map.Entry<String, BlockingQueue<IProductionOrder>> machineQueue : this.machineSchedule.entrySet()) {
            orders.addAll(machineQueue.getValue());
            machineQueue.getValue().clear();
        }

        return orders;
    }

    public IProcessingCapacity removeOrder(int orderID) throws NoSuchFieldException {

        for (Map.Entry<String, BlockingQueue<IProductionOrder>> machineQueue : this.machineSchedule.entrySet()) {
            Iterator<IProductionOrder> iter = machineQueue.getValue().iterator();
            IProductionOrder order;

            while (iter.hasNext()) {
                order = iter.next();
                if (order.getOrderID() == orderID){
                    iter.remove();
                    return new ProcessingCapacity();
                }
            }
        }

        throw new NoSuchFieldException();
    }

    public ProcessingCapacity getCapacity() {
        return capacity;
    }

    public boolean isStopped() {
        //TODO Check for if the machines are all stopped, and that all entries realting to this plant is
        // removed from the database or set to being deactivated

        return true;
    }

    public IProductionOrder getOrder(int orderID) {

        for (Map.Entry<String, BlockingQueue<IProductionOrder>> machineQueue : this.machineSchedule.entrySet()) {
            System.out.println("ProcessingPlant machineName: " + machineQueue.getKey());
            System.out.println("\tProcessingPlant Queue size: " + machineQueue.getValue().size());

            for (IProductionOrder order : machineQueue.getValue()) {
                System.out.println("\tProcessingPlant order: " + order);
                if (order.getOrderID() == orderID){
                    return order;
                }
            }

        }

        return null;
    }

    public IProcessingCapacity changeOrders(List<IProductionOrder> orders) {
        Set<String> startMachines = new HashSet<>();

        for (IProductionOrder order : orders) {
            for (Map.Entry<String, List<IProductionOrder>> destination : this.scheduler.schedule(order, this.machines.values()).entrySet()) {
                this.machineSchedule.get(destination.getKey()).addAll(destination.getValue());
                startMachines.add(destination.getKey());
            }
        }

        MESOutFacade.getInstance().updateOrders(orders);

        startMachines.retainAll(this.idleMachines);
        for (String startMachine : startMachines) {
            this.executeNextOrder(startMachine);
        }


        return new ProcessingCapacity();
    }

    public void uploadBatchData(String machineID) {

        IMesMachine machine = this.machines.get(machineID);

        try {
            //batch
            float batchSize = machine.readProductsInBatch();
            int defectives = machine.readNumberOfDefectiveProducts();
            float productTypeID = machine.readCurrentProductID();
            float batchID = machine.readBatchIDCurrent();
            ProductTypeEnum product = machine.getProductType(productTypeID);
            MESOutFacade.getInstance().insertIntoBatch((int)batchID, product, (int)batchSize, defectives, this.plantID);


            //batch_log
            int completedOrderID = machine.getCurrentOrderID();
            MESOutFacade.getInstance().insertIntoBatch_Log((int)batchID, machineID, completedOrderID, this.plantID);
            //defectives
            float machineSpeed = machine.readMachineSpeedCurrent();
            //TODO reactivate this when ready to connect to physical machine
            //MESOutFacade.getInstance().logDefective(machineID, defectives, batchSize, machineSpeed, product);


            //update status order
            MESOutFacade.getInstance().setOrderCompleted(completedOrderID);
            new BatchReport((int)batchID, this.plantID);

        } catch (ServiceException | IOException e) {
            e.printStackTrace();
        }

    }

    public Set<String> getMachineIDs(){
        return machines.keySet();
    }
    public IOEEToGUI getOEE(String machineID) {
        OEE oee = new OEE();
        IOEE ioee = MESOutFacade.getInstance().getOEE(machineID, plantID);
        HashMap<String, Long> statisitcsMap = new HashMap<>();


        long lastCheckedTime = Long.MAX_VALUE;
        for (Date date : ioee.getStateChangeMap().keySet()) {
            if(date.getTime() < lastCheckedTime){
                lastCheckedTime = date.getTime();
            }
        }

        for (Map.Entry<Date, String> entry : ioee.getStateChangeMap().entrySet()) {
            if(!statisitcsMap.containsKey(entry.getValue())){
                statisitcsMap.put(entry.getValue(), entry.getKey().getTime() - lastCheckedTime);
                lastCheckedTime = entry.getKey().getTime();
            }
            else{
                Long newValue = statisitcsMap.get(entry.getValue()) + entry.getKey().getTime() - lastCheckedTime;
                statisitcsMap.replace(entry.getValue(),newValue);
            }
        }
        oee.setStatisticsMap(statisitcsMap);
        oee.setoEEValue(ioee.getOEE());

        return oee;
    }
}
