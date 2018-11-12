package logic.erp;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import java.security.InvalidParameterException;
import java.util.*;

public class ERP {
    private List<IProductionOrder> productionOrders;
    private HashMap<String, ProcessingPlant> processingPlants;
    private int nextOrderID;
    private int nextBatchID;

    public ERP()
    {
        productionOrders = new ArrayList<IProductionOrder>();
        processingPlants = new HashMap<>();
        ProcessingPlant plant = new ProcessingPlant("THEPLANT");
        processingPlants.put("THEPLANT",plant);
        initialiseBatchID();
        initialiseOrderID();
        initialiseOrders();
    }

    public boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
        ProductionOrder order = null;

        try {
            order = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
        } catch (InvalidParameterException ex){
            return false;
        }

        order.setOrderID(nextOrderID++);
        //ERPOutFacade.getInstance().
        return productionOrders.add(order);
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

    public List<IProductionOrder> getProductionOrders() {
        List<IProductionOrder> list = new ArrayList<>();

        for (IProductionOrder order:this.productionOrders){
            list.add(order.clone());
        }
        return list;
    }

    public boolean removeMachine(String processingPlantID, String machineName) {
        return processingPlants.get(processingPlantID).removeMachine(machineName);
    }

    public int getNextOrderID() {
        return nextOrderID;
    }

    public int getNextBatchID() {
        return nextBatchID;
    }

    private void initialiseBatchID(){
        nextBatchID = ERPOutFacade.getInstance().getNextBatchID();
    }

    private void initialiseOrderID(){
        nextOrderID = ERPOutFacade.getInstance().getNextOrderID();
    }

    private void initialiseOrders(){
        List<IProductionOrder> orders = ERPOutFacade.getInstance().getPendingOrders();
        for (IProductionOrder p: orders) {
            productionOrders.add(p);
        }
    }

    public boolean changeOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority, int orderID, boolean status) {
        for (IProductionOrder productionOrder : this.productionOrders) {
            if (productionOrder.getOrderID()==orderID){
                ProductionOrder changedOrder = null;

                try {
                    changedOrder = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
                    changedOrder.setOrderID(orderID);
                    productionOrders.remove(productionOrder);
                    productionOrders.add(changedOrder);
                    return true;
                } catch (InvalidParameterException ex){
                    return false;
                }
            }
        }
        return false;
    }
}
