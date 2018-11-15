package logic.erp.scheduler;

import Acquantiance.IProductionOrder;

import java.util.List;

public abstract class SchedulerType {

    public abstract List<IProductionOrder> schedule(List<IProductionOrder> orders);
}
