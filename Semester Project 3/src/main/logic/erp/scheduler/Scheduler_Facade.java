package logic.erp.scheduler;

import Acquantiance.IProcessingCapacity;
import Acquantiance.IProductionOrder;

import logic.erp.IScheduler_Facade;

import java.util.List;
import java.util.Map;

/**
 * Schedules the orders according to some algorithm
 */
public class Scheduler_Facade implements IScheduler_Facade {

    private SchedulerType scheduler;

    public Scheduler_Facade() {
        this.scheduler = new RetardedSchedular();
    }

    @Override
    public Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Map<String, IProcessingCapacity> processingCapacities) {
        return this.scheduler.schedule(order, processingCapacities);
    }

    @Override
    public Map<String, List<IProductionOrder>> reSchedule(List<IProductionOrder> pendingOrders, Map<String, IProcessingCapacity> processingCapacities) {
        return this.scheduler.reSchedule(pendingOrders, processingCapacities);
    }

    public void setScheduler(SchedulerTypesEnum type) {

        switch (type) {
            case REGULAR:
                this.scheduler = new RetardedSchedular();
                break;
        }
    }

    public SchedulerType getScheduler() {
        return scheduler;
    }
}
