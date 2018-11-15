package logic.erp.scheduler;

import Acquantiance.IProductionOrder;

import logic.erp.IScheduler_Facade;

import java.util.List;

/**
 * Schedules the orders according to some algorithm
 */
public class Scheduler_Facade implements IScheduler_Facade {

    private SchedulerType scheduler;

    public Scheduler_Facade() {
        this.scheduler = new RetardedSchedular();
    }

    @Override
    public List<IProductionOrder> schedule(List<IProductionOrder> orders) {
        return scheduler.schedule(orders);
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
