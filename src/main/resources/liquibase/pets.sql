CREATE TABLE cats
(
    id       BIGINT generated by default as identity primary key,
    nickname TEXT   NOT NULL,
    breed    TEXT   NOT NULL,
    age      INTEGER NOT NULL,
    message  TEXT   NOT NULL
);

CREATE TABLE dogs
(
    id       BIGINT generated by default as identity primary key,
    nickname TEXT   NOT NULL,
    breed    TEXT   NOT NULL,
    age      INTEGER NOT NULL,
    message  TEXT   NOT NULL


)