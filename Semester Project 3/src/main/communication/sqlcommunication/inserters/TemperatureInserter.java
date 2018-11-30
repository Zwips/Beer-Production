package communication.sqlcommunication.inserters;
/** Represents an Temperature inserter
 * @author Michael P
 * @param TemperatureInserter constructor creates the object containing batchid, timestamp & valueCelcius
 * @param insert method inserts the object into the database.
 */
import communication.sqlcommunication.tools.DatabaseConnector;

import java.sql.Connection;
import java.sql.Timestamp;

public class TemperatureInserter {


    private String values;
    private String tables;
    private Connection connection;


    public TemperatureInserter() {
        // "INSERT INTO temperature(batchid, timeOfReading, valueCelcius) VALUES (?,?,?)";

        this.values = "(?,?,?,?)";
        this.tables = "temperature(batchid, timeOfReading, valueCelcius, factoryid)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(int batchID, Timestamp timeOfReading, float value, String factoryID){
        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values, factoryID);
    }
}