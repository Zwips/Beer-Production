package logic.mes;

/** Represents a processing plant.
 * @author Asmus
 * @param addMachine Method for adding a machine to the list of machines.
 * @param checkForMachine Method for checking if a machine exists in the list of machines.
 * @param removeMachine Method for removing a machine from the list of machines.
 */

import Acquantiance.IMachineConnectionInformation;
import Acquantiance.IMesMachine;
import Acquantiance.IProcessingCapacity;
import Acquantiance.IProductionOrder;
import com.prosysopc.ua.ServiceException;
import logic.mes.scheduler.PlantSchedulerFacade;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProcessingPlant {

    private IPlantSchedulerFacade scheduler;
    private HashMap<String, IMesMachine> machines;
    private String plantID;
    private int nextBatchID;
    private ProcessingCapacity capacity;
    private Map<String, ConcurrentLinkedQueue<IProductionOrder>> machineSchedule;


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
            this.machineSchedule.put(machineName, new ConcurrentLinkedQueue<>());
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
        ConcurrentLinkedQueue<IProductionOrder> queue = this.machineSchedule.get(machineID);
        IProductionOrder order = queue.poll();

        if (order != null) {
            return this.machines.get(machineID).executeOrder(order, this.nextBatchID);
        }
        return false;
    }

    /** Method for initialising the next batchID
     *
     */
    private void initialiseBatchID(){
        nextBatchID = MESOutFacade.getInstance().getNextBatchID(this.plantID);
    }

    //TODO change to a plant scheduler
    @SuppressWarnings("Duplicates")
    ProcessingCapacity addOrders(List<IProductionOrder> orders){

        for (IProductionOrder order : orders) {
            for (Map.Entry<String, List<IProductionOrder>> destination : this.scheduler.schedule(order, this.machines.values()).entrySet()) {
                this.machineSchedule.get(destination.getKey()).addAll(destination.getValue());
            }
        }

        MESOutFacade.getInstance().saveOrders(orders);

        return new ProcessingCapacity();
    }

    //TODO or it should remove each element and leave an empty queue, and how about machine queues?
    List<IProductionOrder> getAllProductionOrders(){

        List<IProductionOrder> orders = new ArrayList<>();

        for (Map.Entry<String, ConcurrentLinkedQueue<IProductionOrder>> machineQueue : this.machineSchedule.entrySet()) {
            orders.addAll(machineQueue.getValue());
            machineQueue.getValue().clear();
        }

        return orders;
    }

    public IProcessingCapacity removeOrder(int orderID) throws NoSuchFieldException {

        for (Map.Entry<String, ConcurrentLinkedQueue<IProductionOrder>> machineQueue : this.machineSchedule.entrySet()) {
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

        for (Map.Entry<String, ConcurrentLinkedQueue<IProductionOrder>> machineQueue : this.machineSchedule.entrySet()) {
            Iterator<IProductionOrder> iter = machineQueue.getValue().iterator();
            IProductionOrder order;

            while (iter.hasNext()) {
                order = iter.next();
                if (order.getOrderID() == orderID){
                    return order;
                }
            }
        }

        return null;
    }

    @SuppressWarnings("Duplicates")
    public IProcessingCapacity changeOrders(List<IProductionOrder> orders) {

        for (IProductionOrder order : orders) {
            for (Map.Entry<String, List<IProductionOrder>> destination : this.scheduler.schedule(order, this.machines.values()).entrySet()) {
                this.machineSchedule.get(destination.getKey()).addAll(destination.getValue());
            }
        }

        MESOutFacade.getInstance().updateOrders(orders);

        return new ProcessingCapacity();
    }
}
