package communication.sqlcommunication.dataclasses;
/** Represents an Communication Production Order
 * @author Michael P
 * @param OrdersRemoveByOrderID constructor creates the object CommunicationProductionOrder.
 * @param clone method clones the object CommunicationProductionOrder.
 */
import acquantiance.IProductionOrder;
import acquantiance.ProductTypeEnum;

import java.util.Date;

public class CommunicationProductionOrder implements IProductionOrder {

    private int amount;
    private ProductTypeEnum type;
    private Date earliestDeliveryDate;
    private Date latestDeliveryDate;
    private int priority;
    private boolean status;
    private int orderID;

    public CommunicationProductionOrder() {
    }

    public CommunicationProductionOrder(int amount, ProductTypeEnum type, Date earliestDeliveryDate, Date latestDeliveryDate, int priority, boolean status, int orderID) {
        this.amount = amount;
        this.type = type;
        this.earliestDeliveryDate = earliestDeliveryDate;
        this.latestDeliveryDate = latestDeliveryDate;
        this.priority = priority;
        this.status = status;
        this.orderID = orderID;
    }

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

    @Override
    public IProductionOrder clone() {
        CommunicationProductionOrder order = new CommunicationProductionOrder(this.amount,type, earliestDeliveryDate, latestDeliveryDate, priority, status, orderID);
        return order;
    }

    @Override
    public String toString(){
        return "Order ID: " + this.orderID;
    }

}
