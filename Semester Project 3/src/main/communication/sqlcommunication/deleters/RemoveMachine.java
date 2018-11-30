package communication.sqlcommunication.deleters;

import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.Delete;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;

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