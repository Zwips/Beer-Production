package Acquantiance;

import java.util.List;

public interface IMESFacade {

    IMesMachine createMachine(String name, String address, String userID, String password);
    int getNextOrderID();
    int getNextBatchID();
    List<IProductionOrder> getPendingOrders();

}
