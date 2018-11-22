package logic.erp;

import Acquantiance.IMESFacade;
import Acquantiance.IMachineConnectionInformation;
import Acquantiance.IProcessingCapacity;
import Acquantiance.IProductionOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ERPOutFacade {

    private static ERPOutFacade instance = null;
    private IMESFacade mesFacade;

    private ERPOutFacade(){
    }

    public static ERPOutFacade getInstance(){
        if (ERPOutFacade.instance == null){
            ERPOutFacade.instance = new ERPOutFacade();
        }

        return ERPOutFacade.instance;
    }

    public Set<String> getPlantIDs() {
        return mesFacade.getplantIDs();
    }

    public void injectCommunicationFacade(IMESFacade mesFacade){
        this.mesFacade = mesFacade;
    }

    public int getNextOrderID(){
        return mesFacade.getNextOrderID();
    }

    List<IProductionOrder> getPendingOrders(){
        return mesFacade.getPendingOrders();
    }

    public HashMap<String, List<IMachineConnectionInformation>> getMachines(){
        return mesFacade.getMachines();
    }

    public void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password){
        mesFacade.InsertMachine(factoryID,machineID, machine_IP, userID, password);
    }

    public void deleteMachine(String machineID){
        mesFacade.deleteMachine(machineID);
    }

    public Map<String, IProcessingCapacity> getProductionCapacities() {
        return this.mesFacade.getProductionsCapacities();
    }

    public void addPlant(String plantID) {
        this.mesFacade.addPlant(plantID);
    }

    public boolean removePlant(String plantID) {
        return this.mesFacade.removePlant(plantID);
    }

    public Map<String, IProcessingCapacity> addOrders(Map<String, List<IProductionOrder>> destinations) {
        return this.mesFacade.addOrders(destinations);
    }

    public boolean addMachine(String processingPlantID, String machineName, String ipAddress, String userID, String password) {
        return this.mesFacade.addMachine(processingPlantID, machineName, ipAddress, userID, password);
    }

    public boolean checkForMachine(String machineName) {
        return this.mesFacade.checkForMachine(machineName);
    }

    public boolean removeMachine(String thePlant, String machineName) {
        return this.mesFacade.removeMachine(thePlant, machineName);
    }

    public IProcessingCapacity removeOrder(String plantID, int orderID) throws NoSuchFieldException {
        return this.mesFacade.removeOrder(plantID, orderID);
    }

    public IProductionOrder getOrder(String plantID, int orderID) {

        return this.mesFacade.getOrder(plantID, orderID);
    }

    public Map<String, IProcessingCapacity> changeOrders(Map<String, List<IProductionOrder>> destinations) {

        return this.mesFacade.changeOrders(destinations);
    }
}
