package communication.sqlcommunication.updaters;

import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ManualStoragerResetter {

    public static void main(String[] args) {
        new ManualStoragerResetter("beerplant");
    }

    private String values;
    private String tables;
    private String conditions;
    private Connection connection;


    private ManualStoragerResetter(String factoryID)
    {
        this.tables = "storage";
        this.values = "target_amount = ?, current_amount = ?";
        this.conditions = "factory_id = ? AND type = ?";
        connection = new DatabaseConnector().openConnection();
//        createStorage(factoryID);
        resetStorage(factoryID);
    }

    private void resetStorage(String factoryID){

        for (ProductTypeEnum value : ProductTypeEnum.values()) {
            updateStatus(0,5000,factoryID,value);
        }
    }


    public boolean updateStatus(int currentAmount, int targetAmount, String factoryID, ProductTypeEnum type) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, targetAmount));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.INT, currentAmount));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.STRING, factoryID));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.STRING, type.getType()));


        new Update().update(connection, tables, values, conditions, wildCardInfo);
        return true;
    }
    private void createStorage(String factoryID){
        String values = "(?,?,?,?)";
        String tables = "storage(factory_id, type, current_amount, target_amount)";
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        for (ProductTypeEnum type : ProductTypeEnum.values()) {
            wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, factoryID));
            wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, type.getType()));
            wildCardInfo.add(new PrepareInfo(3, PrepareType.INT, 0));
            wildCardInfo.add(new PrepareInfo(4, PrepareType.INT, 5000));
            new Insert().insertion(connection,tables,values,wildCardInfo);
        }

    }
}
