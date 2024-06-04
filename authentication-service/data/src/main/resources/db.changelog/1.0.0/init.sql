create table users
(
    id       int primary key generated always as identity,
    nickname varchar(50)         not null,
    email    varchar(100) unique not null,
    password varchar(255)        not null,
    created  timestamp default current_timestamp
);