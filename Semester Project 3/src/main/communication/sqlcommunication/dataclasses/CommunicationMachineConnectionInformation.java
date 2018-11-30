package communication.sqlcommunication.dataclasses;

import acquantiance.IMachineConnectionInformation;
import acquantiance.IMachineSpecification;

public class CommunicationMachineConnectionInformation implements IMachineConnectionInformation {
    private String machineID;
    private String machineIP;
    private String machineUsername;
    private String machinePassword;
    private IMachineSpecification specs;

    public void setMachineSpecification(IMachineSpecification specs) {
        this.specs = specs;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public void setMachineIP(String machineIP) {
        this.machineIP = machineIP;
    }

    public void setMachineUsername(String machineUsername) {
        this.machineUsername = machineUsername;
    }

    public void setMachinePassword(String machinePassword) {
        this.machinePassword = machinePassword;
    }

    @Override
    public IMachineSpecification getMachineSpecification() {
        return specs;
    }

    @Override
    public String getMachineID() {
        return machineID;
    }

    @Override
    public String getMachineIP() {
        return machineIP;
    }

    @Override
    public String getMachineUsername() {
        return machineUsername;
    }

    @Override
    public String getMachinePassword() {
        return machinePassword;
    }


}
