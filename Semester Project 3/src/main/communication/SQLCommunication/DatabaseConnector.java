package communication.SQLCommunication;

import java.sql.*;


public class DatabaseConnector {


	public Statement OpenConnection() {
		try {
			Class.forName("org.postgressql.Driver");
		} catch (java.lang.ClassNotFoundException e) {

			System.out.println(e);
		}

			String url = "jdbc:postgresql://tek-mmmi-db0a.tek.c.sdu.dk/si3_2018_group_21";
			String username = "si3_2018_group_21";
			String password = "grim26:bijou";
			Statement st = null;

			try {
				Connection db = DriverManager.getConnection(url, username, password);

			} catch (SQLException e) {
				System.out.println(e);
			}
			return st;

		}

		public void CloseConnection(ResultSet rs, Statement st){
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
