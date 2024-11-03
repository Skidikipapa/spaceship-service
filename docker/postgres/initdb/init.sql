CREATE SCHEMA IF NOT EXISTS spaceship;

ALTER DATABASE spaceship_data SET search_path TO spaceship;

CREATE SEQUENCE spaceship_sequence START 1;

CREATE TABLE IF NOT EXISTS spaceship (
    id INTEGER PRIMARY KEY DEFAULT nextval('spaceship_sequence'),
    name VARCHAR (50) NOT NULL,
    capacity INTEGER NOT NULL,
    fuel_consumption NUMERIC NOT NULL
);

ALTER SEQUENCE spaceship_sequence OWNED BY spaceship.id;

INSERT INTO spaceship (name, capacity, fuel_consumption) VALUES ('x-wing', 1, 100.0);
INSERT INTO spaceship (name, capacity, fuel_consumption) VALUES ('endurance', 1000, 7879.23);
INSERT INTO spaceship (name, capacity, fuel_consumption) VALUES ('nostromo', 8, 5400.11);
INSERT INTO spaceship (name, capacity, fuel_consumption) VALUES ('millennium falcon', 6, 200.41);
INSERT INTO spaceship (name, capacity, fuel_consumption) VALUES ('sulaco', 21, 400.01);
