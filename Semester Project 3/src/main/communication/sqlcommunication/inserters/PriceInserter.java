package communication.sqlcommunication.inserters;

import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.Insert;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PriceInserter {
    private String values;
    private String tables;
    private Connection connection;
    private String fail;

    public PriceInserter() {

        this.values = "(?,?,?,?)";
        this.tables = "prices(product_type, sales_price, production_cost, profit)";
        connection = new DatabaseConnector().openConnection();

    }

    public void instert(ProductTypeEnum productType, double salesPrice, double productionCost, double profit){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, productType.getType()));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.DOUBLE, salesPrice));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.DOUBLE, productionCost));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.DOUBLE, profit));

        new Insert().insertion(connection, tables, values, wildCardInfo);

    }

}
