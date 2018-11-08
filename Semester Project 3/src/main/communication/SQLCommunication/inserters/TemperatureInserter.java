package communication.SQLCommunication.inserters;

import communication.SQLCommunication.tools.DatabaseConnector;

import java.sql.Connection;
import java.sql.Timestamp;

public class TemperatureInserter {


    private String values;
    private String tables;
    private Connection connection;


    public TemperatureInserter() {
        // "INSERT INTO temperature(batchid, timeOfReading, valueCelcius) VALUES (?,?,?)";

        this.values = "(?,?,?)";
        this.tables = "temperature(batchid, timeOfReading, valueCelcius)";
        connection = new DatabaseConnector().OpenConnection();
    }

    public void insert(int batchID, Timestamp timeOfReading, float value){
        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values);
    }
}
