use quan_ly_sinh_vien;
/*Hiển thị tất cả các sinh viên có tên bắt đầu bảng ký tự ‘h’*/
SELECT 
    *
FROM
    student
WHERE
    `student_name` LIKE 'h%';


/*Hiển thị các thông tin lớp học có thời gian bắt đầu vào tháng 12.*/
SELECT 
    *
FROM
    class
WHERE
    MONTH(start_date) = 12;


/*Hiển thị tất cả các thông tin môn học có credit trong khoảng từ 3-5.*/
SELECT 
    *
FROM
    subject
WHERE
    credit BETWEEN 3 AND 5;

/*Thay đổi mã lớp(ClassID) của sinh viên có tên ‘Hung’ là 2.*/
-- set update_
UPDATE student 
SET 
    class_id = 2
WHERE
    student.student_name = 'Hung';

/*
Hiển thị các thông tin: StudentName, SubName, Mark. 
Dữ liệu sắp xếp theo điểm thi (mark) giảm dần.
 nếu trùng sắp theo tên tăng dần.*/
 
 SELECT 
    student_name, sub_name, mark.mark
FROM
    student
        INNER JOIN
    mark ON student.student_id = mark.student_id
        INNER JOIN
    subject ON mark.sub_id = subject.sub_id
ORDER BY mark.mark DESC , student_name ASC;