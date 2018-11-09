package communication;

import Acquantiance.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ISQLCommunicationFacade {

    IBatch selectFromBatch(int batchID);
    Map<Date,Float> selectFromTemperature(String machineID, Date dateFrom);
    Map<Date,Float> selectFromHumidity(String machineID, Date dateFrom);
    Map<Date,Float> selectFromVibration(String machineID, Date dateFrom);

    IBatchLog getBatchLogByBatchID(int batchID);
    List<IBatchLog> getBatchLogByMachineID(String machineID);

    IProductionOrder selectFromOrder(int orderID);
    List<IProductionOrder> getPendingOrders(Date dateFrom, Date dateTo);
    List<IProductionOrder> getCompletedOrders();
    void setOrderCompleted(int orderId);

    void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective);

    void InsertIntoBatch_log(int batchID, String MachineID, int orderID);
    void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product);
    void logTemperature(float value, Date timestamp, int batchID);
    void logVibration(float value, Date timestamp, int batchID);
    void logHumidity(float value, Date timestamp, int batchID);
    void logOrder(IProductionOrder order);

    int getNextOrderID();
    int getNextBatchID();

    void deleteOrderByOrderID(int orderID);
    void deleteBatchLogByBatchID(int BatchID);

    }
