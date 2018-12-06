package communication.sqlcommunication.updaters;

import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Update;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PriceUpdater {
    private String values;
    private String tables;
    private String conditions;
    private Connection connection;

    public PriceUpdater() {
        this.tables = "prices";
        this.values = "sales_price = ?, production_cost = ?, profit = ?";
        this.conditions = "product_type = ?";

        connection = new DatabaseConnector().openConnection();
    }

    public boolean update(double salesPrice, double productionCost, double profit, ProductTypeEnum productType){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.DOUBLE, salesPrice));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.DOUBLE, productionCost));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.DOUBLE, profit));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.STRING, productType.getType()));

        new Update().update(connection, tables, values, conditions, wildCardInfo);
        return true;
    }

}
