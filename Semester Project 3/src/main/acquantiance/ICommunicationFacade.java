package acquantiance;

import java.util.*;

public interface ICommunicationFacade {
    IBatch getBatchByBatchID(int batchID, String factoryID);
    Map getTemperaturesByMachine(String machineID, Date dateFrom);
    Map getHumiditiesByMachine(String machineID, Date dateFrom);
    Map getVibrationsByMachine(String machineID, Date dateFrom);

    IBatchLog getBatchLogByBatchID(int batchID, String factoryID);
    List<IBatchLog> getBatchLogByMachineID(String machineID);

    IProductionOrder selectFromOrder(int orderID);
    List<IProductionOrder> getPendingOrders(Date dateFrom, Date dateTo);
    List<IProductionOrder> getPendingOrders();
    List<IProductionOrder> getCompletedOrders();
    void setOrderCompleted(int orderId);

    void insertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective, String factoryID);

    void insertIntoBatch_log(int batchID, String machineID, int orderID, String factoryID);
    void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product);
    void logTemperature(float value, Date timestamp, int batchID, String factoryID);
    void logVibration(float value, Date timestamp, int batchID, String factoryID);
    void logHumidity(float value, Date timestamp, int batchID, String factoryID);

    void logOrder(IProductionOrder order);

    void SendAbortEMail(String machineName);
    void SendStopEmail(String machineName);
    void SendInventoryEmail(String machineName);
    void SendMaintenenceEmail(String machineName);
    void SendPowerLossEmail(String machineName);
    int getNextOrderID();
    int getNextBatchID();

    int getNextBatchID(String plantID);

    HashMap<String, List<IMachineConnectionInformation>> getMachines();
    void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password);
    void deleteMachine(String machineID);

    List<IMachineConnectionInformation> getMachines(String plantID);

    Set<String> getPlantIDs();

    void logOrders(List<IProductionOrder> orders);

    void updateOrders(List<IProductionOrder> orders);

    void logOEE(String factoryID, String machineID, int batchID, String state, Date timestamp, boolean isProducing);
    IOEE getOEEByMachine(String machineID, String factoryID);
    IOEE getOEEByBatchID(int batchID, String factoryID);
    IMachineConnection connectToMachine(String IPAddress, String userID, String password);
}
