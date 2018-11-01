package communication.SQLCommunication.temp.tools;

import communication.SQLCommunication.DatabaseConnector;

import java.sql.*;
import java.util.List;

public class Insert {

    public Insert() {
    }

    public boolean insertion(Connection connection, String table, String values, List<PrepareInfo> prepareInfos){

        try{
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO "+ table + " VALUES "+values);

            pStatement = new SetPreparedStatement().setIntoStatement(pStatement, prepareInfos);

            pStatement.execute();
        } catch(SQLException e){
            System.out.println("Exception" + e);
            return false;
        }

        return true;
    }

}