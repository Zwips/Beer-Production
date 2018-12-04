package logic.mes.mesacquantiance;

import acquantiance.IBusinessOrder;
import logic.erp.BusinessOrder;
import logic.mes.scheduler.DeliveryOrder;
import logic.mes.scheduler.PlantSchedulerTypesEnum;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IPlantSchedulerFacade {

    void setScheduler(PlantSchedulerTypesEnum type);

    PlantSchedulerTypesEnum getScheduler();

    Map<String, List<DeliveryOrder>> schedule(IBusinessOrder order, Collection<IMesMachine> machines);

    Map<String, List<DeliveryOrder>> reSchedule(List<IBusinessOrder> pendingOrders, Collection<IMesMachine> machines);

    Set<String> addOrders(List<IBusinessOrder> orders, Collection<IMesMachine> machines);

    List<IBusinessOrder> getAllProductionOrders();

    String removeOrder(int orderID) throws NoSuchFieldException;

    IBusinessOrder getOrder(int orderID);

    Set<String> changeOrders(List<IBusinessOrder> orders, Collection<IMesMachine> machines);

    IProductionOrder getNextOrder(String machineID);

    void addQueue(String machineName);
}
