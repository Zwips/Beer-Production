package communication.sqlcommunication.selecters;
/** Represents an orders next orderid Retriever
 * @author Michael P
 * @param NextOrderIDRetriever method retrieves the latest order from the database with the MAX keyword
 * @param getNextOrderID returns the latest order.
 */
import communication.sqlcommunication.tools.DatabaseConnector;
import communication.sqlcommunication.tools.PrepareInfo;
import communication.sqlcommunication.tools.Select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        this.connection = new DatabaseConnector().openConnection();
    }

    public int gerNextOrderID(){

        List<PrepareInfo> wildCardInfo = new ArrayList<>();

        ResultSet results = new Select().query(connection, selections, tables, conditions, wildCardInfo);


        try {
            results.next();
            int nextOrderID = results.getInt(1);

            return nextOrderID;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
