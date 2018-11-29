package communication.SQLCommunication.selecters;
/** Represents an batch log by batchid retriever.
 * @author Michael P
 * @param BatchLogByBatchIDRetriever method retrieves the batchlogs from the database with the given BatchID
 * @param getBatchLog creates the ArrayList Batchlog and returns it.                     .
 */
import acquantiance.IBatchLog;
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

public class BatchLogByBatchIDRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public BatchLogByBatchIDRetriever() {
        this.selections = "batchid, machineid, orderid";
        this.tables = "batch_log";
        this.conditions = "BatchID = ?";

        this.connection = new DatabaseConnector().openConnection();
    }

    public IBatchLog getBatchLog(int batchID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);

        CommunicationBatchLog batchLog = new CommunicationBatchLog();

        try {
            results.next();

            String machineName = results.getString("machineid");
            int orderID = results.getInt("orderid");

            batchLog.setBatchID(batchID);
            batchLog.setMachineID(machineName);
            batchLog.setOrderID(orderID);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new DatabaseConnector().closeConnection(connection);
        return batchLog;
    }



}
