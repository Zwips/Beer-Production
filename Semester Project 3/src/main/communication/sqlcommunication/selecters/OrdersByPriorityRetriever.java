package communication.sqlcommunication.selecters;
/** Represents an orders by priority 1-3 Retriever
 * @author Michael P
 * @param OrdersByPriorityRetriever method retrieves the orders from the database with priority 1-3
 * @param getOrdersByPriority creates the ArrayList orders with the priority from 1-3 returns it.                     .
 */
import acquantiance.IBusinessOrder;
import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.dataclasses.CommunicationBusinessOrder;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrdersByPriorityRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public OrdersByPriorityRetriever() {
        this.selections = "*";
        this.tables = "orders";
        this.conditions = "priority = ?";

        this.connection = new DatabaseConnector().openConnection();
    }

    public List<IBusinessOrder> getOrdersByPriority(int priority){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, priority));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        List<IBusinessOrder> orders = new ArrayList<>();

        try {
            while(results.next()){
                CommunicationBusinessOrder order = new CommunicationBusinessOrder();

                order.setAmount(results.getInt("amount"));
                order.setOrderID(results.getInt("orderid"));
                order.setPriority(results.getInt("priority"));
                order.setStatus(results.getBoolean("status"));
                order.setEarliestDeliveryDate(new Date(results.getTimestamp("EarliestDeliveryDate").getTime()));
                order.setLatestDeliveryDate(new Date(results.getTimestamp("LatestDeliveryDate").getTime()));

                ProductTypeEnum type = ProductTypeEnum.get(results.getString("ProductType"));
                order.setType(type);

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
