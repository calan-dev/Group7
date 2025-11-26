package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportService {
    private final DatabaseManager db;

    public ReportService(DatabaseManager db) { this.db = db; }


    // countries by population (scope + limit)
    public List<Country> countriesByPopulation(Scope scope, String value, Integer topN) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT Name, Continent, Region, Population FROM country");
        switch (scope) {
            case WORLD -> { /* */ }
            case CONTINENT -> sql.append(" WHERE Continent = ?");
            case REGION -> sql.append(" WHERE Region = ?");
        }

        sql.append(" ORDER BY Population DESC, Name ASC");


        boolean useLimit = topN != null && topN > 0;
        if (useLimit) sql.append(" LIMIT ?");

        Connection c = db.getConnection();


        try (PreparedStatement ps = c.prepareStatement(sql.toString())) {
            int i = 1;
            if (scope == Scope.CONTINENT || scope == Scope.REGION) ps.setString(i++, value);
            if (useLimit) ps.setInt(i, topN);
            try (ResultSet rs = ps.executeQuery()) { return mapCountries(rs); }

        }
    }

    //map out countries
    private static List<Country> mapCountries(ResultSet rs) throws Exception {
        List<Country> out = new ArrayList<>();
        while (rs.next()) {
            out.add(new Country(rs.getString("Name"), rs.getString("Continent"), rs.getString("Region"), rs.getLong("Population")));
        }
        return out;
    }
    public List<City> topNCitiesInCountry(String country, int n) throws Exception {
        String sql = """
            SELECT city.Name, country.Name AS Country, city.District, city.Population
            FROM city
            JOIN country ON city.CountryCode = country.Code
            WHERE country.Name = ?
            ORDER BY city.Population DESC
            LIMIT ?
            """;

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, country);
            ps.setInt(2, n);

            try (ResultSet rs = ps.executeQuery()) {
                return mapCities(rs);
            }
        }
    }

    // -------------------------------------------------------------
    // 2. Top N Countries in a Continent
    // -------------------------------------------------------------
    public List<Country> topNCountriesInContinent(String continent, int n) throws Exception {
        String sql = """
            SELECT Name, Continent, Region, Population
            FROM country
            WHERE Continent = ?
            ORDER BY Population DESC
            LIMIT ?
            """;

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, continent);
            ps.setInt(2, n);

            try (ResultSet rs = ps.executeQuery()) {
                return mapCountries(rs);
            }
        }
    }

    // -------------------------------------------------------------
    // 3. Capital Cities in a Region
    // -------------------------------------------------------------
    public List<CapitalCity> capitalCitiesInRegion(String region) throws Exception {
        String sql = """
            SELECT city.Name, country.Name AS Country, city.Population
            FROM city
            JOIN country ON country.Capital = city.ID
            WHERE country.Region = ?
            ORDER BY city.Population DESC
            """;

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, region);

            try (ResultSet rs = ps.executeQuery()) {
                return mapCapitalCities(rs);
            }
        }
    }

    // -------------------------------------------------------------
    // Mapping Cities
    // -------------------------------------------------------------
    private static List<City> mapCities(ResultSet rs) throws Exception {
        List<City> out = new ArrayList<>();

        while (rs.next()) {
            out.add(new City(
                    rs.getString("Name"),
                    rs.getString("Country"),
                    rs.getString("District"),
                    rs.getLong("Population")
            ));
        }
        return out;
    }

    // -------------------------------------------------------------
    // Mapping Capital Cities
    // -------------------------------------------------------------
    private static List<CapitalCity> mapCapitalCities(ResultSet rs) throws Exception {
        List<CapitalCity> out = new ArrayList<>();

        while (rs.next()) {
            out.add(new CapitalCity(
                    rs.getString("Name"),
                    rs.getString("Country"),
                    rs.getLong("Population")
            ));
        }
        return out;
    }
}
