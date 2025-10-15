# USE CASE: 6 Generate report on a specific number of populations (capital cities)

## Goal in Context

As a User I want to produce a report on the populations of capital cities in a (world/continent/region) so that I can view the top N capital cities ranked by population, where N is a number, I choose.

## Scope

World Database

## Level

Primary task

## Preconditions

Database contains population data.

## Success End Condition

Generated a report on the populations of the desired number of capital cities in a (world/continent/region) organised by largest to smallest.

## Failed End Condition

No report is produced

## Primary Actor

Report user

## Trigger

A request for the top N capital cities in (world/continent/region) to be ranked by population

## MAIN SUCCESS SCENARIO


1. The report user requests a list of the top (N) capital cities organised by population. Where N is a number selected by the user

2. The user selects the scope:

   1. World
   2. Continent
   3. Region

3. The user selects the amount of capital cities they would like to have displayed.

4. The system retrieves all capital city records that match the selected scope.

5. Report contains the required columns: name,country,population

6. Report User provides report to the origisation.

## EXTENSIONS

2. Invalid scope selected  
   1. If the user does not select a valid scope (World, Continent, or Region), the system prompts the user to choose one before continuing.
   2. If the user enters a continent or region name that does not exist in the database, the system displays a message such as "No such continent/region found" and does not run the report.

3. The user selects an invalid amount of capital cities
   1. If the user was to select an amount of countries that exceeded the amount on the list or was null then the system will display a message such as "Number of capital cities is invalid" and does not run the report.

4. No capital city records returned  
   1. If no capital city records are returned from the report users' query, the system shows an error message.

## SUB-VARIATIONS

1. If two capital cities have the same population, the system applies a secondary alphabetical sort by country name.

SCHEDULE

DUE DATE: 12/11/25
