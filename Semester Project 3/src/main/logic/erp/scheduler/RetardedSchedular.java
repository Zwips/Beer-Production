package logic.erp.scheduler;

import acquantiance.IProcessingCapacity;
import acquantiance.IBusinessOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetardedSchedular implements Scheduler {

    @Override
    public Map<String, List<IBusinessOrder>> schedule(IBusinessOrder order, Map<String, IProcessingCapacity> processingCapacities) {
        HashMap<String, List<IBusinessOrder>> orders = new HashMap<String, List<IBusinessOrder>>();

        for (String plant : processingCapacities.keySet()) {
            List<IBusinessOrder> ordersForPlant = new ArrayList<>();
            ordersForPlant.add(order);
            orders.put(plant, ordersForPlant);
        }
        return orders;
    }

    @Override
    public Map<String, List<IBusinessOrder>> reSchedule(List<IBusinessOrder> pendingOrders, Map<String, IProcessingCapacity> processingCapacities) {
        Map<String, List<IBusinessOrder>> destinations = new HashMap<>();

        for (String plant : processingCapacities.keySet()) {
            destinations.put(plant,pendingOrders);
            break;
        }

        return destinations;
    }
}
