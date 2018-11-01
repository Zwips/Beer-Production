package communication.SQLCommunication.temp.updaters;

import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;
import communication.SQLCommunication.temp.tools.Update;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusSetter {

    private String values;
    private String tables;
    private String conditions;
    private Connection connection;

    public OrderStatusSetter() {
        this.tables = "Orders";
        this.values = "status = ?";
        this.conditions = "batchid = ?";

        connection = new DatabaseConnector().OpenConnection();
    }

    public boolean updateStatus(int orderID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.BOOLEAN, true));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.INT, orderID));


        new Update().update(connection, tables, values, conditions, wildCardInfo);
        return true;
    }
}
