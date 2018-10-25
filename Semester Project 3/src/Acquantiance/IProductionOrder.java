package Acquantiance;

import java.util.Date;

public interface IProductionOrder {
  int getAmount();
  float getProductType();
  Date getEarliestDeliveryDate();
  Date getLatestDeliveryDate();
  int getPriority();






}
