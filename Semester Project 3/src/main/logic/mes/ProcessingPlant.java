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
import logic.erp.ERPOutFacade;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ProcessingPlant {

    private HashMap<String, IMesMachine> machines;
    private String plantID;
    private ConcurrentLinkedQueue<IProductionOrder> Queue;
    private int nextBatchID;
    private ProcessingCapacity capacity;

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
            return true;
        } else{
            return false;
        }
    }

    boolean checkForMachine(String machineName)
    {
        return machines.containsKey(machineName);
    }

    boolean removeMachine(String machineName)
    {
        return machines.remove(machineName, machines.get(machineName));
    }

    boolean executeNextOrder(String machineID){
        IProductionOrder order = this.Queue.poll();

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
    ProcessingCapacity addOrders(List<IProductionOrder> orders){

        if (this.Queue==null) {
            this.Queue = new ConcurrentLinkedQueue<>();
            for (IProductionOrder order : orders) {
                this.Queue.add(order);
            }
        } else {
            for (IProductionOrder order : orders) {
                this.Queue.add(order);
            }
        }

        return new ProcessingCapacity();
    }

    //TODO or it should remove each element and leave an empty queue?
    List<IProductionOrder> getAllProductionOrders(){

        List<IProductionOrder> orders = new ArrayList<>();
        for (IProductionOrder productionOrder : this.Queue) {
            orders.add(productionOrder);
        }

        this.Queue = null;
        return orders;
    }

}
