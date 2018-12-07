package logic.erp;

import acquantiance.IProcessingCapacity;
import acquantiance.IBusinessOrder;

import java.util.List;
import java.util.Map;

public interface IScheduler_Facade {

    Map<String, List<IBusinessOrder>> schedule(IBusinessOrder order, Map<String, IProcessingCapacity> processingCapacities);

    Map<String, List<IBusinessOrder>>  reSchedule(List<IBusinessOrder> pendingOrders, Map<String, IProcessingCapacity> processingCapacities);

    String getOrderLocations(int orderID);
}
