package communication.SQLCommunication.temp;

import Acquantiance.IBatchLog;
import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.dataClasses.CommunicationBatchLog;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;
import communication.SQLCommunication.temp.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchLog {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public BatchLog() {
        this.selections = "*";
        this.tables = "batch_log";
        this.conditions = "batchid = ?";

        this.connection = new DatabaseConnector().OpenConnection();
    }

    IBatchLog getBatch(int batchID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));

        ResultSet results = new Select().query(connection, selections, tables, conditions,wildCardInfo);

        CommunicationBatchLog batchLog = new CommunicationBatchLog();
        try {
            results.next();

            batchLog.setBatchID(results.getInt("batchid"));
            batchLog.setMachineID(results.getString("machineid"));
            batchLog.setOrderID(results.getInt("orderid"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return batchLog;
    }
}
