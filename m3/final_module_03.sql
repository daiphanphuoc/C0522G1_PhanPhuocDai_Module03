create database final_module_03;

use final_module_03;

create table o(
id int primary key auto_increment,
name varchar(50),
is_delete bit default 0
);

select * from o where  is_delete = 0 ;
select * from o where is_delete = 0 and id = ?;
select * from o where is_delete = 0 and id  not in (select id from o where is_delete = 0 and id = ? );
select * from o where name like ?;

insert into o(name) values(?);
update o set name = ? where is_delete = 0 and id =?;
update o set is_delete = 1 where is_delete = 0 and id =?;