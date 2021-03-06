package communication.sqlcommunication.dataclasses;

import acquantiance.IBatchLog;

public class CommunicationBatchLog implements IBatchLog {

    private String machineID;
    private int orderID;
    private int batchID;

    @Override
    public String getMachineID() {
        return this.machineID;
    }

    @Override
    public int getBatchID() {
        return this.batchID;
    }

    @Override
    public int getOrderID() {
        return this.orderID;
    }

    public void setMachineID(String machineID) {
        this.machineID = machineID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }
}
