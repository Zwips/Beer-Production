package communication.SQLCommunication;

import Acquantiance.ProductTypeEnum;
import communication.ISQLCommunicationFacade;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import sun.security.util.PendingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.ResultSet;
import java.util.Date;

/*
Knows everything about the specific database, because communication with a database is not an industry standard

 */

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
        throw new NotImplementedException();
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
//        insert.InsertIntoBatch_log(batchID, MachineID);
    }

    @Override
    public void InsertIntoHumidity(int batchID, String timeOfReading, float valuePercent) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
//        insert.InsertIntoHumidity(batchID, timeOfReading, valuePercent);
    }

    @Override
    public void InsertIntoTemperature(int batchID, String timeOfReading, float valueCelcius) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
//        insert.InsertIntoTemperature(batchID, timeOfReading, valueCelcius);
    }

    @Override
    public void InsertIntoVibration(int batchID, String timeOfReading, float valuePBS) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
//        insert.InsertIntoVibration(batchID, timeOfReading, valuePBS);
    }

    @Override
    public void InsertIntoOrders(int amount, String productType, String earliestDeliveryDate, String latestDeliveryDate, int priority, boolean status, int batchID) {
        InsertIntoDatabase insert = new InsertIntoDatabase();
//        insert.InsertIntoOrders(amount, productType, earliestDeliveryDate, latestDeliveryDate, priority, status, batchID);
    }


    /*
    needs to be implemented with database
     */
    @Override
    public void logDefectives(String machineID, int numberOfDefective, float productsInBatch, float machineSpeed, ProductTypeEnum product) {
        File file = new File("superLog.txt");
        PrintWriter output22 = null;
        try {
            output22 = new PrintWriter(new FileOutputStream(file,true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        output22.println(machineID + ", " + numberOfDefective + ", " + productsInBatch + ", " + machineSpeed + ", " + product.getType());
        output22.close();
    }

    @Override
    public void logTemperature(float value, Date timestamp, int batchID) {
        //TODO
        //add the needed calls
    }

    @Override
    public void logVibration(float value, Date timestamp, int batchID) {
        //TODO
        //add the needed calls
    }

    @Override
    public void logHumidity(float value, Date timestamp, int batchID) {
        //TODO
        //add the needed calls
    }

}
