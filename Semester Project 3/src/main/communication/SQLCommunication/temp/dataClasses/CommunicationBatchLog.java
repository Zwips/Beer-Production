package communication.SQLCommunication.temp.dataClasses;

import Acquantiance.IBatchLog;

public class CommunicationBatchLog implements IBatchLog {
    //TODO remove this comment

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
