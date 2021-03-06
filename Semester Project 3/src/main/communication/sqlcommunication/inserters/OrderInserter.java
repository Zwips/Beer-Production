package communication.sqlcommunication.inserters;
/** Represents an order inserter
 * @author Michael P
 * @param Orderinserter constructor creates the object order containing amount, producttype, Earliestdeliverydate, latestdeliverydate, priority, status & orderID
 * @param insert method inserts the object into the order table in the database.
 */
import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.Insert;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderInserter {

    private String values;
    private String tables;
    private Connection connection;
    private String fail;

    public OrderInserter() {
        this.tables = "Orders(Amount, ProductType, EarliestDeliveryDate, LatestDeliveryDate, Priority, Status, orderID)";
        this.values = "(?,?,?,?,?,?,?)";
        connection = new DatabaseConnector().openConnection();
    }


    public void insert(int amount, ProductTypeEnum product, Timestamp earliestDate, Timestamp latestDate, int priority, boolean status, int orderID) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, amount));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, product.getType()));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.TIMESTAMP, earliestDate));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.TIMESTAMP, latestDate));
        wildCardInfo.add(new PrepareInfo(5, PrepareType.INT, priority));
        wildCardInfo.add(new PrepareInfo(6, PrepareType.BOOLEAN, false)); // false because it is not yet done
        wildCardInfo.add(new PrepareInfo(7, PrepareType.INT, orderID));

        new Insert().insertion(connection, tables, values, wildCardInfo);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
