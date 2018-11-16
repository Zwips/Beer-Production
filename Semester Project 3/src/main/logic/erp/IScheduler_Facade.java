package logic.erp;

import Acquantiance.IProcessingCapacity;
import Acquantiance.IProductionOrder;

import java.util.List;
import java.util.Map;

public interface IScheduler_Facade {

    Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Map<String, IProcessingCapacity> processingCapacities);

    Map<String, List<IProductionOrder>>  reSchedule(List<IProductionOrder> pendingOrders, Map<String, IProcessingCapacity> processingCapacities);
}