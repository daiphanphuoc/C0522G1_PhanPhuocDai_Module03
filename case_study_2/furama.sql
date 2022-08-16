CREATE DATABASE IF NOT EXISTS  furama_web;

USE furama_web;

CREATE TABLE IF NOT EXISTS `role` (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(255),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS `user` (
    user_name VARCHAR(255) PRIMARY KEY,
    `password` VARCHAR(255),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS user_role (
    role_id INT,
    user_name VARCHAR(255),
    PRIMARY KEY (role_id , user_name),
    FOREIGN KEY (user_name)
        REFERENCES `user` (user_name),
    FOREIGN KEY (role_id)
        REFERENCES `role` (role_id),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS `position` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS division (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS education_degree (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    date_of_birth DATE NOT NULL,
    id_card VARCHAR(12) NOT NULL UNIQUE,
    salary DOUBLE NOT NULL CHECK (salary >= 3000000),
    phone_number VARCHAR(12) NOT NULL,
    email VARCHAR(45),
    address VARCHAR(50),
    position_id INT,
    FOREIGN KEY (position_id)
        REFERENCES `position` (id),
    education_degree_id INT,
    FOREIGN KEY (education_degree_id)
        REFERENCES education_degree (id),
    division_id INT,
    FOREIGN KEY (division_id)
        REFERENCES division (id),
    user_name VARCHAR(255) UNIQUE,
    FOREIGN KEY (user_name)
        REFERENCES `user` (`user_name`),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS customer_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_type_id INT,
    FOREIGN KEY (customer_type_id)
        REFERENCES customer_type (id),
    `name` VARCHAR(50) NOT NULL,
    gender BIT(1) NOT NULL,
    date_of_birth DATE NOT NULL,
    id_card VARCHAR(12) NOT NULL UNIQUE,
    salary DOUBLE NOT NULL CHECK (salary >= 3000000),
    phone_number VARCHAR(12) NOT NULL,
    email VARCHAR(45),
    address VARCHAR(50),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS facility_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS rent_type (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS facility (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL,
    area INT CHECK (area > 30),
    cost DOUBLE CHECK (cost > 0),
    max_people INT CHECK (max_people BETWEEN 1 AND 19),
    rent_type_id INT,
    FOREIGN KEY (rent_type_id)
        REFERENCES rent_type (id),
    facility_type_id INT,
    FOREIGN KEY (facility_type_id)
        REFERENCES facility_type (id),
    standard_room VARCHAR(45),
    description_other_convenience VARCHAR(45),
    pool_area DOUBLE,
    number_of_floors INT,
    facility_free TEXT,
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS contract (
    id INT AUTO_INCREMENT PRIMARY KEY,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    deposit DOUBLE NOT NULL,
    employee_id INT,
    customer_id INT,
    facility_id INT,
    is_delete BIT DEFAULT 0,
    FOREIGN KEY (employee_id)
        REFERENCES employee (id),
    FOREIGN KEY (customer_id)
        REFERENCES customer (id),
    FOREIGN KEY (facility_id)
        REFERENCES facility (id)
);

CREATE TABLE IF NOT EXISTS attach_facility (
    id INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(45) NOT NULL,
    cost DOUBLE NOT NULL CHECK (cost > 0),
    unit VARCHAR(10) NOT NULL,
    `status` VARCHAR(45),
    is_delete BIT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS contrat_detail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    contract_id INT,
    attach_facility_id INT,
    quantity INT NOT NULL,
    is_delete BIT DEFAULT 0,
    FOREIGN KEY (contract_id)
        REFERENCES contract (id),
    FOREIGN KEY (attach_facility_id)
        REFERENCES attach_facility (id)
);