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

    boolean addMachine(String name, String address, String userID, String password){
        IMesMachine machine = ERPOutFacade.getInstance().createMachine(name, address, userID, password);
        if(machine.isConnected()) {
            machines.put(name, machine);
            return true;
        }
        else
        {
            return false;
        }
    }
}
