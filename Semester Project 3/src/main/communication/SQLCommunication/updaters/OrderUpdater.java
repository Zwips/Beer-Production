package communication.SQLCommunication.updaters;
/** Represents an StatusSetter
 * @author Michael P
 * @param OrderStatusSetter method set the status of all
 * orders to false=pending by default.
 * @param updateStatus method changes the default value
 * from false to true=completed.
 */
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.*;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderUpdater {

    private String values;
    private String tables;
    private String conditions;
    private Connection connection;

    public OrderUpdater() {
        this.values = "amount = ?, ProductType = ?, earliestDeliveryDate = ?, latestdeliverydate = ?, priority = ?, status = ?";
        this.tables = "Orders";
        this.conditions = "orderid = ?";
        connection = new DatabaseConnector().openConnection();
        //fail.length();
    }


    public boolean update(int amount, ProductTypeEnum product, Timestamp earliestDate, Timestamp latestDate, int priority, boolean status, int orderID) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, amount));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, product.getType()));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.TIMESTAMP, earliestDate));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.TIMESTAMP, latestDate));
        wildCardInfo.add(new PrepareInfo(5, PrepareType.INT, priority));
        wildCardInfo.add(new PrepareInfo(6, PrepareType.BOOLEAN, status)); // false because it is not yet done
        wildCardInfo.add(new PrepareInfo(7, PrepareType.INT, orderID));

        new Update().update(connection, tables, values, conditions, wildCardInfo);
        return true;
    }
}
