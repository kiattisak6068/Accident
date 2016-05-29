# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table accident (
  id                            integer auto_increment not null,
  description                   varchar(255),
  lat                           float,
  lng                           float,
  time                          varchar(255),
  date                          datetime(6),
  constraint pk_accident primary key (id)
);

create table employees (
  id                            integer auto_increment not null,
  email                         varchar(255),
  pwd                           varchar(255),
  constraint pk_employees primary key (id)
);

create table users (
  id                            integer auto_increment not null,
  fname                         varchar(255),
  lname                         varchar(255),
  email                         varchar(255),
  pwd                           varchar(255),
  constraint pk_users primary key (id)
);


# --- !Downs

drop table if exists accident;

drop table if exists employees;

drop table if exists users;

