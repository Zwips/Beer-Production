package communication.SQLCommunication.deleters;
/** Represents an batchlog remover
 * @author Michael P
 * @param OrdersRemoveByOrderID constructor creates the batchlog to be removed with the given batchid
 * @param delete method deletes the batchlog from the batchlog table in the database & returns true if successful.
 */
import communication.SQLCommunication.tools.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RemoveBatchLogByBatchID {
    private String values;
    private String tables;
    private String conditions;
    private Connection connection;

    public RemoveBatchLogByBatchID() {
        this.tables = "batch_log";
        this.conditions = "batchid = ?";

        connection = new DatabaseConnector().openConnection();
    }

    public boolean delete(int batchID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.INT, batchID));


        new Delete().delete(connection, tables,  conditions, wildCardInfo);
        return true;
    }
}
