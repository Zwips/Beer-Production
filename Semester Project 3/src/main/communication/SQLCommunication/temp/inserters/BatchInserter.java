package communication.SQLCommunication.temp.inserters;

import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.DatabaseConnector;
import communication.SQLCommunication.temp.tools.Insert;
import communication.SQLCommunication.temp.tools.PrepareInfo;
import communication.SQLCommunication.temp.tools.PrepareType;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BatchInserter {
    private String values;
    private String tables;
    private Connection connection;


    public BatchInserter() {
        this.values = "(?,?,?,?)";
        this.tables = "batch(BatchID, ProductType, Amount, Defective)";
        connection = new DatabaseConnector().OpenConnection();
    }

    public void insert(int batchID, ProductTypeEnum product, int amount, int defective) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, amount));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, product.getType()));
        wildCardInfo.add(new PrepareInfo(7, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(7, PrepareType.INT, batchID));

        new Insert().insertion(connection, tables, values, wildCardInfo);
    }
}

