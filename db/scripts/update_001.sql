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