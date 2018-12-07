package communication.sqlcommunication.selecters;
/** Represents an pending orders retriever
 * @author Michael P
 * @param PendingOrdersRetriever method retrieves the pending orders from the database where status=false
 * @param getPendingOrders creates the ArrayList orders containing pending orders and returns it.                     .
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PendingOrdersRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public PendingOrdersRetriever() {
        this.selections = "*";
        this.tables = "orders";
        this.conditions = "status = false";

        this.connection = new DatabaseConnector().openConnection();
    }

    public List<IBusinessOrder> getPendingOrders(Timestamp after, Timestamp before){

        String conditions = this.conditions+" AND latestdeliverydate < ? AND latestdeliverydate > ?";
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.TIMESTAMP, before));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.TIMESTAMP, after));

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
    public List<IBusinessOrder> getPendingOrders(){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();

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
