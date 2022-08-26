CREATE DATABASE demo_user;
USE demo_user;

create table users (
 id  int NOT NULL AUTO_INCREMENT,
 name varchar(50) NOT NULL,
 email varchar(50) NOT NULL,
 country varchar(100),
 PRIMARY KEY (id)
);

insert into users(name, email, country) values('Minh','minh@codegym.vn','Viet Nam');
insert into users(name, email, country) values('Kante','kante@che.uk','Kenia');