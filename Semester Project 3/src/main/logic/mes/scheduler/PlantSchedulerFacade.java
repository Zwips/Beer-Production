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
    private Map<Integer, String> ordersSentTo;

    public PlantSchedulerFacade() {
        this.scheduler = new RetardedPlantSchedular();
        this.ordersSentTo = new HashMap<>();

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
    public Map<String, List<DeliveryOrder>> schedule(IBusinessOrder order, Collection<IMesMachine> machines) {
        Map<String, List<DeliveryOrder>> destinations = this.scheduler.schedule(order, machines);

        this.setOrdersSentTo(destinations);

        return destinations;
    }

    @Override
    public Map<String, List<DeliveryOrder>> reSchedule(List<IBusinessOrder> pendingOrders, Collection<IMesMachine> machines) {
        this.ordersSentTo.clear();

        Map<String, List<DeliveryOrder>> destinations = this.scheduler.reSchedule(pendingOrders, machines);

        this.setOrdersSentTo(destinations);

        return destinations;
    }

    private void setOrdersSentTo(Map<String, List<DeliveryOrder>> destinations){
        for (Map.Entry<String, List<DeliveryOrder>> destination : destinations.entrySet()) {
            for (DeliveryOrder order : destination.getValue()) {
                this.ordersSentTo.put(order.getOrderID(), destination.getKey());
            }
        }
    }




    private Map<String, BlockingQueue<DeliveryOrder>> machineSchedule;
    private Map<Integer, IBusinessOrder> businessOrders;
    private Set<Integer> startedOrders;

    @Override
    public Set<String> addOrders(List<IBusinessOrder> orders, Collection<IMesMachine> machines){

        Set<String> startMachines = new HashSet<>();

        for (IBusinessOrder order : orders) {
            this.businessOrders.put(order.getOrderID(),order);

            Set<Map.Entry<String, List<DeliveryOrder>>> destinations = this.schedule(order, machines).entrySet();
            for (Map.Entry<String, List<DeliveryOrder>> destination : destinations) {
                this.machineSchedule.get(destination.getKey()).addAll(destination.getValue());
            }
        }

        MESOutFacade.getInstance().saveOrders(orders);

        for (Map.Entry<String, BlockingQueue<DeliveryOrder>> queues : this.machineSchedule.entrySet()) {
            if (queues.getValue().size()!=0){
                startMachines.add(queues.getKey());
            }
        }

        return startMachines;
    }

    @Override
    public List<IBusinessOrder> getAllProductionOrders(){

        for (Map.Entry<String, BlockingQueue<DeliveryOrder>> machineQueue : this.machineSchedule.entrySet()) {
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
            for (Map.Entry<String, BlockingQueue<DeliveryOrder>> machineQueue : this.machineSchedule.entrySet()) {
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

        for (Map.Entry<String, BlockingQueue<DeliveryOrder>> machineQueue : this.machineSchedule.entrySet()) {
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
        Set<String> startMachines = new HashSet<>();

        for (IBusinessOrder order : orders) {
            for (Map.Entry<String, List<DeliveryOrder>> destination : this.schedule(order, machines).entrySet()) {
                this.machineSchedule.get(destination.getKey()).addAll(destination.getValue());
                startMachines.add(destination.getKey());
            }
        }

        MESOutFacade.getInstance().updateOrders(orders);

        return startMachines;
    }

    @Override
    public IProductionOrder getNextOrder(String machineID) {

        BlockingQueue<DeliveryOrder> queue = this.machineSchedule.get(machineID);

        synchronized(queue){
            int queueSize = queue.size();
            if (queueSize > 0) {
                if (queue.peek().getPlannedStart().before(new Date(System.currentTimeMillis()+60000))) {
                    DeliveryOrder order = queue.poll();
                    this.startedOrders.add(order.getOrderID());
                    return order;
                }
            }
        }

        return null;
    }

    @Override
    public void addQueue(String machineName){
        this.machineSchedule.put(machineName, new LinkedBlockingQueue<>());
    }



}
