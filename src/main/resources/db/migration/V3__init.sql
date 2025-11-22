create table users
(
    id       int generated always as identity primary key,
    name     varchar(50) not null,
    lastname varchar(50) not null,
    age      int   not null,
    age_category varchar(50) not null,
    email varchar(50) not null
);