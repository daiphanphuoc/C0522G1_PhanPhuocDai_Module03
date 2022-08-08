create database `student_management`;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

create table `Teacher`(
	id int not null,
    name varchar(45),
    age int ,
    country varchar(45),
    primary key (id)
);

create table `Class`(
	id int not null primary key,
    name varchar(45)
)