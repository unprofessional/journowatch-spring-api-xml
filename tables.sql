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
	status integer NOT NULL DEFAULT (-1), -- -1 is unknown, 0 is inactive, 1 is active
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
	status integer NOT NULL DEFAULT (-1), -- -1 is unknown, 0 is inactive, 1 is active
	overallscore integer NOT NULL DEFAULT -1,
	bio character varying(2048)
)
WITH (
	OIDS=FALSE
);

-- Table: journoratings
DROP TABLE journoratings;
CREATE TABLE journoratings
(
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	"timestamp" timestamp with time zone NOT NULL DEFAULT now(),
	status integer NOT NULL DEFAULT (-1), -- user banned/inactive/deleted, etc
	owneruuid uuid,
	journouuid uuid,
	score integer NOT NULL DEFAULT (-1),
	headline character varying(128),
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
	journouuid uuid,
	venueuuid uuid,
	uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
	type integer NOT NULL DEFAULT (-1),
	CONSTRAINT relationships_pkey PRIMARY KEY (uuid)
)
WITH (
	OIDS=FALSE
);

-- Table: articles

DROP TABLE articles;
CREATE TABLE articles
(
  uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
  authorname character varying(128),
  title character varying(128),
  brief character varying(1024),
  datepublished date,
  overallscore integer NOT NULL DEFAULT (-1),
  CONSTRAINT articles_pkey PRIMARY KEY (uuid)
)
WITH (
  OIDS=FALSE
);