package acquantiance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMESFacade {

    int getNextOrderID();
    List<IBusinessOrder> getPendingOrders();
    HashMap<String, List<IMachineConnectionInformation>> getMachines();
    void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password);
    void deleteMachine(String machineID);

    Set<String> getplantIDs();

    Map<String, IProcessingCapacity> getProductionsCapacities();

    void addPlant(String plantID);

    boolean removePlant(String plantID);

    Map<String, IProcessingCapacity> addOrders(Map<String, List<IBusinessOrder>> destinations);

    boolean addMachine(String processingPlantID, String machineName, String ipAddress, String userID, String password);

    boolean checkForMachine(String machineName);

    boolean removeMachine(String thePlant, String machineName);

    IProcessingCapacity removeOrder(String plantID, int orderID) throws NoSuchFieldException;

    IBusinessOrder getOrder(String plantID, int orderID);

    Map<String, IProcessingCapacity> changeOrders(Map<String, List<IBusinessOrder>> destinations, IBusinessOrder oldOrder);

    Set<String> getMachineIDsByFactoryID(String factoryID);
    IOEEToGUI getOEE(String machineID,String factoryID);
    List<IBusinessOrder> getAllProductionOrdersInPlants();
}
