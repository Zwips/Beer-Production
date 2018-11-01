package communication.SQLCommunication.temp.inserters;

import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.tools.Insert;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BatchLogInserter {

    private String values;
    private String tables;
    private Connection connection;

    public BatchLogInserter() {
        this.values = "(?,?)";
        this.tables = "batch_log(batchid, machineid)";
        connection = new DatabaseConnector().OpenConnection();
    }

    boolean insert(int batchID, int machineID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.INT, machineID));

        return new Insert().insertion(connection, tables, values, wildCardInfo);
    }
}
