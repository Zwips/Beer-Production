package communication;

import Acquantiance.*;
import communication.MailCommunication.MailCommunicationFacade;
import communication.SQLCommunication.SQLCommunicationFacade;

import java.util.*;

public class CommunicationFacade implements ICommunicationFacade {

    private ISQLCommunicationFacade sqlFacade;
    private IMailCommunicationFacade mailFacade;


    public CommunicationFacade(){
        sqlFacade = new SQLCommunicationFacade(); // skal muligvis flyttes til glue
        mailFacade = new MailCommunicationFacade(); // skal muligvis flyttes til glue
    }

    @Override
    public IBatch getBatchByBatchID(int batchID, String factoryID) {
       return sqlFacade.selectFromBatch(batchID, factoryID);
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
    public IBatchLog getBatchLogByBatchID(int batchID, String factoryID) {
        return sqlFacade.getBatchLogByBatchID(batchID,factoryID);
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
    public List<IProductionOrder> getPendingOrders() {
        return sqlFacade.getPendingOrders();
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
    public void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective, String factoryID) {
        sqlFacade.InsertIntoBatch(batchID,productType,amount,defective,factoryID);
    }

    @Override
    public void InsertIntoBatch_log(int batchID, String machineID, int orderID, String factoryID) {
        sqlFacade.InsertIntoBatch_log(batchID,machineID,orderID,factoryID);
    }

    @Override
    public void logDefectives(String machineId, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product) {
        sqlFacade.logDefectives(machineId, numberOfDefective,productsInBatch,machineSpeed,product);
    }

    @Override
    public void logTemperature(float value, Date timestamp, int batchID, String factoryID) {
        sqlFacade.logTemperature(value,timestamp,batchID,factoryID);
    }

    @Override
    public void logVibration(float value, Date timestamp, int batchID, String factoryID) {
        sqlFacade.logVibration(value,timestamp,batchID,factoryID);
    }

    @Override
    public void logHumidity(float value, Date timestamp, int batchID, String factoryID) {
        sqlFacade.logHumidity(value,timestamp,batchID,factoryID);
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
        mailFacade.SendMaintenanceEmail(machineName);
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

    @Override
    public int getNextBatchID(String plantID) {
        return sqlFacade.getNextBatchID(plantID);
    }

    @Override
    public HashMap<String, List<IMachineConnectionInformation>> getMachines() {
        return sqlFacade.getMachines();
    }

    @Override
    public void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password) {
        sqlFacade.InsertMachine(factoryID,machineID,machine_IP,userID,password);
    }

    @Override
    public void deleteMachine(String machineID) {
        sqlFacade.deleteMachine(machineID);
    }

    @Override
    public List<IMachineConnectionInformation> getMachines(String plantID) {
        return sqlFacade.getMachines(plantID);
    }

    @Override
    public Set<String> getPlantIDs() {
        return sqlFacade.getPlantIDs();
    }

    @Override
    public void logOrders(List<IProductionOrder> orders) {
        this.sqlFacade.logOrders(orders);
    }

    @Override
    public void updateOrders(List<IProductionOrder> orders) {
        this.sqlFacade.updateOrders(orders);
    }


}
