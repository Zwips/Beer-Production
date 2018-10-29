package logic.erp;

import Acquantiance.IMesMachine;

import java.util.HashMap;

public class ProcessingPlant {
    private HashMap<String, IMesMachine> machines;
    private String plantID;

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
        }
        else
        {
            return false;
        }
    }
}
