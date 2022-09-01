use furama_web;

select customer.*,customer_type.name as customer_type_name 
from customer 
join customer_type on customer.customer_type_id = customer_type.id 
where customer.is_delete = 0;

select customer.*, customer_type.name as customer_type_name 
from customer join customer_type on customer.customer_type_id = customer_type.id 
            where customer.is_delete = 0 and customer.name like '%Nhi%' and customer.address like '%L%' 
            and customer_type.name like '%S%';

update   customer  set is_delete =1 where id =1;

UPDATE customer 
SET 
    customer_type_id = 1,
    name = 'Đại',
    gender = 1,
    date_of_birth = '2002-2-2',
    id_card = '111111111111',
    phone_number = '0389993835',
    email = 'dai@gmail.com',
    address = 'Quảng Nam'
WHERE
    id = 1 AND is_delete = 0;