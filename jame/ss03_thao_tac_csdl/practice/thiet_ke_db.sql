create database if not exists c0522g1;
use c0522g1;
create table class_type(
id int primary key auto_increment,
name varchar(50)
);
create table class(
id int primary key auto_increment,
name varchar(50),
class_type_id int,
foreign key(class_type_id) references class_type(id)
);
create table jame (
`account` varchar(50),
`password` varchar(25),
primary key (`account`)
);
create table student(
 id int primary key auto_increment,
 name varchar(50),
 birthday date,
 gender boolean,
 email varchar(50),
 point int,
 `account` varchar(50),
 class_id int,
 foreign key(`account`) references jame(`account`),
 foreign key(class_id) references class(id)
 )
;
create table instructor(
 id int primary key auto_increment,
 name varchar(50),
 birthday date,
 salary float
);

create table instructor_class(
class_id int,
instructor_id int,
start_time date,
primary key(class_id,instructor_id),
foreign key(class_id) references class(id),
foreign key(instructor_id) references instructor(id)
);


-- Nhập thông tin cho bảng 
insert into class_type (name) values ('full time'), ('part time'),('orther');

insert into class (name,class_type_id) values ('c1121g1',1), ('c1221g1',1),('a0821i1',2),('a0921i1',2);

insert into jame(`account`,`password`)
 values('cunn','12345'),('chunglh','12345'),('hoanhh','12345'),('dungd','12345'),('huynhtd','12345'),
 ('hainm','12345'),('namtv','12345'),('hieuvm','12345'),('kynx','12345'),('vulm','12345');

insert into jame(`account`,`password`)
 values('anv','12345'),('bnv','12345');

 
insert into instructor(`name`,birthday, salary)
 values('tran van chanh','1985-02-03',100),('tran minh chien','1985-02-03',200),('vu thanh tien','1985-02-03',300);
insert into instructor(`name`,birthday, salary)
 values('tran van nam','1989-12-12',100);

 
 insert into student(`name`,birthday, gender,`point`, class_id,`account`) 
 values ('nguyen ngoc cu','1981-12-12',1,8,1,'cunn'),('le hai chung','1981-12-12',1,5,1,'chunglh'),
 ('hoang huu hoan','1990-12-12',1,6,2,'hoanhh'),('dau dung','1987-12-12',1,8,1,'dungd'),
 ('ta dinh huynh','1981-12-12',1,7,2,'huynhtd'),('nguyen minh hai','1987-12-12',1,9,1,'hainm'),
 ('tran van nam','1989-12-12',1,4,2,'namtv'),('vo minh hieu','1981-12-12',1,3,1,'hieuvm'),
 ('le xuan ky','1981-12-12',1,7,2,'kynx'),('le minh vu','1981-12-12',1,7,1,'vulm');

 insert into student(`name`,birthday, gender,`point`, class_id,`account`) 
 values ('nguyen van a','1981-12-12',1,8,null,'anv'),('tran van b','1981-12-12',1,5,null,'bnv');

 insert into instructor_class(class_id,instructor_id) values (1,1),(1,2),(2,1),(2,2),(3,1),(3,2);
 
 
 /*Lấy ra thông tin các học viên, và cho biết các học viên đang theo học lớp nào*/
  select student.id, student.`name`, birthday, gender, email, `point`, `account`, class_id, class.`name`
 from student 
  join class on class.id=student.class_id;
  
  
 /*Lấy ra thông tin các học viên, và cho biết các học viên đang theo học lớp nào 
 và cả các bạn đã đăng ký nhưng chưa có lớp học.*/
 select student.id, student.`name`, birthday, gender, email, `point`, `account`, class_id, class.`name`
 from student 
 left join class on class.id=student.class_id;
 

/*Lấy thông tin của các học viên tên 'Tien' và 'Toan’.*/
select *
from student
where `name` like '%nam' or `name` like '%Toan';

/*5. Lấy ra học viên có điểm lớn hơn 5 .*/
select *
from student 
where `point` >5;

/*6. Lấy ra học viên có họ là “nguyen”*/
select *
from student 
where `name` like 'nguyen%';


/*Thông kế số lượng học sinh theo từng loại điểm.*/
select `point`, count(id) as so_luong
from student 
group by `point`;


/*8 . Thông kế số lượng học sinh theo điểm và điểm phải lớn hơn 5*/
select `point`, count(id) as so_luong
from student 
where `point`>5
group by `point`;

/*9. Thông kế số lượng học sinh theo điểm lớn hơn 5 và chỉ hiện thị với số lượng>=2*/
select `point`, count(id) as so_luong
from student 
where `point`>5 
group by `point`
having count(id)>=2;

/*10. Lấy ra danh sách học viên của lớp c1121g1 và sắp xếp tên học viên theo alphabet.*/
select *
from student inner join class on student.class_id=class.id
where class.`name` ='c1121g1'
order by student.`name` asc;

