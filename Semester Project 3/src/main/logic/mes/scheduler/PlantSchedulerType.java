package logic.mes.scheduler;

import acquantiance.IProductionOrder;
import acquantiance.IMesMachine;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class PlantSchedulerType {

    public abstract Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Collection<IMesMachine> machines);

    public abstract Map<String, List<IProductionOrder>> reSchedule(List<IProductionOrder> pendingOrders, Collection<IMesMachine> machines);
}
