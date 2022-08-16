
use quan_ly_ban_hang;
INSERT INTO customer(customer_name,customer_age)
VALUES
('Minh Quan',10),
('Ngoc Oanh',20),
('Hong Ha',50);

INSERT INTO `order`(customer_id,order_date,order_price)
VALUES 
(1,'2006/3/21',null),
(2,'2006/3/23',null),
(1,'2006/3/16',null);

INSERT INTO product( product_name,product_price)
VALUES 
('May Giat',3),
('Tu Lanh',5),
('Dieu Hoa',7),
('Quat',1),
('Bep Dien',2);

INSERT INTO order_detail
VALUES 
(1,1,3),
(1,3,7),
(1,4,2),
(2,1,1),
(3,1,8),
(2,5,4),
(2,3,3);
/*Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order*/
select order_id , order_date, order_price
from `order`;

/*Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách*/
 SELECT 
    customer.customer_id AS ID,
    customer_name AS `Name`,
    customer_age AS Age,
    product_name AS Product,
    product_price AS Price
FROM
    customer
        INNER JOIN
    `order` ON customer.customer_id = `order`.customer_id
        INNER JOIN
    order_detail ON order_detail.order_id = `order`.order_id
        INNER JOIN
    product ON product.product_id = order_detail.product_id;
 
 /*
Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào*/
SELECT 
    *
FROM
    customer
WHERE
    customer.customer_id NOT IN (SELECT DISTINCT
            customer_id
        FROM
            `order`);

/*
Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn 
(giá một hóa đơn được tính bằng tổng giá bán
 của từng loại mặt hàng xuất hiện trong hóa đơn.
 Giá bán của từng loại được tính = odQTY*pPrice)*/
 SELECT 
    `order`.order_id,
    order_date,
    IFNULL(product_price, 0) * IFNULL(od_qty, 0) AS order_price
FROM
    `order`
        INNER JOIN
    order_detail ON `order`.order_id = order_detail.order_id
        INNER JOIN
    product ON order_detail.product_id = product.product_id
GROUP BY `order`.order_id;
 