package communication.SQLCommunication.temp.selecters;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.dataClasses.CommunicationProductionOrder;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;
import communication.SQLCommunication.temp.tools.Select;

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
        this.conditions = "date < ? AND date > ?";

        this.connection = new DatabaseConnector().OpenConnection();
    }

    public List<IProductionOrder> getPendingOrders(Timestamp after, Timestamp before){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.TIMESTAMP, before));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.TIMESTAMP, after));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        List<IProductionOrder> orders = new ArrayList<>();

        try {
            while(results.next()){
                CommunicationProductionOrder order = new CommunicationProductionOrder();

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
