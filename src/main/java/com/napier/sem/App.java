package com.napier.sem;

import java.util.List;

public class App {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();

        try {
            db.connect();
            ReportService service = new ReportService(db);

            var topCitiesJapan = service.topNCitiesInCountry("Japan", 10);
            MarkdownWriter.writeMarkdownTable(
                    "Top10CitiesJapan.md",
                    List.of("Name", "Country", "District", "Population"),
                    topCitiesJapan.stream().map(c ->
                            List.of(c.name(), c.country(), c.district(), String.valueOf(c.population()))
                    ).toList()
            );

            var topCountriesAsia = service.topNCountriesInContinent("Asia", 5);
            MarkdownWriter.writeMarkdownTable(
                    "Top5CountriesAsia.md",
                    List.of("Name", "Continent", "Region", "Population"),
                    topCountriesAsia.stream().map(c ->
                            List.of(c.name(), c.continent(), c.region(), String.valueOf(c.population()))
                    ).toList()
            );

            var capsWesternEurope = service.capitalCitiesInRegion("Western Europe");
            MarkdownWriter.writeMarkdownTable(
                    "CapitalCitiesWesternEurope.md",
                    List.of("Name", "Country", "Population"),
                    capsWesternEurope.stream().map(c ->
                            List.of(c.name(), c.country(), String.valueOf(c.population()))
                    ).toList()
            );



            /*
            As a report user I want to produce a report on the populations of countries in a
            (world/continent/region) so that I can see a report the countries organised by largest to smallest.
            */

            List<Country> world = service.countriesByPopulation(Scope.WORLD, null, null);
            printCountries("Countries in the World Largest to Smallest", world);

            /*
            As a Report User I want to produce a report on the populations of countries in a (world/continent/region)
            so that I can view the top N countries ranked by population,
            where N is a number I choose.
            */

            List<Country> asia = service.countriesByPopulation(Scope.CONTINENT, "Asia", 5);
            printCountries("Top 5 Countries in Asia", asia);


        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());

        } finally {
            db.disconnect();
        }
    }

    //print the results
    private static void printCountries(String title, List<Country> countries) {
        System.out.println("\n" + title + "\n");
        System.out.printf("%-45s %-15s %-25s %12s%n",
                "Country", "Continent", "Region", "Population");
        for (Country c : countries) {
            System.out.printf("%-45s %-15s %-25s %12d%n",
                    c.name(), c.continent(), c.region(), c.population());
        }
    }
}
