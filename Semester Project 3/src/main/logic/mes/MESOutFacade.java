package logic.mes;

import Acquantiance.*;

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

    public List<IProductionOrder> getPendingOrders() {
        return communicationFacade.getPendingOrders();
    }

    HashMap<String, List<IMachineConnectionInformation>> getMachines(){
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

    public void saveOrders(List<IProductionOrder> orders) {
        this.communicationFacade.logOrders(orders);
    }

    public void updateOrders(List<IProductionOrder> orders) {
        this.communicationFacade.updateOrders(orders);
    }
}
