package edu.univ.erp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String AUTH_DB_URL = "jdbc:mysql://localhost:3306/auth_db";
    private static final String ERP_DB_URL = "jdbc:mysql://localhost:3306/erp_db";
    private static final String USER = "root";  // your MySQL username
    private static final String PASSWORD = "Ahin@2005"; // replace with your password

    public static Connection getAuthConnection() {
        try {
            return DriverManager.getConnection(AUTH_DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(" Error connecting to auth_db: " + e.getMessage());
            return null;
        }
    }

    public static Connection getERPConnection() {
        try {
            return DriverManager.getConnection(ERP_DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(" Error connecting to erp_db: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn1 = getAuthConnection();
        Connection conn2 = getERPConnection();

        if (conn1 != null && conn2 != null) {
            System.out.println(" Successfully connected to both databases!");
        } else {
            System.out.println(" Failed to connect to one or both databases.");
        }
    }
}
