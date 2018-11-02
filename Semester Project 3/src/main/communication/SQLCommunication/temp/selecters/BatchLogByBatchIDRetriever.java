package communication.SQLCommunication.temp.selecters;

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

public class BatchLogByBatchIDRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public BatchLogByBatchIDRetriever() {
        this.selections = "batchid, machineid, orderid";
        this.tables = "batch_log";
        this.conditions = "BatchID = ?";

        this.connection = new DatabaseConnector().OpenConnection();
    }

    public IBatchLog getBatchLog(int batchID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        String machineID = null;
        CommunicationBatchLog batchLog = null;

        try {
            while (results.next()){
                String machineName = results.getString("machineid");
                int orderID = results.getInt("orderid");
                batchLog = new CommunicationBatchLog();
                batchLog.setBatchID(batchID);
                batchLog.setMachineID(machineName);
                batchLog.setOrderID(orderID);
            }

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
