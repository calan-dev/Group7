package com.napier.sem;

import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IntegrationTests {
    private DatabaseManager db;
    private ReportService reportService;

    @BeforeAll
    void setup() throws Exception {
        db = new DatabaseManager();
        db.connect();
        reportService = new ReportService(db);
    }
    @AfterAll
    void tearDown() throws Exception {
        db.disconnect();
    }
    @Test
    void testCountriesByPopulationWorld() throws Exception {
        List<Country> results = reportService.countriesByPopulation(Scope.WORLD, null, 5);
        assertNotNull(results);
        assertFalse(results.isEmpty() );
        assertTrue(results.size() <= 5);
    }
    @Test
    void testCountriesByPopulationContinent() throws Exception {
        List<Country> results = reportService.countriesByPopulation(Scope.CONTINENT, "Asia", 5);
        assertNotNull(results);
        assertFalse(results.isEmpty());
    }
   @Test
   void testCountriesByPopulationRegion() throws Exception {
       List<Country> results = reportService.countriesByPopulation(Scope.REGION, "Caribbean", 5);
       assertNotNull(results);
       assertFalse(results.isEmpty());
   }

   @Test
   void testInvalidContinent() throws Exception {
       List<Country> results = reportService.countriesByPopulation(Scope.CONTINENT, "Atlantis", 5);
       assertNotNull(results);
       assertFalse(results.isEmpty());
   }
}
