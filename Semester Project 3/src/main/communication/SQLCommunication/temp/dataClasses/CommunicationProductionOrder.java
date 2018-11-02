package communication.SQLCommunication.temp.dataClasses;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import java.util.Date;

public class CommunicationProductionOrder implements IProductionOrder {

    private int amount;
    private ProductTypeEnum type;
    private Date earliestDeliveryDate;
    private Date latestDeliveryDate;
    private int priority;
    private boolean status;
    private int orderID;

    @Override
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setType(ProductTypeEnum type) {
        this.type = type;
    }

    public void setEarliestDeliveryDate(Date earliestDeliveryDate) {
        this.earliestDeliveryDate = earliestDeliveryDate;
    }

    public void setLatestDeliveryDate(Date latestDeliveryDate) {
        this.latestDeliveryDate = latestDeliveryDate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public ProductTypeEnum getProductType() {
        return this.type;
    }

    @Override
    public Date getEarliestDeliveryDate() {
        return this.earliestDeliveryDate;
    }

    @Override
    public Date getLatestDeliveryDate() {
        return this.latestDeliveryDate;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public boolean getStatus() {
        return this.status;
    }
}
