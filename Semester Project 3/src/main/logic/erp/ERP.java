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
import Acquantiance.IProcessingCapacity;
import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;
import logic.erp.scheduler.Scheduler_Facade;
import logic.mes.ProcessingPlant;

import java.security.InvalidParameterException;
import java.util.*;

public class ERP {

    private Map<String, IProcessingCapacity> processingCapacities;
    private Set<String> processingPlants;
    private int nextOrderID;
    private IScheduler_Facade scheduler_facade;

    /** Constructer for ERP.
     *
     */
    public ERP() {
        processingPlants = new HashSet<>();
        processingCapacities = new HashMap<>();
        this.scheduler_facade = new Scheduler_Facade();

        initialiseOrderID();
        initialiseOrders();
        intitialisePlantIDs();
        initialiseCapacities();
    }


    private void initialiseCapacities() {
        this.processingCapacities = ERPOutFacade.getInstance().getProductionCapacities();
    }

    private void intitialisePlantIDs() {
        processingPlants = ERPOutFacade.getInstance().getPlantIDs();
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
        Map<String, List<IProductionOrder>> destinations = this.scheduler_facade.schedule(order, processingCapacities);

        this.processingCapacities.putAll(ERPOutFacade.getInstance().addOrders(destinations));

        return true;
    }

    /** Method to add a new processing plant to the list of processing plants.
     *
     * @param plantID the ID of the plant being added.
     */
    public void addProcessingPlant(String plantID){
        processingPlants.add(plantID);
        ERPOutFacade.getInstance().addPlant(plantID);
    }

    /** Method for removing a processing plant from the list of processing plants.
     *
     * @param plantID the ID of the plant being removed.
     */
    public void removeProcessingPlant(String plantID)
    {
        boolean removed = ERPOutFacade.getInstance().removePlant(plantID);
        if (removed){
            this.processingPlants.remove(plantID);
        }
    }

    /** Method for checking if a processing plant exists.
     *
     * @param plantID the ID for the plant being checked for.
     * @return boolean if the ID exists in the list of processing plants.
     */
    public boolean checkForProcessingPlant(String plantID){
        return processingPlants.contains(plantID); //TODO should it call deeper??
    }


    /** Method for adding a Machine to a processing plant.
     *
     * @param processingPlantID the ID for the plant the machine is being added to.
     * @param machineName the name of the machine being added.
     * @param ipAddress the Ipaddress of the machine being added.
     * @param userID the userID to the machine.
     * @param password the password to the machine.
     * @return boolean if machine has properly been added to the right processing plant.
     */
    public boolean addMachine(String processingPlantID, String machineName, String ipAddress, String userID, String password){
        return ERPOutFacade.getInstance().addMachine(processingPlantID, machineName, ipAddress, userID, password);
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
        return ERPOutFacade.getInstance().addMachine("THEPLANT", machineName, IPAddress, userID, password);
    }

    /** Method to check if a machine already exists in the list of processing plants.
     *
     * @param machineName the name of the machine being checked for.
     * @return boolean if the machine already exists or not.
     */
    public boolean checkForMachine(String machineName) {
        return ERPOutFacade.getInstance().checkForMachine(machineName);

        /*Set<Map.Entry<String, ProcessingPlant>> processingPlantSet = processingPlants.entrySet();
        for (Map.Entry<String, ProcessingPlant> entrySet : processingPlantSet) {
            if (entrySet.getValue().checkForMachine(machineName)) {
                return true;
            }
        }
        return false;*/
    }

    /** Method for removing a machine from the list of processing plants.
     *
     * @param machineName the name of the machine being removed.
     * @return boolean if the machine has been removed or not.
     */
    public boolean removeMachine(String machineName) {
        return  ERPOutFacade.getInstance().removeMachine("THEPLANT", machineName);
    }

    /** Method for getting the production order queue.
     *
     * @return the production order list.
     */
    public List<IProductionOrder> getProductionOrders( ){
        return ERPOutFacade.getInstance().getPendingOrders();
    }

    /** Method for removing a machine from a specific processing plant.
     *
     * @param processingPlantID the ID of the processing plant the machine is being removed from.
     * @param machineName the name of the machine being removed.
     * @return boolean if the machine has been removed or not.
     */
    public boolean removeMachine(String processingPlantID, String machineName) {
        return ERPOutFacade.getInstance().removeMachine(processingPlantID, machineName);
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
    /*public int getNextBatchID() {
        return nextBatchID;
    }*/

    /** Method for initialising the next orderID
     *
     */
    private void initialiseOrderID(){
        nextOrderID = ERPOutFacade.getInstance().getNextOrderID();
    }

    /** Method for initialising the orders for the system.
     *
     */
    private void initialiseOrders(){
        List<IProductionOrder> pendingOrders = ERPOutFacade.getInstance().getPendingOrders();

        Map<String, List<IProductionOrder>> destinations = this.scheduler_facade.reSchedule(pendingOrders, processingCapacities);

        this.processingCapacities.putAll(ERPOutFacade.getInstance().addOrders(destinations));
    }

    public boolean changeOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority, int orderID, boolean status) {
        try {
            ProductionOrder changedOrder = new ProductionOrder(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority);
            changedOrder.setOrderID(orderID);

            this.processingCapacities.putAll(ERPOutFacade.getInstance().removeOrder(orderID));

            Map<String, List<IProductionOrder>> destinations = this.scheduler_facade.schedule(changedOrder, processingCapacities);

            ERPOutFacade.getInstance().addOrders(destinations);
            //ERPOutFacade.getInstance(). TODO: Should this go through the MES layer or directly to SQL communication

            return true;
        } catch (InvalidParameterException ex){
            return false;
        }
    }



}
