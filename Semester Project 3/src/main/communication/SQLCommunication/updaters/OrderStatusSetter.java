package communication.SQLCommunication.updaters;

import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;
import communication.SQLCommunication.tools.Update;

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
        this.conditions = "orderid = ?";

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