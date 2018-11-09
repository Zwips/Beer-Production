package logic.erp;

import Acquantiance.IERPFacade;
import Acquantiance.ProductTypeEnum;

import java.util.Date;

public class ERPFacade implements IERPFacade {
    ERP erp = new ERP();

@Override
    public boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
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

    @Override
    public void removeProcessingPlant(String plantID) {
        erp.removeProcessingPlant(plantID);
    }

    @Override
    public boolean removeMachine(String processingPantID, String machineName) {
        return erp.removeMachine(processingPantID, machineName);
    }

    @Override
    public boolean removeMachine(String machineName) {
        return erp.removeMachine(machineName);
    }


}
