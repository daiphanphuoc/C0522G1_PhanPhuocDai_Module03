
/*28.	Tạo Stored Procedure sp_xoa_dich_vu_va_hd_room để tìm các dịch vụ được thuê bởi khách hàng với loại dịch vụ là “Room”
 từ đầu năm 2015 đến hết năm 2019 để xóa thông tin của các dịch vụ đó (tức là xóa các bảng ghi trong bảng dich_vu) 
 và xóa những hop_dong sử dụng dịch vụ liên quan (tức là phải xóa những bản gi trong bảng hop_dong) và những bản liên quan khác.
*/
create view w_dich_vu_room as
select dv.ma_dich_vu, hd.ma_hop_dong from dich_vu  dv 
inner join  loai_dich_vu ldv on ldv.ma_loai_dich_vu=dv.ma_loai_dich_vu
inner join hop_dong hd on hd.ma_dich_vu=dv.ma_dich_vu
where  (year(ngay_lam_hop_dong) between 2015 and 2020) and ten_loai_dich_vu='Room' and dv.is_delete =0;

delimiter //
create procedure sp_xoa_dich_vu_va_hd_room()
begin
set sql_safe_updates =0;

update hop_dong_chi_tiet
set is_delete =1
where ma_hop_dong in (select distinct t.ma_hop_dong  from (select dv.ma_dich_vu, hd.ma_hop_dong from dich_vu  dv 
inner join  loai_dich_vu ldv on ldv.ma_loai_dich_vu=dv.ma_loai_dich_vu
inner join hop_dong hd on hd.ma_dich_vu=dv.ma_dich_vu
where  (year(ngay_lam_hop_dong) between 2015 and 2020) and ten_loai_dich_vu='Room' and dv.is_delete =0) as t);

update hop_dong
set is_delete=1
where hop_dong.ma_hop_dong in (select distinct t.ma_hop_dong  from (select dv.ma_dich_vu, hd.ma_hop_dong from dich_vu  dv 
inner join  loai_dich_vu ldv on ldv.ma_loai_dich_vu=dv.ma_loai_dich_vu
inner join hop_dong hd on hd.ma_dich_vu=dv.ma_dich_vu
where  (year(ngay_lam_hop_dong) between 2015 and 2020) and ten_loai_dich_vu='Room' and dv.is_delete =0)as t);

update dich_vu
set is_delete =1
where dich_vu.ma_dich_vu in (select distinct t.ma_dich_vu from (select dv.ma_dich_vu, hd.ma_hop_dong from dich_vu  dv 
inner join  loai_dich_vu ldv on ldv.ma_loai_dich_vu=dv.ma_loai_dich_vu
inner join hop_dong hd on hd.ma_dich_vu=dv.ma_dich_vu
where  (year(ngay_lam_hop_dong) between 2015 and 2020) and ten_loai_dich_vu='Room' and dv.is_delete =0)as t);

set sql_safe_updates =1;

end //
delimiter ;

call  sp_xoa_dich_vu_va_hd_room();