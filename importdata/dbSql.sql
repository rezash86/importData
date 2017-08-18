CREATE DATABASE "importDb"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'English_United States.1252'
       LC_CTYPE = 'English_United States.1252'
       CONNECTION LIMIT = -1;
       
       


CREATE TABLE miso_market_price_five_minutes
(
	originaldatetime timestamp without time zone NOT NULL,
	hubname VARCHAR(50) NOT NULL,
	lmp numeric,
	loss numeric,
	congestion numeric,
	CONSTRAINT pk_miso_market_price_five_minutes
	PRIMARY KEY (originaldatetime, hubname)
);