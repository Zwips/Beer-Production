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

    public Queue<ProductionOrder> getProductionOrderQueue() {
        return productionOrderQueue;
    }

    void addProcessingPlant(String plantID){
        ProcessingPlant plant = new ProcessingPlant(plantID);
        processingPlants.put(plantID, plant);
    }

    boolean addMachine(String processingPantID, String name, String address, String userID, String password){
        return processingPlants.get(processingPantID).addMachine(name, address, userID, password);
    }

    boolean addMachine(String machineName, String IPAddress, String userID, String password){
        return THEPLANT.addMachine(machineName, IPAddress, userID, password);
    }

    boolean checkForMachine(String processingPlantID, String machineName){
        return processingPlants.get(processingPlantID).checkForMachine(machineName);
    }
    boolean checkForMachine(String machineName)
    {
        return THEPLANT.checkForMachine(machineName);
    }

    boolean removeMachine(String processingPlantID, String machineName) {
        return processingPlants.get(processingPlantID).removeMachine(machineName);
    }

    boolean removeMachine(String machineName) {
        return THEPLANT.removeMachine(machineName);
    }

}
