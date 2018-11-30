package logic.mes.pid;

import acquantiance.ProductTypeEnum;
import logic.mes.IOrder;

public class PIDOrder implements IOrder {
    private ProductTypeEnum productTypeEnum;
    private int amount;
    private float productionSpeed;


    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setProductionSpeed(float productionSpeed) {
        this.productionSpeed = productionSpeed;
    }

    public void setProductTypeEnum(ProductTypeEnum productTypeEnum) {
        this.productTypeEnum = productTypeEnum;
    }


    @Override
    public ProductTypeEnum getProductTypeEnum() {
        return productTypeEnum;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public float getProductionSpeed() {
        return productionSpeed;
    }



}