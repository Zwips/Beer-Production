package logic.erp;

import Acquantiance.ProductTypeEnum;

import java.util.*;

public class ERP {
    private Queue<ProductionOrder> productionOrderQueue;
    private HashMap<String, ProcessingPlant> processingPlants;
    private final ProcessingPlant THEPLANT;

    public ERP()
    {
        productionOrderQueue = new PriorityQueue<>();
        processingPlants = new HashMap<>();
        THEPLANT = new ProcessingPlant("THEPLANT");
    }

    public boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
        ProductionOrder order = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
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
        return THEPLANT.addMachine(machineName, IPAddress, userID, password);
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
        return THEPLANT.removeMachine(machineName);
    }

    public Queue<ProductionOrder> getProductionOrderQueue() {
        return this.productionOrderQueue;
    }

    public boolean removeMachine(String processingPlantID, String machineName) {
        return processingPlants.get(processingPlantID).removeMachine(machineName);
    }
}
