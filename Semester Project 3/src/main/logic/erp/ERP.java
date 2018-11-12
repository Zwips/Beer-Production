package logic.erp;

import Acquantiance.IMachineConnectionInformation;
import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import java.util.*;

public class ERP {
    private Queue<IProductionOrder> productionOrderQueue;
    private HashMap<String, ProcessingPlant> processingPlants;
    private int nextOrderID;
    private int nextBatchID;

    public ERP()
    {
        productionOrderQueue = new PriorityQueue<>();
        processingPlants = new HashMap<>();
        ProcessingPlant plant = new ProcessingPlant("THEPLANT");
        processingPlants.put("THEPLANT",plant);
        initialiseBatchID();
        initialiseOrderID();
        initialiseOrderQueue();
        intitialiseFactories();
    }

    public boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
        ProductionOrder order = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
        order.setOrderID(nextOrderID++);
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



    public boolean addMachine(String processingPantID, String machineID, String address, String userID, String password){
        if (processingPlants.get(processingPantID).addMachine(machineID, address, userID, password))
        {
            ERPOutFacade.getInstance().InsertMachine(processingPantID,machineID,address,userID,password);
            return true;
        }
        return false;
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

    public List<IProductionOrder> getProductionOrderQueue() {
        List<IProductionOrder> list = new ArrayList<>();

        for (IProductionOrder order:this.productionOrderQueue){
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

    private void initialiseOrderQueue(){
        List<IProductionOrder> orders = ERPOutFacade.getInstance().getPendingOrders();
        for (IProductionOrder p: orders) {
            productionOrderQueue.add(p);
        }
    }

    private void intitialiseFactories(){
        Set<Map.Entry<String, List<IMachineConnectionInformation>>> entryList  = ERPOutFacade.getInstance().getMachines().entrySet();
        for (Map.Entry<String, List<IMachineConnectionInformation>> entry: entryList) {
            if(processingPlants.containsKey(entry.getKey()))
            {
                List<IMachineConnectionInformation> machineInfoList = entry.getValue();
                for (IMachineConnectionInformation machineInfo : machineInfoList ) {
                    processingPlants.get(entry.getKey()).addMachine(machineInfo.getMachineID(), machineInfo.getMachineIP(), machineInfo.getMachineUsername(), machineInfo.getMachinePassword());
                   }

            }
            else
            {
                processingPlants.put(entry.getKey(), new ProcessingPlant(entry.getKey()));
                List<IMachineConnectionInformation> machineInfoList = entry.getValue();
                for (IMachineConnectionInformation machineInfo : machineInfoList ) {
                    processingPlants.get(entry.getKey()).addMachine(machineInfo.getMachineID(), machineInfo.getMachineIP(), machineInfo.getMachineUsername(), machineInfo.getMachinePassword());
                }
            }
        }


    }
}
