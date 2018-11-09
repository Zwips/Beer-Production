package communication.SQLCommunication.selecters;

import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;
import communication.SQLCommunication.tools.Select;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class TemperatureByMachineRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public TemperatureByMachineRetriever() {
        this.selections = "valuecelcius, timeofreading";
        this.tables = "temperature";
        this.conditions = "timeofreading > ? AND batchid IN(Select batchID" +
                                                    " FROM batch_log" +
                                                    " WHERE machineID = ?)"; //TODO eller skulle det være den anden vej?

        this.connection = new DatabaseConnector().OpenConnection();
    }

    public Map<Date, Float> getTemperatures(String machineID, Timestamp date){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.TIMESTAMP, date));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, machineID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        Map<Date, Float> temperatureMeasurements = new HashMap<>();

        try {
            while(results.next()){
                Float value = results.getFloat("valuecelcius");
                Date time = new Date(results.getTimestamp("timeofreading").getTime());
                temperatureMeasurements.put(time, value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temperatureMeasurements;
    }
}