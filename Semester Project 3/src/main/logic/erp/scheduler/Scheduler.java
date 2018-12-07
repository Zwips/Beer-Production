package logic.erp.scheduler;

import acquantiance.IProcessingCapacity;
import acquantiance.IBusinessOrder;

import java.util.List;
import java.util.Map;

public interface Scheduler {

    public abstract Map<String, List<IBusinessOrder>> schedule(IBusinessOrder order, Map<String, IProcessingCapacity> processingCapacities);

    public abstract Map<String, List<IBusinessOrder>> reSchedule(List<IBusinessOrder> pendingOrders, Map<String, IProcessingCapacity> processingCapacities);

}
