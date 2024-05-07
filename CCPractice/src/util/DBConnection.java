package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");

                // Connection details from DBPropertyUtil
                String url = "jdbc:mysql://localhost:3306/InsuranceManagementSystem"; //We can Change this to our DB URL
                String username = "root"; // We can Change this to our DB user name
                String password = "ashwinraj"; //We can Change this to our DB password

                // Establish connection
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("Connection established: " + conn);
               // System.out.println("Connection is established...");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
        }
    }
        return conn;
    }
}
