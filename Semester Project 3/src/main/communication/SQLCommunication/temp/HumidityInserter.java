package communication.SQLCommunication.temp;

import communication.SQLCommunication.DatabaseConnector;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

public class HumidityInserter {

    private String values;
    private String tables;
    private Connection connection;


    public HumidityInserter() {
        // "INSERT INTO temperature(batchid, timeOfReading, valuePercent) VALUES (?,?,?)";

        this.values = "(?,?,?)";
        this.tables = "temperature(batchid, timeOfReading, valuePercent)";
        connection = new DatabaseConnector().OpenConnection();

    }


    public void insert(int batchID, Timestamp timeOfReading, float value){

        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values);

    }
}
