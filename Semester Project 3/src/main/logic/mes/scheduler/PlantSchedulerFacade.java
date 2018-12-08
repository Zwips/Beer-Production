package logic.mes.scheduler;

import acquantiance.IBusinessOrder;
import logic.mes.MESOutFacade;
import logic.mes.mesacquantiance.IMesMachine;
import logic.mes.mesacquantiance.IPlantSchedulerFacade;
import logic.mes.mesacquantiance.IProductionOrder;
import logic.mes.mesacquantiance.IRelativeMachineSpeeds;

import java.util.*;


/**
 * Schedules the orders according to some algorithm
 */
public class PlantSchedulerFacade implements IPlantSchedulerFacade {

    private PlantScheduler scheduler;
    private Map<String, TreeSet<DeliveryOrder>> machineSchedule;
    private Map<Integer, IBusinessOrder> businessOrders;
    private Set<Integer> startedOrders;
    private IRelativeMachineSpeeds speedTable;

    public PlantSchedulerFacade(IRelativeMachineSpeeds speedTable) {
        this.machineSchedule = new HashMap<>();
        this.businessOrders = new HashMap<>();
        this.startedOrders = new HashSet<>();

        this.speedTable = speedTable;

        this.scheduler = new SimplePlantScheduler(speedTable);
    }

    @Override
    public void setScheduler(PlantSchedulerTypesEnum type) {

        switch (type) {
            case REGULAR:
                this.scheduler = new RetardedPlantSchedular();
                break;
            case SIMPLE:
                this.scheduler = new SimplePlantScheduler(this.speedTable);
            default:
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
            this.businessOrders.put(order.getOrderID(), order);
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
            else {
                this.businessOrders.remove(order.getOrderID());
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
                Date startOfNextOrder = queue.first().getPlannedStart();
                Date endOfNextPID = new Date(System.currentTimeMillis()+60000);

                if (startOfNextOrder.before(endOfNextPID)) {
                    order = queue.pollFirst();
                    this.startedOrders.add(order.getOrderID());
                }
            }
        }

        return order;
    }

    @Override
    public void addQueue(String machineName){
        this.machineSchedule.put(machineName, new TreeSet<>());
    }

    @Override
    public boolean removeQueue(String machineName, Collection<IMesMachine> machines) {
        TreeSet<DeliveryOrder> queue = this.machineSchedule.get(machineName);

        this.machineSchedule.remove(machineName);

        for (DeliveryOrder deliveryOrder : queue) {
            IBusinessOrder order = this.businessOrders.get(deliveryOrder.getOrderID());

            boolean success = this.schedule(order, machines);

            if (success) {
                queue.remove(deliveryOrder);
            }
        }

        if (queue.isEmpty()) {
            return true;
        } else {
            this.machineSchedule.put(machineName, queue);
            return false;
        }
    }

}
