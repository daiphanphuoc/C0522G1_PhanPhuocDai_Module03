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

create table permision(
id int primary key,
name varchar(50)
);

insert into Permision(id, name) values(1, 'add');
insert into Permision(id, name) values(2, 'edit');
insert into Permision(id, name) values(3, 'delete');
insert into Permision(id, name) values(4, 'view');

create table user_permision(
permision_id int,
user_id int,
foreign key (permision_id) references permision(id),
foreign key (user_id) references users(id),
primary key(user_id,permision_id)
);