create table if not exists companies
(
    id   serial primary key,
    name varchar(64) not null unique
);

create table if not exists company_locales
(
    company_id  int references companies (id),
    lang        varchar(2),
    description varchar(255) not null,
    primary key (company_id, lang)
);

create table if not exists users
(
    id         serial primary key,
    username   varchar(64) not null unique,
    birth_date date,
    firstname  varchar(64),
    lastname   varchar(64),
    role       varchar(32),
    company_id int references companies (id)
);

create table if not exists payments
(
    id          serial primary key,
    amount      int not null,
    receiver_id int not null references users (id)
);

create table if not exists chats
(
    id   serial primary key,
    name varchar(64) not null
);

create table if not exists user_chats
(
    id      serial primary key,
    user_id int references users (id),
    chat_id int references chats (id)
);