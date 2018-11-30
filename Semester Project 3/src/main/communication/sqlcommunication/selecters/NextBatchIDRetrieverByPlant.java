package communication.sqlcommunication.selecters;
/** Represents an orders next batchid Retriever
 * @author Michael P
 * @param NextBatchIDRetriever method retrieves the latest Batch from the database with the MAX keyword
 * @param getNextBatchID returns the latest Batch.
 */

import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.PrepareType;
import communication.sqlcommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NextBatchIDRetrieverByPlant {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public NextBatchIDRetrieverByPlant() {
        this.selections = "MAX(batchid)";
        this.tables = "batch_log";
        this.conditions = "factoryid = ?";

        this.connection = new DatabaseConnector().openConnection();
    }

    public int getNextBatchID(String plantID){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();
        wildCardInfo.add(new PrepareInfo(1, PrepareType.STRING, plantID));

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);

        try {
            results.next();
            int nextBatchID = results.getInt(1);

            new DatabaseConnector().closeConnection(connection);
            return nextBatchID;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        new DatabaseConnector().closeConnection(connection);
        return 1;
    }
}
