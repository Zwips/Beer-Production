package communication.SQLCommunication.inserters;

import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.Insert;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LogOEEInserter {
    private String values;
    private String tables;
    private Connection connection;


    public LogOEEInserter() {
        this.values = "(?,?,?,?,?,?)";
        this.tables = "oee(factoryid, machineid, state, timeofchange, batchid, isproducing)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(String factoryID, String machineID, int batchID, String state, Timestamp timestamp, boolean isProducing) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, factoryID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, machineID));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.STRING, state));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.TIMESTAMP, timestamp));
        wildCardInfo.add(new PrepareInfo(5, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(6, PrepareType.BOOLEAN, isProducing));


        new Insert().insertion(connection, tables, values, wildCardInfo);
    }
}
