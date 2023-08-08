DROP SCHEMA IF EXISTS sep2reexam_database CASCADE
CREATE schema sep2reexam_database;
SET schema 'sep2reexam_database';

CREATE TABLE menu (
    id SERIAL PRIMARY KEY,
    food VARCHAR(255),
    price DECIMAL(10,2)
);

CREATE TABLE screen (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE movie (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    start_time TIME,
    end_time TIME,
    date DATE,
    length INTEGER,
    genre VARCHAR(255),
    screen_id INTEGER REFERENCES screen(id)
);

CREATE TABLE seat (
    id SERIAL PRIMARY KEY,
    row VARCHAR(10),
    number INTEGER,
    screen_id INTEGER REFERENCES screen(id)
);

CREATE TABLE ticket (
    id SERIAL PRIMARY KEY,
    seat_id INTEGER REFERENCES seat(id),
    movie_id INTEGER REFERENCES movie(id),
    menu_id INTEGER REFERENCES menu(id)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    administrator BOOLEAN DEFAULT false
);