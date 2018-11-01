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

public class MachineRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public MachineRetriever() {
        this.selections = "machineid";
        this.tables = "batch_log";
        this.conditions = "BatchID = ?";

        this.connection = new DatabaseConnector().OpenConnection();
    }

    String getMachine(int batchID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        String machineID = null;

        try {
            results.next();

            machineID = results.getString("machineid");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return machineID;
    }



}
