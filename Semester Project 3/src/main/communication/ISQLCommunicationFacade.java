package communication;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;

import java.sql.ResultSet;
import java.util.Date;

public interface ISQLCommunicationFacade {

    ResultSet SelectFromBatch(int batchID);
    ResultSet SelectFromTemperature(int batchID);
    ResultSet SelectFromHumidity(int batchID);
    ResultSet SelectFromVibration(int batchID);
    ResultSet SelectFromBatchLog(int batchID);
    ResultSet SelectFromOrder(int orderID);

    void InsertIntoBatch(int batchID, String productType, int amount, int defective);
    void InsertIntoBatch_log(int batchID, int MachineID);
    void InsertIntoHumidity(int batchID, String timeOfReading, float valuePercent);
    void InsertIntoTemperature(int batchID, String timeOfReading, float valueCelcius);
    void InsertIntoVibration(int batchID, String timeOfReading, float valuePBS);
    void InsertIntoOrders(int amount, String productType, String earliestDeliveryDate, String latestDeliveryDate, int priority, boolean status, int batchID);

    void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product);
    void logTemperature(float value, Date timestamp, int batchID);
    void logVibration(float value, Date timestamp, int batchID);
    void logHumidity(float value, Date timestamp, int batchID);
    void logOrder(IProductionOrder order);

    }
