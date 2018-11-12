package communication.SQLCommunication.tools;
/** Represents an database selector
 * @author Michael P
 * @param query method creates a prepaired statement
 * for selecting data in the database & returns a resultset
 */
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
        }

        return results;
    }
}
