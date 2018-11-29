package logic.mes.scheduler;

import acquantiance.IProductionOrder;
import acquantiance.IMesMachine;
import logic.mes.IPlantSchedulerFacade;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Schedules the orders according to some algorithm
 */
public class PlantSchedulerFacade implements IPlantSchedulerFacade {

    private PlantScheduler scheduler;
    private Map<Integer, String> ordersSentTo;

    public PlantSchedulerFacade() {
        this.scheduler = new RetardedPlantSchedular();
        this.ordersSentTo = new HashMap<>();
    }

    @Override
    public void setScheduler(PlantSchedulerTypesEnum type) {

        switch (type) {
            case REGULAR:
                this.scheduler = new RetardedPlantSchedular();
                break;
        }
    }

    @Override
    public PlantSchedulerTypesEnum getScheduler() {
        if (this.scheduler instanceof RetardedPlantSchedular){
            return PlantSchedulerTypesEnum.REGULAR;
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

    @Override
    public Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Collection<IMesMachine> machines) {
        Map<String, List<IProductionOrder>> destinations = this.scheduler.schedule(order, machines);

        this.setOrdersSentTo(destinations);

        return destinations;
    }

    @Override
    public Map<String, List<IProductionOrder>> reSchedule(List<IProductionOrder> pendingOrders, Collection<IMesMachine> machines) {
        this.ordersSentTo.clear();

        Map<String, List<IProductionOrder>> destinations = this.scheduler.reSchedule(pendingOrders, machines);

        this.setOrdersSentTo(destinations);

        return destinations;
    }

}
