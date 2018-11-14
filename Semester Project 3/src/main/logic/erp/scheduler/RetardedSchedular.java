package logic.erp.scheduler;

import Acquantiance.IProductionOrder;

import java.util.List;

public class RetardedSchedular extends SchedulerType {

    @Override
    public List<IProductionOrder> schedule(List<IProductionOrder> orders) {
        return orders;
    }
}
