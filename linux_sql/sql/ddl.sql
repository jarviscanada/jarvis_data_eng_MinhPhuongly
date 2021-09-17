\c host_agent;

CREATE TABLE IF NOT EXISTS host_info(
	id	SERIAL NOT NULL,
	hostname	VARCHAR NOT NULL,
	cpu_number	SMALLINT NOT NULL,
	cpu_architecture VARCHAR NOT NULL,
	cpu_model	VARCHAR NOT NULL,
	cpu_mhz		DECIMAL(9,3) NOT NULL,
	l2_cache	DECIMAL(6,2) NOT NULL,
	total_mem	DECIMAL(9,2) NOT NULL,
	timestamp	DATE NOT NULL,
	CONSTRAINT PK_host_info PRIMARY KEY(id),
	CONSTRAINT UC_host_info_hostname UNIQUE(hostname)
);

CREATE TABLE IF NOT EXISTS host_usage(
	timestamp	DATE NOT NULL,
	host_id		INTEGER NOT NULL,
	memory_free	DECIMAL(12,2) NOT NULL,
	cpu_idle	DECIMAL(5,2) NOT NULL,
	cpu_kernel	DECIMAL(5,2) NOT NULL,
	disk_io		DECIMAL(5,2) NOT NULL,
	disk_available	DECIMAL(9,2) NOT NULL,
	CONSTRAINT PK_host_usage PRIMARY KEY(timestamp, host_id),
	CONSTRAINT FK_host_usage FOREIGN KEY(host_id) 
		REFERENCES host_info(id)
);


