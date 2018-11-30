package logic.mes;

import acquantiance.ProductTypeEnum;

public interface IOrder {
    ProductTypeEnum getProductTypeEnum();
    int getAmount();
    float getProductionSpeed();



}
