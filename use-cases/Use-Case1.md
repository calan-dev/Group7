# USE CASE: 1 Generate report on populations(countries)

## CHARACTERISTIC INFORMATION

### Goal in Context

As a User I want to produce a report on the populations of countries in a  (world/continent/region)  organised by largest to smallest.

### Scope

World Database

### Level

Primary task

### Preconditions

Database contains population data.

### Success End Condition

Generated a report on the populations of a country in a (world/continent/region) organised by largest to smallest.

### Failed End Condition

No report is produced

### Primary Actor

Report user

### Trigger

A request for countries in (world/continent/region) to be ranked by population

## MAIN SUCCESS SCENARIO



1. The Report user requests a list of countries organised by population.

2. The user selects the scope:

   1. World

   2. Continent
   3. Region

3. The system retrieves all country records that match the selected scope.

4. Report contains the required columns: Code, Name, Continent, Region, Population, Capital.

5. Report User provides report to the origisation.

## EXTENSIONS

2. Invalid scope selected  
   1. If the user does not select a valid scope (World, Continent, or Region), the system 	prompts the user to choose one before continuing.

   11. If the user enters a continent or region name that does not exist in the database, the system displays a message such as “No such continent/region found” and does not run the report.

3. No country records returned  
   1. If no country records are returned from the report users' query, the system shows 	an error message.

## SUB-VARIATIONS

1. If two countries have the same population, the system applies a secondary alphabetical sort by country name.

## SCHEDULE

DUE DATE: 12/11/25