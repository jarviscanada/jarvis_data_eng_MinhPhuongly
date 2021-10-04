# Introduction
This app uses JDBC for creating CRUD functions working with a dockerized 
Postgres Database. The tech-stack used in this project includes Java Core, Maven,
JDBC, PSQL, Docker and DBeaver.

# Implementaiton
## ER Diagram
![ERD of the database](assets/JDBC_ERD.png)

## Design Patterns
The patterns include below classes:
* A DatabaseConnectionManager class for generating a connection to the database using jdbc and postgresql driver.
* A DataTransferObject (DTO) interface which require to implement a getId().
* A DataAccessObject (DAO) abstract class which extends a generic 
type of the DTO.  
* A Customer class which implements the DTO interface and some extra properties.
* A CustomerDAO class which extends the DAO class to
create CRUD functions for working with Customer table in the database using
an instance of the Customer class. 

Note: There is another OrderDAO which has the same design pattern 
with the CustomerDAO. It was implemented to retrieve data from multiple tables.

# Test
The database was implemented using sql files through psql CLIs and 
checked using DBeaver and SQL queries.  

All the functions created in the DAO classes also were tested manually by
calling them from the main function of the JDBCExecutor class.
