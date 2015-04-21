drop table if exists user;

create table if not exists user (
  id bigint unsigned not null auto_increment,
  username varchar(100) not null,
  password varchar(100) not null,
  role bigint unsigned not null,
  constraint pk_users primary key(id),
  unique(username)
);

insert into user (id, username, password, role) values (1,'admin', '$2a$10$Qq5F41u.Efgj1DMg58NfferED6lXgUrdKzD.Y8.W2zPV3Q7u7A0Wa', 1);
insert into user (id, username, password, role) values (2,'user', '$2a$10$aN0uYzdtiihu6kOV0uFBdOTeXf1BfafnYRsUk8OpqBcIPS4o6h/hm', 0);