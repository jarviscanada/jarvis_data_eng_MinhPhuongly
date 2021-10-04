# Introduction
This app uses JDBC for creating CRUD functions working with a dockerized 
Postgres Database. There is a CustomerDAO class for working with Customer table and another DAO class for collecting
data from multiple tables in the database called OrderDAO. The tech-stack used in this project includes Java Core, Maven,
JDBC, PSQL, Docker and DBeaver.

# Implementaiton
## ER Diagram
![ERD of the database](assets/JDBC_ERD.png)

## Design Patterns
* Data Access OBject (DAO) Pattern:

* Repository Pattern:

# Test
The database was implemented using sql files through psql CLIs and 
checked using DBeaver and SQL queries.  

All the functions created in the DAO classes also were tested manually by
calling them from the main function of the JDBCExecutor class.
