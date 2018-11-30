package communication;

import acquantiance.*;

import java.util.*;

public interface ISQLCommunicationFacade {

    IBatch selectFromBatch(int batchID, String factoryID);
    Map<Date,Float> selectFromTemperature(String machineID, Date dateFrom);
    Map<Date,Float> selectFromHumidity(String machineID, Date dateFrom);
    Map<Date,Float> selectFromVibration(String machineID, Date dateFrom);

    IBatchLog getBatchLogByBatchID(int batchID, String factoryID);
    List<IBatchLog> getBatchLogByMachineID(String machineID);

    IProductionOrder selectFromOrder(int orderID);
    List<IProductionOrder> getPendingOrders(Date dateFrom, Date dateTo);
    List<IProductionOrder> getPendingOrders();
    List<IProductionOrder> getCompletedOrders();
    void setOrderCompleted(int orderId);

    void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective, String factoryID);

    void InsertIntoBatch_log(int batchID, String MachineID, int orderID, String factoryID);
    void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product);
    void logTemperature(float value, Date timestamp, int batchID, String factoryID);
    void logVibration(float value, Date timestamp, int batchID, String factoryID);
    void logHumidity(float value, Date timestamp, int batchID, String factoryID);
    void logOrder(IProductionOrder order);

    int getNextOrderID();
    int getNextBatchID();

    void deleteOrderByOrderID(int orderID);
    void deleteBatchLogByBatchID(int BatchID, String factoryID);
    void deleteMachine(String machineID);

    HashMap<String, List<IMachineConnectionInformation>> getMachines();
    List<IMachineConnectionInformation> getMachines(String plantID);
    void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password);
    int getNextBatchID(String plantID);

    void logOEE(String factoryID, String machineID, int batchID, String state, Date timestamp, boolean isProducing);
    IOEE getOEEByMachine(String machineID, String factoryID);
    IOEE getOEEByBatchID(int batchID, String factoryID);

    Set<String> getPlantIDs();

    void logOrders(List<IProductionOrder> orders);

    void updateOrders(List<IProductionOrder> orders);

    void updateOrder(IProductionOrder order);

    void updateStorageCurrentAmount(int currentAmount, String factoryID, ProductTypeEnum type);
    void updateStorageTargetAmount(int targetAmount, String factoryID, ProductTypeEnum type);
    IStorage getStorage(String factoryID);
}
