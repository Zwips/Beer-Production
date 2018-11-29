package acquantiance;

import java.util.Date;
import java.util.List;

public interface IERPFacade {
    boolean addOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority);

    boolean addMachine( String machineName, String address, String userID, String password);
    boolean addMachine(String processingPantID, String machineName, String address, String userID, String password);
    void addProcessingPlant(String plantID);
    void removeProcessingPlant(String plantID);

    boolean removeMachine(String processingPantID, String machineName);
    boolean removeMachine(String machineName);
    List<IProductionOrder> getProductionOrderQueue();

    boolean updateOrder(int amount, ProductTypeEnum productType, Date earliestDeliveryDate, Date latestDeliveryDate, int priority, int orderID);

    IProductionOrder getOrder(int orderID);

    int getNextOrderID();
}