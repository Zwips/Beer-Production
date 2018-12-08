package logic.mes;

import acquantiance.IMESFacade;
import acquantiance.IMachineConnectionInformation;
import acquantiance.IProcessingCapacity;
import acquantiance.IBusinessOrder;
import acquantiance.*;

import java.util.*;

public class MESFacade implements IMESFacade {

    private  HashMap<String, ProcessingPlant> processingPlants;

    public MESFacade() {
        this.processingPlants = new HashMap<>();
        this.initialiseFactories();
    }

    private void initialiseFactories(){
        Map<String, List<IMachineConnectionInformation>> allMachines  = MESOutFacade.getInstance().getMachines();
        Set<String> plantIDs = allMachines.keySet();

        for (String plantID : plantIDs) {
            if (plantID.equals("beerplant")){
                continue;
            }
            processingPlants.put(plantID, new ProcessingPlant(plantID, allMachines.get(plantID)));
        }
    }

    @Override
    public int getNextOrderID() {
        return MESOutFacade.getInstance().getNextOrderID();
    }

    @Override
    public List<IBusinessOrder> getPendingOrders() {
        return MESOutFacade.getInstance().getPendingOrders();
    }

    @Override
    public HashMap<String, List<IMachineConnectionInformation>> getMachines() {
        return MESOutFacade.getInstance().getMachines();
    }

    @Override
    public void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password) {
        this.processingPlants.get(factoryID).addMachine(machineID, machine_IP, userID, password);
        MESOutFacade.getInstance().insertMachine(factoryID, machineID, machine_IP, userID, password);
    }

    @Override
    public void deleteMachine(String machineID) {
        MESOutFacade.getInstance().deleteMachine(machineID);
    }

    @Override
    public boolean removeMachine(String thePlant, String machineName) {
        return this.processingPlants.get(thePlant.toLowerCase()).removeMachine(machineName);
    }

    @Override
    public Set<String> getplantIDs() {
        return MESOutFacade.getInstance().getPlantIDs();
    }

    @Override
    public Map<String, IProcessingCapacity> getProductionsCapacities() {

        Map<String, IProcessingCapacity> capacities = new HashMap<>();

        for (Map.Entry<String, ProcessingPlant> plantEntry : this.processingPlants.entrySet()) {
            capacities.put(plantEntry.getKey(), plantEntry.getValue().getCapacity());
        }

        return capacities;
    }

    @Override
    public void addPlant(String plantID) {
        ProcessingPlant newPlant = new ProcessingPlant(plantID);

        this.processingPlants.put(plantID, newPlant);
    }

    @Override
    public boolean removePlant(String plantID) {
        ProcessingPlant plant = this.processingPlants.get(plantID);

        if (plant != null) {
            if (plant.isStopped()) {
                this.processingPlants.remove(plantID);
                return true;
            }
        }

        return false;
    }

    @Override
    public Map<String, IProcessingCapacity> addOrders(Map<String, List<IBusinessOrder>> destinations) {
        Map<String, IProcessingCapacity> capacity = new HashMap<>();

        for (Map.Entry<String, List<IBusinessOrder>> destination : destinations.entrySet()) {
            String plantID = destination.getKey();
            List<IBusinessOrder> orders = destination.getValue();

            capacity.put(plantID, this.processingPlants.get(plantID).addOrders(orders));
        }

        return capacity;
    }

    @Override
    public boolean addMachine(String processingPlantID, String machineName, String ipAddress, String userID, String password) {
        return this.processingPlants.get(processingPlantID.toLowerCase()).addMachine(machineName, ipAddress, userID, password);
    }

    @Override
    public boolean checkForMachine(String machineName) {
        for (Map.Entry<String, ProcessingPlant> plantEntry : this.processingPlants.entrySet()) {
            if (plantEntry.getValue().checkForMachine(machineName)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public IProcessingCapacity removeOrder(String plantID, int orderID) throws NoSuchFieldException {
        return this.processingPlants.get(plantID).removeOrder(orderID);
    }

    @Override
    public IBusinessOrder getOrder(String plantID, int orderID) {
        return this.processingPlants.get(plantID).getOrder(orderID);
    }

    @Override
    public Map<String, IProcessingCapacity> changeOrders(Map<String, List<IBusinessOrder>> destinations, IBusinessOrder oldOrder) {
        Map<String, IProcessingCapacity> capacity = new HashMap<>();

        for (Map.Entry<String, List<IBusinessOrder>> destination : destinations.entrySet()) {
            String plantID = destination.getKey();
            List<IBusinessOrder> orders = destination.getValue();

            capacity.put(plantID, this.processingPlants.get(plantID).changeOrders(orders, oldOrder));
        }

        return capacity;
    }

    @Override
    public List<IBusinessOrder> getAllProductionOrdersInPlants(){

        List<IBusinessOrder> orders = new ArrayList<>();
        for (ProcessingPlant plant : this.processingPlants.values()) {
            orders.addAll(plant.getAllProductionOrders());
        }

        return orders;
    }

    @Override
    public Set<String> getMachineIDsByFactoryID(String factoryID) {
        return processingPlants.get(factoryID).getMachineIDs();
    }

    @Override
    public IOEEToGUI getOEE(String machineID,String factoryID) {
        return processingPlants.get(factoryID).getOEE(machineID);
    }




}
