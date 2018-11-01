package communication.SQLCommunication.temp;

import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;
import communication.SQLCommunication.temp.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchByMachineRetriever {


    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public BatchByMachineRetriever() {
        this.selections = "batchid";
        this.tables = "batch_log";
        this.conditions = "machineID = ?";

        this.connection = new DatabaseConnector().OpenConnection();
    }

    String getMachine(int machineID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, machineID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        String batchID = null;

        try {
            results.next();

            batchID = results.getString("machineid");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return batchID;
    }
}
