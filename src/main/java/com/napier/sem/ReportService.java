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
}
