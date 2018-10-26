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
    public boolean addMachine(String processingPantID, String name, String address, String userID, String password) {
        return erp.addMachine(processingPantID, name, address, userID, password);
    }

    @Override
    public boolean addMachine( String name, String address, String userID, String password) {
        return erp.addMachine(name, address, userID, password);
    }

    @Override
    public void addProcessingPlant(String plantID) {
        erp.addProcessingPlant(plantID);
    }


}
