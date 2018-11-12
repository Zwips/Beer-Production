package communication.SQLCommunication.tools;
/** Represents an updater
 * @author Michael P
 * @param update method updates various data in the database
 * & returns true if successful
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Update {

    public Update() {
    }

    public boolean update(Connection connection, String table, String values, String conditions, List<PrepareInfo> prepareInfos){

        try{
            PreparedStatement pStatement = connection.prepareStatement("UPDATE "+ table + " SET "+values+" Where "+conditions);

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
