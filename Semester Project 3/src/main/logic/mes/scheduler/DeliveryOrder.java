package logic.mes.scheduler;

import acquantiance.ProductTypeEnum;
import logic.mes.mesacquantiance.IProductionOrder;

import java.util.Date;

public class DeliveryOrder implements IProductionOrder {


    private Date deliveryTime;
    private int orderID;
    private ProductTypeEnum productType;
    private int amount;
    private float productionSpeed;

    public DeliveryOrder(Date deliveryTime, int orderID, ProductTypeEnum productType, int amount, float productionSpeed) {
        this.deliveryTime = deliveryTime;
        this.orderID = orderID;
        this.productType = productType;
        this.amount = amount;
        this.productionSpeed = productionSpeed;
    }

    @Override
    public Integer getOrderID(){
        return this.orderID;
    }

    public Date getPlannedStart(){
        return this.deliveryTime;
    }

    @Override
    public ProductTypeEnum getProductType() {
        return this.productType;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public float getProductionSpeed() {
        return this.productionSpeed;
    }

    public void setAmount(int newAmount) {
        if (newAmount>0){
            this.amount = newAmount;
        } else {
            this.amount = 0;
        }
    }
}
