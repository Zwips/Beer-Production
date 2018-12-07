package logic.mes.scheduler;

import acquantiance.IBusinessOrder;
import acquantiance.ProductTypeEnum;
import logic.mes.mesacquantiance.IMesMachine;

import java.util.*;

public class RetardedPlantSchedular implements PlantScheduler {

    @Override
    public Map<String, List<DeliveryOrder>> schedule(IBusinessOrder order, Collection<IMesMachine> machines) {
        Map<String, List<DeliveryOrder>> destinations = new HashMap<>();
        List<DeliveryOrder> orders = new ArrayList<>();

        for (IMesMachine machine : machines) {

            int amount = order.getAmount();
            int orderID = order.getOrderID();
            ProductTypeEnum type = order.getProductType();
            Date earlyDeliveryDate = order.getEarliestDeliveryDate();
            float speed = machine.getMachineSpecificationReadable().getOptimalSpeed(order.getProductType());

            DeliveryOrder deliveryOrder = new DeliveryOrder(earlyDeliveryDate, orderID, type, amount, speed);

            orders.add(deliveryOrder);
            destinations.put(machine.getMachineID(), orders);
            return destinations;
        }

//        IMesMachine[] machine = machines.toArray(new IMesMachine[machines.size()]);
//
//        destinations.put(machine[(int)Math.random()*machines.size()].getMachineID(), orders);
//        return destinations;
        return null;
    }

    @Override
    public Map<String, List<DeliveryOrder>> reSchedule(List<IBusinessOrder> pendingOrders, Collection<IMesMachine> machines) {

        Map<String, List<DeliveryOrder>> destinations = new HashMap<>();
        List<DeliveryOrder> orders = new ArrayList<>();

        for (IMesMachine machine : machines) {
            for (IBusinessOrder pendingOrder : pendingOrders) {
                int amount = pendingOrder.getAmount();
                int orderID = pendingOrder.getOrderID();
                ProductTypeEnum type = pendingOrder.getProductType();
                Date earlyDeliveryDate = pendingOrder.getEarliestDeliveryDate();
                float speed = machine.getMachineSpecificationReadable().getOptimalSpeed(pendingOrder.getProductType());

                DeliveryOrder deliveryOrder = new DeliveryOrder(earlyDeliveryDate, orderID, type, amount, speed);

                orders.add(deliveryOrder);

            }
            destinations.put(machine.getMachineID(), orders);
            return destinations;
        }

        return null;
    }
}
