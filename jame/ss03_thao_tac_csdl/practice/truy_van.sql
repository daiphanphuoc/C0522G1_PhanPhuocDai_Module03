use quan_ly_sinh_vien;
/*Hiển thị danh sách tất cả các học viên*/
SELECT 
    *
FROM
    student;
/*Hiển thị danh sách các học viên đang theo học.*/
SELECT 
    *
FROM
    student
        INNER JOIN
    class ON class.class_id = student.class_id
WHERE
    student.status = TRUE;
/*Hiển thị danh sách các môn học có thời gian học nhỏ hơn 10 giờ.*/
SELECT 
    *
FROM
    `subject`
        INNER JOIN
    mark ON mark.sub_id = `subject`.sub_id
WHERE
    exam_times < 10;

/*Hiển thị danh sách học viên lớp A1*/
SELECT 
    *
FROM
    class
        INNER JOIN
    student ON student.class_id = class.class_id
WHERE
    class_name = 'A1';
/*
Hiển thị điểm môn CF của các học viên.*/

SELECT 
    mark.mark
FROM
    mark,
    subject
WHERE
    subject.sub_id = mark.sub_id
        AND sub_name = 'CF';