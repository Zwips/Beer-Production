package logic.mes.scheduler;

import acquantiance.ProductTypeEnum;
import logic.mes.mesacquantiance.IProductionOrder;

import java.util.Date;

public class DeliveryOrder implements IProductionOrder, Comparable<DeliveryOrder> {


    private Date deliveryTime;
    private Date deadline;
    private long productionTime;
    private int orderID;
    private ProductTypeEnum productType;
    private int amount;
    private float productionSpeed;

    public DeliveryOrder(Date deliveryTime, int orderID, ProductTypeEnum productType, int amount, float productionSpeed, Date deliveryDeadline) {
        this.productionTime = (long) ((amount/productionSpeed)*60*1000);
        this.orderID = orderID;
        this.productType = productType;
        this.amount = amount;
        this.productionSpeed = productionSpeed;

        this.deliveryTime = new Date(deliveryTime.getTime()-productionTime);
        this.deadline = new Date(deliveryDeadline.getTime()-productionTime);
    }

    @Override
    public Integer getOrderID(){
        return this.orderID;
    }

    public Date getPlannedStart(){
        return this.deliveryTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public long getProductionTime() {
        return productionTime;
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

    @Override
    public int compareTo(DeliveryOrder o) {
        int compare = this.deliveryTime.compareTo(o.getPlannedStart());

        if (compare==0) {
            compare = this.deadline.compareTo(o.getDeadline());
        }

        if (compare==0){
            compare = this.orderID-o.getOrderID();
        }

        return compare;
    }
}
