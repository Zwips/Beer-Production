package communication.SQLCommunication;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SelectFromDatabase {

    DatabaseConnector dbHandler;

    public SelectFromDatabase(){

    }

    public static void main(String[] args) {
        SelectFromDatabase select = new SelectFromDatabase();
        ResultSet result = select.SelectFromBatch(1);
        try {
            System.out.println(result.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet SelectFromBatch(int batchID) {

        Statement st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Batch.BatchID, Batch.ProductType, Batch.Amount, Batch.Defective WHERE Batch.BatchID = ?");
            pStatement.setInt(1, batchID);
            rs = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } finally {
            dbHandler.CloseConnection(rs, st);

            return rs;
        }
    }

    public ResultSet SelectFromTemperature(int batchID) {

        Statement st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Temperature.BatchID, Temperature.timeOfReading, Temperature.ValueCelcius WHERE BatchID = ?");
            pStatement.setInt(1, batchID);
            rs = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } finally {
            dbHandler.CloseConnection(rs, st);
            return rs;
        }
    }

    public ResultSet SelectFromHumidity(int batchID) {

        Statement st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Humidity.BatchID, Humidity.timeOfReading, Humidity.ValuePercent WHERE BatchID = ?");
            pStatement.setInt(1, batchID);
            rs = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } finally {
            dbHandler.CloseConnection(rs, st);

            return rs;
        }
    }

    public ResultSet SelectFromVibration(int batchID) {

        Statement st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Vibration.BatchID, Vibration.timeOfReading, Vibration.ValuePBS WHERE BatchID = ?");
            pStatement.setInt(1, batchID);
            rs = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } finally {
            dbHandler.CloseConnection(rs, st);

            return rs;
        }
    }

    public ResultSet SelectFromBatchLog(int batchID) {

        Statement st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Batch_log.BatchID, Batch_log.MachineID WHERE BatchID = ?");
            pStatement.setInt(1, batchID);
            rs = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } finally {
            dbHandler.CloseConnection(rs, st);

            return rs;
        }
    }

    public ResultSet SelectFromOrder(int orderID) {

        Statement st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.getConnection().prepareStatement("SELECT Orders.Amount, Orders.ProductType, Orders.EarliestDeliveryDate, Orders.LatestDeliveryDate, Orders.Priority, Orders.Status, Orders.OrderID, Orders.BatchID WHERE OrderID = ?");
            pStatement.setInt(1, orderID);
            rs = pStatement.executeQuery();
        } catch(SQLException e){
            System.out.println("Exception" + e);
        } finally {
            dbHandler.CloseConnection(rs, st);

            return rs;
        }
    }
}
