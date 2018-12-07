package communication;

import acquantiance.*;
import communication.machineconnection.MachineConnection;
import communication.mailcommunication.MailCommunicationFacade;
import communication.sqlcommunication.SQLCommunicationFacade;

import java.io.File;
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
    public IBusinessOrder selectFromOrder(int orderID) {
        return sqlFacade.selectFromOrder(orderID);
    }

    @Override
    public List<IBusinessOrder> getPendingOrders(Date dateFrom, Date dateTo) {
        return sqlFacade.getPendingOrders(dateFrom,dateTo);
    }

    @Override
    public List<IBusinessOrder> getPendingOrders() {
        return sqlFacade.getPendingOrders();
    }

    @Override
    public List<IBusinessOrder> getCompletedOrders() {
        return sqlFacade.getCompletedOrders();
    }

    @Override
    public void setOrderCompleted(int orderId) {
        sqlFacade.setOrderCompleted(orderId);
    }

    @Override
    public void insertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective, String factoryID) {
        sqlFacade.InsertIntoBatch(batchID,productType,amount,defective,factoryID);
    }

    @Override
    public void insertIntoBatch_log(int batchID, String machineID, int orderID, String factoryID) {
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
    public void logOrder(IBusinessOrder order) {
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
    public void logOrders(List<IBusinessOrder> orders) {
        this.sqlFacade.logOrders(orders);
    }

    @Override
    public void updateOrders(List<IBusinessOrder> orders) {
        this.sqlFacade.updateOrders(orders);
    }

    @Override
    public void logOEE(String factoryID, String machineID, int batchID, String state, Date timestamp, boolean isProducing) {
        sqlFacade.logOEE(factoryID,machineID, batchID, state, timestamp, isProducing);
    }

    @Override
    public IOEE getOEEByMachine(String machineID, String factoryID) {
        return sqlFacade.getOEEByMachine(machineID,factoryID);
    }

    @Override
    public IOEE getOEEByBatchID(int batchID, String factoryID) {
        return sqlFacade.getOEEByBatchID(batchID,factoryID);
    }

    @Override
    public IMachineConnection connectToMachine(String IPAddress, String userID, String password) {
        return new MachineConnection(IPAddress,userID,password);
    }

    @Override
    public void updateStorageCurrentAmount(int currentAmount, String factoryID, ProductTypeEnum type) {
        sqlFacade.updateStorageCurrentAmount(currentAmount, factoryID, type);
    }

    @Override
    public void updateStorageTargetAmount(int targetAmount, String factoryID, ProductTypeEnum type) {
        sqlFacade.updateStorageTargetAmount(targetAmount, factoryID, type);
    }

    @Override
    public IStorage getStorage(String factoryID) {
        return sqlFacade.getStorage(factoryID);
    }

    @Override
    public void saveBatchReport(int batchID, String factoryID, File file) {
        sqlFacade.saveBatchReport(batchID, factoryID, file);
    }


}
