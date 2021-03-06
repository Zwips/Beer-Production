package communication.sqlcommunication.inserters;
/** Represents an Batch inserter
 * @author Michael P
 * @param BatchLogInserter constructor creates the batch containing batchid, Producttype, amount & defective.
 * @param insert method inserts the batch into the Batch table in the database.
 */
import acquantiance.ProductTypeEnum;
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.Insert;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BatchInserter {

    private String values;
    private String tables;
    private Connection connection;

    public BatchInserter() {
        this.values = "(?,?,?,?,?)";
        this.tables = "batch(BatchID, ProductType, Amount, Defective, FactoryID)";
        connection = new DatabaseConnector().openConnection();
    }

    public void insert(int batchID, ProductTypeEnum product, int amount, int defective, String factoryID) {

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, product.getType()));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.INT, amount));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.INT, defective));
        wildCardInfo.add(new PrepareInfo(5, PrepareType.STRING, factoryID));

        new Insert().insertion(connection, tables, values, wildCardInfo);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

