package logic.erp.scheduler;

import Acquantiance.IProcessingCapacity;
import Acquantiance.IProductionOrder;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetardedSchedular extends SchedulerType {

    @Override
    public Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Map<String, IProcessingCapacity> processingCapacities) {
        HashMap<String, List<IProductionOrder>> orders = new HashMap<String, List<IProductionOrder>>();

        for (String plant : processingCapacities.keySet()) {
            List<IProductionOrder> ordersForPlant = new ArrayList<>();
            ordersForPlant.add(order);
            orders.put(plant, ordersForPlant);
        }
        return orders;
    }

    @Override
    public Map<String, List<IProductionOrder>> reSchedule(List<IProductionOrder> pendingOrders, Map<String, IProcessingCapacity> processingCapacities) {
        Map<String, List<IProductionOrder>> destinations = new HashMap<>();

        for (String plant : processingCapacities.keySet()) {
            destinations.put(plant,pendingOrders);
            break;
        }

        return destinations;
    }
}
