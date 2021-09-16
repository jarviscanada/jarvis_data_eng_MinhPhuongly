#!/bin/bash

#Usage: bash psql_docker.sh start|stop [db_username] [db_password]

#Get CLI args
to_do=$1
db_username=$2
db_password=$3


#start docker
sudo systemctl status docker || systemctl start docker

#check container status
docker container inspect jrvs-psql
container_status=$?

#User switch case to handle operation options
case $to_do in
	create)
	#check if the container is already created
	if [ $container_status -eq 0 ] 
	then
		echo 'Container already exists'
		exit 1
	fi

	#check number of CLI argsd
	if [ $# -ne 3 ]
	then
		echo 'Create require username and password'
		exit 1
	fi
	
	#Create container
	docker volume create pgdata
	docker run --name jrvs-psql -e POSTGRES_PASSWORD=$db_password -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
	exit $?
	;;
	
	start|stop)
	#check container status; exit 1 if the container has not been created
	if [ $container_status -ne 0 ]
	then
		echo 'Container has not been created'
		exit 1
	fi

	#Start or stop the containe
	docker container $to_do jrvs-psql
	exit $?
	;;

	*)
	echo 'Illigal command'
	echo 'Commands: start|stop|create'
	exit 1
	;;
esac

exit 0

