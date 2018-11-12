package communication.SQLCommunication.inserters;
/** Represents an Humidity inserter
 * @author Michael P
 * @param HumidityInserter constructor creates the object containing batchid, timestamp & valuePercent.
 * @param insert method inserts the object into the table humidity in the database.
 */
import communication.SQLCommunication.tools.DatabaseConnector;

import java.sql.Connection;
import java.sql.Timestamp;

public class HumidityInserter {

    private String values;
    private String tables;
    private Connection connection;


    public HumidityInserter() {
        // "INSERT INTO temperature(batchid, timeOfReading, valuePercent) VALUES (?,?,?)";

        this.values = "(?,?,?)";
        this.tables = "humidity(batchid, timeOfReading, valuePercent)";
        connection = new DatabaseConnector().openConnection();

    }


    public void insert(int batchID, Timestamp timeOfReading, float value){

        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values);

    }
}
