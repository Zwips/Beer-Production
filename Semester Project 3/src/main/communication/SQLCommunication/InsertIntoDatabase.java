package communication.SQLCommunication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("Duplicates")
public class InsertIntoDatabase {

	DatabaseHandler dbHandler;

	public void InsertIntoBatch(int amount, int batchID, String types, int defective) {

		Statement st = null;
		ResultSet rs = null;

		try{
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Batch(Amount, BatchID, Types, Defective) VALUES (?, ?, ?, ?)");
			pStatement.setInt(1, amount);
			pStatement.setInt(2, batchID);
			pStatement.setString(3, types);
			pStatement.setInt(4, defective);
			rs = pStatement.executeQuery();
		} catch(SQLException e){
			System.out.println("Exception" + e);
		} finally{
			try{
			st.getConnection().close();
			st.close();
			if (rs!=null){
				rs.close();
			}
			}catch(SQLException e) {

			}
		}
	}

	public void InsertIntoBatch_log(int BatchID, int MachineID) {

		Statement st = null;
		ResultSet rs = null;

		try {
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Batch_log(BatchID, MachineID) VALUES (?, ?)");
			pStatement.setInt(1, BatchID);
			pStatement.setInt(2, MachineID);
			rs = pStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Exception" + e);
		} finally {
			try {
				st.getConnection().close();
				st.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}
		}

	}

	public void InsertIntoHumidity(int BatchID, String timeOfReading) {

		Statement st = null;
		ResultSet rs = null;

		try {
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Humidity(BatchID, timeOfReading) VALUES (?, ?)");
			pStatement.setInt(1, BatchID);
			pStatement.setString(2, timeOfReading);
			rs = pStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Exception" + e);
		} finally {
			try {
				st.getConnection().close();
				st.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}
		}

	}

	public void InsertIntoTemperature(int BatchID, String timeOfReading) {

		Statement st = null;
		ResultSet rs = null;

		try {
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Humidity(BatchID, timeOfReading) VALUES (?, ?)");
			pStatement.setInt(1, BatchID);
			pStatement.setString(2, timeOfReading);
			rs = pStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Exception" + e);
		} finally {
			try {
				st.getConnection().close();
				st.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}
		}

	}

	public void InsertIntoVibration(int BatchID, String timeOfReading) {

		Statement st = null;
		ResultSet rs = null;

		try {
			st = dbHandler.OpenConnection();
			PreparedStatement pStatement = st.getConnection().prepareStatement("INSERT INTO Vibration(BatchID, timeOfReading) VALUES (?, ?)");
			pStatement.setInt(1, BatchID);
			pStatement.setString(2, timeOfReading);
			rs = pStatement.executeQuery();
		} catch (SQLException e) {
			System.out.println("Exception" + e);
		} finally {
			try {
				st.getConnection().close();
				st.close();
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}
		}

	}

}
