package logic.mes.scheduler;

import Acquantiance.IProductionOrder;
import Acquantiance.IMesMachine;

import java.util.*;

public class RetardedPlantSchedular implements PlantScheduler {

    @Override
    public Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Collection<IMesMachine> machines) {
        Map<String, List<IProductionOrder>> destinations = new HashMap<>();
        List<IProductionOrder> orders = new ArrayList<>();

        for (IMesMachine machine : machines) {
            orders.add(order);
            destinations.put(machine.getMachineID(), orders);
            return destinations;
        }

//        IMesMachine[] machine = machines.toArray(new IMesMachine[machines.size()]);
//
//        destinations.put(machine[(int)Math.random()*machines.size()].getMachineID(), orders);
//        return destinations;
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
