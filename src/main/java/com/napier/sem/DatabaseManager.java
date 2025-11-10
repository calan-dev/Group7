package com.napier.sem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// DATABASE manager for dealing with connecting and disconnecting for  MySql
public class DatabaseManager {

    //live link to db
    private Connection connection;

    //connect method called when connecting to mysql server
    public void connect() throws SQLException {


        //Database settings takes env vars or uses defaults
        String host = getenv("DB_HOST", "127.0.0.1");
        String port = getenv("DB_PORT", "3306");
        String name = getenv("DB_NAME", "world");
        String user = getenv("DB_USER", "user");
        String pass = getenv("DB_PASS", "pass");

        //jdbc connection url
        String url = "jdbc:mysql://" + host + ":" + port + "/" + name
                + "?useSSL=false&allowPublicKeyRetrieval=true";

        //establish connection
        connection = DriverManager.getConnection(url, user, pass);

        //confirm connection was established and where to
        System.out.printf("Connected to MySQL at %s:%s/%s%n", host, port, name);
    }



    //provides access to the active connection (used for queires)
    public Connection getConnection() { return connection; }

    //disconnect method called when disconnecting from mysql server
    public void disconnect() {
        if (connection != null) {
            try { connection.close(); } catch (SQLException ignored) {}
        }
    }

    //reads env vars or falls back to def
    private static String getenv(String key, String def) {
        String v = System.getenv(key);
        return (v == null || v.isBlank()) ? def : v; //return def when no env var
    }
}
