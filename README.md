# test-app
Test Application with Spring Boot and JPA/Hibernate. 

### Objective
The idea behind this application is to try to reproduce an issue we are seeing in our project, where `MonthBreakdown` entites have **null** attributes after saving a `PriceSummary` with JPA/Hibernate. When `MonthBreakdown` cascade is configured to REMOVE (not ALL) the problem is reproduced in our project (here is not reproducible). You can tweak `ServicePriceBreakdown` class by changing `monthBreakdowns` attribute cascading configuration (use REMOVE or ALL).

### How To Run
To build and run the application for a specific database do:
```
$ mvn -Dspring.profiles.active=<database> clean install
```

Where <database> is:

 * h2
 * mssql
 * mysql
 * oracle

 **Note:** to run with `mysql` is its required to change all entities in the `my.app.entity` package from using `GenerationType.SEQUENCE` to `GenerationType.AUTO`

### Database configurations
To configure your database details, find any of the `application-<database>.properties` files.
