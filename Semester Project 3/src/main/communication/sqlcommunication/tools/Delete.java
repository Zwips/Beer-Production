package communication.sqlcommunication.tools;
/** Represents an deleter
 * @author Michael P
 * @param delete method deletes various data in the database
 * & returns true if successful
 */
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
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
