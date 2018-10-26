package logic.erp;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ERP {
    Queue<ProductionOrder> productionOrderQueue = new LinkedList<>();

    public boolean addOrder(int amount, float productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
        ProductionOrder order = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
        return productionOrderQueue.add(order);

    }

}
