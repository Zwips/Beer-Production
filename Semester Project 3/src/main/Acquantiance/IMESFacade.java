package Acquantiance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IMESFacade {

    int getNextOrderID();
    List<IProductionOrder> getPendingOrders();
    HashMap<String, List<IMachineConnectionInformation>> getMachines();
    void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password);
    void deleteMachine(String machineID);

    Set<String> getplantIDs();

    Map<String, IProcessingCapacity> getProductionsCapacities();

    void addPlant(String plantID);

    boolean removePlant(String plantID);

    Map<String, IProcessingCapacity> addOrders(Map<String, List<IProductionOrder>> destinations);

    boolean addMachine(String processingPlantID, String machineName, String ipAddress, String userID, String password);

    boolean checkForMachine(String machineName);

    boolean removeMachine(String thePlant, String machineName);

    Map<String, IProcessingCapacity> removeOrder(int orderID);
}
