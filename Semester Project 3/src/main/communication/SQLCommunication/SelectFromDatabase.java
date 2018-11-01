package communication.SQLCommunication;


import java.sql.*;


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

        ResultSet rs = null;
        Connection st = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.prepareStatement("SELECT Batch.BatchID, Batch.ProductType, Batch.Amount, Batch.Defective WHERE Batch.BatchID = ?");
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

        Connection st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.prepareStatement("SELECT Temperature.BatchID, Temperature.timeOfReading, Temperature.ValueCelcius WHERE BatchID = ?");
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

        Connection st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.prepareStatement("SELECT Humidity.BatchID, Humidity.timeOfReading, Humidity.ValuePercent WHERE BatchID = ?");
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

        Connection st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.prepareStatement("SELECT Vibration.BatchID, Vibration.timeOfReading, Vibration.ValuePBS WHERE BatchID = ?");
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

        Connection st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.prepareStatement("SELECT Batch_log.BatchID, Batch_log.MachineID WHERE BatchID = ?");
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

        Connection st = null;
        ResultSet rs = null;

        try{
            st = dbHandler.OpenConnection();
            PreparedStatement pStatement = st.prepareStatement("SELECT Orders.Amount, Orders.ProductType, Orders.EarliestDeliveryDate, Orders.LatestDeliveryDate, Orders.Priority, Orders.Status, Orders.OrderID, Orders.BatchID WHERE OrderID = ?");
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
