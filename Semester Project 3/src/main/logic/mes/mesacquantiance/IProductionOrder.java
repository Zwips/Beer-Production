package logic.mes.mesacquantiance;

import acquantiance.ProductTypeEnum;

import java.util.Date;

public interface IProductionOrder {


    ProductTypeEnum getProductType();
    int getAmount();
    float getProductionSpeed();

    Integer getOrderID();
}
