package project.Business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/projectjava";
    private static final String USER = "root";
    private static final String PASSWORD = "aayush";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn;
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected successfully from ConnectDB!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
