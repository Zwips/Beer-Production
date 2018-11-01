package communication.SQLCommunication.temp.tools;

import communication.SQLCommunication.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Select {

    private DatabaseConnector connector;

    public Select() {
        this.connector = new DatabaseConnector();
    }

    public ResultSet query(String selections, String tables, String conditions, List<PrepareInfo> prepareInfos){

        Statement statement = null;
        ResultSet results = null;

        try{
            statement = connector.OpenConnection();
            PreparedStatement pStatement = statement.getConnection().prepareStatement("SELECT " + selections + " FROM " + tables + " WHERE "+conditions);

            pStatement = new SetPreparedStatement().setIntoStatement(pStatement, prepareInfos);

            results = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } finally {
            connector.CloseConnection(results, statement);
        }

        return results;
    }
}
