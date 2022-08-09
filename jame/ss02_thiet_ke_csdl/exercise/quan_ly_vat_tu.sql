create database quan_ly_vat_tu;

use quan_ly_vat_tu;

CREATE TABLE nha_cung_cap (
    ma_ncc INT AUTO_INCREMENT PRIMARY KEY,
    ten_ncc VARCHAR(45) NOT NULL,
    dia_chi VARCHAR(50) NOT NULL
);

CREATE TABLE sdt_ncc (
    so_dien_thoai VARCHAR(15) PRIMARY KEY,
    ma_ncc INT NOT NULL,
    FOREIGN KEY (ma_ncc)
        REFERENCES nha_cung_cap (ma_ncc)
);

CREATE TABLE phieu_nhap (
    so_pn INT AUTO_INCREMENT PRIMARY KEY,
    ngay_nhap DATE
);

CREATE TABLE vat_tu (
    ma_vtu INT AUTO_INCREMENT PRIMARY KEY,
    ten_vtu VARCHAR(50)
);

CREATE TABLE chi_tiet_phieu_nhap (
    ma_vtu INT,
    so_pn INT,
    don_gia DOUBLE DEFAULT 0,
    so_luong INT DEFAULT 1,
    PRIMARY KEY (ma_vtu , so_pn),
    FOREIGN KEY (ma_vtu)
        REFERENCES vat_tu (ma_vtu),
    FOREIGN KEY (so_pn)
        REFERENCES phieu_nhap (so_pn)
);

CREATE TABLE phieu_xuat (
    so_px INT AUTO_INCREMENT PRIMARY KEY,
    ngay_xuat DATE
);

CREATE TABLE chi_tiet_phieu_xuat (
    ma_vtu INT,
    so_px INT,
    don_gia DOUBLE DEFAULT 0,
    so_luong INT DEFAULT 1,
    PRIMARY KEY (ma_vtu , so_px),
    FOREIGN KEY (ma_vtu)
        REFERENCES vat_tu (ma_vtu),
    FOREIGN KEY (so_px)
        REFERENCES phieu_xuat (so_px)
);

CREATE TABLE don_dh (
    so_dh INT AUTO_INCREMENT PRIMARY KEY,
    ngay_dh DATE,
    ma_ncc INT,
    FOREIGN KEY (ma_ncc)
        REFERENCES nha_cung_cap (ma_ncc)
);

CREATE TABLE chi_tiet_don_dh (
    so_dh INT,
    ma_vtu INT,
    PRIMARY KEY (so_dh , ma_vtu),
    FOREIGN KEY (so_dh)
        REFERENCES don_dh (so_dh),
    FOREIGN KEY (ma_vtu)
        REFERENCES vat_tu (ma_vtu)
);