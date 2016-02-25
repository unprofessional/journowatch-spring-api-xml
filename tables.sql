/* Ensure the OSSP extension for UUID generation is installed */
-- CREATE EXTENSION "uuid-ossp";

-- Table: users
DROP TABLE users;
CREATE TABLE users
(
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	username character varying(16) NOT NULL,
	email character varying(64),
	firstname character varying(64),
	lastname character varying(64),
	role integer, -- integer???
	password character varying(64) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (uuid)
)
WITH (
	OIDS=FALSE
);

-- Table: journos
DROP TABLE journos;
CREATE TABLE journos
(
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	fullname character varying(16) NOT NULL,
	status character varying(32), -- integer NOT NULL DEFAULT -1, ???
	overallscore integer NOT NULL DEFAULT (-1),
	bio character varying(2048),
	CONSTRAINT journos_pkey PRIMARY KEY (uuid)
)
WITH (
	OIDS=FALSE
);

-- Table: venues
DROP TABLE venues;
CREATE TABLE venues
(
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	name character varying(16) NOT NULL,
	status character varying(32), -- integer NOT NULL DEFAULT -1, ???
	overallscore integer NOT NULL DEFAULT -1,
	bio character varying(2048)
)
WITH (
	OIDS=FALSE
);

-- Table: ratings
DROP TABLE ratings;
CREATE TABLE ratings
(
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	"timestamp" timestamp with time zone NOT NULL DEFAULT now(),
	owneruuid uuid,
	journouuid uuid,
	score integer NOT NULL DEFAULT (-1),
	comment character varying(1024),
	CONSTRAINT ratings_pkey PRIMARY KEY (uuid)
)
WITH (
	OIDS=FALSE
);

-- Table: partnerships
DROP TABLE partnerships;
CREATE TABLE partnerships
(
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	journouuid uuid,
	venueuuid uuid
)
WITH (
	OIDS=FALSE
);