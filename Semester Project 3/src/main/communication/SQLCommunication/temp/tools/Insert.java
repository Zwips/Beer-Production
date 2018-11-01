package communication.SQLCommunication.temp.tools;

import communication.SQLCommunication.DatabaseConnector;

import java.sql.*;
import java.util.List;

public class Insert {

    private DatabaseConnector connector;

    public Insert(DatabaseConnector connector) {
        this.connector = connector;
    }

    boolean insertion(String table, String values, List<PrepareInfo> prepareInfos){

        Connection st = null;
        ResultSet rs = null;

        try{
            st = connector.OpenConnection();
            PreparedStatement pStatement = st.prepareStatement("INSERT INTO "+ table + " VALUES "+values);

            pStatement = new SetPreparedStatement().setIntoStatement(pStatement, prepareInfos);

            rs = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
            return false;
        } finally {
            connector.CloseConnection(rs, st);
        }

        return true;
    }

}