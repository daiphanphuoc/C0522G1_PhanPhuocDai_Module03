 /*
25.	Tạo Trigger có tên tr_xoa_hop_dong khi xóa bản ghi trong bảng hop_dong 
thì hiển thị tổng số lượng bản ghi còn lại có trong bảng hop_dong ra giao diện console của database.
Lưu ý: Đối với MySQL thì sử dụng SIGNAL hoặc ghi log thay cho việc ghi ở console.*/
CREATE TABLE hop_dong_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ma_hop_dong INT ,
    ngay_lam_hop_dong DATETIME,
    ngay_ket_thuc DATETIME,
    tien_dat_coc DOUBLE,
    ma_nhan_vien INT,
    ma_khach_hang INT,
    ma_dich_vu INT,
    is_delete BIT DEFAULT 0,
    ngay_cap_nhat DATE,
    so_luong_hop_dong int
);

drop  trigger  tr_xoa_hop_dong;
delimiter //
create trigger tr_xoa_hop_dong after update on hop_dong for each row
begin
declare c int ;
set c =0;
set c = (select count(ma_hop_dong) from hop_dong where is_delete=0);
select c;
insert into hop_dong_log(ma_hop_dong,ngay_lam_hop_dong,ngay_ket_thuc,tien_dat_coc, ma_nhan_vien,ma_khach_hang, ma_dich_vu,is_delete,ngay_cap_nhat,so_luong_hop_dong)
values(old.ma_hop_dong,old.ngay_lam_hop_dong,old.ngay_ket_thuc,old.tien_dat_coc,old. ma_nhan_vien,old.ma_khach_hang, old.ma_dich_vu,old.is_delete,now(),c);
end //
delimiter ;
set sql_safe_updates=0;
update hop_dong 
set is_delete =1
where ma_hop_dong = 5;

select so_luong_hop_dong from hop_dong_log order by ngay_cap_nhat desc limit 2;