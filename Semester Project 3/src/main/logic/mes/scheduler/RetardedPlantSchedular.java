package logic.mes.scheduler;

import Acquantiance.IProductionOrder;
import Acquantiance.IMesMachine;

import java.util.*;

public class RetardedPlantSchedular extends PlantSchedulerType {

    @Override
    public Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Collection<IMesMachine> machines) {
        Map<String, List<IProductionOrder>> destinations = new HashMap<>();
        List<IProductionOrder> orders = new ArrayList<>();

        for (IMesMachine machine : machines) {
            orders.add(order);
            destinations.put(machine.getMachineID(), orders);
            return destinations;
        }

        return null;
    }

    @Override
    public Map<String, List<IProductionOrder>> reSchedule(List<IProductionOrder> pendingOrders, Collection<IMesMachine> machines) {
        Map<String, List<IProductionOrder>> orders = new HashMap<>();

        for (IMesMachine machine : machines) {
            orders.put(machine.getMachineID(), pendingOrders);
            return orders;
        }

        return null;
    }
}
