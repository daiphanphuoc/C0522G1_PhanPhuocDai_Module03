create database `student_management`;

CREATE TABLE `student` (
    `id` INT NOT NULL,
    `name` VARCHAR(45) DEFAULT NULL,
    `age` INT DEFAULT NULL,
    `country` VARCHAR(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8MB4 COLLATE = UTF8MB4_0900_AI_CI;

CREATE TABLE `teacher` (
    `id` INT NOT NULL,
    `name` VARCHAR(45),
    `age` INT,
    `country` VARCHAR(45),
    PRIMARY KEY (id)
);

CREATE TABLE `class` (
    `id` INT NOT NULL PRIMARY KEY,
    `name` VARCHAR(45)
)