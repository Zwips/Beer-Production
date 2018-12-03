package logic.mes.scheduler;

import acquantiance.ProductTypeEnum;
import logic.mes.mesacquantiance.IOrder;

public class DeliveryOrder implements IOrder {


    @Override
    public ProductTypeEnum getProductTypeEnum() {
        return null;
    }

    @Override
    public int getAmount() {
        return 0;
    }

    @Override
    public float getProductionSpeed() {
        return 0;
    }
}
