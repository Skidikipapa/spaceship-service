CREATE SEQUENCE crew_sequence START 1;

CREATE TABLE IF NOT EXISTS crew (
    id INTEGER PRIMARY KEY DEFAULT nextval('crew_sequence'),
    name VARCHAR (50) NOT NULL,
    lastName VARCHAR (50) NOT NULL,
    rank INTEGER NOT NULL,
    spaceshipId INTEGER NOT NULL,
    CONSTRAINT crew_fk FOREIGN KEY (spaceshipId) REFERENCES spaceship (id)
);

ALTER SEQUENCE crew_sequence OWNED BY crew.id;