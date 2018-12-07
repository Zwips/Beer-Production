package logic.mes.scheduler;

import acquantiance.IBusinessOrder;
import logic.mes.mesacquantiance.IMesMachine;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface PlantScheduler {

    Map<String, List<DeliveryOrder>> schedule(IBusinessOrder order, Collection<IMesMachine> machines);
    Map<String, List<DeliveryOrder>> reSchedule(List<IBusinessOrder> pendingOrders, Collection<IMesMachine> machines);

}
