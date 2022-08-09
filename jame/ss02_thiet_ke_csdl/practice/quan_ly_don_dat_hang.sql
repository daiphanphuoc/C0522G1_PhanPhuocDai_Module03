create database quan_ly_don_dat_hang;

use quan_ly_don_dat_hang;

CREATE TABLE dv_khach (
    ma_dv INT AUTO_INCREMENT PRIMARY KEY,
    ten_dv VARCHAR(50) NOT NULL,
    dia_chi VARCHAR(50) NOT NULL,
    dien_thoai VARCHAR(15) NOT NULL
);

CREATE TABLE nguoi_dat (
    ma_nguoi_dat INT AUTO_INCREMENT PRIMARY KEY,
    ho_ten_nd VARCHAR(50),
    ma_dv INT,
    FOREIGN KEY (ma_dv)
        REFERENCES dv_khach (ma_dv)
);

CREATE TABLE nguoi_nhan (
    ma_nguoi_nhan INT AUTO_INCREMENT PRIMARY KEY,
    ho_ten_nd VARCHAR(50),
    ma_dv INT,
    FOREIGN KEY (ma_dv)
        REFERENCES dv_khach (ma_dv)
);

CREATE TABLE hang (
    ma_hang INT AUTO_INCREMENT PRIMARY KEY,
    ten_hang VARCHAR(50) NOT NULL,
    don_vi_tinh VARCHAR(20) NOT NULL,
    mo_ta TEXT
);

CREATE TABLE nguoi_giao (
    ma_nguoi_giao INT AUTO_INCREMENT PRIMARY KEY,
    ho_ten VARCHAR(50)
);

CREATE TABLE noi_giao (
    ma_noi_giao INT AUTO_INCREMENT PRIMARY KEY,
    ten_noi_giao VARCHAR(50)
);

CREATE TABLE chi_tiet_dh (
    so_dh INT AUTO_INCREMENT PRIMARY KEY,
    ngay_dat DATE,
    ma_nguoi_dat INT,
    ma_hang INT,
    so_luong INT DEFAULT 1,
    FOREIGN KEY (ma_nguoi_dat)
        REFERENCES nguoi_dat (ma_nguoi_dat),
    FOREIGN KEY (ma_hang)
        REFERENCES hang (ma_hang)
);

CREATE TABLE chi_tiet_giao_nhan (
    so_pg INT AUTO_INCREMENT PRIMARY KEY,
    ngay_giao DATE,
    ma_nguoi_giao INT,
    ma_nguoi_nhan INT,
    ma_hang INT,
    so_luong INT DEFAULT 1,
    don_gia DOUBLE,
    ma_noi_giao INT,
    FOREIGN KEY (ma_nguoi_giao)
        REFERENCES nguoi_giao (ma_nguoi_giao),
        FOREIGN KEY (ma_nguoi_nhan)
        REFERENCES nguoi_nhan (ma_nguoi_nhan),
    FOREIGN KEY (ma_noi_giao)
        REFERENCES noi_giao (ma_noi_giao),
    FOREIGN KEY (ma_hang)
        REFERENCES hang (ma_hang)
);

