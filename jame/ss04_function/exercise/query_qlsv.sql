use quan_ly_sinh_vien;

/*Hiển thị tất cả các thông tin môn học (bảng subject) có credit lớn nhất.*/
select *
from `subject`
order by credit desc
limit 1;

/*
Hiển thị các thông tin môn học có điểm thi lớn nhất.*/
select *
from `subject`
where subject.sub_id in( select sub_id from mark order by mark.mark desc )
limit 1;


/*Hiển thị các thông tin sinh viên và điểm trung bình của mỗi sinh viên, xếp hạng theo thứ tự điểm giảm dần*/

select student.*, avg(mark)
from student inner join mark on student.student_id = mark.student_id
group by student_id
order by avg(mark) desc;