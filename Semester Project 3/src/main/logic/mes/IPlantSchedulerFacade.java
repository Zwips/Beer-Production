package logic.mes;

import Acquantiance.IProductionOrder;
import logic.mes.scheduler.PlantSchedulerTypesEnum;
import Acquantiance.IMesMachine;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IPlantSchedulerFacade {

    void setScheduler(PlantSchedulerTypesEnum type);

    PlantSchedulerTypesEnum getScheduler();

    Map<String, List<IProductionOrder>> schedule(IProductionOrder order, Collection<IMesMachine> machines);

    Map<String, List<IProductionOrder>>  reSchedule(List<IProductionOrder> pendingOrders, Collection<IMesMachine> machines);

}
