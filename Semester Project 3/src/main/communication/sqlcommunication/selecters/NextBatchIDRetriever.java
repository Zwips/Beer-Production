package communication.sqlcommunication.selecters;
/** Represents an orders next batchid Retriever
 * @author Michael P
 * @param NextBatchIDRetriever method retrieves the latest Batch from the database with the MAX keyword
 * @param getNextBatchID returns the latest Batch.
 */
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

    public class NextBatchIDRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public NextBatchIDRetriever() {
        this.selections = "MAX(batchid)";
        this.tables = "batch_log";
        this.conditions = "true = true";

        this.connection = new DatabaseConnector().openConnection();
    }

    public int getNextBatchID(){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);
        int nextBatchID = 1;
        try {
            results.next();
            nextBatchID = results.getInt(1);

            new DatabaseConnector().closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nextBatchID;
    }
}
