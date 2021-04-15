DROP TABLE movie
/
DROP TABLE genres
/
DROP TABLE association
/
CREATE TABLE movie(
id INT PRIMARY KEY,
title VARCHAR(25) unique NOT NULL,
release_date DATE,
duration NUMBER(3),
score NUMERIC(3,1)
)
/

CREATE TABLE genres(
id INT PRIMARY KEY,
name VARCHAR(25) unique NOT NULL
)
/

CREATE TABLE association(
id_movie INT references movie(id),
id_genres INT references genres(id)
)