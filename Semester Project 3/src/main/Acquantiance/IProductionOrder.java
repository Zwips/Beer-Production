package Acquantiance;

import java.util.Date;

public interface IProductionOrder {
  int getAmount();
  ProductTypeEnum getProductType();
  Date getEarliestDeliveryDate();
  Date getLatestDeliveryDate();
  int getPriority();






}
