package communication.SQLCommunication.inserters;
/** Represents an order inserter
 * @author Michael P
 * @param Orderinserter constructor creates the object order containing amount, producttype, Earliestdeliverydate, latestdeliverydate, priority, status & orderID
 * @param insert method inserts the object into the order table in the database.
 */
import acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.Insert;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderInserter {


    private String values;
    private String tables;
    private Connection connection;
    private String fail;


    public OrderInserter() {
        // "INSERT INTO Orders(Amount, ProductType, EarliestDeliveryDate, LatestDeliveryDate, Priority, Status, BatchID) VALUES (?,?,?,?,?,?,?)";

        this.values = "(?,?,?,?,?,?,?)";
        this.tables = "Orders(Amount, ProductType, EarliestDeliveryDate, LatestDeliveryDate, Priority, Status, orderID)";
        connection = new DatabaseConnector().openConnection();
        //fail.length();
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
    }
}
