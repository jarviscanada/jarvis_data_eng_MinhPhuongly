FROM postgres:9.6-alpine
# docker-entrypoint-initdb.d is a dir in postgres where all sql files will be used to initilize the database
COPY sql_ddl/init_db.sql /docker-entrypoint-initdb.d/
COPY sql_ddl/schema.sql /docker-entrypoint-initdb.d/