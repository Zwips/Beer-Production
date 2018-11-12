package communication.SQLCommunication.deleters;

import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.Delete;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RemoveMachine {
    private String tables;
    private String conditions;
    private Connection connection;

    public RemoveMachine() {
        this.tables = "machines";
        this.conditions = "machineid = ?";

        connection = new DatabaseConnector().openConnection();
    }

    public boolean delete(String machineID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, machineID));


        new Delete().delete(connection, tables,  conditions, wildCardInfo);
        return true;
    }
}
