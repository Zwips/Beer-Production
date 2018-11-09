package logic.erp;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import java.util.*;

public class ERP {
    private Queue<IProductionOrder> productionOrderQueue;
    private HashMap<String, ProcessingPlant> processingPlants;
    private int orderID;

    public ERP()
    {
        productionOrderQueue = new PriorityQueue<>();
        processingPlants = new HashMap<>();
        ProcessingPlant plant = new ProcessingPlant("THEPLANT");
        processingPlants.put("THEPLANT",plant);
        orderID = 0; //TODO add funtionality for finding next orderID in database.
    }

    public boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
        ProductionOrder order = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
        order.setOrderID(orderID++);
        return productionOrderQueue.add(order);

    }

    public void addProcessingPlant(String plantID){
        ProcessingPlant plant = new ProcessingPlant(plantID);
        processingPlants.put(plantID, plant);
    }

    public void removeProcessingPlant(String plantID)
    {
        processingPlants.remove(plantID, processingPlants.get(plantID));
    }

    public boolean checkForProcessingPlant(String plantID){
        return processingPlants.containsKey(plantID);
    }



    public boolean addMachine(String processingPantID, String name, String address, String userID, String password){
        return processingPlants.get(processingPantID).addMachine(name, address, userID, password);
    }

    public boolean addMachine(String machineName, String IPAddress, String userID, String password){
        return processingPlants.get("THEPLANT").addMachine(machineName, IPAddress, userID, password);
    }


    public boolean checkForMachine(String machineName)
    {
        Set<Map.Entry<String, ProcessingPlant>> processingPlantSet = processingPlants.entrySet();
        for (Map.Entry<String, ProcessingPlant> entrySet : processingPlantSet) {
            if(entrySet.getValue().checkForMachine(machineName))
            {
                return true;
            }
        }
        return false;
    }

    public boolean removeMachine(String machineName) {
        return processingPlants.get("THEPLANT").removeMachine(machineName);
    }

    public Queue<IProductionOrder> getProductionOrderQueue() {
        return this.productionOrderQueue;
    }

    public boolean removeMachine(String processingPlantID, String machineName) {
        return processingPlants.get(processingPlantID).removeMachine(machineName);
    }
}
