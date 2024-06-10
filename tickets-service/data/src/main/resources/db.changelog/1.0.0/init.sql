create table stations
(
    id   int primary key generated always as identity,
    name varchar(50) not null
);

create table orders
(
    id   int primary key generated always as identity,
    user_id int not null,
    from_station_id int not null references stations(id),
    to_station_id int not null references stations(id),
    status int not null,
    created timestamp default current_timestamp
)