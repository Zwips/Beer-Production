package gui;

import Acquantiance.IERPFacade;
import Acquantiance.ProductTypeEnum;
import logic.erp.ERPFacade;

import java.util.Date;

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

}
