package communication.SQLCommunication.inserters;

import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.Insert;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BatchLogInserter {

    private String values;
    private String tables;
    private Connection connection;

    public BatchLogInserter() {
        this.values = "(?,?,?)";
        this.tables = "batch_log(batchid, machineid, orderid)";
        connection = new DatabaseConnector().openConnection();
    }

    public boolean insert(int batchID, String machineID, int orderID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, machineID));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.INT, orderID));

        return new Insert().insertion(connection, tables, values, wildCardInfo);
    }
}
