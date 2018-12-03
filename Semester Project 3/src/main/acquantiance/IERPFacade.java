package acquantiance;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    IOEE getOEEByMachine(String machineID, String factoryID);

    int getNextOrderID();

    Set<String> getProcessingPlants();

    Set<String> getMachineIDsByFactoryID(String factoryID);
    IOEEToGUI getOEE(String machineID,String factoryID);
    }
