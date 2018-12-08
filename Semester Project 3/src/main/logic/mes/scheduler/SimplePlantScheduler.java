package logic.mes.scheduler;

import acquantiance.IBusinessOrder;
import acquantiance.ProductTypeEnum;
import logic.mes.mesacquantiance.IMesMachine;
import logic.mes.mesacquantiance.IRelativeMachineSpeeds;

import java.util.*;

public class SimplePlantScheduler implements PlantScheduler {

    private IRelativeMachineSpeeds speedTable;

    public SimplePlantScheduler(IRelativeMachineSpeeds speedTable) {
        this.speedTable = speedTable;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public boolean schedule(IBusinessOrder order, Collection<IMesMachine> machines, Map<String, TreeSet<DeliveryOrder>> machineSchedule) {

        boolean success;

        List<String> orderedMachines = this.speedTable.getMostEffectiveMachines(order.getProductType());

        for (String machineID : orderedMachines) {
            IMesMachine machine = null;

            for (IMesMachine unsortedMachine : machines) {
                if (unsortedMachine.getMachineID().equals(machineID)){
                    machine = unsortedMachine;
                    break;
                }
            }

            success = this.placeOrder(machine, order, machineSchedule);

            if (success){
                return true;
            }
        }

        return false;
    }

    @SuppressWarnings("Duplicates")
    private boolean placeOrder(IMesMachine machine, IBusinessOrder order, Map<String, TreeSet<DeliveryOrder>> machineSchedule) {
        DeliveryOrder deliveryOrder;

        int amount = (int) (order.getAmount() * 1.1);
        int orderID = order.getOrderID();
        ProductTypeEnum type = order.getProductType();
        Date earlyDeliveryDate = order.getEarliestDeliveryDate();
        float speed = machine.getMachineSpecificationReadable().getOptimalSpeed(order.getProductType());
        Date lateDeliveryDate = order.getLatestDeliveryDate();
        long production = (long) (1.1 * 60 * 1000 * amount / speed);

        TreeSet<DeliveryOrder> queue = machineSchedule.get(machine.getMachineID());

        if (queue == null) {
            return false;
        }

        synchronized (queue) {

            Iterator<DeliveryOrder> iterator = queue.iterator();

            DeliveryOrder checkedOrder1 = null;
            DeliveryOrder checkedOrder2;
            boolean onlyFirstValue = true;

            while (iterator.hasNext()) {
                if (onlyFirstValue) {
                    checkedOrder1 = iterator.next();
                    onlyFirstValue = false;

                    if ((new Date(new Date().getTime() + production)).before(checkedOrder1.getPlannedStart())
                            && earlyDeliveryDate.before(checkedOrder1.getPlannedStart())) {

                        Date date;

                        if (earlyDeliveryDate.before(new Date())) {
                            date = new Date();
                        } else {
                            date = earlyDeliveryDate;
                        }

                        deliveryOrder = new DeliveryOrder(date, orderID, type, amount, speed, lateDeliveryDate);
                        queue.add(deliveryOrder);
                        return true;
                    }
                } else {
                    checkedOrder2 = iterator.next();

                    if (checkedOrder2.getPlannedStart().getTime() > (checkedOrder1.getPlannedStart().getTime() + checkedOrder1.getProductionTime()) + production) {
                        deliveryOrder = new DeliveryOrder(new Date(checkedOrder1.getPlannedStart().getTime() + checkedOrder1.getProductionTime()), orderID, type, amount, speed, lateDeliveryDate);
                        queue.add(deliveryOrder);
                        return true;
                    }

                    checkedOrder1 = checkedOrder2;
                }

                if (!iterator.hasNext()) {
                    if (checkedOrder1.getPlannedStart().getTime() + checkedOrder1.getProductionTime() + production < lateDeliveryDate.getTime()) {
                        deliveryOrder = new DeliveryOrder(new Date(checkedOrder1.getPlannedStart().getTime() + checkedOrder1.getProductionTime()), orderID, type, amount, speed, lateDeliveryDate);
                        queue.add(deliveryOrder);
                        return true;
                    }
                }
            }

            if (onlyFirstValue) {
                if (earlyDeliveryDate.getTime() + production < lateDeliveryDate.getTime()
                        && new Date(new Date().getTime() + production).before(lateDeliveryDate)) {

                    Date date;

                    if (earlyDeliveryDate.before(new Date())) {
                        date = new Date();
                    } else {
                        date = earlyDeliveryDate;
                    }

                    deliveryOrder = new DeliveryOrder(date, orderID, type, amount, speed, lateDeliveryDate);
                    queue.add(deliveryOrder);
                    return true;
                }
            }
        }

        return false;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public boolean reSchedule(Collection<IBusinessOrder> pendingOrders, Collection<IMesMachine> machines, Map<String, TreeSet<DeliveryOrder>> machineSchedule) {

        boolean success = true;
        HashMap<String, TreeSet<DeliveryOrder>> tempSchedule = new HashMap<>();

        for (Map.Entry<String, TreeSet<DeliveryOrder>> entry : machineSchedule.entrySet()) {
            tempSchedule.put(entry.getKey(), new TreeSet<>());
        }

        for (IBusinessOrder pendingOrder : pendingOrders) {
            success = this.schedule(pendingOrder, machines, tempSchedule);

            if (!success){
                break;
            }
        }

        if (success){
            machineSchedule = tempSchedule;
        }

        return success;
    }
}
