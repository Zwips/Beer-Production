package communication.SQLCommunication.tools;

import java.sql.*;
import java.util.List;

public class Select {

    public Select() {
    }

    public ResultSet query(Connection connection, String selections, String tables, String conditions, List<PrepareInfo> prepareInfos){

        ResultSet results = null;

        try{
            PreparedStatement pStatement = connection.prepareStatement("SELECT " + selections + " FROM " + tables + " WHERE "+conditions);

            pStatement = new SetPreparedStatement().setIntoStatement(pStatement, prepareInfos);

            results = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return results;
    }
}
