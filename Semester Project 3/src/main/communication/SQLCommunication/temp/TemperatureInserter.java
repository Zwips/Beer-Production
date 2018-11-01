package communication.SQLCommunication.temp;

import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.tools.Insert;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public void insert(int batchID, Date timeOfReading, float value){

        LogMeasurement.logMeasurement(batchID, timeOfReading, value, connection, tables, values);

    }
}
