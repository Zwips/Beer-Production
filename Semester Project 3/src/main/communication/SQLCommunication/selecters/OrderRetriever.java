package communication.SQLCommunication.selecters;

import Acquantiance.IProductionOrder;
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.dataClasses.CommunicationProductionOrder;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;
import communication.SQLCommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public OrderRetriever() {
        this.selections = "*";
        this.tables = "orders";
        this.conditions = "orderid = ?";

        this.connection = new DatabaseConnector().OpenConnection();
    }

    public IProductionOrder getOrder(int batchid){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchid));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        CommunicationProductionOrder order = new CommunicationProductionOrder();

        try {
            results.next();
            order.setAmount(results.getInt("amount"));
            order.setPriority(results.getInt("priority"));
            order.setStatus(results.getBoolean("status"));
            order.setOrderID(results.getInt("orderid"));
            order.setEarliestDeliveryDate(new Date(results.getTimestamp("EarliestDeliveryDate").getTime()));
            order.setLatestDeliveryDate(new Date(results.getTimestamp("LatestDeliveryDate").getTime()));

            ProductTypeEnum type = ProductTypeEnum.get(results.getString("ProductType"));
            order.setType(type);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new DatabaseConnector().CloseConnection(connection);
        return order;
    }
}
