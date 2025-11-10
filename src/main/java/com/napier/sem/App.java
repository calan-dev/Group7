package com.napier.sem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {

        //creates new instance of databasemanager to connect to mysql
        DatabaseManager db = new DatabaseManager();

        try {
            //connects to db
            db.connect();

            // sql query
            String sql = "SELECT Name, Population FROM country ORDER BY Population DESC LIMIT 5"; // current query gets top 5 countries by population and orders descending


                // getConnection() gets the active connection object
                // prepareStatement() makes the sql string into a PreparedStatement
                //executeQuery() then runs the PreparedStatement
            try (Connection c = db.getConnection();
                 PreparedStatement ps = c.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("Name") + " : " + rs.getLong("Population"));
                }
            }
        } finally {
            //closes db
            db.disconnect();
        }
    }
}

