#!/bin/bash

#Usage note: bash host_info.sh [psql_host] [psql_port] [db_name] [psql_user] [psql_password]
#Example: bash host_info.sh localhost 5432 host_agent postgres password

#Get CLI args
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

if [ $# -ne 5 ]
then
  echo "Not enough 5 parameters"
  exit 1
fi

#Parsing host hardware specifications
lscpu_out=`lscpu`
meminfo_out=`cat /proc/meminfo`

cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
hostname=$(hostname -f)
cpu_architecture=$(echo "$lscpu_out"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$lscpu_out"  | egrep "^Model name:" | awk '{$1="";$2="";print}' | xargs)
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache_temp=$(echo "$lscpu_out"  | egrep "^L2 cache:" | awk '{print $3}' | xargs)
l2_cache=${l2_cache_temp::-1}
total_mem=$(echo "$meminfo_out" | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(date +"%Y-%m-%d %H:%M:%S")

insert_string="INSERT INTO host_info(
        hostname,cpu_number,cpu_architecture,
        cpu_model,cpu_mhz,l2_cache,total_mem,timestamp)
      VALUES(
        '$hostname','$cpu_number','$cpu_architecture',
        '$cpu_model','$cpu_mhz','$l2_cache','$total_mem','$timestamp'
      )"


export PGPASSWORD=$psql_password
psql -h $psql_host -U $psql_user -p $psql_port -d $db_name -c "$insert_string"


exit $?