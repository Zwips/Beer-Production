package logic.mes.mesacquantiance;

import acquantiance.IBusinessOrder;
import logic.mes.scheduler.PlantSchedulerTypesEnum;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IPlantSchedulerFacade {

    void setScheduler(PlantSchedulerTypesEnum type);

    PlantSchedulerTypesEnum getScheduler();

    boolean schedule(IBusinessOrder order, Collection<IMesMachine> machines);

    boolean reSchedule(List<IBusinessOrder> pendingOrders, Collection<IMesMachine> machines);

    Set<String> addOrders(List<IBusinessOrder> orders, Collection<IMesMachine> machines);

    List<IBusinessOrder> getAllProductionOrders();

    String removeOrder(int orderID) throws NoSuchFieldException;

    IBusinessOrder getOrder(int orderID);

    Set<String> changeOrders(List<IBusinessOrder> orders, Collection<IMesMachine> machines, IBusinessOrder oldOrder);

    IProductionOrder getNextOrder(String machineID);

    void addQueue(String machineName);

    boolean removeQueue(String machineName, Collection<IMesMachine> machines);
}
