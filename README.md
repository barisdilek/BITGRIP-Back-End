# Java Coding Challenge
The goal of this coding challenge is to give you an opportunity to show us what you know.

## How to use
Read the full story and check your development environment. You should have Java 9 or 11 and Maven 3.5 or later installed.
After you have completed the challenge, run `mvn clean`, archive the project folder into an compressed archive (zip, tgz, bz2) and send it back to our recruiting representative.

## Tech Stack
Here is a list of the technologies that already form the framework of the solution.
You must not change or extend the given dependencies.

* Java 11
* Spring Boot 2.5
* Spring 5.3 (incl. Spring Web MVC, Spring Data JPA, Spring Actuator, Spring Validation and their dependencies)
* HyperSQL DB 2.5
* Lombok 1.18

## The Challenge requirements
1. Import the given `btw21_struturdaten.csv` and `btw21_ergebnisse.csv` from `src/main/resources` into a data structure, where `Gebietsnummer` represents the correlation key.
2. Map csv header names according to this list and find best matching field type:
      1. `btw21_struturdaten.csv` - `district`
         1. Gebietsnummer - `id`
         2. Gebietsname - `name`
         3. Fläche (km²) - `area`
         4. Bevölkerung - insgesamt (in 1000) - `population`
         5. Bevölkerung Ausländer/-innen (%) - `foreignerPct`
         6. Alter unter 18 (%) - `ageLt18Pct`
         7. Alter 75 und mehr (%) - `ageGt75Pct`
         8. Bodenfläche - Siedlung und Verkehr (%) - `areaSettlePct`
         9. Bodenfläche - Vegetation und Gewässer (%) -  `areaNaturePct`
         10. Wohnfläche (je EW) - `livingSpace`
         11. PKW Insgesamt (je 1000 EW) - `cars`
         12. Verfügbares Einkommen (EUR je EW) - `income`
         13. Bruttoinlandsprodukt (EUR je EW) - `bip`
         14. Arbeitslosenquote - insgesamt - `unemploymentRate`
      2. `btw21_ergebnisse.csv` - `vote`
         1. Gebietsnummer - refers to `district.id`
         2. Gruppenart - `partyType`
         3. Gruppenname - `name`
         4. Gruppenreihenfolge - `rank`
         5. Stimme - `voteType`
         6. Anzahl - `count`
         7. Prozent - `pct`
      3. Store `vote` sorted by `count` descending.
3. Import csv while the application is starting.
4. Implement a scheduled job that does a forward geocoding for all `district.name`
      1. Use the following API endpoint `https://nominatim.openstreetmap.org/search?q={district.name}&format=jsonv2&countrycodes=de&limit=1`
      2. Limit the request rate to 1 request per second
      3. The API is not reliable and not all `district` can be geocoded. Work around this.
      4. Store the lat and lon values within the `district` for `category` of type `boundary` 
      5. The job starts working after the application is started  
5. Implement the following REST Endpoints with JSON formatted responses:
      1. `GET /districts` -> List of all `district.id`, `district.name`, `district.lat` and `district.lon`
      2. `GET /districts/sorted` -> List of all `district` data with sorting and paging and related `vote.name`, `vote.voteType` and `vote.pct`.
      3. `GET /districts/{id}` -> Details of one given `district.id` including all data of matching `district` and related `vote.name`, `vote.voteType` and `vote.pct`
      4. `GET /votes` -> Unique list of all `vote.name`
      5. `GET /votes?name={name}` -> Details of one given `vote.name` including all data of matching `vote` and related `district.id`, `district.name`, `district.lat` and `district.lon`

## The Assessment
After you have submitted your result, we will analyze and evaluate it.
We will pay attention to the following points, so these have a special influence on the evaluation.

#### Correctness
The source should compile and the application should start. 

#### Functionality
The challenge requirements listed above are implemented and working properly.

#### Intent
The intent of your code should be clear and understandable. Complex parts of it are documented with comments.

#### Framework alignment
The features of the framework have been used. 
Nothing was newly implemented that was already in the framework.

#### Layers
The software architecture is divided into layers. The layers are always accessed in one direction only.

#### Naming
The names of the packages, classes, methods, variables, and parameters are meaningfull and helps the reader to understand the code.

#### Code efficiency
The code is structured efficiently and all files follow the same style guide. 

#### Syntax sugar
The code uses language features introduced with Java 9 and 11.
