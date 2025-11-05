package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection;

    public static void connect() {
        try {
            String url = "jdbc:mysql://mysql:3306/world?useSSL=false";
            String user = "appuser";
            String password = "apppass";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void disconnect() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.out.println("Disconnection failed: " + e.getMessage());
        }
    }
}
