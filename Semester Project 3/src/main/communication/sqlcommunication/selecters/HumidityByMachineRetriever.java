package communication.sqlcommunication.selecters;
/** Represents an Humidty Retriever
 * @author Michael P
 * @param HumidityByMachineRetriever method retrieves the humidity information from the database including valuepercent, timestamp, batchid & machineid
 * @param getHumidity creates the hashmap humidityMeasurements containing the humidity information and returns it.                     .
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

public class HumidityByMachineRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public HumidityByMachineRetriever() {
        this.selections = "valuepercent, timeofreading";
        this.tables = "humidity";
        this.conditions = "timeofreading > ? AND batchid IN(Select batchID" +
                " FROM batch_log" +
                " WHERE machineID = ?)"; //TODO eller skulle det være den anden vej?

        this.connection = new DatabaseConnector().openConnection();
    }

    public Map<Date, Float> getHumidity(String machineID, Timestamp date){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.TIMESTAMP, date));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, machineID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        Map<Date, Float> temperatureMeasurements = new HashMap<>();

        try {
            while(results.next()){
                Float value = results.getFloat("valuepercent");
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

        new DatabaseConnector().closeConnection(connection);
        return temperatureMeasurements;
    }


}
