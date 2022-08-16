/*27.	Tạo Function thực hiện yêu cầu sau:
a.	Tạo Function func_dem_dich_vu: Đếm các dịch vụ đã được sử dụng với tổng tiền là > 2.000.000 VNĐ.*/
create view w_dich_vu_tong_tien as
select dv.ma_dich_vu
	from dich_vu dv
		join hop_dong hd on hd.ma_dich_vu =dv.ma_dich_vu
			group by dv.ma_dich_vu
				having sum(chi_phi_thue*datediff(ngay_ket_thuc,ngay_lam_hop_dong))>2000000;
delimiter //
create function func_dem_dich_vu() 
returns int 
deterministic
begin
declare c int ;
set c=0;
select  count(ma_dich_vu) into c from w_dich_vu_tong_tien;
return c;
end //
delimiter ;

select func_dem_dich_vu();
/*
b.	Tạo Function func_tinh_thoi_gian_hop_dong: 
Tính khoảng thời gian dài nhất tính từ lúc bắt đầu làm hợp đồng đến lúc kết thúc hợp đồng
 mà khách hàng đã thực hiện thuê dịch vụ (lưu ý chỉ xét các khoảng thời gian dựa vào từng lần làm hợp đồng thuê dịch vụ,
 không xét trên toàn bộ các lần làm hợp đồng). Mã của khách hàng được truyền vào như là 1 tham số của function này.*/
 delimiter //
create function func_tinh_thoi_gian_hop_dong(fc_ma_khach_hang int) 
returns int 
deterministic
begin
declare c int ;
SELECT 
    MAX(thoi_gian)
INTO c FROM
    (SELECT 
        DATEDIFF(ngay_ket_thuc, ngay_lam_hop_dong) AS thoi_gian
    FROM
        hop_dong
    WHERE
        ma_khach_hang = fc_ma_khach_hang
    GROUP BY ma_hop_dong) AS t;
return c;
end //
delimiter ;
select func_tinh_thoi_gian_hop_dong(7);