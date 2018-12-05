package logic.mes;

import acquantiance.*;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MESOutFacade {

    private static MESOutFacade instance = null;
    private ICommunicationFacade communicationFacade;

    private MESOutFacade(){
    }

    public static MESOutFacade getInstance(){
        if (MESOutFacade.instance == null){
            MESOutFacade.instance = new MESOutFacade();
        }

        return MESOutFacade.instance;
    }

    public void injectCommunicationFacade(ICommunicationFacade communicationFacade){
        this.communicationFacade = communicationFacade;
    }

    public void logDefective(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product){
        communicationFacade.logDefectives(machineID, numberOfDefective, productsInBatch, machineSpeed, product);
    }

    public IBatch getBatch(int BatchID, String factoryID){
        return communicationFacade.getBatchByBatchID(BatchID, factoryID);
    }

    public void SendStopEmail(String machineName) {
        communicationFacade.SendStopEmail(machineName);
    }

    public void SendInventoryEmail(String machineName) {
        communicationFacade.SendInventoryEmail(machineName);
    }

    public void SendAbortEmail(String machineName) {
        communicationFacade.SendAbortEMail(machineName);
    }

    public void SendMaintenenceEmail(String machineName) {
        communicationFacade.SendMaintenenceEmail(machineName);
    }

    public void SendPowerlossEmail(String machineName){
        communicationFacade.SendPowerLossEmail(machineName);
    }

    public void logTemperature(float value, Date timestamp, int batchID, String factoryID) {
        this.communicationFacade.logTemperature(value, timestamp, batchID, factoryID);
    }

    public void logVibration(float value, Date timestamp, int batchID, String factoryID) {
        this.communicationFacade.logVibration(value, timestamp, batchID, factoryID);
    }

    public void logHumidity(float value, Date timestamp, int batchID, String factoryID){
        this.communicationFacade.logHumidity(value, timestamp, batchID, factoryID);
    }

    int getNextOrderID(){
        return communicationFacade.getNextOrderID();
    }

    int getNextBatchID(String plantID){
        return communicationFacade.getNextBatchID(plantID);
    }

    public List<IBusinessOrder> getPendingOrders() {
        return communicationFacade.getPendingOrders();
    }

    public HashMap<String, List<IMachineConnectionInformation>> getMachines(){
        return communicationFacade.getMachines();
    }

    List<IMachineConnectionInformation> getMachines(String plantID){
        return communicationFacade.getMachines(plantID);
    }

    void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password){
        communicationFacade.InsertMachine(factoryID,machineID, machine_IP, userID, password);
    }

    void deleteMachine(String machineID){
        communicationFacade.deleteMachine(machineID);
    }

    public Set<String> getPlantIDs() {
        return this.communicationFacade.getPlantIDs();
    }

    public void saveOrders(List<IBusinessOrder> orders) {
        this.communicationFacade.logOrders(orders);
    }

    public void updateOrders(List<IBusinessOrder> orders) {
        this.communicationFacade.updateOrders(orders);
    }

    public void logOEE(String factoryID, String machineID, int batchID, String state, Date timestamp, boolean isProducing){
        communicationFacade.logOEE(factoryID, machineID, batchID, state, timestamp, isProducing);
    }

    public void insertIntoBatch(int batchID, ProductTypeEnum product, int batchSize, int defectives, String plantID) {
        this.communicationFacade.insertIntoBatch(batchID, product, batchSize, defectives, plantID);
    }

    public void insertIntoBatch_Log(int batchID, String machineID, Integer completedOrderID, String plantID) {
        this.communicationFacade.insertIntoBatch_log(batchID, machineID, completedOrderID, plantID);
    }

    public void setOrderCompleted(int completedOrderID) {
        this.communicationFacade.setOrderCompleted(completedOrderID);
    }

    public IMachineConnection connectToMachine(String IPAddress,String userID,String password){
        return communicationFacade.connectToMachine(IPAddress, userID, password);
    }

    public void updateStorageCurrentAmount(int currentAmount, String factoryID, ProductTypeEnum type) {
        communicationFacade.updateStorageCurrentAmount(currentAmount, factoryID, type);
    }

    public void updateStorageTargetAmount(int targetAmount, String factoryID, ProductTypeEnum type) {
        communicationFacade.updateStorageTargetAmount(targetAmount, factoryID, type);
    }

    public IStorage getStorage(String factoryID) {
        return communicationFacade.getStorage(factoryID);
    }

    public IOEE getOEE(String machineID,String factoryID){
        return communicationFacade.getOEEByMachine(machineID,factoryID);
    }



    public void saveBatchReport(int batchID, String factoryID, File file) {
        communicationFacade.saveBatchReport(batchID, factoryID, file);
    }
}
