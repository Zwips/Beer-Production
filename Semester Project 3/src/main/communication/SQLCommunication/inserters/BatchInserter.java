package communication.SQLCommunication.inserters;
/** Represents an Batch inserter
 * @author Michael P
 * @param BatchLogInserter constructor creates the batch containing batchid, Producttype, amount & defective.
 * @param insert method inserts the batch into the Batch table in the database.
 */
import Acquantiance.ProductTypeEnum;
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.Insert;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BatchInserter {
    private String values;
    private String tables;
    private Connection connection;


    public BatchInserter() {
        this.values = "(?,?,?,?)";
        this.tables = "batch(BatchID, ProductType, Amount, Defective)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(int batchID, ProductTypeEnum product, int amount, int defective) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, product.getType()));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.INT, amount));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.INT, defective));

        new Insert().insertion(connection, tables, values, wildCardInfo);
    }
}

