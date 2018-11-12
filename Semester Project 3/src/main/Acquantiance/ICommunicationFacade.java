package Acquantiance;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICommunicationFacade {
    IBatch getBatchByBatchID(int batchID);
    Map getTemperaturesByMachine(String machineID, Date dateFrom);
    Map getHumiditiesByMachine(String machineID, Date dateFrom);
    Map getVibrationsByMachine(String machineID, Date dateFrom);

    IBatchLog getBatchLogByBatchID(int batchID);
    List<IBatchLog> getBatchLogByMachineID(String machineID);

    IProductionOrder selectFromOrder(int orderID);
    List<IProductionOrder> getPendingOrders(Date dateFrom, Date dateTo);
    List<IProductionOrder> getPendingOrders();
    List<IProductionOrder> getCompletedOrders();
    void setOrderCompleted(int orderId);

    void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective);

    void InsertIntoBatch_log(int batchID, String machineID, int orderID);
    void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product);
    void logTemperature(float value, Date timestamp, int batchID);
    void logVibration(float value, Date timestamp, int batchID);
    void logHumidity(float value, Date timestamp, int batchID);

    void logOrder(IProductionOrder order);

    void SendAbortEMail(String machineName);
    void SendStopEmail(String machineName);
    void SendInventoryEmail(String machineName);
    void SendMaintenenceEmail(String machineName);
    void SendPowerLossEmail(String machineName);
    int getNextOrderID();
    int getNextBatchID();

    HashMap<String, List<IMachineConnectionInformation>> getMachines();
    void InsertMachine(String factoryID, String machineID, String machine_IP, String userID, String password);
    void deleteMachine(String machineID);
}
