drop table if exists authorities cascade;
drop table if exists users cascade;

create table authorities
(
    id        serial,
    authority varchar(50) not null unique,
    primary key (id)
);

create table users
(
    id           serial,
    username     varchar(50)  not null unique,
    password     varchar(100) not null,
    enabled      boolean default true,
    authority_id integer      not null,
    foreign key (authority_id) references authorities (id)
);

insert into authorities (authority)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, authority_id)
values ('root', '$2a$10$UpPe7xvv6mNyg1aLNZYB8uwoYz4AfQqGo8im/CiYO7RxrsY09Pc3S',
        (select id from authorities where authority = 'ROLE_ADMIN'));
-- password = root