CREATE TABLE post (
                      id SERIAL PRIMARY KEY,
                      name TEXT,
                      description TEXT,
                      city_id INT,
                      created DATE
);

DROP TABLE post;