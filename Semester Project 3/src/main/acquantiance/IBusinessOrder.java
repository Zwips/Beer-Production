package acquantiance;

import java.util.Date;

public interface IBusinessOrder {
    int getAmount();
    ProductTypeEnum getProductType();
    Date getEarliestDeliveryDate();
    Date getLatestDeliveryDate();
    int getPriority();
    boolean getStatus();
    int getOrderID();
    String toString();
    IBusinessOrder clone();


}
