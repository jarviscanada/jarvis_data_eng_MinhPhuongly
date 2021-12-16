# Spark/Scala Project
## Introduction
- With the need of processing big data, the team leader decided to use Apache Spark as a solution in clustered environment. Databricks and Zeppelin are the two options that are in consideration. 
- Technologies applied in the project: Zeppelin, Databricks, Pyspark (Structured APIs, Dataframe), Azure, HDFS, Hive

## Databricks and Zeppelin Implementations
### Databricks
- The project used an Online Retail data as a dataset to answer some bussiness questions in a Databricks notebook such as monthly sales, monthly active user, new and existing users, etc.  
- The notebook was executed in an Azure Cluster which consisted of 1 Driver and 1 worker. The data was uploaded to Azure data table using Databrick UI
- a flow chart will need to be placed here

### Zeppelin
- The project used WDI data as a dataset to answer the bussiness questions in the Hadoop project. Instead of using JDBC/Hive, this time we used Spark/PySpark to perform the tasks.
- The code was written in Zeppelin notebook and execute using PySpark interpreter, while the data is uploaded to HDFS and used to create Hive table for analitic purposes.
- a flow chart will need to be placed here 

## Future Improvement
- Perform importing data to Databricks using JDBC instead of uploading files
- Working with unstructured datasets
- Try some larger datasets
