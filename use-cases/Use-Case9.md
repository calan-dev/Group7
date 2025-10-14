# USE CASE: 9 View populations (language speakers)

## Goal in Context

As a User I want to produce a report on the number of people who speak Chinese, English, Hindi. Spanish, or Arabic so that I can see the amount ranked from greatest to smallest, including the percentage of the world's population.

## Scope

World Database

## Level

Primary task

## Preconditions

Database contains language population data.

Database contains world population totals in order to calculate the percentage of population.

## Success End Condition

The organisation can view the number of people who speak Chinese, English, Hindi, Spanish, Arabic from greatest number to smallest as well as including the percentage of the world population.

### Failed End Condition

No data is viewable

## Primary Actor

User

## Trigger

The user requests to view the populations of the specified languages and their percentages of the world population.

## MAIN SUCCESS SCENARIO

1. The user request to view data for language speakers
2. The system retrieves the number of speakers for Chinese, English, Hindi, Spanish, and Arabic.
3. The system calculates each language's percentage of the world population.
4. The system orders the results from the language with the greatest number of speakers to the smallest.
5. The system displays the information that the user has requested

## EXTENSIONS

2. Population data is not available
   1. The system sends an error message informing the user the data is not available

## SUB-VARIATIONS

- If two languages have the same number of speakers, the system applies a secondary alphabetical sort by language name.

SCHEDULE

DUE DATE: 12/11/25