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

DELIMITER $$

CREATE PROCEDURE get_user_by_id(IN user_id INT)
BEGIN
    SELECT users.name, users.email, users.country
    FROM users
    where users.id = user_id;
    END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE get_user_all()
BEGIN
    SELECT users.name, users.email, users.country
    FROM users;
    END$$
DELIMITER ;

  DELIMITER $$
  CREATE PROCEDURE delete_user(in user_id int)
  BEGIN
  delete FROM users where id=user_id;
  end $$
  DELIMITER ;

DELIMITER $$
CREATE PROCEDURE insert_user(
IN user_name varchar(50),
IN user_email varchar(50),
IN user_country varchar(50)
)
BEGIN
    INSERT INTO users(name, email, country) VALUES(user_name, user_email, user_country);
    END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE update_user(
IN user_id int,
IN user_name varchar(50),
IN user_email varchar(50),
IN user_country varchar(50)
)
BEGIN
    update users
    set name = user_name,
    email = user_email,
    country = user_country
    where id = user_id;
    END$$
DELIMITER ;