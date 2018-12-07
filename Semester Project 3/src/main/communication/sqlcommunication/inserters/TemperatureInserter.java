package communication.sqlcommunication.inserters;
/** Represents an Temperature inserter
 * @author Michael P
 * @param TemperatureInserter constructor creates the object containing batchid, timestamp & valueCelcius
 * @param insert method inserts the object into the database.
 */
import communication.sqlcommunication.tools.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TemperatureInserter {

    private String values;
    private String tables;
    private Connection connection;

    public TemperatureInserter() {
        this.tables = "temperature(batchid, timeOfReading, valueCelcius, factoryid)";
        this.values = "(?,?,?,?)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(int batchID, Timestamp timeOfReading, float value, String factoryID){
        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values, factoryID);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
