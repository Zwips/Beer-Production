package communication.sqlcommunication.updaters;

import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Update;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class StorageTargetAmountUpdater {
    private String values;
    private String tables;
    private String conditions;
    private Connection connection;

    public StorageTargetAmountUpdater() {
        this.tables = "storage";
        this.values = "target_amount = ?";
        this.conditions = "factoryid = ? AND type = ?";

        connection = new DatabaseConnector().openConnection();
    }

    public boolean updateStatus(int targetAmount, String factoryID, ProductTypeEnum type) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, targetAmount));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, factoryID));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.STRING, type.getType()));


        new Update().update(connection, tables, values, conditions, wildCardInfo);
        return true;
    }
}

