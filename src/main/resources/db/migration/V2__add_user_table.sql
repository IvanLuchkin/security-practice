drop table if exists test;

create table "user"(
  id serial primary key,
  username text unique not null,
  password text not null
);