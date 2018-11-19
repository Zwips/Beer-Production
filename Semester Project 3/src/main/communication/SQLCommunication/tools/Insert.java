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

    public static void main(String[] args) {
        Insert insert = new Insert();
        insert.insertionTest();
    }

    void insertionTest(){

        long i = 1;
        while(i<100) {
            Connection connection2 = new DatabaseConnector().openConnection();

            try {
                PreparedStatement pStatement = connection2.prepareStatement("INSERT INTO " + "temptable(timex, numbery, namez)" + " VALUES " + "(?,?,?)");

                pStatement.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
                pStatement.setLong(2, i);
                pStatement.setString(3, "Michael");


                pStatement.execute();
                connection2.close();

            } catch (SQLException e) {
                System.out.println("Exception" + e);
                try {
                    connection2.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            i++;
        }

        Connection connection = new DatabaseConnector().openConnection();
        try {
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO " + "temptable(timex, numbery, namez)" + " VALUES " + "(?,?,?)");
            while(i<2000) {
                pStatement.setTimestamp(1, new Timestamp(new java.util.Date().getTime()));
                pStatement.setLong(2, i++);
                pStatement.setString(3, "Michael");

                pStatement.execute();
            }
        } catch (SQLException e) {
            System.out.println("Exception" + e);
        }
    }

}