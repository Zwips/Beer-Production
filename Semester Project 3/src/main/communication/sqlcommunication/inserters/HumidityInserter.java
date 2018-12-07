package communication.sqlcommunication.inserters;
/** Represents an Humidity inserter
 * @author Michael P
 * @param HumidityInserter constructor creates the object containing batchid, timestamp & valuePercent.
 * @param insert method inserts the object into the table humidity in the database.
 */
import communication.sqlcommunication.tools.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HumidityInserter {

    private String values;
    private String tables;
    private Connection connection;

    public HumidityInserter() {
        this.tables = "humidity(batchid, timeOfReading, valuePercent, factoryid)";
        this.values = "(?,?,?,?)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(int batchID, Timestamp timeOfReading, float value, String factoryID){

        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values,factoryID);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
