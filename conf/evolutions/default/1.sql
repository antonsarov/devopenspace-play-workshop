# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table item (
  id                        bigint not null,
  name                      varchar(255),
  image                     varchar(255),
  price                     decimal(38),
  constraint pk_item primary key (id))
;

create sequence item_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists item;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists item_seq;

