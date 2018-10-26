package communication.SQLCommunication;

import communication.ISQLCommunicationFacade;

import java.sql.Array;
import java.sql.ResultSet;

public class SQLCommunicationFacade implements ISQLCommunicationFacade {


    @Override
    public ResultSet SelectFromBatch(int batchID) {
//        SelectFromDatabase select = new SelectFromDatabase();
//        ResultSet results = select.SelectFromBatch(batchID);
//
//        SQLBatch batch = new SQLBatch();
//        byte ID = results.getByte(2);
//        byte ammount = reults.getByte(3);
//
//        batch.setID(ID);
//        batch.setAmmount(ammount);
//
//        results.
//
//        return results;
    }

    @Override
    public ResultSet SelectFromTemperature(int batchID) {
        return null;
    }

    @Override
    public ResultSet SelectFromHumidity(int batchID) {
        return null;
    }

    @Override
    public ResultSet SelectFromVibration(int batchID) {
        return null;
    }

    @Override
    public ResultSet SelectFromBatchLog(int batchID) {
        return null;
    }

    @Override
    public ResultSet SelectFromOrder(int orderID) {
        return null;
    }

    @Override
    public void InsertIntoBatch(int batchID, String productType, int amount, int defective) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
        insert.InsertIntoBatch(batchID, productType, amount, defective);
    }

    @Override
    public void InsertIntoBatch_log(int batchID, int MachineID) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
        insert.InsertIntoBatch_log(batchID, MachineID);
    }

    @Override
    public void InsertIntoHumidity(int batchID, String timeOfReading, float valuePercent) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
        insert.InsertIntoHumidity(batchID, timeOfReading, valuePercent);
    }

    @Override
    public void InsertIntoTemperature(int batchID, String timeOfReading, float valueCelcius) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
        insert.InsertIntoTemperature(batchID, timeOfReading, valueCelcius);
    }

    @Override
    public void InsertIntoVibration(int batchID, String timeOfReading, float valuePBS) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
        insert.InsertIntoVibration(batchID, timeOfReading, valuePBS);
    }

    @Override
    public void InsertIntoOrders(int amount, String productType, String earliestDeliveryDate, String latestDeliveryDate, int priority, boolean status, int batchID) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
        insert.InsertIntoOrders(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority, status, batchID);
    }
}
