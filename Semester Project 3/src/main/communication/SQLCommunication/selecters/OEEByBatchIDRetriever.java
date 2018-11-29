package communication.SQLCommunication.selecters;

import acquantiance.IOEE;
import communication.SQLCommunication.dataClasses.CommunicationOEE;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;
import communication.SQLCommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OEEByBatchIDRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public OEEByBatchIDRetriever() {
        this.selections = "*";
        this.tables = "oee";
        this.conditions = "batchid = ? AND factoryid = ? ORDER BY timeofchange ORDER BY timeofchange";

        this.connection = new DatabaseConnector().openConnection();
    }

    public IOEE getOEE(int batchid, String factoryID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchid));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, factoryID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        CommunicationOEE oee = new CommunicationOEE();
        String factory = "";
        String machineID = "";
        long oeePercent = 0;
        Map<Date, Boolean> downTimeMap = new HashMap<>();
        Map<Date,String> stateChangeMap = new HashMap<>();
        long startTime = 0;
        try {
            results.next();
            factory = results.getString("factoryid");
            machineID = results.getString("machineid");
            startTime = results.getTime("timeofchange").getTime();
            stateChangeMap.put(new Date(results.getTimestamp("timeofchange").getTime()),results.getString("state"));
            downTimeMap.put(new Date(results.getTimestamp("timeofchange").getTime()), results.getBoolean("isproducing"));
            while(results.next())
            {
                stateChangeMap.put(new Date(results.getTimestamp("timeofchange").getTime()),results.getString("state"));
                downTimeMap.put(new Date(results.getTimestamp("timeofchange").getTime()), results.getBoolean("isproducing"));
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
        long totalDowntime = 0;
        for (Map.Entry<Date,Boolean> entry: downTimeMap.entrySet()) {
            if (entry.getValue())
            {
                totalUptime += entry.getKey().getTime() - startTime;
                startTime = entry.getKey().getTime();
            }
            else {
                totalDowntime += entry.getKey().getTime() - startTime;
                startTime = entry.getKey().getTime();
            }
        }
        oeePercent = totalUptime  / (totalUptime + totalDowntime);

        oee.setFactoryID(factory);
        oee.setMachineID(machineID);
        oee.setOee(oeePercent);
        oee.setStateChangeMap(stateChangeMap);


        new DatabaseConnector().closeConnection(connection);

        return oee;
    }
}
