package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDataBase {
    private static final String JDBCURL = "jdbc:mysql://localhost:3306/furama_web?useSSL=false";
    private static final String JDBCUSERNAME = "root";
    private static final String JDBCPASSWORD = "phanphuocdai";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(JDBCURL, JDBCUSERNAME, JDBCPASSWORD);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
}
