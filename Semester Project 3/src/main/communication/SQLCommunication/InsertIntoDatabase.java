package communication.SQLCommunication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertIntoDatabase {

	DatabaseConnector dbHandler;

	public InsertIntoDatabase(){

	}

	public void InsertIntoBatch(int batchID, String productType, int amount, int defective) {

		Statement st = null;
		ResultSet rs = null;

		try{
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Batch(BatchID, ProductType, Amount, Defective) VALUES (?, ?, ?, ?)");

			pStatement.setInt(1, batchID);
			pStatement.setString(2, productType);
			pStatement.setInt(3, amount);
			pStatement.setInt(4, defective);
			rs = pStatement.executeQuery();
		} catch(SQLException e){
			System.out.println("Exception" + e);
		} finally {
			dbHandler.CloseConnection(rs, st);
		}
	}

	public void InsertIntoBatch_log(int batchID, int MachineID) {

		Statement st = null;
		ResultSet rs = null;

		try {
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Batch_log(BatchID, MachineID) VALUES (?, ?)");
			pStatement.setInt(1, batchID);
			pStatement.setInt(2, MachineID);
			rs = pStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Exception" + e);
		} finally {
			dbHandler.CloseConnection(rs, st);
		}
	}

	public void InsertIntoHumidity(int batchID, String timeOfReading, float valuePercent) {

		Statement st = null;
		ResultSet rs = null;

		try {
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Humidity(BatchID, timeOfReading, ValuePercent) VALUES (?, ?, ?)");
			pStatement.setInt(1, batchID);
			pStatement.setString(2, timeOfReading);
			pStatement.setFloat(3, valuePercent);
			rs = pStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Exception" + e);
		} finally {
			dbHandler.CloseConnection(rs, st);
		}
	}

	public void InsertIntoTemperature(int batchID, String timeOfReading, float valueCelcius) {

		Statement st = null;
		ResultSet rs = null;

		try {
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Temperature(BatchID, timeOfReading, ValueCelcius) VALUES (?, ?, ?)");
			pStatement.setInt(1, batchID);
			pStatement.setString(2, timeOfReading);
			pStatement.setFloat(3, valueCelcius);
			rs = pStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Exception" + e);
		} finally {
			dbHandler.CloseConnection(rs, st);
		}
	}

	public void InsertIntoVibration(int batchID, String timeOfReading, float valuePBS) {

		Statement st = null;
		ResultSet rs = null;

		try {
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Vibration(BatchID, timeOfReading, ValuePBS) VALUES (?, ?, ?)");
			pStatement.setInt(1, batchID);
			pStatement.setString(2, timeOfReading);
			pStatement.setFloat(3, valuePBS);
			rs = pStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Exception" + e);
		} finally {
			dbHandler.CloseConnection(rs, st);
		}
	}

	public void InsertIntoOrders(int amount, String productType, String earliestDeliveryDate, String latestDeliveryDate, int priority, boolean status, int batchID) {

		Statement st = null;
		ResultSet rs = null;

		try {
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Orders(Amount, ProductType, EarliestDeliveryDate, LatestDeliveryDate, Priority, Status, BatchID) VALUES (?, ?, ?, ?, ?, ?, ?)");

			pStatement.setInt(1, amount);
			pStatement.setString(2, productType);
			pStatement.setString(3, earliestDeliveryDate);
			pStatement.setString(4, latestDeliveryDate);
			pStatement.setInt(5, priority);
			pStatement.setBoolean(6, status);
			pStatement.setInt(7, batchID);
			rs = pStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Exception" + e);
		} finally {
			dbHandler.CloseConnection(rs, st);
		}
	}
}
