package logic.erp;

import acquantiance.IMESFacade;
import acquantiance.IMachineConnectionInformation;
import acquantiance.IProcessingCapacity;
import acquantiance.IBusinessOrder;
import acquantiance.*;
import communication.ISQLCommunicationFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ERPOutFacade {

    private static ERPOutFacade instance = null;
    private IMESFacade mesFacade;
    private ISQLCommunicationFacade sQLCommunationFacade;

    private ERPOutFacade(){
    }

    public static ERPOutFacade getInstance(){
        if (ERPOutFacade.instance == null){
            ERPOutFacade.instance = new ERPOutFacade();
        }

        return ERPOutFacade.instance;
    }

    public void injectSQLFacade(ISQLCommunicationFacade iSQLCommunationFacade){
      this.sQLCommunationFacade = iSQLCommunationFacade;
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

    List<IBusinessOrder> getPendingOrders(){
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

    public Map<String, IProcessingCapacity> addOrders(Map<String, List<IBusinessOrder>> destinations) {
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

    public IBusinessOrder getOrder(String plantID, int orderID) {

        return this.mesFacade.getOrder(plantID, orderID);
    }

    public Map<String, IProcessingCapacity> changeOrders(Map<String, List<IBusinessOrder>> destinations, IBusinessOrder oldOrder) {

        return this.mesFacade.changeOrders(destinations, oldOrder);
    }

    public IOEE getOEEByMachine(String machineID, String factoryID) {
        return this.sQLCommunationFacade.getOEEByMachine(machineID, factoryID);
    }


    public List<IBusinessOrder> getAllProductionOrders(){
        return this.mesFacade.getAllProductionOrdersInPlants();
    }

    public Set<String> getMachineIDsByFactoryID(String factoryID) {
        return mesFacade.getMachineIDsByFactoryID(factoryID);
    }
    public IOEEToGUI getOEE(String machineID,String factoryID) {
        return mesFacade.getOEE(machineID,factoryID);
    }
}
