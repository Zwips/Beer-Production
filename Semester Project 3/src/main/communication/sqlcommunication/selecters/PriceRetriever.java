package communication.sqlcommunication.selecters;

import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PriceRetriever {
    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public PriceRetriever() {
        this.selections = "*";
        this.tables = "prices";
        this.conditions = "product_type = ?";

        this.connection = new DatabaseConnector().openConnection();
    }

    public double getPrice(ProductTypeEnum productType) {
        double price = 0;

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, productType.getType()));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);

        try {
            results.next();
            price = results.getInt(1);

            new DatabaseConnector().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new DatabaseConnector().closeConnection(connection);
        return price;
    }
}
