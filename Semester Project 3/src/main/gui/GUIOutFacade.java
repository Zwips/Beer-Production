package gui;

import acquantiance.IERPFacade;
import acquantiance.IBusinessOrder;
import acquantiance.ProductTypeEnum;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class GUIOutFacade {

    private static GUIOutFacade instance = null;
    private IERPFacade erpFacade;

    private GUIOutFacade(){
    }

    public static GUIOutFacade getInstance(){
        if (GUIOutFacade.instance == null){
            GUIOutFacade.instance = new GUIOutFacade();
        }

        return GUIOutFacade.instance;
    }

    public void injectCommunicationFacade(IERPFacade erpFacade){
        this.erpFacade = erpFacade;
    }

    public boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){

        return erpFacade.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);

    }
    public boolean updateOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority, int orderID) {
        return erpFacade.updateOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority, orderID);
    }
    boolean addMachine(String machineName, String IPaddress, String userID, String password){
        return erpFacade.addMachine(machineName, IPaddress, userID, password);
    }

    boolean removeMachine(String machineName) {
        return erpFacade.removeMachine(machineName);
    }

    boolean removeMachine(String processingPlantID, String machineName) {
        return erpFacade.removeMachine(processingPlantID, machineName);
    }

    void addProcessingPlant(String processingPlantID) {
        erpFacade.addProcessingPlant(processingPlantID);
    }

    void removeProcessingPlant(String processingPlantID) {
        erpFacade.removeProcessingPlant(processingPlantID);
    }


    List<IBusinessOrder> getProductionOrderQueue(){

        return erpFacade.getProductionOrderQueue();
    }
    public IOEEToGUI getOEEByMachine(String machineID, String factoryID) {
        return erpFacade.getOEE(machineID,factoryID);
    }
    public Set<String> getProcessingPlants(){
        return this.erpFacade.getProcessingPlants();
    }
    public Set<String> getMachineIDsByFactoryID(String factoryID) {
        return this.erpFacade.getMachineIDsByFactoryID(factoryID);
    }



}
