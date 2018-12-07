package communication.sqlcommunication.selecters;

import acquantiance.IStorage;
import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.dataclasses.CommunicationStorage;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class StorageRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public StorageRetriever() {
        this.selections = "*";
        this.tables = "storage";
        this.conditions = "factory_id = ?";

        this.connection = new DatabaseConnector().openConnection();
    }

    public IStorage getStorage(String factoryID) {
        IStorage storage = new CommunicationStorage();
        storage.setFactoryID(factoryID);


        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, factoryID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);

        try {
            while (results.next()) {
                storage.setCurrentAmount(results.getInt("current_amount"), ProductTypeEnum.get(results.getString("type")));
                storage.setTargetAmount(results.getInt("target_amount"), ProductTypeEnum.get(results.getString("type")));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storage;
    }
}
