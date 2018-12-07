package communication.sqlcommunication.updaters;
/** Represents an StatusSetter
 * @author Michael P
 * @param OrderStatusSetter method set the status of all
 * orders to false=pending by default.
 * @param updateStatus method changes the default value
 * from false to true=completed.
 */
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Update;

import java.sql.Connection;
import java.sql.SQLException;
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

        connection = new DatabaseConnector().openConnection();
    }

    public boolean updateStatus(int orderID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.BOOLEAN, true));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.INT, orderID));

        boolean success = new Update().update(connection, tables, values, conditions, wildCardInfo);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return success;
    }
}
