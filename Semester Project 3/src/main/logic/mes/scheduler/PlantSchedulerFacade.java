package logic.mes.scheduler;

import acquantiance.IBusinessOrder;
import logic.mes.MESOutFacade;
import logic.mes.mesacquantiance.IMesMachine;
import logic.mes.mesacquantiance.IPlantSchedulerFacade;
import logic.mes.mesacquantiance.IProductionOrder;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Schedules the orders according to some algorithm
 */
public class PlantSchedulerFacade implements IPlantSchedulerFacade {

    private PlantScheduler scheduler;
    private Map<String, TreeSet<DeliveryOrder>> machineSchedule;
    private Map<Integer, IBusinessOrder> businessOrders;
    private Set<Integer> startedOrders;


    public PlantSchedulerFacade() {
        this.scheduler = new RetardedPlantSchedular();
        this.machineSchedule = new HashMap<>();
        this.businessOrders = new HashMap<>();
        this.startedOrders = new HashSet<>();
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

    @Override
    public boolean schedule(IBusinessOrder order, Collection<IMesMachine> machines) {

        boolean success = this.scheduler.schedule(order, machines, this.machineSchedule);

        if (!success){
            success = this.scheduler.reSchedule(this.businessOrders.values(), machines, machineSchedule);
        }

        return success;
    }

    @Override
    public boolean reSchedule(List<IBusinessOrder> pendingOrders, Collection<IMesMachine> machines) {

        return this.scheduler.reSchedule(pendingOrders, machines, machineSchedule);
    }

    @Override
    public Set<String> addOrders(List<IBusinessOrder> orders, Collection<IMesMachine> machines){ //TODO return list of not scheduled orders

        Set<String> startMachines = new HashSet<>();
        List<IBusinessOrder> addedOrders = new ArrayList<>();
        boolean success;

        for (IBusinessOrder order : orders) {

            success = this.schedule(order, machines);

            if (success){
                this.businessOrders.put(order.getOrderID(),order);
                addedOrders.add(order);
            }
        }

        MESOutFacade.getInstance().saveOrders(addedOrders);

        for (Map.Entry<String, TreeSet<DeliveryOrder>> queues : this.machineSchedule.entrySet()) {
            if (queues.getValue().size()!=0){
                startMachines.add(queues.getKey());
            }
        }

        return startMachines;
    }

    @Override
    public List<IBusinessOrder> getAllProductionOrders(){

        for (Map.Entry<String, TreeSet<DeliveryOrder>> machineQueue : this.machineSchedule.entrySet()) {
            machineQueue.getValue().clear();
        }

        for (Integer startedOrderID : this.startedOrders) {
            this.businessOrders.remove(startedOrderID);
        }

        return new ArrayList<>(this.businessOrders.values());
    }

    @Override
    public String removeOrder(int orderID) throws NoSuchFieldException {

        if (!this.startedOrders.contains(orderID)) {
            for (Map.Entry<String, TreeSet<DeliveryOrder>> machineQueue : this.machineSchedule.entrySet()) {
                Iterator<DeliveryOrder> iter = machineQueue.getValue().iterator();
                DeliveryOrder order;

                while (iter.hasNext()) {
                    order = iter.next();
                    if (order.getOrderID() == orderID){
                        iter.remove();
                        this.businessOrders.remove(orderID);
                        if (machineQueue.getValue().size()<1){
                            return machineQueue.getKey();
                        } else {
                            return null;
                        }
                    }
                }
            }
        }

        throw new NoSuchFieldException();
    }

    @Override
    public IBusinessOrder getOrder(int orderID) {

        for (Map.Entry<String, TreeSet<DeliveryOrder>> machineQueue : this.machineSchedule.entrySet()) {
            for (DeliveryOrder order : machineQueue.getValue()) {
                if (order.getOrderID() == orderID){
                    return this.businessOrders.get(orderID);
                }
            }
        }

        return null;
    }

    @Override
    public Set<String> changeOrders(List<IBusinessOrder> orders, Collection<IMesMachine> machines) {
        return this.addOrders(orders, machines);
    }

    @Override
    public IProductionOrder getNextOrder(String machineID) {

        TreeSet<DeliveryOrder> queue = this.machineSchedule.get(machineID);
        DeliveryOrder order = null;

        synchronized(queue){
            int queueSize = queue.size();
            if (queueSize > 0) {
                for (DeliveryOrder deliveryOrder : queue) { //TODO this is only a stopgap, correct order should be in top.
                    if (deliveryOrder.getPlannedStart().before(new Date(System.currentTimeMillis()+60000))) {
                        order = deliveryOrder;
                        queue.remove(order);
                        this.startedOrders.add(order.getOrderID());
                        break;
                    }
                }
            }
        }

        return order;
    }

    @Override
    public void addQueue(String machineName){
        this.machineSchedule.put(machineName, new TreeSet<>());
    }

}
