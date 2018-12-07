package logic.erp.scheduler;

import acquantiance.IProcessingCapacity;
import acquantiance.IBusinessOrder;

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
    public Map<String, List<IBusinessOrder>> schedule(IBusinessOrder order, Map<String, IProcessingCapacity> processingCapacities) {
        Map<String, List<IBusinessOrder>> destinations = this.scheduler.schedule(order, processingCapacities);

        this.setOrdersSentTo(destinations);

        return destinations;
    }

    @Override
    public Map<String, List<IBusinessOrder>> reSchedule(List<IBusinessOrder> pendingOrders, Map<String, IProcessingCapacity> processingCapacities) {
        this.ordersSentTo.clear();

        Map<String, List<IBusinessOrder>> destinations = this.scheduler.reSchedule(pendingOrders, processingCapacities);

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

    private void setOrdersSentTo(Map<String, List<IBusinessOrder>> destinations){
        for (Map.Entry<String, List<IBusinessOrder>> destination : destinations.entrySet()) {
            for (IBusinessOrder order : destination.getValue()) {
                this.ordersSentTo.put(order.getOrderID(), destination.getKey());
            }
        }
    }
}
