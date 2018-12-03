package communication.sqlcommunication.selecters;
/** Represents an machine oee Retriever
 * @author Michael P
 * @param getOEE retrieves the OEE depend on machineid from the database
 */
import acquantiance.IOEE;
import communication.sqlcommunication.dataclasses.CommunicationOEE;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OEEByMachineRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public OEEByMachineRetriever() {
        this.selections = "*";
        this.tables = "oee";
        this.conditions = "machineid = ? AND factoryid = ? ORDER BY timeofchange";

        this.connection = new DatabaseConnector().openConnection();
    }

    public IOEE getOEE(String machineID, String factoryID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, machineID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, factoryID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        CommunicationOEE oee = new CommunicationOEE();
        String factory = "";
        String machine = "";
        long oeePercent = 0;
        Map<Date, Boolean> downTimeMap = new HashMap<>();
        Map<Date,String> stateChangeMap = new TreeMap<>();
        long startTime = 0;
        boolean isProducing = false;
        try {
            results.next();
            factory = results.getString("factoryid");
            machine = results.getString("machineid");
            startTime = new Date(results.getTimestamp("timeofchange").getTime()).getTime();
            Date date = new Date(results.getTimestamp("timeofchange").getTime());
            long dateLong = date.getTime();
            stateChangeMap.put(date,results.getString("state"));
            downTimeMap.put(date, results.getBoolean("isproducing"));
            isProducing = results.getBoolean("isproducing");
            while(results.next())
            {
                date = new Date(results.getTimestamp("timeofchange").getTime());
                stateChangeMap.put(date,results.getString("state"));
                downTimeMap.put(date, results.getBoolean("isproducing"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        long totalUptime = 0;
        long totalDowntime = 1;
        for (Map.Entry<Date,Boolean> entry: downTimeMap.entrySet()) {
            if (isProducing)
            {
                totalUptime += entry.getKey().getTime() - startTime;
                startTime = entry.getKey().getTime();
                isProducing = entry.getValue();
            }
            else {
                totalDowntime += entry.getKey().getTime() - startTime;
                startTime = entry.getKey().getTime();
                isProducing = entry.getValue();
            }
        }
        oeePercent = totalUptime  / (totalUptime + totalDowntime);

        oee.setFactoryID(factory);
        oee.setMachineID(machine);
        oee.setOee(oeePercent);
        oee.setStateChangeMap(stateChangeMap);


        new DatabaseConnector().closeConnection(connection);

        return oee;
    }
}
