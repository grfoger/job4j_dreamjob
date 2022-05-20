CREATE TABLE post (
                      id SERIAL PRIMARY KEY,
                      name TEXT,
                      description TEXT,
                      city_id INT,
                      created DATE
);

CREATE TABLE candidate (
                           id SERIAL PRIMARY KEY,
                           name TEXT,
                           description TEXT,
                           created DATE,
                           file BYTEA
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR ,
                       password TEXT
);

ALTER TABLE users ADD CONSTRAINT email_unique UNIQUE (email);