package communication.SQLCommunication.selecters;
/** Represents an orders next orderid Retriever
 * @author Michael P
 * @param NextOrderIDRetriever method retrieves the latest order from the database with the MAX keyword
 * @param getNextOrderID returns the latest order.
 */
import communication.SQLCommunication.tools.DatabaseConnector;
import communication.SQLCommunication.tools.PrepareInfo;
import communication.SQLCommunication.tools.PrepareType;
import communication.SQLCommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

public class NextOrderIDRetriever {

    private String selections;
    private String tables;
    private String conditions;
    private Connection connection;

    public NextOrderIDRetriever() {
        this.selections = "MAX(orderid)";
        this.tables = "orders";
        this.conditions = "true = true";

        this.connection = new DatabaseConnector().OpenConnection();
    }

    public int gerNextOrderID(){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);


        try {
            while(results.next()){
                return results.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }
}
