package communication.SQLCommunication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseHandler {



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
	}
