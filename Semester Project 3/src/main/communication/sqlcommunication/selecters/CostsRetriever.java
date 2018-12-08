package communication.sqlcommunication.selecters;

import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostsRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public CostsRetriever() {
        this.selections = "*";
        this.tables = "prices";
        this.conditions = "true = true";

        this.connection = new DatabaseConnector().openConnection();
    }

    public Map<ProductTypeEnum, Double> getCosts() {
        List<PrepareInfo> wildCardInfo = new ArrayList<>();

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);

        Map<ProductTypeEnum, Double> costs = new HashMap<>();

        try {
            if (results.isBeforeFirst()) {

                while(results.next()){
                    double cost = results.getDouble("production_cost");
                    String typeString = results.getString("product_type");

                    ProductTypeEnum type = ProductTypeEnum.get(typeString);

                    costs.put(type, cost);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return costs;
    }


}
