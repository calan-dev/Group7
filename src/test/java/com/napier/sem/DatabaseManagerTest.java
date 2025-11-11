package com.napier.sem;

import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {

    private DatabaseManager db;

    @BeforeEach
    void setup() {
        db = new DatabaseManager();
    }

    @AfterEach
    void tearDown() {
        db.disconnect();
    }

    @Test
    @DisplayName("Connect should establish a non-null connection")
    void testConnectEstablishesConnection() throws SQLException {
        db.connect();
        Connection conn = db.getConnection();
        assertNotNull(conn, "Connection should not be null after connect()");
        assertFalse(conn.isClosed(), "Connection should be open after connect()");
    }

    @Test
    @DisplayName("Disconnect should safely close the connection")
    void testDisconnectClosesConnection() throws SQLException {
        db.connect();
        db.disconnect();
        Connection conn = db.getConnection();
        // The connection reference still exists, but should now be closed
        assertTrue(conn.isClosed(), "Connection should be closed after disconnect()");
    }

    @Test
    @DisplayName("getenv should return default when env var not set")
    void testDefaultEnvironmentValues() throws Exception {
        // Reflection trick to test private method
        var method = DatabaseManager.class.getDeclaredMethod("getenv", String.class, String.class);
        method.setAccessible(true);
        String result = (String) method.invoke(null, "NON_EXISTENT_ENV_VAR", "defaultValue");
        assertEquals("defaultValue", result);
    }
}
