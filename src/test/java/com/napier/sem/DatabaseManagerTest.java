package com.napier.sem;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {
    /**
     * The database manager has been delcared here this should be used accross all tests.
     * the name db has been assigned to it
     */
    private DatabaseManager db;

    /**
     * The beforeEach will create a new DatabaseManager instance every time a test is run
     */
    @BeforeEach
    void setup() {
        db = new DatabaseManager();
    }

    /**
     * AfterEach will be run after every test. This will ensure that the database is closed
     * It also makes sure that no connections are left open
     */
    @AfterEach
    void tearDown() {
        db.disconnect();
    }

    /**
     * The intention of this method is to validate that there is a successful connection to MySQL
     * the steps establish a connection then validate that the connection is open
     * the test will check that the connection is not null and is not closed then return a successful test
     */
    @Test
    void testConnectEstablishesConnection() throws SQLException {
        db.connect();
        Connection conn = db.getConnection();
        assertNotNull(conn);
        assertFalse(conn.isClosed());
    }

    /**
     * This method will make sure that the database connection is closed clean
     * it first has to connect to the database then it will disconnect right after
     *  the connection object will then be called again then validated that it is closed
     *
     */
    @Test
    void testDisconnectClosesConnection() throws SQLException {
        db.connect();
        db.disconnect();
        Connection conn = db.getConnection();
        assertTrue(conn.isClosed());
    }

    /**
     *  Here a false environment is created and then we test it to make sure the default
     *  value when an environment variable does not exit
     * A basic reflection is used here to access the private method then
     * a fake variable name is then passed and verification is done to ensure the default value is returned
     */
    @Test
    void testDefaultEnvironmentValues() throws Exception {
        var method = DatabaseManager.class.getDeclaredMethod("getenv", String.class, String.class);
        method.setAccessible(true);
        String result = (String) method.invoke(null, "NON_EXISTENT_ENV_VAR", "defaultValue");
        assertEquals("defaultValue", result);
    }
}
