package communication.SQLCommunication.selecters;

import Acquantiance.IBatchLog;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.dataClasses.CommunicationBatchLog;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;
import communication.SQLCommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchLogByMachineRetriever {


    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public BatchLogByMachineRetriever() {
        this.selections = "batchid, machineid, orderid";
        this.tables = "batch_log";
        this.conditions = "machineID = ?";

        this.connection = new DatabaseConnector().OpenConnection();
    }

    public List<IBatchLog> getBatchLogs(String machineID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, machineID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        List<IBatchLog> batchLogs = new ArrayList<>();

        try {
            while (results.next()){
                int batchID = results.getInt("batchid");
                String machineName = results.getString("machineid");
                int orderID = results.getInt("orderid");
                CommunicationBatchLog batchLog = new CommunicationBatchLog();
                batchLog.setBatchID(batchID);
                batchLog.setMachineID(machineName);
                batchLog.setOrderID(orderID);
                batchLogs.add(batchLog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return batchLogs;
    }
}