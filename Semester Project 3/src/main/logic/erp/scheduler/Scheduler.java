package logic.erp.scheduler;

import acquantiance.IProcessingCapacity;
import acquantiance.IProductionOrder;

import java.util.List;
import java.util.Map;

public interface Scheduler {

    public abstract Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Map<String, IProcessingCapacity> processingCapacities);

    public abstract Map<String, List<IProductionOrder>> reSchedule(List<IProductionOrder> pendingOrders, Map<String, IProcessingCapacity> processingCapacities);

}
