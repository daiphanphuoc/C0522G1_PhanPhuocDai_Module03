select employee.*, position.name as position_name, division.name as division_name, education_degree.name as education_degree_name
from employee 
join position on position_id=position.id
join division on division_id=division.id
join education_degree on education_degree_id=education_degree.id
where employee.is_delete = 0 and employee.id = 1;

update  employee set employee_name =?, employee_birthday = ?,  employee_id_card = ?, employee_salary =?, employee_phone =? , employee_email = ?, employee_address =?, position_id = ?, education_degree_id = ?, division_id = ?, gender = ? where id = ?;