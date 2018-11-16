package logic.mes;

import Acquantiance.*;

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
            processingPlants.put(plantID, new ProcessingPlant(plantID, allMachines.get(plantID)));
        }
    }



    @Override
    public int getNextOrderID() {
        return MESOutFacade.getInstance().getNextOrderID();
    }

        @Override
    public List<IProductionOrder> getPendingOrders() {
        return MESOutFacade.getInstance().getPendingOrders();
    }

    @Override
    public HashMap<String, List<IMachineConnectionInformation>> getMachines() {
        return MESOutFacade.getInstance().getMachines();
    }

    @Override
    public void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password) {
        this.processingPlants.get(factoryID).addMachine(machineID, machine_IP, userID, password);
        MESOutFacade.getInstance().InsertMachine(factoryID, machineID, machine_IP, userID, password);
    }

    @Override
    public void deleteMachine(String machineID) {
        MESOutFacade.getInstance().deleteMachine(machineID);
    }

    @Override
    public boolean removeMachine(String thePlant, String machineName) {
        return false;
    }

    @Override
    public Set<String> getplantIDs() {
        return MESOutFacade.getInstance().getPlantIDs();
    }

    @Override
    public Map<String, IProcessingCapacity> getProductionsCapacities() {
        return null;
    }

    @Override
    public void addPlant(String plantID) {

    }

    @Override
    public boolean removePlant(String plantID) {
        return false;
    }

    @Override
    public Map<String, IProcessingCapacity> addOrders(Map<String, List<IProductionOrder>> destinations) {
        Map<String, IProcessingCapacity> capacity = new HashMap<>();

        for (Map.Entry<String, List<IProductionOrder>> destination : destinations.entrySet()) {
            String plantID = destination.getKey();
            List<IProductionOrder> orders = destination.getValue();

            capacity.put(plantID, this.processingPlants.get(plantID).addOrders(orders));
        }

        return capacity;
    }

    @Override
    public boolean addMachine(String processingPlantID, String machineName, String ipAddress, String userID, String password) {
        return false;
    }

    @Override
    public boolean checkForMachine(String machineName) {
        return false;
    }

    @Override
    public IProcessingCapacity removeOrder(String plantID, int orderID) throws NoSuchFieldException {
        return this.processingPlants.get(plantID).removeOrder(orderID);
    }


}
