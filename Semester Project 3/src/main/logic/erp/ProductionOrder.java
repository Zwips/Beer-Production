package logic.erp;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import java.util.Date;

public class ProductionOrder implements IProductionOrder, Comparable<ProductionOrder> {
    private int amount;
    private ProductTypeEnum productType;
    private Date earliestDeliveryDate;
    private Date latestDeliveryDate;
    private int priority;



    public ProductionOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority) {
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
    public ProductTypeEnum getProductType() {
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

    public int getInternalPriority()
    {

        return priority;
    }

    @Override
    public int compareTo(ProductionOrder o) {
        return this.getInternalPriority() - o.getInternalPriority();
    }


}
