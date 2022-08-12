create database demo;
use demo;
CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_code VARCHAR(20),
    product_name VARCHAR(50),
    product_price DOUBLE,
    product_amount INT,
    product_description TEXT,
    product_status BIT DEFAULT 1
);
 insert into products(product_code,product_name,product_price,product_amount,product_description)
 values
 ('1111','bánh',4000,3,'ngon'),
 ('1112','kẹo',40300,3,'ngon'),
 ('1113','muối',4000,3,'ngon'),
 ('1114','lap top',4333000,3,'rẻ'),
 ('1115','chuột',411000,3,'đắt'),
 ('1116','bàn phím',411000,3,'ngon'),
 ('1117','xe máy',40000000,3,'trung'),
 ('1118','ô tô',4000000000,3,'siêu xe'),
 ('1119','mì',4000,3,'ngon'),
 ('1121','nước ngọt',10000,3,'ngon'),
 ('1122','bia',14000,3,'ngon'),
 ('1123','rượu',40000,3,'ngon');
/*
Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
So sánh câu truy vấn trước và sau khi tạo index
*/
explain select * from products;
alter table products add  unique index code_index(product_code);
alter table products add  unique index nam_index(product_name);
alter table products add   index price_index(product_price) ;

explain select * from products where product_code ='1112';

alter table products add   index product_index(product_name,product_price);

alter table products drop index product_index;

explain select * from products  where product_name  ='xe máy'and product_price >1000000 ;

/*Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
Tiến hành sửa đổi view
Tiến hành xoá view*/
CREATE VIEW w_product AS
    SELECT product_code, product_name, product_price, product_status
    FROM products;

SELECT *
FROM w_product;

insert into w_product
values('1124','rượu nhất',50000,'ngon');

UPDATE w_product 
SET product_name = 'rượu nhì'
WHERE product_code = '1124';

SELECT *
FROM products;
    
DELETE FROM w_product 
WHERE product_code = '1124';

drop view w_product;

/*Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product*/
DELIMITER //
	create procedure p_all_products()
		begin
			select * from products;
        end //
DELIMITER ;
 call p_all_products;
/*Tạo store procedure thêm một sản phẩm mới*/
DELIMITER //
create procedure p_insert_products(in `code` varchar(20),in `name` varchar(50), in price double,in  amount int, in `description` TEXT)
begin 
 insert into products(product_code,product_name,product_price,product_amount,product_description)
 values(`code`,`name`,price,amount,`description`);
end //
DELIMITER ;
call p_insert_products('2222','heo',3000000,2,'Béo');
/*Tạo store procedure sửa thông tin sản phẩm theo id*/
DELIMITER //
create procedure p_update_products(in product_id int ,in `code` varchar(20),in `name` varchar(50), in price double,in  amount int, in `description` TEXT)
begin 
 update  products
 set product_code=`code`,
 product_name=`name`,
 product_price=price,
 product_amount=amount,
 product_description= `description`
 where id=product_id;
end //
DELIMITER ;

select * from products;
call p_update_products(14,'2122','heo quay',30010000,2,'Béo');
/*Tạo store procedure xoá sản phẩm theo id*/
Delimiter //
	create procedure p_delete_by_id(in p_id int)
		begin
			delete from products where id=p_id;
		end //
delimiter ; 
