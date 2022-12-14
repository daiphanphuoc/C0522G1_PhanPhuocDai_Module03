create database quan_ly_ban_hang;

use quan_ly_ban_hang;

CREATE TABLE customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(50),
    customer_age INT
);

CREATE TABLE product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(40),
    product_price DOUBLE
);

CREATE TABLE `order` (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_date DATE,
    order_price DOUBLE,
    FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id)
);

CREATE TABLE order_detail (
    order_id INT,
    product_id INT,
    od_qty INT,
    PRIMARY KEY (order_id , product_id),
    FOREIGN KEY (product_id)
        REFERENCES product (product_id),
    FOREIGN KEY (order_id)
        REFERENCES `order` (order_id)
);
