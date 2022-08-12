USE furama;

/*11.	Hiển thị thông tin các dịch vụ đi kèm đã được sử dụng 
bởi những khách hàng có ten_loai_khach là “Diamond” 
và có dia_chi ở “Vinh” hoặc “Quảng Ngãi”.*/
SELECT 
    ma_dich_vu_di_kem,
    ten_dich_vu_di_kem,
    gia,
    don_vi,
    trang_thai
FROM
    dich_vu_di_kem
WHERE
    ma_dich_vu_di_kem IN (SELECT DISTINCT
            ma_dich_vu_di_kem
        FROM
            hop_dong AS hd
                INNER JOIN
            khach_hang AS kh ON kh.ma_khach_hang = hd.ma_khach_hang
                INNER JOIN
            loai_khach AS lk ON lk.ma_loai_khach = kh.ma_loai_khach
                INNER JOIN
            hop_dong_chi_tiet AS hdct ON hd.ma_hop_dong = hdct.ma_hop_dong
        WHERE
            ten_loai_khach = 'Diamond'
                AND (dia_chi LIKE '% Vinh'
                OR dia_chi LIKE '% Quảng Ngãi'));



SELECT 
    ma_dich_vu_di_kem,
    ten_dich_vu_di_kem,
    gia,
    don_vi,
    trang_thai
FROM
    dich_vu_di_kem
WHERE
    ma_dich_vu_di_kem IN (SELECT DISTINCT
            ma_dich_vu_di_kem
        FROM
            hop_dong AS hd
                INNER JOIN
            khach_hang AS kh ON kh.ma_khach_hang = hd.ma_khach_hang
                INNER JOIN
            loai_khach AS lk ON lk.ma_loai_khach = kh.ma_loai_khach
                INNER JOIN
            hop_dong_chi_tiet AS hdct ON hd.ma_hop_dong = hdct.ma_hop_dong
        WHERE
            ten_loai_khach = 'Diamond');
/*

12.	Hiển thị thông tin ma_hop_dong, ho_ten (nhân viên), ho_ten (khách hàng), so_dien_thoai (khách hàng),
 ten_dich_vu, so_luong_dich_vu_di_kem (được tính dựa trên việc sum so_luong ở dich_vu_di_kem),
 tien_dat_coc của tất cả các dịch vụ đã từng được khách hàng đặt vào 3 tháng cuối năm 2020 
 nhưng chưa từng được khách hàng đặt vào 6 tháng đầu năm 2021.*/
  SELECT 
    hd.ma_hop_dong,
    nv.ho_ten,
    kh.ho_ten,
    kh.so_dien_thoai,
    hd.ngay_lam_hop_dong,
    dv.ten_dich_vu,
    SUM(IFNULL(hdct.so_luong, 0)) AS so_luong_dich_vu_di_kem,
    hd.tien_dat_coc
FROM
    hop_dong AS hd
        LEFT JOIN
    hop_dong_chi_tiet AS hdct ON hd.ma_hop_dong = hdct.ma_hop_dong
        INNER JOIN
    nhan_vien AS nv ON nv.ma_nhan_vien = hd.ma_nhan_vien
        INNER JOIN
    khach_hang AS kh ON kh.ma_khach_hang = hd.ma_khach_hang
        INNER JOIN
    dich_vu AS dv ON dv.ma_dich_vu = hd.ma_dich_vu
WHERE
    YEAR(ngay_lam_hop_dong) = 2020
        AND MONTH(ngay_lam_hop_dong) IN (10 , 11, 12)
        AND hd.ma_dich_vu NOT IN (SELECT 
            hd.ma_dich_vu
        FROM
            hop_dong hd
        WHERE
            YEAR(ngay_lam_hop_dong) = 2021
                AND MONTH(ngay_lam_hop_dong) IN (1 , 2, 3, 4, 5, 6))
GROUP BY hd.ma_hop_dong;
 
 
 /*13.	Hiển thị thông tin các Dịch vụ đi kèm được sử dụng nhiều nhất bởi các Khách hàng đã đặt phòng. 
 (Lưu ý là có thể có nhiều dịch vụ có số lần sử dụng nhiều như nhau).*/
CREATE VIEW thong_ke AS
    SELECT 
        hdct.ma_dich_vu_di_kem, SUM(so_luong) AS su_dung
    FROM
        hop_dong_chi_tiet hdct
    GROUP BY hdct.ma_dich_vu_di_kem
    HAVING SUM(so_luong) = (SELECT 
            SUM(so_luong) AS su_dung
        FROM
            hop_dong_chi_tiet hdct
        GROUP BY hdct.ma_dich_vu_di_kem
        ORDER BY SUM(so_luong) DESC
        LIMIT 1);

 SELECT 
    dvdk.*
FROM
    dich_vu_di_kem dvdk
WHERE
    ma_dich_vu_di_kem IN (SELECT 
            ma_dich_vu_di_kem
        FROM
            thong_ke);
 
 

 
 /*14.Hiển thị thông tin tất cả các Dịch vụ đi kèm chỉ mới được sử dụng một lần duy nhất.
 Thông tin hiển thị bao gồm ma_hop_dong, ten_loai_dich_vu, ten_dich_vu_di_kem, so_lan_su_dung 
 (được tính dựa trên việc count các ma_dich_vu_di_kem).*/
 SELECT 
        dvdk.*, count(hdct.ma_dich_vu_di_kem) AS su_dung
    FROM
        hop_dong_chi_tiet hdct
        inner join dich_vu_di_kem dvdk on hdct.ma_dich_vu_di_kem=dvdk.ma_dich_vu_di_kem
    GROUP BY hdct.ma_dich_vu_di_kem
    HAVING su_dung=1;
 
 /*15.	Hiển thi thông tin của tất cả nhân viên bao gồm ma_nhan_vien, ho_ten, ten_trinh_do, ten_bo_phan, so_dien_thoai, dia_chi
 mới chỉ lập được tối đa 3 hợp đồng từ năm 2020 đến 2021.*/
 select nv.ma_nhan_vien, ho_ten, ten_trinh_do, ten_bo_phan, so_dien_thoai, dia_chi
 from nhan_vien nv 
 inner join hop_dong hd on hd.ma_nhan_vien= nv.ma_nhan_vien
 inner join bo_phan bp on bp.ma_bo_phan=nv.ma_bo_phan
 inner join trinh_do td on td.ma_trinh_do=nv.ma_trinh_do
 where year(hd.ngay_lam_hop_dong) in(2020,2021)
 group by nv.ma_nhan_vien
 having count(hd.ma_hop_dong)<=3;
 
 /*16.	Xóa những Nhân viên chưa từng lập được hợp đồng nào từ năm 2019 đến năm 2021.*/
 SELECT 
    *
FROM
    nhan_vien;
    
 SET sql_safe_updates =0;
 
 UPDATE nhan_vien 
SET 
    is_delete = 1
WHERE
    ma_nhan_vien  NOT IN (SELECT DISTINCT
            ma_nhan_vien
        FROM
            hop_dong
        WHERE
            YEAR(ngay_lam_hop_dong) BETWEEN 2019 AND 2021);

/*

17.	Cập nhật thông tin những khách hàng có ten_loai_khach từ Platinum lên Diamond, 
chỉ cập nhật những khách hàng đã từng đặt phòng với Tổng Tiền thanh toán trong năm 2021 là lớn hơn 10.000.000 VNĐ.*/
CREATE VIEW khach_hang_thanh_toan AS
    SELECT 
        kh.ma_khach_hang,
        ten_loai_khach,
        SUM(IFNULL(chi_phi_thue, 0) * DATEDIFF(ngay_ket_thuc, ngay_lam_hop_dong) + IFNULL(so_luong, 0) * IFNULL(gia, 0)) AS tt
    FROM
        khach_hang kh
            INNER JOIN
        loai_khach lk ON lk.ma_loai_khach = kh.ma_loai_khach
            INNER JOIN
        hop_dong hd ON hd.ma_khach_hang = kh.ma_khach_hang
            LEFT JOIN
        hop_dong_chi_tiet hdct ON hdct.ma_hop_dong = hd.ma_hop_dong
            LEFT JOIN
        dich_vu_di_kem AS dvdk ON hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
            LEFT JOIN
        dich_vu ON dich_vu.ma_dich_vu = hd.ma_dich_vu
    WHERE
        ten_loai_khach = 'Platinium'
            AND YEAR(hd.ngay_lam_hop_dong) = 2021
    GROUP BY ma_khach_hang
    HAVING SUM(IFNULL(chi_phi_thue, 0) * DATEDIFF(ngay_ket_thuc, ngay_lam_hop_dong) + IFNULL(so_luong, 0) * IFNULL(gia, 0)) > 10000000; 
    
    update khach_hang
    set ma_loai_khach=(select ma_loai_khach from loai_khach where ten_loai_khach='Diamond')
    where ma_khach_hang =(select ma_khach_hang from khach_hang_thanh_toan where  tt>10000000);
    


/*18.	Xóa những khách hàng có hợp đồng trước năm 2021 (chú ý ràng buộc giữa các bảng).*/
set sql_safe_updates =0;
UPDATE khach_hang 
SET 
    is_delete = 1
WHERE
    ma_khach_hang IN (SELECT 
            hd.ma_khach_hang
        FROM
            hop_dong hd
        WHERE
            YEAR(ngay_lam_hop_dong) < 2021);



/*19.	Cập nhật giá cho các dịch vụ đi kèm được sử dụng trên 10 lần trong năm 2020 lên gấp đôi.*/

create view thong_ke_dvdk as
select dvdk.ma_dich_vu_di_kem , dvdk.gia
 from hop_dong_chi_tiet hdct 
 join dich_vu_di_kem dvdk on dvdk.ma_dich_vu_di_kem=hdct.ma_dich_vu_di_kem
 join hop_dong hd on hd.ma_hop_dong=hdct.ma_hop_dong
 where year(ngay_lam_hop_dong)=2020
group by ma_dich_vu_di_kem
having sum(hdct.so_luong)>10;

set sql_safe_updates=0;

update dich_vu_di_kem
set gia=2*gia
where ma_dich_vu_di_kem in(select ma_dich_vu_di_kem from thong_ke_dvdk);

drop view thong_ke_dvdk;

/*20.	Hiển thị thông tin của tất cả các nhân viên và khách hàng có trong hệ thống,
 thông tin hiển thị bao gồm id (ma_nhan_vien, ma_khach_hang), ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi.
*/

select ma_nhan_vien id , ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi
from nhan_vien
union all
select ma_khach_hang id , ho_ten, email, so_dien_thoai, ngay_sinh, dia_chi
from khach_hang
