package communication.SQLCommunication.inserters;

import Acquantiance.ProductTypeEnum;
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


    public OrderInserter() {
        // "INSERT INTO Orders(Amount, ProductType, EarliestDeliveryDate, LatestDeliveryDate, Priority, Status, BatchID) VALUES (?,?,?,?,?,?,?)";

        this.values = "(?,?,?,?,?,?,?)";
        this.tables = "Orders(Amount, ProductType, EarliestDeliveryDate, LatestDeliveryDate, Priority, Status, orderID)";
        connection = new DatabaseConnector().OpenConnection();
    }


    public void insert(int amount, ProductTypeEnum product, Timestamp earliestDate, Timestamp latestDate, int priority, boolean status, int orderID) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, amount));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, product.getType()));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.TIMESTAMP, earliestDate));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.TIMESTAMP, latestDate));
        wildCardInfo.add(new PrepareInfo(5, PrepareType.INT, priority));
        wildCardInfo.add(new PrepareInfo(6, PrepareType.BOOLEAN, status)); // false because it is not yet done
        wildCardInfo.add(new PrepareInfo(7, PrepareType.INT, orderID));

        new Insert().insertion(connection, tables, values, wildCardInfo);
    }
}
