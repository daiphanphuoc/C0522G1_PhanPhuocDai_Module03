create database if not exists quan_ly_diem_thi;

use quan_ly_diem_thi;

CREATE TABLE IF NOT EXISTS hoc_sinh (
    mahs VARCHAR(20) PRIMARY KEY,
    ten_hs VARCHAR(45) NOT NULL,
    ngay_sinh DATE NOT NULL,
    lop VARCHAR(20),
    gt VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS mon_hoc (
    mamh VARCHAR(20) PRIMARY KEY,
    ten_mh VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS bang_diem (
    mahs VARCHAR(20),
    mamh VARCHAR(20),
    diem_thi INT,
    ngay_kt DATETIME,
    PRIMARY KEY (mahs , mamh),
    FOREIGN KEY (mahs)
        REFERENCES hoc_sinh (mahs),
    FOREIGN KEY (mamh)
        REFERENCES mon_hoc (mamh)
);

CREATE TABLE IF NOT EXISTS giao_vien (
    magv VARCHAR(20) PRIMARY KEY,
    ten_gv VARCHAR(50) NOT NULL,
    sdt VARCHAR(15)
);

alter table mon_hoc add magv varchar(20);
alter table mon_hoc add constraint fk_magv foreign key (magv) references giao_vien(magv); 