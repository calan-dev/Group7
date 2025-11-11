package com.napier.sem;

// Simple data holder for a country row
public class Country {
    public final String name;
    public final String continent;
    public final String region;
    public final long population;


    public Country(String name, String continent, String region, long population) {
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.population = population;
    }

    @Override
    //Override the default toString so that we can print out an orgnised string : (name (continent / region): population)
    public String toString() {
        return String.format("%s (%s / %s) : %d", name, continent, region, population);
    }
}
