USE quan_ly_sinh_vien;

SELECT address, COUNT(student_id) AS 'Số lượng học viên'
FROM student
GROUP BY address;

SELECT 
    S.student_id, S.student_name, AVG(mark)
FROM
    student S
        JOIN
    mark M ON S.student_id = M.student_id
GROUP BY S.student_id , S.student_name;


SELECT 
    S.student_id, S.student_name, AVG(mark)
FROM
    student S
        JOIN
    mark M ON S.student_id = M.student_id
GROUP BY S.student_id , S.student_name
HAVING AVG(mark) > 15;

SELECT 
    S.student_id, S.student_name, AVG(mark)
FROM
    student S
        JOIN
    mark M ON S.student_id = M.student_id
GROUP BY S.student_id , S.student_name
ORDER BY AVG(mark) DESC , student_name ASC
LIMIT 1;