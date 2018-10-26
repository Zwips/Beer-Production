package logic.erp;

import Acquantiance.IERPFacade;

import java.util.Date;

public class ERPFacade implements IERPFacade {
    ERP erp = new ERP();

@Override
    public boolean addOrder(int amount, float productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){

   return erp.addOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);

    }

}
