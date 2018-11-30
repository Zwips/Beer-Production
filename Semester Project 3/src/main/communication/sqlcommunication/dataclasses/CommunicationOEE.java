package communication.sqlcommunication.dataclasses;

import acquantiance.IOEE;

import java.util.Date;
import java.util.Map;

public class CommunicationOEE implements IOEE {

    private float oee;
    private Map<Date, String> stateChangeMap;
    private String machineID;
    private String factoryID;

    public void setOee(float oee) {
        this.oee = oee;
    }

    public void setStateChangeMap(Map<Date, String> stateChangeMap) {
        this.stateChangeMap = stateChangeMap;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public void setFactoryID(String factoryID) {
        this.factoryID = factoryID;
    }

    @Override
    public float getOEE() {
        return oee;
    }

    @Override
    public Map<Date, String> getStateChangeMap() {
        return stateChangeMap;
    }

    @Override
    public String getMachineID() {
        return machineID;
    }

    @Override
    public String getFactoryID() {
        return factoryID;
    }
}
