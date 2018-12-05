package communication.sqlcommunication.inserters;
/** Represents an Batchlog inserter
 * @author Michael P
 * @param BatchLogInserter constructor creates the object batchlog containing batchid, machineid & orderid
 * @param insert method inserts the object into the Batchlog table in the database.
 */
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.Insert;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BatchLogInserter {

    private String values;
    private String tables;
    private Connection connection;

    public BatchLogInserter() {
        this.values = "(?,?,?,?)";
        this.tables = "batch_log(batchid, machineid, orderid, factoryid)";
        connection = new DatabaseConnector().openConnection();
    }

    public boolean insert(int batchID, String machineID, Integer orderID, String factoryID){
        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));
        wildCardInfo.add(new PrepareInfo(2, PrepareType.STRING, machineID));
        wildCardInfo.add(new PrepareInfo(3, PrepareType.INT, orderID));
        wildCardInfo.add(new PrepareInfo(4, PrepareType.STRING, factoryID));

        return new Insert().insertion(connection, tables, values, wildCardInfo);
    }
}
