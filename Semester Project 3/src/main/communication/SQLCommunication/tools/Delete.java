package communication.SQLCommunication.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Delete {

    public Delete(){
    }

    public boolean delete(Connection connection, String table, String conditions, List<PrepareInfo> prepareInfos){

        try{
            PreparedStatement pStatement = connection.prepareStatement("DELETE "+ "FROM " + table + " Where "+conditions);

            pStatement = new SetPreparedStatement().setIntoStatement(pStatement, prepareInfos);

            pStatement.execute();
        } catch(SQLException e){
            System.out.println("Exception" + e);
            return false;
        }

        return true;
    }
}
