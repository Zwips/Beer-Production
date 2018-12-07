package logic.mes.scheduler;

import acquantiance.IBusinessOrder;
import logic.mes.mesacquantiance.IMesMachine;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public interface PlantScheduler {

    boolean reSchedule(Collection<IBusinessOrder> pendingOrders, Collection<IMesMachine> machines, Map<String, TreeSet<DeliveryOrder>> machineSchedule);

    boolean schedule(IBusinessOrder order, Collection<IMesMachine> machines, Map<String, TreeSet<DeliveryOrder>> machineSchedule);
}
