create database student_management;

CREATE TABLE student (
    id INT ,
    name VARCHAR(45) DEFAULT NULL,
    age INT DEFAULT NULL,
    country VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (id)
) ;

CREATE TABLE teacher (
    id INT NOT NULL,
    name VARCHAR(45),
    age INT,
    country VARCHAR(45),
    PRIMARY KEY (id)
);

CREATE TABLE class (
    id INT NOT NULL PRIMARY KEY,
    `name` VARCHAR(45)
)