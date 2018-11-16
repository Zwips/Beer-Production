package logic.erp;

/** Represents our ERP
 * @author Asmus
 * @param addOrder method to add an order to the production order queue.
 * @param addProcessingPlant method to add a processing plant to the list of processing plants.
 * @param removeProcessingPlant method for removing a processing plant from the list of processing plants.
 * @param checkForProcessingPlant method for checking if a processing plant exists.
 * @param addMachine Method for adding a Machine to a processing plant.
 * @param checkForMachine Method to check if a machine already exists in the list of processing plants.
 * @param removeMachine Method for removing a machine from the list of processing plants.
 */

import Acquantiance.IMachineConnectionInformation;
import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import java.security.InvalidParameterException;
import java.util.*;

public class ERP {
    private List<IProductionOrder> productionOrders;
    private HashMap<String, ProcessingPlant> processingPlants;
    private int nextOrderID;
    private int nextBatchID;

    /** Constructer for ERP.
     *
     */
    public ERP() {
        productionOrders = new ArrayList<IProductionOrder>();
        processingPlants = new HashMap<>();
        ProcessingPlant plant = new ProcessingPlant("THEPLANT");
        processingPlants.put("THEPLANT",plant);
        initialiseBatchID();
        initialiseOrderID();
        initialiseOrders();
        intitialiseFactories();
    }

    /**Method to add an order to the production order queue.
     *
     * @param amount is the amount in the order.
     * @param productType the type of product to be produced.
     * @param earliestDeliveryDate the earliest date the order can be delivered.
     * @param latestDeliveryDate the latest date the order can be delivered.
     * @param priority how high of a priority the order has.
     * @return the production order queue with the added order.
     */
    public boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority){
        ProductionOrder order = null;

        try {
            order = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
        } catch (InvalidParameterException ex){
            return false;
        }

        order.setOrderID(nextOrderID++);
        //ERPOutFacade.getInstance(). TODO: Should this go through the MES layer or directly to SQL communication
        return productionOrders.add(order);
    }

    /** Method to add a new processing plant to the list of processing plants.
     *
     * @param plantID the ID of the plant being added.
     */
    public void addProcessingPlant(String plantID){
        ProcessingPlant plant = new ProcessingPlant(plantID);
        processingPlants.put(plantID, plant);
    }

    /** Method for removing a processing plant from the list of processing plants.
     *
     * @param plantID the ID of the plant being removed.
     */
    public void removeProcessingPlant(String plantID)
    {
        processingPlants.remove(plantID, processingPlants.get(plantID));
    }

    /** Method for checking if a processing plant exists.
     *
     * @param plantID the ID for the plant being checked for.
     * @return boolean if the ID exists in the list of processing plants.
     */
    public boolean checkForProcessingPlant(String plantID){
        return processingPlants.containsKey(plantID);
    }


    /** Method for adding a Machine to a processing plant.
     *
     * @param processingPantID the ID for the plant the machine is being added to.
     * @param name the name of the machine being added.
     * @param address the Ipaddress of the machine being added.
     * @param userID the userID to the machine.
     * @param password the password to the machine.
     * @return boolean if machine has properly been added to the right processing plant.
     */
    public boolean addMachine(String processingPantID, String name, String address, String userID, String password){
        return processingPlants.get(processingPantID).addMachine(name, address, userID, password);
    }

    /** Method for adding a machine to THEPLANT processing plant.
     *
     * @param machineName the name of the machine being added.
     * @param IPAddress the Ipaddress of the machine being added.
     * @param userID the userID to the machine being added.
     * @param password the password to the machine being added.
     * @return boolean if the machine has properly been added.
     */
    public boolean addMachine(String machineName, String IPAddress, String userID, String password){
        return processingPlants.get("THEPLANT").addMachine(machineName, IPAddress, userID, password);
    }

    /** Method to check if a machine already exists in the list of processing plants.
     *
     * @param machineName the name of the machine being checked for.
     * @return boolean if the machine already exists or not.
     */
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

    /** Method for removing a machine from the list of processing plants.
     *
     * @param machineName the name of the machine being removed.
     * @return boolean if the machine has been removed or not.
     */
    public boolean removeMachine(String machineName) {
        return processingPlants.get("THEPLANT").removeMachine(machineName);
    }

    /** Method for getting the production order queue.
     *
     * @return the production order list.
     */
    public List<IProductionOrder> getProductionOrders( ){
        List<IProductionOrder> list = new ArrayList<>();

        for (IProductionOrder order:this.productionOrders){
            list.add(order.clone());
        }
        return list;
    }

    /** Method for removing a machine from a specific processing plant.
     *
     * @param processingPlantID the ID of the processing plant the machine is being removed from.
     * @param machineName the name of the machine being removed.
     * @return boolean if the machine has been removed or not.
     */
    public boolean removeMachine(String processingPlantID, String machineName) {
        return processingPlants.get(processingPlantID).removeMachine(machineName);
    }

    /** Method for getting the next orderID
     *
     * @return the next orderID
     */
    public int getNextOrderID() {
        return nextOrderID;
    }

    /** Method for getting the next BatchID
     *
     * @return the next BatchID
     */
    public int getNextBatchID() {
        return nextBatchID;
    }

    /** Method for initialising the next batchID
     *
     */
    private void initialiseBatchID(){
        nextBatchID = ERPOutFacade.getInstance().getNextBatchID();
    }

    /** Method for initialising the next orderID
     *
     */
    private void initialiseOrderID(){
        nextOrderID = ERPOutFacade.getInstance().getNextOrderID();
    }

    /** Method for initialising the order queue.
     *
     */
    private void initialiseOrders(){
        List<IProductionOrder> orders = ERPOutFacade.getInstance().getPendingOrders();
        for (IProductionOrder order: orders) {
            productionOrders.add(order);
        }
    }

    public boolean changeOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority, int orderID) {
        for (IProductionOrder productionOrder : this.productionOrders) {
            if (productionOrder.getOrderID()==orderID){
                ProductionOrder changedOrder;

                try {
                    changedOrder = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
                    changedOrder.setOrderID(orderID);
                    productionOrders.remove(productionOrder);
                    productionOrders.add(changedOrder);
                    //ERPOutFacade.getInstance(). TODO: Should this go through the MES layer or directly to SQL communication
                    return true;
                } catch (InvalidParameterException ex){
                    return false;
                }
            }
        }
        return false;
    }

    private void intitialiseFactories(){
        Set<Map.Entry<String, List<IMachineConnectionInformation>>> entryList  = ERPOutFacade.getInstance().getMachines().entrySet();
        for (Map.Entry<String, List<IMachineConnectionInformation>> entry: entryList) {
            if(processingPlants.containsKey(entry.getKey())) {
                List<IMachineConnectionInformation> machineInfoList = entry.getValue();
                for (IMachineConnectionInformation machineInfo : machineInfoList ) {
                    processingPlants.get(entry.getKey()).addMachine(machineInfo.getMachineID(), machineInfo.getMachineIP(), machineInfo.getMachineUsername(), machineInfo.getMachinePassword());
                }
            } else {
                processingPlants.put(entry.getKey(), new ProcessingPlant(entry.getKey()));
                List<IMachineConnectionInformation> machineInfoList = entry.getValue();
                for (IMachineConnectionInformation machineInfo : machineInfoList ) {
                    processingPlants.get(entry.getKey()).addMachine(machineInfo.getMachineID(), machineInfo.getMachineIP(), machineInfo.getMachineUsername(), machineInfo.getMachinePassword());
                }
            }
        }
    }

}
