CREATE TABLE candidate (
                      id SERIAL PRIMARY KEY,
                      name TEXT,
                      description TEXT,
                      created DATE,
                      file BYTEA
);

DROP TABLE candidate;

DELETE FROM candidate;

SELECT * FROM candidate;

ALTER TABLE candidate ADD COLUMN file BYTEA;
ALTER TABLE candidate DROP COLUMN file;