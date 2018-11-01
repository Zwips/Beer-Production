package communication.SQLCommunication.temp.tools;

import communication.SQLCommunication.DatabaseConnector;

import java.sql.*;
import java.util.List;

public class Select {

    private DatabaseConnector connector;

    public Select() {
        this.connector = new DatabaseConnector();
    }

    public ResultSet query(String selections, String tables, String conditions, List<PrepareInfo> prepareInfos){

        Connection connection = null;
        ResultSet results = null;

        try{
            connection = connector.OpenConnection();
            PreparedStatement pStatement = connection.prepareStatement("SELECT " + selections + " FROM " + tables + " WHERE "+conditions);

            pStatement = new SetPreparedStatement().setIntoStatement(pStatement, prepareInfos);

            results = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } finally {
            connector.CloseConnection(results, connection);
        }

        return results;
    }
}
