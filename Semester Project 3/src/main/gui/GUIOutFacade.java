package gui;

import Acquantiance.IERPFacade;
import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import java.util.Date;
import java.util.List;
import java.util.Queue;

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
    public boolean updateOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority, int orderID, boolean status) {
        return erpFacade.updateOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority, orderID, status);
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


    List<IProductionOrder> getProductionOrderQueue(){

        return erpFacade.getProductionOrderQueue();
    }

}
