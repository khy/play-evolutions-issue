# --- !Ups

CREATE TABLE people (
  id bigserial PRIMARY KEY,
  name text NOT NULL
);

# --- !Downs

DROP TABLE IF EXISTS people;
