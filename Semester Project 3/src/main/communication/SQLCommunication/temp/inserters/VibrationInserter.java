package communication.SQLCommunication.temp.inserters;

import communication.SQLCommunication.DatabaseConnector;

import java.sql.Connection;
import java.util.Date;

public class VibrationInserter {


    private String values;
    private String tables;
    private Connection connection;


    public VibrationInserter() {
        // "INSERT INTO temperature(batchid, timeOfReading, ValuePBS) VALUES (?,?,?)";

        this.values = "(?,?,?)";
        this.tables = "temperature(batchid, timeOfReading, ValuePBS)";
        connection = new DatabaseConnector().OpenConnection();

    }


    public void insert(int batchID, Date timeOfReading, float value){

        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values);

    }

}
