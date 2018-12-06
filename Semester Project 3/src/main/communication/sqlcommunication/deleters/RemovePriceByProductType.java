package communication.sqlcommunication.deleters;

import communication.sqlcommunication.tools.DatabaseConnector;
import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.Delete;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RemovePriceByProductType {
    private String tables;
    private String conditions;
    private Connection connection;

    public RemovePriceByProductType() {
        this.tables = "prices";
        this.conditions = "product_type = ?";

        connection = new DatabaseConnector().openConnection();
    }

    public boolean delete(ProductTypeEnum producttype){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, producttype));

        new Delete().delete(connection, tables,  conditions, wildCardInfo);
        return true;
    }

}
