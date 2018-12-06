package logic.mes;

/** Represents a processing plant.
 * @author Asmus
 * @param addMachine Method for adding a machine to the list of machines.
 * @param checkForMachine Method for checking if a machine exists in the list of machines.
 * @param removeMachine Method for removing a machine from the list of machines.
 */

import acquantiance.*;
import com.prosysopc.ua.ServiceException;
import logic.mes.mesacquantiance.*;
import logic.mes.pid.PIDFacade;
import logic.mes.scheduler.DeliveryOrder;
import logic.mes.scheduler.PlantSchedulerFacade;

import java.io.IOException;
import java.util.*;

public class ProcessingPlant {

    private String plantID;
    private int nextBatchID;

    private IPlantSchedulerFacade scheduler;
    private IPIDFacade pidFacade;
    private ISpeedOptimizerFacade speedOptimizerFacade;

    private HashMap<String, IMesMachine> machines;
    private Set<String> idleMachines;

    private ProcessingCapacity capacity;
    private IStorage storage;

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
        this.idleMachines = new HashSet<>();
        this.pidFacade = new PIDFacade();

        this.initialiseStorage();
        this.initialiseBatchID();
        this.initialiseMachines(machines);
        this.initialiseProcessingCapacity();

    }

    private void initialiseStorage() {
        this.storage = MESOutFacade.getInstance().getStorage(this.plantID);
    }

    private void initialiseProcessingCapacity() {
        this.capacity = new ProcessingCapacity();
    }

    void initialiseMachines(List<IMachineConnectionInformation> machines) {
        for (IMachineConnectionInformation machineInfo : machines ) {
            this.addMachine(machineInfo.getMachineID(), machineInfo.getMachineIP(), machineInfo.getMachineUsername(), machineInfo.getMachinePassword());
        }
    }

    /** Method for initialising the next batchID
     *
     */
    private void initialiseBatchID(){
        nextBatchID = MESOutFacade.getInstance().getNextBatchID(this.plantID)+1;
    }


    boolean addMachine(String machineName, String IPAddress, String userID, String password){
        Machine machine = new Machine(machineName, IPAddress, userID, password,plantID);

        try {
            machine.subscribeToCurrentState(new MachinestateCompleteSubscriber(machineName, this));
        } catch (ServiceException e) {
            e.printStackTrace();
            return false;
        }

        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            this.analyseProduction(machineName, type);
        }

        if(machine.isConnected()) {
            machines.put(machineName, machine);
            this.scheduler.addQueue(machineName);

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

    public ProcessingCapacity getCapacity() {
        return capacity;
    }

    public boolean isStopped() {
        //TODO Check for if the machines are all stopped, and that all entries realting to this plant is
        // removed from the database or set to being deactivated

        return true;
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
            Integer completedOrderID = machine.getCurrentOrderID();
            MESOutFacade.getInstance().insertIntoBatch_Log((int)batchID, machineID, completedOrderID, this.plantID);

            //storage
            int completedProducts = (int) (batchSize - defectives);
            synchronized (this.storage){
                this.storage.setCurrentAmount(this.storage.getCurrentAmount(product)+completedProducts, product);
            }
            MESOutFacade.getInstance().updateStorageCurrentAmount(completedProducts, plantID, product);


            //defectives
            float machineSpeed = machine.readMachineSpeedCurrent();
            //TODO reactivate this when ready to connect to physical machine
            //MESOutFacade.getInstance().logDefective(machineID, defectives, batchSize, machineSpeed, product);


            //update status order
            if (completedOrderID != null){
                MESOutFacade.getInstance().setOrderCompleted(completedOrderID);
            }

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
        oee.setTimeOfChangeMap(ioee.getStateChangeMap());

        long runningTime = 0;
        long downTime = 1;
        for (Map.Entry<String, Long> entry : statisitcsMap.entrySet()) {
            if(entry.getKey().equals("Execute"))
            {
                runningTime = entry.getValue();
            }
            else
            {
                downTime += entry.getValue();
            }
        }
        double totalTime = runningTime + downTime;
        double oeePercent = runningTime/totalTime;

        oee.setoEEValue((float)oeePercent);
        return oee;
    }






    boolean executeNextOrder(String machineID){
        IProductionOrder order;
        boolean anotherOrder = false;

        do{
            order = this.scheduler.getNextOrder(machineID);

            if (order == null) {
                order = this.pidFacade.getOrder(this.storage, this.machines.get(machineID).getMachineSpecificationReadable());
                anotherOrder = false;
            } else {
                int productAmount = order.getAmount();
                int stock;
                synchronized (this.storage){
                    stock = this.storage.getCurrentAmount(order.getProductType());

                    if (productAmount>stock){
                        this.storage.setCurrentAmount(0, order.getProductType());
                    } else {
                        this.storage.setCurrentAmount(stock-productAmount, order.getProductType());
                        anotherOrder = true;
                    }
                }
                ((DeliveryOrder)order).setAmount(productAmount-stock);
                MESOutFacade.getInstance().updateStorageCurrentAmount(order.getAmount()-productAmount, plantID, order.getProductType());

                if (anotherOrder){
                    MESOutFacade.getInstance().setOrderCompleted(order.getOrderID());
                }
            }
        }while(anotherOrder);

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



    ProcessingCapacity addOrders(List<IBusinessOrder> orders){
        Set<String> startMachines = this.scheduler.addOrders(orders, this.machines.values());
        startMachines.retainAll(this.idleMachines);

        for (String startMachine : startMachines) {
            this.executeNextOrder(startMachine);
        }

        return new ProcessingCapacity();
    }

    List<IBusinessOrder> getAllProductionOrders(){
        return this.scheduler.getAllProductionOrders();
    }

    public IProcessingCapacity removeOrder(int orderID) throws NoSuchFieldException {
        this.idleMachines.add(this.scheduler.removeOrder(orderID));
        return new ProcessingCapacity();
    }

    public IBusinessOrder getOrder(int orderID) {
        return this.scheduler.getOrder(orderID);
    }

    public IProcessingCapacity changeOrders(List<IBusinessOrder> orders) {

        Set<String> startMachines = this.scheduler.changeOrders(orders, this.machines.values());

        startMachines.retainAll(this.idleMachines);
        for (String startMachine : startMachines) {
            this.executeNextOrder(startMachine);
        }

        return new ProcessingCapacity();
    }

    private ISpeedOptimizerFacade optimizer;

    public void analyseProduction(String machineID) {
        //TODO read real cost and sell values
        try {
            float productID = this.machines.get(machineID).readCurrentProductID();
            ProductTypeEnum type = this.machines.get(machineID).getProductType(productID);
            List<IErrorRateDataPoint> data = MESOutFacade.getInstance().getDefectivesByMachine(machineID,type);
            this.optimizer.optimize(this.machines.get(machineID).getMachineSpecificationOptimizable(), data , 5, 10,type);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void analyseProduction(String machineID, ProductTypeEnum type) {
        //TODO read real cost and sell values
        List<IErrorRateDataPoint> data = MESOutFacade.getInstance().getDefectivesByMachine(machineID,type);
        this.optimizer.optimize(this.machines.get(machineID).getMachineSpecificationOptimizable(), data , 5, 10,type);
    }
}
