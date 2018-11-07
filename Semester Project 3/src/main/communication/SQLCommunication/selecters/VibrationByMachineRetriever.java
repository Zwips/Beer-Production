package communication.SQLCommunication.selecters;

import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;
import communication.SQLCommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class VibrationByMachineRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public VibrationByMachineRetriever() {
        this.selections = "valuepbs, timeofreading";
        this.tables = "vibration";
        this.conditions = "machineID = ? AND date < ?"; //TODO eller skulle det være den anden vej?

        this.connection = new DatabaseConnector().OpenConnection();
    }

    public Map<Date, Float> getVibrations(String machineID, Timestamp date){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, machineID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.TIMESTAMP, date));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        Map<Date, Float> vibrationMeasurements = new HashMap<>();

        try {
            while(results.next()){
                Float value = results.getFloat("valuepbs");
                Date time = new Date(results.getTimestamp("timeofreading").getTime());
                vibrationMeasurements.put(time, value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vibrationMeasurements;
    }
}
