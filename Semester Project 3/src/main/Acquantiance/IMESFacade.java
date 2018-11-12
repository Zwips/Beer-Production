package Acquantiance;

import java.util.HashMap;
import java.util.List;

public interface IMESFacade {

    IMesMachine createMachine(String name, String address, String userID, String password);
    int getNextOrderID();
    int getNextBatchID();
    List<IProductionOrder> getPendingOrders();
    HashMap<String, List<IMachineConnectionInformation>> getMachines();
    void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password);
    void deleteMachine(String machineID);


}
