package logic.mes.scheduler;

import Acquantiance.IMesMachine;
import Acquantiance.IProductionOrder;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface PlantScheduler {

    public abstract Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Collection<IMesMachine> machines);

    public abstract Map<String, List<IProductionOrder>> reSchedule(List<IProductionOrder> pendingOrders, Collection<IMesMachine> machines);

}
