package logic.erp;

import Acquantiance.IProductionOrder;

import java.util.Date;

public class ProductionOrder implements IProductionOrder {
    private int amount;
    private float productType;
    private Date earliestDeliveryDate;
    private Date latestDeliveryDate;
    private int priority;





    public ProductionOrder(int amount, float productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority) {
        this.amount = amount;
        this.productType = productType;
        this.earliestDeliveryDate = earliestDeliveryDate;
        this.latestDeliveryDate = latestDeliveryDate;
        this.priority = priority;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public float getProductType() {
        return productType;
    }

    @Override
    public Date getEarliestDeliveryDate() {
        return earliestDeliveryDate;
    }

    @Override
    public Date getLatestDeliveryDate() {
        return latestDeliveryDate;
    }

    @Override
    public int getPriority() {
        return priority;
    }
}
