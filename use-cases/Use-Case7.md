# USE CASE: 7 Generate report on populations inside and outside of cities

## Goal in Context

As a User I want to produce a report on people living in cities and people not living in cities in a (country/region/continent)

## Scope

World Database

## Level

Primary task

## Preconditions

Database contains population data.  
Database contains world population totals in order to calculate the percentage of population.

## Success End Condition

Generated a report on the populations of people living or not living in a city in a (country/continent/region)

## Failed End Condition

No report is produced

## Primary Actor

Report user

## Trigger

A request for a report on the populations of people living or not living in a city in a (country/continent/region)

## MAIN SUCCESS SCENARIO

1. The Report user requests a report the populations of people living or not living in a city
2. The user selects the scope:
   1. Country
   2. Continent
   3. Region 
3. The system retrieves all records that match the selected scope. 
4. Report contains the required columns: Name, Population, living in cities (including a %), Not Living in cities (including a %)
5. Report User provides a report to the originsation.

## EXTENSIONS

2. Invalid scope selected  
    1. If the user does not select a valid scope (Country, Continent, or Region), the system prompts the user to choose one before continuing.
   2. If the user enters a continent or region name that does not exist in the database, the system displays a message such as "No such country/continent/region found" and does not run the report.

3. No population records returned  
   1. If no population records are returned from the report users' query, the system shows an error message.

## SUB-VARIATIONS

1. If two countries have the same population, the system applies a secondary alphabetical sort by country name.

SCHEDULE

DUE DATE: 12/11/25