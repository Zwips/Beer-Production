package logic.erp.scheduler;

import acquantiance.IProcessingCapacity;
import acquantiance.IProductionOrder;

import logic.erp.IScheduler_Facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Schedules the orders according to some algorithm
 */
public class Scheduler_Facade implements IScheduler_Facade {

    private Scheduler scheduler;
    private Map<Integer, String> ordersSentTo;

    public Scheduler_Facade() {
        this.scheduler = new RetardedSchedular();
        this.ordersSentTo = new HashMap<>();
    }

    @Override
    public Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Map<String, IProcessingCapacity> processingCapacities) {
        Map<String, List<IProductionOrder>> destinations = this.scheduler.schedule(order, processingCapacities);

        this.setOrdersSentTo(destinations);

        return destinations;
    }

    @Override
    public Map<String, List<IProductionOrder>> reSchedule(List<IProductionOrder> pendingOrders, Map<String, IProcessingCapacity> processingCapacities) {
        this.ordersSentTo.clear();

        Map<String, List<IProductionOrder>> destinations = this.scheduler.reSchedule(pendingOrders, processingCapacities);

        this.setOrdersSentTo(destinations);

        return destinations;
    }

    @Override
    public String getOrderLocations(int orderID){
        return this.ordersSentTo.get(orderID);
    }

    public void setScheduler(SchedulerTypesEnum type) {

        switch (type) {
            case REGULAR:
                this.scheduler = new RetardedSchedular();
                break;
        }
    }

    public SchedulerTypesEnum getScheduler() {
        if (this.scheduler instanceof RetardedSchedular){
            return SchedulerTypesEnum.REGULAR;
        } else{
            return null;
        }
    }

    private void setOrdersSentTo(Map<String, List<IProductionOrder>> destinations){
        for (Map.Entry<String, List<IProductionOrder>> destination : destinations.entrySet()) {
            for (IProductionOrder order : destination.getValue()) {
                this.ordersSentTo.put(order.getOrderID(), destination.getKey());
            }
        }
    }
}
