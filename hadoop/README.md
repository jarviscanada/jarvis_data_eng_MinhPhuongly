# Hadoop Project

## Table of contents
* [Introduction](#Introduction)
* [Hadoop Cluster](#Hadoop-cluster)
* [The Hive Project](#Hive-project)
* [Improvements](#improvements)

## Introduction
- This project will help the data analytic team process big data using Apache Hadoop and other tools. 
- What you have learned or evaluated
- Talk about the Hadoop cluster, tools, and Hie project

## Hadoop Cluster
- cluster architecture diagram
    - 1 master and 2 workers nodes
    - HDFS, YARN, Zeppelin, Hive (hive Server, hive metastore, RDBMS), etc.
- Big data tools
  - MapReduce
  - YARN
  - HDFS
  - Hive
  - Zeppelin
- hardware specifications
  - Master Node (2 CPU cores - 12 GB Memory): 1 Node
  - Worker Node (2 CPU Cores - 12 GB Memory): 2 Nodes

## Hive Project
In order to improve performance of Hive queries:
### Improving hive queries
#### Partitions Dataset
- `PARTITION` keyword is used in creating Hive tables, which will divide the dataset into partitions based on the given column(s).
- There two partition types:
  - `Static`: manually partition using specific values in the partition column. This is a good way for loading big data files into Hive table. It is faster than the dynamic partition.
  - `Dynamic`: dynamically partition the dataset into partitions based on values of the partition columns. This is convenience when loading data from another Hive table.   
Note: when mixing static and dynamic, static must come first. 
If there is no static partition, use `set hive.exec.dynamic.partition.mode=nonstrict` to allow fully dynamic partition.

#### Columnar File (Parquet File)
- `STORED AS PARQUET` is used when creating a hive table to have to dataset stored as columnar files. 
- Columnar files are files with parquet extension, a parquet file size is a lot smaller than a normal text file and its processing time is also a lot faster than text file.

### Zeppelin Notebook screenshots
![Zeppelin Notebook page 1](./assets/zeppelin_notebook1.png)
![Zeppelin Notebook page 2](./assets/zeppelin_notebook2.png)

## Improvements
- at least three improvements