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


}
