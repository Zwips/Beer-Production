package logic.erp;

/** Representing a production order.
 * @author Asmus
 * @param isStatus Method for seeing current status.
 * @param clone Method for cloning everything from productionOrder to a new order.
 */

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import java.util.Date;

public class ProductionOrder implements IProductionOrder, Comparable<ProductionOrder> {
    private int amount;
    private ProductTypeEnum productType;
    private Date earliestDeliveryDate;
    private Date latestDeliveryDate;
    private int priority;
    private int orderID;
    private boolean status;

    public ProductionOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority) {
        this.amount = amount;
        this.productType = productType;
        this.earliestDeliveryDate = earliestDeliveryDate;
        this.latestDeliveryDate = latestDeliveryDate;
        this.priority = priority;
        this.status = false;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int getAmount() {
        return amount;
    }
    @Override
    public String toString(){
        return "OrderID: "+orderID;
    }

    @Override
    public IProductionOrder clone() {
       ProductionOrder order = new ProductionOrder(this.amount, this.productType, this.earliestDeliveryDate, this.latestDeliveryDate, this.priority);
       order.setStatus(this.status);
       order.setOrderID(orderID);
       return order;
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

    @Override
    public boolean getStatus() {
        return this.status;
    }

    @Override
    public int getOrderID() {
        return this.orderID;
    }

    public int getInternalPriority(){
        return priority;
    }

    @Override
    public int compareTo(ProductionOrder o) {
        return this.getInternalPriority() - o.getInternalPriority();
    }


}
