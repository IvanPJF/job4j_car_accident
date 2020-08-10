create table accident_type
(
    id   serial,
    name varchar(200),
    primary key (id)
);

create table rules
(
    id   serial,
    name varchar(200),
    primary key (id)
);

create table accident
(
    id               serial,
    name             varchar(2000),
    text             varchar(2000),
    address          varchar(2000),
    accident_type_id integer,
    primary key (id),
    foreign key (accident_type_id) references accident_type (id)
);

create table accident_rule
(
    accident_id integer,
    rule_id     integer,
    primary key (accident_id, rule_id),
    foreign key (accident_id) references accident (id),
    foreign key (rule_id) references rules (id)
);