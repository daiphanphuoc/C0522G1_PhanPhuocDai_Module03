use furama;

/*task 2
2.	Hiển thị thông tin của tất cả nhân viên có trong hệ thống 
có tên bắt đầu là một trong các ký tự “H”, “T” hoặc “K” và có tối đa 15 kí tự.
*/
SELECT 
    *
FROM
    nhan_vien
WHERE
    CHAR_LENGTH(ho_ten) <= 15
        AND (ho_ten LIKE 'H%' OR ho_ten LIKE 'K%'
        OR ho_ten LIKE 'T%');


SELECT 
    *
FROM
    nhan_vien
WHERE
    (ho_ten LIKE '[HKT]%')
        AND CHAR_LENGTH(ho_ten) <= 15;

SELECT 
    *
FROM
    nhan_vien
WHERE
    CHAR_LENGTH(ho_ten) <= 15
        AND ho_ten REGEXP '^[HKT]';

/*task 3
3.	Hiển thị thông tin của tất cả khách hàng 
có độ tuổi từ 18 đến 50 tuổi và có địa chỉ ở “Đà Nẵng” hoặc “Quảng Trị”.
*/

SELECT 
    *
FROM
    khach_hang
WHERE
    (dia_chi LIKE '%Đà Nẵng'
        OR dia_chi LIKE '%Quảng Trị')
        AND (SELECT (DATEDIFF(CURDATE(), ngay_sinh) / 365) BETWEEN 18 AND 50);

/*task4
Đếm xem tương ứng với mỗi khách hàng đã từng đặt phòng bao nhiêu lần. 
Kết quả hiển thị được sắp xếp tăng dần theo số lần đặt phòng của khách hàng.
 Chỉ đếm những khách hàng nào có Tên loại khách hàng là “Diamond”.
*/

SELECT 
    khach_hang.ma_khach_hang,
    ho_ten,
    khach_hang.ma_loai_khach,
    COUNT(hop_dong.ma_khach_hang) AS so_lan
FROM
    khach_hang
        INNER JOIN
    loai_khach ON khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
        INNER JOIN
    hop_dong ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
WHERE
    ten_loai_khach = 'Diamond'
GROUP BY khach_hang.ma_khach_hang
ORDER BY so_lan ASC;

/*task 5
5.	Hiển thị ma_khach_hang, ho_ten, ten_loai_khach, ma_hop_dong, ten_dich_vu, ngay_lam_hop_dong, ngay_ket_thuc, tong_tien 
(Với tổng tiền được tính theo công thức như sau: 
Chi Phí Thuê + Số Lượng * Giá, với Số Lượng và Giá là từ bảng dich_vu_di_kem, hop_dong_chi_tiet)
 cho tất cả các khách hàng đã từng đặt phòng.
 (những khách hàng nào chưa từng đặt phòng cũng phải hiển thị ra).
*/

SELECT 
    hop_dong.ma_hop_dong,
    khach_hang.ma_khach_hang,
    ho_ten,
    ten_loai_khach,
    ten_dich_vu,
    ngay_lam_hop_dong,
    ngay_ket_thuc,
    (IFNULL(chi_phi_thue, 0) + IFNULL(so_luong, 0) * IFNULL(gia, 0)) AS tong_tien
FROM
    khach_hang
        LEFT JOIN
    loai_khach ON khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
        LEFT JOIN
    hop_dong ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
        LEFT JOIN
    dich_vu ON dich_vu.ma_dich_vu = hop_dong.ma_dich_vu
        LEFT JOIN
    hop_dong_chi_tiet AS hdct ON hdct.ma_hop_dong = hop_dong.ma_hop_dong
        LEFT JOIN
    dich_vu_di_kem AS dvdk ON hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
GROUP BY ma_hop_dong
ORDER BY khach_hang.ma_khach_hang , hop_dong.ma_hop_dong ASC;

/*6.	Hiển thị ma_dich_vu, ten_dich_vu, dien_tich, chi_phi_thue, ten_loai_dich_vu
  của tất cả các loại dịch vụ chưa từng được khách hàng thực hiện đặt từ quý 1 của năm  2021 (Quý 1 là tháng 1, 2, 3).
*/

SELECT DISTINCT
    dich_vu.ma_dich_vu,
    ten_dich_vu,
    dien_tich,
    chi_phi_thue,
    ten_loai_dich_vu
FROM
    dich_vu
        INNER JOIN
    loai_dich_vu ON loai_dich_vu.ma_loai_dich_vu = dich_vu.ma_loai_dich_vu
WHERE
    dich_vu.ma_dich_vu NOT IN (SELECT DISTINCT
            ma_dich_vu
        FROM
            hop_dong
        WHERE
            YEAR(ngay_lam_hop_dong) = 2021
                AND MONTH(ngay_lam_hop_dong) IN (1 , 2, 3));





/*
7.	Hiển thị thông tin ma_dich_vu, ten_dich_vu, dien_tich, so_nguoi_toi_da, chi_phi_thue, ten_loai_dich_vu của tất cả các loại dịch vụ đã từng được khách hàng đặt phòng trong năm 2020 nhưng chưa từng được khách hàng đặt phòng trong năm 2021.
*/
SELECT DISTINCT
    dich_vu.ma_dich_vu,
    ten_dich_vu,
    dien_tich,
    so_nguoi_toi_da,
    chi_phi_thue,
    ten_loai_dich_vu
FROM
    dich_vu
        INNER JOIN
    loai_dich_vu ON loai_dich_vu.ma_loai_dich_vu = dich_vu.ma_loai_dich_vu
WHERE
    dich_vu.ma_dich_vu NOT IN (SELECT DISTINCT
            ma_dich_vu
        FROM
            hop_dong
        WHERE
            YEAR(ngay_lam_hop_dong) = 2021)
        AND dich_vu.ma_dich_vu IN (SELECT DISTINCT
            ma_dich_vu
        FROM
            hop_dong
        WHERE
            YEAR(ngay_lam_hop_dong) = 2020);

/*8.	Hiển thị thông tin ho_ten khách hàng có trong hệ thống, với yêu cầu ho_ten không trùng nhau.
Học viên sử dụng theo 3 cách khác nhau để thực hiện yêu cầu trên.*/
SELECT 
    ho_ten
FROM
    khach_hang
GROUP BY ho_ten;

SELECT DISTINCT
    ho_ten
FROM
    khach_hang;

SELECT 
    ho_ten
FROM
    khach_hang 
UNION (SELECT 
    ho_ten
FROM
    khach_hang);
/*
9.	Thực hiện thống kê doanh thu theo tháng, nghĩa là tương ứng với mỗi tháng trong năm 2021 thì sẽ có bao nhiêu khách hàng thực hiện đặt phòng.*/
SELECT 
    SUM(IFNULL(chi_phi_thue, 0) + IFNULL(so_luong, 0) * IFNULL(gia, 0)) AS doanh_so,
    MONTH(ngay_lam_hop_dong) AS `month`
FROM
    khach_hang
        LEFT JOIN
    loai_khach ON khach_hang.ma_loai_khach = loai_khach.ma_loai_khach
        LEFT JOIN
    hop_dong ON khach_hang.ma_khach_hang = hop_dong.ma_khach_hang
        LEFT JOIN
    dich_vu ON dich_vu.ma_dich_vu = hop_dong.ma_dich_vu
        LEFT JOIN
    hop_dong_chi_tiet AS hdct ON hdct.ma_hop_dong = hop_dong.ma_hop_dong
        LEFT JOIN
    dich_vu_di_kem AS dvdk ON hdct.ma_dich_vu_di_kem = dvdk.ma_dich_vu_di_kem
WHERE
    YEAR(ngay_lam_hop_dong) = 2021
GROUP BY MONTH(ngay_lam_hop_dong);


SELECT 
    COUNT(ma_khach_hang) AS doanh_thu,
    MONTH(ngay_lam_hop_dong) AS month
FROM
    hop_dong
WHERE
    YEAR(ngay_lam_hop_dong) = 2021
GROUP BY MONTH(ngay_lam_hop_dong)
ORDER BY MONTH(ngay_lam_hop_dong);


/*10.	Hiển thị thông tin tương ứng với từng hợp đồng thì đã sử dụng bao nhiêu dịch vụ đi kèm. 
Kết quả hiển thị bao gồm ma_hop_dong, ngay_lam_hop_dong, ngay_ket_thuc, tien_dat_coc, so_luong_dich_vu_di_kem 
(được tính dựa trên việc sum so_luong ở dich_vu_di_kem).*/


SELECT 
    hop_dong.ma_hop_dong,
    ngay_lam_hop_dong,
    ngay_ket_thuc,
    tien_dat_coc,
    IFNULL(SUM(hdct.so_luong), 0) AS so_luong_dich_vu_di_kem
FROM
    hop_dong
        LEFT JOIN
    hop_dong_chi_tiet AS hdct ON hop_dong.ma_hop_dong = hdct.ma_hop_dong
GROUP BY ma_hop_dong;
