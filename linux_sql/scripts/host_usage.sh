#!/bin/bash

#Usage note: bash host_usage.sh [psql_host] [psql_port] [db_name] [psql_user] [psql_password]
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

hostname=$(hostname -f)
memory_free_temp=$(echo "$meminfo_out" | egrep "^MemFree:" | awk '{$1="";print}' | xargs)
memory_free=${memory_free_temp::-3}
cpu_idle=$(vmstat | tail -1 | awk '{print $15}' | xargs)
cpu_kernel=$(vmstat | tail -1 | awk '{print $14}' | xargs)
disk_io=$(vmstat -d | tail -1 | awk '{print $10}' | xargs)
disk_available_temp=$(df -BM / | tail -1 | awk '{print $4}')
disk_available=${disk_available_temp::-1}
timestamp=$(date +"%Y-%m-%d %H:%M:%S")



host_id_string="(SELECT id,
              '$timestamp','$memory_free',
              '$cpu_idle','$cpu_kernel',
              '$disk_io','$disk_available' from host_info WHERE hostname='$hostname')";

insert_string="INSERT INTO host_usage(
        host_id,timestamp,memory_free,
        cpu_idle,cpu_kernel,disk_io,disk_available)
        $host_id_string
      "


export PGPASSWORD=$psql_password
psql -h $psql_host -U $psql_user -p $psql_port -d $db_name -c "$insert_string"


exit $?