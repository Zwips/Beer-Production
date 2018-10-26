package logic.erp;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ERP {
    private Queue<ProductionOrder> productionOrderQueue;
    private HashMap<String, ProcessingPlant> processingPlants;
    private final ProcessingPlant THEPLANT;

    public ERP()
    {
        productionOrderQueue = new LinkedList<>();
        processingPlants = new HashMap<>();
        THEPLANT = new ProcessingPlant("THEPLANT");
    }

    public boolean addOrder(int amount, float productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
        ProductionOrder order = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
        return productionOrderQueue.add(order);

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

}
