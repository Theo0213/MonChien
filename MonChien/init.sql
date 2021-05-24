
create database cda_21013;

drop table if exists client;

create table if not exists client(
  id numeric NOT NULL,
    nom character varying(50),
    prenom character varying(50),
    date_naissance timestamp without time zone,
    email character varying(200),
    adresse character varying(2000),
    password character varying(50)
);
