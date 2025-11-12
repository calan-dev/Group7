package com.napier.sem;

import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class ReportServiceTest {
    /**
     * The database manager has been delcared here this should be used accross all tests.
     * the name db has been assigned to it
     * The report service is declared and the name service is assigned to it
     */
    private DatabaseManager db;
    private ReportService service;
    /**
     * The beforeEach will create a new DatabaseManager instance every time a test is run
     */
    @BeforeEach
    void setup() throws Exception {
        db = new DatabaseManager();
        db.connect();
        service = new ReportService(db);
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
     * This will test that the database returns a valid non-empty list of countries
     *
     */
    @Test
    void countriesByPopulationWorldTest() throws Exception {
        List<Country> countries = service.countriesByPopulation(Scope.WORLD, null, null);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        assertTrue(countries.size() > 0);
    }

    /**
     * This test will test the continent list generated
     * first it will take in the values
     * check the output is not null or empty
     * then it will make sure a list of 5 countries is returned
     *
     */
    @Test
    void countriesByPopulationContinentTest() throws Exception {
        List<Country> countries = service.countriesByPopulation(Scope.CONTINENT, "Asia", 5);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        assertEquals("Asia", countries.get(0).continent());
        assertTrue(countries.size() <= 5);
    }

    /**
     * A simple test similar to the previous method but it will test for top 3 caribbean countries
     * also has not null and empty checks
     */
    @Test
    void countriesByPopulationRegionTest() throws Exception {
        List<Country> countries = service.countriesByPopulation(Scope.REGION, "Caribbean", 3);
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        assertEquals("Caribbean", countries.get(0).region());
        assertTrue(countries.size() <= 3);
    }

    /**
     * This test will check that when a non valid continent is added that the return from the code is empty
     * it does this by sending the incorrect variable "atlantis" to the code
     * it will check its not null
     * in this case when the empty check is performed that will pass the test
     *
     */
    @Test
    void countriesByPopulationInvalidContinentTest() throws Exception {
        List<Country> countries = service.countriesByPopulation(Scope.CONTINENT, "Atlantis", 5);
        assertNotNull(countries);
        assertTrue(countries.isEmpty());
    }

    /**
     * this is the same as previously but done for an incorrect region
     * like before a succesful empty check will allow the code to pass
     */
    @Test
    void countriesByPopulationInvalidRegionTest() throws Exception {
        List<Country> countries = service.countriesByPopulation(Scope.REGION, "NowhereLand", 5);
        assertNotNull(countries);
        assertTrue(countries.isEmpty());
    }
}

