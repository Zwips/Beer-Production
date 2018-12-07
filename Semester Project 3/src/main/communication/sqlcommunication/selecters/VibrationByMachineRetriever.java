package communication.sqlcommunication.selecters;
/** Represents an Vibration Retriever
 * @author Michael P
 * @param VibrationByMachineRetriever method retrieves
 * the vibration information from the database including
 * timestamp, batchid & machineid
 * @param getVibrations creates the hashmap vibrationMeasurements
 * containing the vibration information and returns it.                     .
    */
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Select;

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
        this.conditions = "timeofreading > ? AND batchid IN(Select batchID" +
                " FROM batch_log" +
                " WHERE machineID = ?)"; //TODO eller skulle det være den anden vej?

        this.connection = new DatabaseConnector().openConnection();
    }

    public Map<Date, Float> getVibrations(String machineID, Timestamp date){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.TIMESTAMP, date));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, machineID));

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
