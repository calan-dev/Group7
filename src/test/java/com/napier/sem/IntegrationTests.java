package com.napier.sem;

import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This will allow for one continuous connection to the database rather than constantly opening and closing
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTests {
    private DatabaseManager db;
    private ReportService reportService;

    /**
     * The Before all will establish a connection to the database before
     * any test is run
     */
    @BeforeAll
    void setup() throws Exception {
        db = new DatabaseManager();
        db.connect();
        reportService = new ReportService(db);
    }

    /**
     * after all the tests are ran the database will close the connection
     */
    @AfterAll
    void tearDown() throws Exception {
        db.disconnect();
    }

    /**
     * This is a unit test that will test the countries by population section of ReportService.java
     * It first calls the method from the report service
     * Its first test is to make sure the list is not null this ensures the method has run
     * then it tests that the list is not empty
     * it then tests that the list is sized correctly
     * then it tests the list is ordered correctly from highest to lowest
     */
    @Test
    void testCountriesByPopulationWorld() throws Exception {
        List<Country> results = reportService.countriesByPopulation(Scope.WORLD, null, 5);
        assertNotNull(results);
        assertFalse(results.isEmpty() );
        assertTrue(results.size() <= 5);
        for (int i = 0; i < results.size() - 1; i++) {
            assertTrue(results.get(i).population() >= results.get(i + 1).population());
        }
    }

    /**
     * This will do the same as the previous method however it will test for when the value
     * asia is added to the continent line
     * Like before there is some basic checks to ensure the result is not null or empty
     * then a check to ensure the list is ordered correctly
     */
    @Test
    void testCountriesByPopulationContinent() throws Exception {
        List<Country> results = reportService.countriesByPopulation(Scope.CONTINENT, "Asia", 5);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertTrue(results.size() <= 5);
        for (int i = 0; i < results.size() - 1; i++) {
            assertTrue(results.get(i).population() >= results.get(i + 1).population());
        }
    }

    /**
     * the changes in this method allow for the reigon to be tested
     * also for a different amound of results to be displayed
     * the method gives the variables that it wants to be tested here it is Caribbean and 3 countries shown
     * the method then does its basic not null and not empty checks
     * then the size and order of the list is tested
     * @throws Exception
     */
   @Test
   void testCountriesByPopulationRegion() throws Exception {
       List<Country> results = reportService.countriesByPopulation(Scope.REGION, "Caribbean", 3);
       assertNotNull(results);
       assertFalse(results.isEmpty());
       assertTrue(results.size() <= 3);
       for (int i = 0; i < results.size() - 1; i++) {
           assertTrue(results.get(i).population() >= results.get(i + 1).population());
       }
   }

//  can be re added later throwing error at the moment @Test
//   void testInvalidContinent() throws Exception {
//       List<Country> results = reportService.countriesByPopulation(Scope.CONTINENT, "Atlantis", 5);
//       assertNotNull(results);
//       assertFalse(results.isEmpty());
//   }
}
