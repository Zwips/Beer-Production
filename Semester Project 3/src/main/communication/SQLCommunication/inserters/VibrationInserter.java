package communication.SQLCommunication.inserters;
/** Represents an Vibration inserter
 * @author Michael P
 * @param VibrationInserter constructor creates the object containing batchid, timestamp & valuePBS
 * @param insert method inserts the object into the vibrations table in the database.
 */
import communication.SQLCommunication.tools.DatabaseConnector;

import java.sql.Connection;
import java.sql.Timestamp;

public class VibrationInserter {


    private String values;
    private String tables;
    private Connection connection;


    public VibrationInserter() {
        // "INSERT INTO temperature(batchid, timeOfReading, ValuePBS) VALUES (?,?,?)";

        this.values = "(?,?,?,?)";
        this.tables = "vibration(batchid, timeOfReading, ValuePBS, factoryid)";
        connection = new DatabaseConnector().openConnection();

    }


    public void insert(int batchID, Timestamp timeOfReading, float value, String factoryID){

        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values, factoryID);

    }

}
