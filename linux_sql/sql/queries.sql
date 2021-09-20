-----------1-------
--Group hosts by CPU number and sort by their memory size in descending order
-- (within each cpu_number group)
select cpu_number, id, total_mem
from host_info
group by cpu_number, total_mem, id
order by total_mem desc ;

-----------2-------
-- making a function so round timestamp into 5 minutes interval
-- date_truc return a date type at 'hour' precision
-- date_part return the amount of minute of the date (integer type)
-- ::int means comverting into integer
-- The manin idea of the below return line is:
--      find the multiples of 5 based on the minutes then convert into date type
--      with INTERVAL of 5 minutes
--      after that, concatenate the minutes with the date of hour precision to have a full date
CREATE OR REPLACE FUNCTION round5(ts timestamp) RETURNS timestamp
    AS $$
    BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
    END;
    $$
LANGUAGE PLPGSQL;

--Average used memory in percentage over 5 mins interval for each host.
-- (used memory = total memory - free memory).
select host_id,
       hostname,
       round5(host_usage.timestamp) as rounded_timestamp,
       round(avg((total_mem - memory_free)*100/total_mem),2) as avg_used_mem_percentage
from host_usage
    inner join host_info on host_info.id = host_usage.host_id
group by rounded_timestamp, host_id, hostname;

-----------3-------
-- detect host failure by checking cron task data points every 5 minutes interval
-- Idea of below query: pick out all host_id(s) having less than 3 data points per 5 minutes
-- note: reused the round5 function from question 2
select host_id,
       round5(timestamp) as ts,
       count(timestamp) as num_data_points
from host_usage
group by ts, host_id
having count(timestamp) < 3;