package communication.SQLCommunication;


import java.sql.*;
import java.util.Map;


public class SelectFromDatabase {

    DatabaseConnector dbHandler;

    public SelectFromDatabase(){
        dbHandler = new DatabaseConnector();
    }

    public static void main(String[] args) {
        SelectFromDatabase select = new SelectFromDatabase();
        ResultSet result = select.SelectFromBatchLog(1);
        try {
//            result.next();
            System.out.println(result.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet SelectFromBatch(int batchID) {

        Statement st = null;
        ResultSet rs = null;
        Connection con = null;
        try{
            con = dbHandler.OpenConnection();
            PreparedStatement pStatement = con.prepareStatement("SELECT Batch.BatchID, Batch.ProductType, Batch.Amount, Batch.Defective WHERE Batch.BatchID = ?");
            pStatement.setInt(1, batchID);
            rs = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } finally {
            dbHandler.CloseConnection(rs, con);

            return rs;
        }
    }
//
//    public ResultSet SelectFromTemperature(int batchID) {
//
//        Statement st = null;
//        ResultSet rs = null;
//
//        try{
//            st = dbHandler.OpenConnection();
//            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Temperature.BatchID, Temperature.timeOfReading, Temperature.ValueCelcius WHERE BatchID = ?");
//            pStatement.setInt(1, batchID);
//            rs = pStatement.executeQuery();
//        } catch(SQLException e){
//            System.out.println("Exception" + e);
//        } finally {
//            dbHandler.CloseConnection(rs, st);
//            return rs;
//        }
//    }
//
//    public ResultSet SelectFromHumidity(int batchID) {
//
//        Statement st = null;
//        ResultSet rs = null;
//
//        try{
//            st = dbHandler.OpenConnection();
//            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Humidity.BatchID, Humidity.timeOfReading, Humidity.ValuePercent WHERE BatchID = ?");
//            pStatement.setInt(1, batchID);
//            rs = pStatement.executeQuery();
//        } catch(SQLException e){
//            System.out.println("Exception" + e);
//        } finally {
//            dbHandler.CloseConnection(rs, st);
//
//            return rs;
//        }
//    }
//
//    public ResultSet SelectFromVibration(int batchID) {
//
//        Statement st = null;
//        ResultSet rs = null;
//
//        try{
//            st = dbHandler.OpenConnection();
//            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Vibration.BatchID, Vibration.timeOfReading, Vibration.ValuePBS WHERE BatchID = ?");
//            pStatement.setInt(1, batchID);
//            rs = pStatement.executeQuery();
//        } catch(SQLException e){
//            System.out.println("Exception" + e);
//        } finally {
//            dbHandler.CloseConnection(rs, st);
//
//            return rs;
//        }
//    }
//
    public ResultSet SelectFromBatchLog(int batchID) {

        Statement st = null;
        ResultSet rs = null;
        Connection con = null;
        try{
            con = dbHandler.OpenConnection();

//            PreparedStatement pStatement = con.prepareStatement("SELECT BatchID, MachineID FROM ? WHERE BatchID = ?");
            st = con.createStatement();
//            rs = st.executeQuery("INSERT INTO batch_log1(BatchID, MachineName) VALUES (1, 'hejsa')");
            rs = st.executeQuery("SELECT * from batch_log");
            rs.next();
            System.out.println(rs.getInt(1));
//            pStatement.setString(1, "Batch_log");
//            pStatement.setInt(2, batchID);
//            rs = pStatement.executeQuery();
        } catch(SQLException e){
//            System.out.println("Exception" + e);
            e.printStackTrace();
        } finally {
//            dbHandler.CloseConnection(rs, con);

            return rs;
        }
    }
//
//    public ResultSet SelectFromOrder(int orderID) {
//
//        Statement st = null;
//        ResultSet rs = null;
//
//        try{
//            st = dbHandler.OpenConnection();
//            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Orders.Amount, Orders.ProductType, Orders.EarliestDeliveryDate, Orders.LatestDeliveryDate, Orders.Priority, Orders.Status, Orders.OrderID, Orders.BatchID WHERE OrderID = ?");
//            pStatement.setInt(1, orderID);
//            rs = pStatement.executeQuery();
//        } catch(SQLException e){
//            System.out.println("Exception" + e);
//        } finally {
//            dbHandler.CloseConnection(rs, st);
//
//            return rs;
//        }
//    }
}
