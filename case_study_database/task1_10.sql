use furama;

/*task 2
2.	Hiển thị thông tin của tất cả nhân viên có trong hệ thống 
có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
*/
select *
from nhan_vien
where char_length(ho_ten)<=15 and (ho_ten like 'H%' or ho_ten like 'K%' or ho_ten like 'T%');

select *
from nhan_vien
where char_length(ho_ten)<=15 and ho_ten regexp '^[HKT]';

/*task 3
3.	Hiển thị thông tin của tất cả khách hàng 
có độ tuổi từ 18 đến 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
*/

select *
from khach_hang
where (dia_chi like '%Đà Nẵng' or dia_chi like '%Quảng Trị') and ( select (datediff(curdate(), ngay_sinh)/365) between 18 and 50);

/*task4
Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần. 
Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng.
 Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”.
*/

select  khach_hang.ma_khach_hang,ho_ten,khach_hang.ma_loai_khach, count(hop_dong.ma_khach_hang) as so_lan
from khach_hang 
inner join loai_khach on khach_hang.ma_loai_khach=loai_khach.ma_loai_khach
inner join hop_dong on khach_hang.ma_khach_hang=hop_dong.ma_khach_hang
where   ten_loai_khach = 'Diamond'
group by khach_hang.ma_khach_hang
order by so_lan asc;

/*task 5
5.	Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien 
(Với tổng tiền được tính theo công thức như sau: 
Chi Phí Thuê + Số Lượng * Giá, với Số Lượng và Giá là từ bảng dich_vu_di_kem, hop_dong_chi_tiet)
 cho tất cả các khách hàng đã từng đặt phòng.
 (những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra).
*/
select  khach_hang.ma_khach_hang, ho_ten, ten_loai_khach, hop_dong.ma_hop_dong,
 ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, (ifnull(chi_phi_thue,0)+ifnull(so_luong,0)*ifnull(gia,0)) as tong_tien 
from khach_hang
left join loai_khach on khach_hang.ma_loai_khach=loai_khach.ma_loai_khach
left join hop_dong on khach_hang.ma_khach_hang=hop_dong.ma_khach_hang
left join dich_vu on dich_vu.ma_dich_vu =hop_dong.ma_dich_vu
left join hop_dong_chi_tiet as hdct on hdct.ma_hop_dong=hop_dong.ma_hop_dong
left join dich_vu_di_kem as dvdk on hdct.ma_dich_vu_di_kem= dvdk.ma_dich_vu_di_kem
group by ma_hop_dong
order by  khach_hang.ma_khach_hang,hop_dong.ma_hop_dong asc

/*
6.	Hiển thị ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ten_loai_dich_vu
 của tất cả các loại dịch vụ chưa từng được khách hàng thực hiện đặt từ quý 1 của năm 2021 (Quý 1 là tháng 1, 2, 3).
*/






/*
7.	Hiển thị thông tin ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ đã từng được khách hàng đặt phòng trong năm 2020 nhưng chưa từng được khách hàng đặt phòng trong năm 2021.
8.	Hiển thị thông tin ho_ten khách hàng có trong hệ thống, với yêu cầu ho_ten không trùng nhau.
Học viên sử dụng theo 3 cách khác nhau để thực hiện yêu cầu trên.
9.	Thực hiện thống kê doanh thu theo tháng, nghĩa là tương ứng với mỗi tháng trong năm 2021 thì sẽ có bao nhiêu khách hàng thực hiện đặt phòng.
10.	Hiển thị thông tin tương ứng với từng hợp đồng thì đã sử dụng bao nhiêu dịch vụ đi kèm. Kết quả hiển thị bao gồm ma_hop_dong, ngay_lam_hop_dong, ngay_ket_thuc, tien_dat_coc, so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem).
*/