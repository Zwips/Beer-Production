package logic.mes;

import Acquantiance.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    public void SendStopEmail(String machineName){
        communicationFacade.SendStopEmail(machineName);
    }

    public void SendInventoryEmail(String machineName) {communicationFacade.SendInventoryEmail(machineName);
    }

    public void SendAbortEmail(String machineName) { communicationFacade.SendAbortEMail(machineName);
    }
    public void SendMaintenenceEmail(String machineName) {
        communicationFacade.SendMaintenenceEmail(machineName);
    }

    public void SendPowerlossEmail(String machineName){  communicationFacade.SendPowerLossEmail(machineName);
    }
    public void logTemperature(float value, Date timestamp, int batchID) {

    }

    public void logVibration(float value, Date timestamp, int batchID) {

    }

    public void logHumidity(float value, Date timestamp, int batchID){

    }

    int getNextOrderID(){
        return communicationFacade.getNextOrderID();
    }
    int getNextBatchID(){
        return communicationFacade.getNextBatchID();
    }


    public List<IProductionOrder> getPendingOrders() {
        return communicationFacade.getPendingOrders();
    }

    HashMap<String, List<IMachineConnectionInformation>> getMachines(){
        return communicationFacade.getMachines();
    }
    void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password){
        communicationFacade.InsertMachine(factoryID,machineID, machine_IP, userID, password);
    }

    void deleteMachine(String machineID){
        communicationFacade.deleteMachine(machineID);
    }

}
