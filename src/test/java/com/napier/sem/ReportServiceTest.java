package com.napier.sem;

import org.junit.jupiter.api.*;
import java.util.List;

public class ReportServiceTest {

    private DatabaseManager db;
    private ReportService service;

    @BeforeEach
    void setup() throws Exception {
        db = new DatabaseManager();
        db.connect();
        service = new ReportService(db);
    }

    @AfterEach
    void tearDown() {
        db.disconnect();
    }

    @Test
    void countriesByPopulationWorldTest() throws Exception {
        // Just call the method; lab-style test doesn't assert
        List<Country> countries = service.countriesByPopulation(Scope.WORLD, null, null);
    }

    @Test
    void countriesByPopulationContinentTest() throws Exception {
        List<Country> countries = service.countriesByPopulation(Scope.CONTINENT, "Asia", 5);
    }

    @Test
    void countriesByPopulationRegionTest() throws Exception {
        List<Country> countries = service.countriesByPopulation(Scope.REGION, "Caribbean", 3);
    }

    @Test
    void countriesByPopulationInvalidContinentTest() throws Exception {
        List<Country> countries = service.countriesByPopulation(Scope.CONTINENT, "Atlantis", 5);
    }

    @Test
    void countriesByPopulationInvalidRegionTest() throws Exception {
        List<Country> countries = service.countriesByPopulation(Scope.REGION, "NowhereLand", 5);
    }
}

