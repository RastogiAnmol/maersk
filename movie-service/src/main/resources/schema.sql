CREATE TABLE movie (
    id   INTEGER  auto_increment    NOT NULL ,
    name VARCHAR(128)               NOT NULL,
    year DATE                       NOT NULL,
    rating INTEGER                  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY movie_name_UNIQUE (name)
);