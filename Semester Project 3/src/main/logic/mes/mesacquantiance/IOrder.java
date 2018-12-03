package logic.mes.mesacquantiance;

import acquantiance.ProductTypeEnum;

public interface IOrder {
    ProductTypeEnum getProductTypeEnum();
    int getAmount();
    float getProductionSpeed();



}
