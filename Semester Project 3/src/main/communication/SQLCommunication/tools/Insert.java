package communication.SQLCommunication.tools;
/** Represents an database selector
 * @author Michael P
 * @param query method creates a prepaired statement
 * for selecting data in the database & returns a resultset
 */
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
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}