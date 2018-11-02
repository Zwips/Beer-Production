package communication;

import Acquantiance.*;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public interface ISQLCommunicationFacade {

    IBatch selectFromBatch(int batchID);
    ITemperatureReadings selectFromTemperature(String batchID, Date dateFrom);
    IHumidityReadings selectFromHumidity(String machineName, Date dateFrom);
    IVibrationReadings selectFromVibration(String machineID, Date dateFrom);

    ResultSet selectFromBatchLog(int batchID);//TODO make a wrapperclass this should return.

    IProductionOrder selectFromOrder(int orderID);
    List<IProductionOrder> getPendingOrders(Date dateFrom, Date dateTo);
    List<IProductionOrder> getCompletedOrders();
    void setOrderCompleted(int orderId);

    void InsertIntoBatch(int batchID, ProductTypeEnum productType, int amount, int defective);

    void InsertIntoBatch_log(int batchID, int MachineID, int orderID);
    void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product);
    void logTemperature(float value, Date timestamp, int batchID);
    void logVibration(float value, Date timestamp, int batchID);
    void logHumidity(float value, Date timestamp, int batchID);

    void logOrder(IProductionOrder order);

    }
