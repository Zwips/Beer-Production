package communication.SQLCommunication.temp.selecters;

import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;
import communication.SQLCommunication.temp.tools.Select;

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
        this.conditions = "machineID = ? AND date < ?"; //TODO eller skulle det være den anden vej?

        this.connection = new DatabaseConnector().OpenConnection();
    }

    public Map<Date, Float> getTemperatures(String machineID, Timestamp date){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, machineID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.TIMESTAMP, date));

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
