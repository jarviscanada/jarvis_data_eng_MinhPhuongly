# Introduction
This app uses JDBC for creating CRUD functions working with a dockerized 
Postgres Database. There is a CustomerDAO class for working with Customer table and another DAO class for collecting
data from multiple tables in the database called OrderDAO. The tech-stack used in this project includes Java Core, Maven,
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

Note: The OrderDAO class share the same pattern with the CustomerDAO class.

# Test
The database was implemented using sql files through psql CLIs and 
checked using DBeaver and SQL queries.  

All the functions created in the DAO classes also were tested manually by
calling them from the main function of the JDBCExecutor class.
