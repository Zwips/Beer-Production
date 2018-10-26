package logic.erp;

import Acquantiance.IERPFacade;

import java.util.Date;

public class ERPFacade implements IERPFacade {
    ERP erp = new ERP();

    @Override
    public boolean addOrder(int amount, float productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
        return erp.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
    }

    @Override
    public boolean addMachine(String processingPlantID, String machineName, String IPAddress, String userID, String password) {
        return erp.addMachine(processingPlantID, machineName, IPAddress, userID, password);
    }

    @Override
    public boolean addMachine(String machineName, String IPAddress, String userID, String password) {
        return erp.addMachine(machineName, IPAddress, userID, password);
    }

    @Override
    public void addProcessingPlant(String plantID) {
        erp.addProcessingPlant(plantID);
    }


}
