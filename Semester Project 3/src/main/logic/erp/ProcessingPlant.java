package logic.erp;

/** Represents a processing plant.
 * @author Asmus
 * @param addMachine Method for adding a machine to the list of machines.
 * @param checkForMachine Method for checking if a machine exists in the list of machines.
 * @param removeMachine Method for removing a machine from the list of machines.
 */

import Acquantiance.IMesMachine;

import java.util.HashMap;


public class ProcessingPlant {
    private HashMap<String, IMesMachine> machines;
    private String plantID;

    /**
     * processing plants containing a Map with Machines
     * @param plantID the String identifier for the plant
     */
    ProcessingPlant(String plantID)
    {
        this.plantID = plantID;
        machines = new HashMap<>();
    }


    boolean addMachine(String machineName, String IPAddress, String userID, String password){
        IMesMachine machine = ERPOutFacade.getInstance().createMachine(machineName, IPAddress, userID, password);
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
}
