/*21.	Tạo khung nhìn có tên là v_nhan_vien để lấy được thông tin của tất cả các nhân viên
 có địa chỉ là “Hải Châu” và đã từng lập hợp đồng cho một hoặc nhiều khách hàng bất kì với ngày lập hợp đồng là “12/12/2019”.
*/
create view v_nhan_vien as
select nv.*
from nhan_vien nv
inner join hop_dong on hop_dong.ma_nhan_vien=nv.ma_nhan_vien
where  dia_chi like '%Hải Châu%' and ngay_lam_hop_dong='2019-12-12';





/*22.	Thông qua khung nhìn v_nhan_vien thực hiện cập nhật địa chỉ thành “Liên Chiểu”
 đối với tất cả các nhân viên được nhìn thấy bởi khung nhìn này.*/
 update v_nhan_vien
 set dia_chi='Lien Chiểu';
 
 
 /*
23.	Tạo Stored Procedure sp_xoa_khach_hang dùng để xóa thông tin
 của một khách hàng nào đó với ma_khach_hang được truyền vào như là 1 tham số của sp_xoa_khach_hang.*/
 
 delimiter //
 create procedure sp_xoa_khach_hang(in sp_ma_khach_hang int)
 begin
	set sql_safe_updates =0;
	update  khach_hang
    set is_delete=1
    where ma_khach_hang=sp_ma_khach_hang;
     set sql_safe_updates =1;
 end //
 delimiter ;
 
 
 /*
24.	Tạo Stored Procedure sp_them_moi_hop_dong dùng để thêm mới vào bảng hop_dong
 với yêu cầu sp_them_moi_hop_dong phải thực hiện kiểm tra tính hợp lệ của dữ liệu bổ sung,
 với nguyên tắc không được trùng khóa chính và đảm bảo toàn vẹn tham chiếu đến các bảng li(ên quan.*/
 
 delimiter //
 create procedure sp_them_moi_hop_dong(in sp_ma_hop_dong int ,in sp_ngay_lam_hop_dong date,in sp_ngay_ket_thuc date,in sp_tien_dat_coc double,in sp_ma_nhan_vien int,in sp_ma_khach_hang int,in sp_ma_dich_vu int)
 begin 
	declare is_ma_hop_dong bool ;
	declare is_ngay_lam_hop_dong bool;    
    declare is_ngay_ket_thuc bool;
    declare is_ma_nhan_vien bool;
    declare is_ma_khach_hang bool;
    declare is_ma_dich_vu bool;
    
    set is_ma_hop_dong= sp_ma_hop_dong not in (select ma_hop_dong from hop_dong where is_delete =0);
    set is_ngay_lam_hop_dong=datediff(sp_ngay_lam_hop_dong, curedate())>=0;
    set is_ngay_ket_thuc=datediff(sp_ngay_ket_thuc,sp_ngay_lam_hop_dong)>=2;
    set is_ma_nhan_vien =sp_ma_nhan_vien in (select ma_nhan_vien from nhan_vien where is_delete=0);
    set is_ma_khach_hang =sp_ma_khach_hang in (select ma_khach_hang from khach_hang where is_delete=0);
    set is_ma_dich_vu =sp_ma_dich_vu in (select ma_dich_vu from dich_vu where is_delete=0);
    
    if(is_ma_hop_dong and is_ma_khach_hang and is_ma_dich_vu and is_ma_nhan_vien and is_ngay_ket_thuc and is_ngay_lam_hop_dong) then
		insert into hop_dong(ngay_lam_hop_dong,ngay_ket_thuc,tien_dat_coc,ma_nhan_vien,ma_khach_hang,ma_dich_vu)
		values(sp_ngay_lam_hop_dong,sp_ngay_ket_thuc,sp_tien_dat_coc,sp_ma_nhan_vien,sp_ma_khach_hang,sp_ma_dich_vu);
	end if;
 end //
 delimiter ;
 

/*
26.	Tạo Trigger có tên tr_cap_nhat_hop_dong khi cập nhật ngày kết thúc hợp đồng,
 cần kiểm tra xem thời gian cập nhật có phù hợp hay không, với quy tắc sau:
 Ngày kết thúc hợp đồng phải lớn hơn ngày làm hợp đồng ít nhất là 2 ngày. 
 Nếu dữ liệu hợp lệ thì cho phép cập nhật, nếu dữ liệu không hợp lệ thì in ra thông báo 
 “Ngày kết thúc hợp đồng phải lớn hơn ngày làm hợp đồng ít nhất là 2 ngày” trên console của database.
Lưu ý: Đối với MySQL thì sử dụng SIGNAL hoặc ghi log thay cho việc ghi ở console.*/
drop trigger tr_cap_nhat_hop_dong;
delimiter //
create trigger tr_cap_nhat_hop_dong before update on hop_dong for each row 
begin
declare hieu int ;
set hieu =datediff(new.ngay_ket_thuc, new.ngay_lam_hop_dong);
if (new.ngay_ket_thuc<>old.ngay_ket_thuc) then
	if(hieu<2) then
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = "dữ liệu không hợp lệ";
	end if;
end if;

end //
delimiter ;
update hop_dong
set ngay_ket_thuc='2020-12-09'
where ma_hop_dong=6;

