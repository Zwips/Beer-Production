package communication.SQLCommunication;

import java.sql.*;


public class DatabaseConnector {

    public Connection OpenConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println(e);
        }

        String url = "jdbc:postgresql://tek-mmmi-db0a.tek.c.sdu.dk:5432/si3_2018_group_21_db";
        String username = "si3_2018_group_21";
        String password = "grim26:bijou";
        Connection db = null;

        try {
            db = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return db;
    }

    public void CloseConnection(ResultSet rs, Connection st){
        try{
            st.close();
            st.close();
            if (rs!=null){ //Todo This shouldn't be necessary
                rs.close();
            }
        }catch(SQLException e) {

        }
    }
}
