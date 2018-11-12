package communication.SQLCommunication.deleters;
/** Represents an order remover
 * @author Michael P
 * @param OrdersRemoveByOrderID constructor creates the order to be removed from the given orderid
 * @param delete method deletes the order from the order table in the database & returns true if successful.
 */
import communication.SQLCommunication.tools.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrdersRemoveByOrderID {
    private String values;
    private String tables;
    private String conditions;
    private Connection connection;

    public OrdersRemoveByOrderID() {
        this.tables = "Orders";
        this.conditions = "orderid = ?";

        connection = new DatabaseConnector().openConnection();
    }

    public boolean delete(int orderID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, orderID));


        new Delete().delete(connection, tables,  conditions, wildCardInfo);
        return true;
    }
}
