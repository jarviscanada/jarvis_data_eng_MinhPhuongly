
    docker volume create pgdata-trading
    docker run --name jrvs-trading -e POSTGRES_USER=$PSQL_USER -e POSTGRES_PASSWORD=$PSQL_PASSWORD -d -v pgdata-trading:/var/lib/postgresql/data -p 5432:5432 postgres
    export PGPASSWORD=$PSQL_PASSWORD
    psql -h $PSQL_HOST -U PSQL_USER -f init_db.sql
    psql -h $PSQL_HOST -U PSQL_USER -f schema.sql

    psql -h $PSQL_HOST -U PSQL_USER -d $PSQL_DB
    \dt : list all tables in a DB
    \connect db_name : switch to another db