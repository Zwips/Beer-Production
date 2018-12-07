package communication.sqlcommunication.selecters;
/** Represents an completed orders retriever
 * @author Michael P
 * @param CompletedOrdersRetriever method retrieves the completed orders from the database where status=true.
 * @param getCompletedOrders creates the ArrayList orders containing completed orders and returns it.                     .
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
import java.util.*;

public class CompletedOrdersRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public CompletedOrdersRetriever() {
        this.selections = "*";
        this.tables = "orders";
        this.conditions = "status = ?";

        this.connection = new DatabaseConnector().openConnection();
    }

    public List<IBusinessOrder> getCompletedOrders(){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.BOOLEAN, true));

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
