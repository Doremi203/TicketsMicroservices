create table users
(
    id       int primary key generated always as identity,
    nickname varchar(50)         not null,
    email    varchar(100) unique not null,
    password varchar(255)        not null,
    created  timestamp default current_timestamp
);

create table sessions
(
    id      int primary key generated always as identity,
    user_id int          not null,
    token   varchar(255) not null,
    expires timestamp    not null,
    foreign key (user_id) references users (id)
);
