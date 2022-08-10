use quan_ly_sinh_vien;
/*Hiển thị danh sách tất cả các học viên*/
select * from student;
/*Hiển thị danh sách các học viên đang theo học.*/
select * 
from student
 inner join class on class.class_id=student.class_id
where student.status=true;
/*Hiển thị danh sách các môn học có thời gian học nhỏ hơn 10 giờ.*/
select * 
from `subject` inner join mark on mark.sub_id=`subject`.sub_id
where exam_times <10;

/*Hiển thị danh sách học viên lớp A1*/
select *
from class inner join student on student.class_id=class.class_id
where class_name='A1';
/*
Hiển thị điểm môn CF của các học viên.*/

select mark.mark
from mark,subject
where subject.sub_id=mark.sub_id and sub_name='CF';