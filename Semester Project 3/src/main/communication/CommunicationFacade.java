package communication;

import Acquantiance.*;
import communication.MailCommunication.MailCommunicationFacede;
import communication.SQLCommunication.SQLCommunicationFacade;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommunicationFacade implements ICommunicationFacade {

    private ISQLCommunicationFacade sqlFacade;
    private IMailCommunicaitonFacade mailFacade;


    public CommunicationFacade(){
        sqlFacade = new SQLCommunicationFacade(); // skal muligvis flyttes til glue
        mailFacade = new MailCommunicationFacede(); // skal muligvis flyttes til glue
    }

    @Override
    public IBatch getBatchByBatchID(int batchID) {
       return sqlFacade.selectFromBatch(batchID);
    }

    @Override
    public Map getTemperaturesByMachine(String machineID, Date dateFrom) {
        return sqlFacade.selectFromTemperature(machineID,dateFrom);
    }

    @Override
    public Map getHumiditiesByMachine(String machineID, Date dateFrom) {
        return sqlFacade.selectFromHumidity(machineID,dateFrom);
    }

    @Override
    public Map getVibrationsByMachine(String machineID, Date dateFrom) {
        return sqlFacade.selectFromVibration(machineID,dateFrom);
    }

    @Override
    public IBatchLog getBatchLogByBatchID(int batchID) {
        return sqlFacade.getBatchLogByBatchID(batchID);
    }

    @Override
    public List<IBatchLog> getBatchLogByMachineID(String machineID) {
        return sqlFacade.getBatchLogByMachineID(machineID);
    }

    @Override
    public IProductionOrder selectFromOrder(int orderID) {
        return sqlFacade.selectFromOrder(orderID);
    }

    @Override
    public List<IProductionOrder> getPendingOrders(Date dateFrom, Date dateTo) {
        return sqlFacade.getPendingOrders(dateFrom,dateTo);
    }

    @Override
    public List<IProductionOrder> getCompletedOrders() {
        return sqlFacade.getCompletedOrders();
    }

    @Override
    public void setOrderCompleted(int orderId) {
        sqlFacade.setOrderCompleted(orderId);
    }

    @Override
    public void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective) {
        sqlFacade.InsertIntoBatch(batchID,productType,amount,defective);
    }

    @Override
    public void InsertIntoBatch_log(int batchID, String machineID, int orderID) {
        sqlFacade.InsertIntoBatch_log(batchID,machineID,orderID);
    }

    @Override
    public void logDefectives(String machineId, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product) {
        sqlFacade.logDefectives(machineId, numberOfDefective,productsInBatch,machineSpeed,product);
    }

    @Override
    public void logTemperature(float value, Date timestamp, int batchID) {
        sqlFacade.logTemperature(value,timestamp,batchID);
    }

    @Override
    public void logVibration(float value, Date timestamp, int batchID) {
        sqlFacade.logVibration(value,timestamp,batchID);
    }

    @Override
    public void logHumidity(float value, Date timestamp, int batchID) {
        sqlFacade.logHumidity(value,timestamp,batchID);
    }

    @Override
    public void logOrder(IProductionOrder order) {
        sqlFacade.logOrder(order);
    }

    @Override
    public void SendAbortEMail(String machineName) {
        mailFacade.SendAbortEmail(machineName);
    }

    @Override
    public void SendStopEmail(String machineName) {
        mailFacade.SendStopEmail(machineName);

    }

    @Override
    public void SendInventoryEmail(String machineName) {
        mailFacade.SendInventoryEmail(machineName);
    }

    @Override
    public void SendMaintenenceEmail(String machineName) {
        mailFacade.SendMaintenenceEmail(machineName);
    }

    @Override
    public void SendPowerLossEmail(String machineName) {
        mailFacade.SendPowerLossEmail(machineName);
    }

    @Override
    public int getNextOrderID() {
        return sqlFacade.getNextOrderID();
    }

    @Override
    public int getNextBatchID() {
        return sqlFacade.getNextBatchID();
    }


}
