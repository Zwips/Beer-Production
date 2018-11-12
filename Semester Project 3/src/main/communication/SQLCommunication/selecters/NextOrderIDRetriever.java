package communication.SQLCommunication.selecters;

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
                int nextOrderID = results.getInt(1);

                new DatabaseConnector().CloseConnection(connection);
                return nextOrderID;
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
