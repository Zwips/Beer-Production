package communication.SQLCommunication.inserters;

import communication.SQLCommunication.tools.DatabaseConnector;

import java.sql.Connection;
import java.sql.Timestamp;

public class VibrationInserter {


    private String values;
    private String tables;
    private Connection connection;


    public VibrationInserter() {
        // "INSERT INTO temperature(batchid, timeOfReading, ValuePBS) VALUES (?,?,?)";

        this.values = "(?,?,?)";
        this.tables = "vibration(batchid, timeOfReading, ValuePBS)";
        connection = new DatabaseConnector().OpenConnection();

    }


    public void insert(int batchID, Timestamp timeOfReading, float value){

        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values);

    }

}
