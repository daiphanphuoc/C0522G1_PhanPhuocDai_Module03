CREATE DATABASE if not exists `furama`;

USE `furama`;

CREATE TABLE IF NOT EXISTS `loai_khach` (
    ` ma_loai_khach` INT NOT NULL PRIMARY KEY,
    `ten_loai_khach` VARCHAR(45) NOT NULL
);

insert into loai_khach 
values(1,'Diamond'),
(2,'Platinium'),
(3,'Gold'),
(4,'Silver'),
(5,'Member');


CREATE TABLE IF NOT EXISTS `khach_hang` (
    `ma_khach_hang` INT NOT NULL PRIMARY KEY,
    `ma_loai_khach` INT NOT NULL,
    FOREIGN KEY (`ma_loai_khach`)
        REFERENCES `loai_khach` (`ma_loai_khach`),
    `ho_ten` VARCHAR(45),
    `ngay_sinh` DATE,
    `gioi_tinh` BIT(1),
    `so_cccd` VARCHAR(12),
    `so_dien_thoai` VARCHAR(20),
    `email` VARCHAR(45),
    `dia_chi` VARCHAR(45)
);

insert into khach_hang
values
(1,'Nguyễn Thị Hào','1970-11-07',0,'643431213','0945423362','23 Nguyễn Hoàng, Đà Nẵng',5),
(2,'Phạm Xuân Diệu','1992-08-08',1,'865342123','0954333333','K77/22 Thái Phiên, Quảng Trị',5),
(3,'Trương Đình Nghệ','1990-02-27',1,'488645199','0373213122','K323/12 Ông Ích Khiêm, Vinh',5),
(4,'Dương Văn Quan','1981-07-08',1,'543432111','0490039241','K453/12 Lê Lợi, Đà Nẵng',5),
(5,'Hoàng Trần Nhi Nhi','1995-12-09',0,'795453345','0312345678','224 Lý Thái Tổ, Gia Lai',5),
(6,'Tôn Nữ Mộc Châu','2005-12-06',0,'732434215','0988888844','37 Yên Thế, Đà Nẵng',5),
(7,'Nguyễn Mỹ Kim','1984-04-08',0,'856453123','0912345698','K123/45 Lê Lợi, Hồ Chí Minh',5),
(8,'Nguyễn Thị Hào','1999-04-08',0,'965656433','0763212345','55 Nguyễn Văn Linh, Kon Tum',5),
(9,'Trần Đại Danh','1994-07-01',1,'432341235','0643343433','24 Lý Thường Kiệt, Quảng Ngãi',5),
(10,'Nguyễn Tâm Đắc','1989-07-01',1,'344343432','0987654321','22 Ngô Quyền, Đà Nẵng',5);

CREATE TABLE IF NOT EXISTS `vi_tri` (
    `ma_vi_tri` INT PRIMARY KEY,
    `ten_vi_tri` VARCHAR(45)
);

insert into vi_tri
values
(1,'Quản Lý'),
(2,'Nhân Viên');

CREATE TABLE IF NOT EXISTS `trinh_do` (
    `ma_trinh_do` INT PRIMARY KEY,
    `ten_trinh_do` VARCHAR(45)
);

insert into trinh_do
values
(1,'Trung Cấp'),
(2,'Cao Đẳng'),
(3,'Đại Học'),
(4,'Sau Đại Học');

CREATE TABLE IF NOT EXISTS `bo_phan` (
    `ma_bo_phan` INT PRIMARY KEY,
    `ten_bo_phan` VARCHAR(50)
);

insert into bo_phan
values
(1,'Sale-Marketing'),
(2,'Hành chính'),
(3,'Phục vụ'),
(4,'Quản lý');

CREATE TABLE IF NOT EXISTS `nhan_vien` (
    `ma_nhan_vien` INT PRIMARY KEY,
    `ho_ten` VARCHAR(45),
    `ngay_sinh` DATE,
    `so_cccd` VARCHAR(12),
    `luong` DOUBLE,
    `so_dien_thoai` VARCHAR(15),
    `email` VARCHAR(45),
    `dia_chi` VARCHAR(45),
    `ma_vi_tri` INT,
    foreign key(`ma_vi_tri`) REFERENCES `vi_tri` (`ma_vi_tri`),
    `ma_trinh_do` INT,
    foreign key(`ma_trinh_do`) REFERENCES `trinh_do` (`ma_trinh_do`),
    `ma_bo_phan` INT, 
    foreign key(`ma_bo_phan`) REFERENCES `bo_phan` (`ma_bo_phan`)
);

insert into nhan_vien
values
(1,'Nguyễn Văn An','1970-11-07','456231786',10000000,'0901234121','annguyen@gmail.com','295 Nguyễn Tất Thành, Đà Nẵng',1,3,1),
(1,'Lê Văn Bình','1997-04-09','654231234',7000000,'0934212314','binhlv@gmail.com','22 Yên Bái, Đà Nẵng',1,2,2),
(1,'Hồ Thị Yến','1995-12-12','999231723',14000000,'0412352315','','K234/11 Điện Biên Phủ, Gia Lai',1,3,2),
(1,'Võ Công Toản','1980-04-04','123231365',17000000,'0374443232','thiyen@gmail.com','77 Hoàng Diệu, Quảng Trị',1,4,4),
(1,'Nguyễn Bỉnh Phát','1999-12-09','454363232',6000000,'0902341231','toan0404@gmail.com','43 Yên Bái, Đà Nẵng',2,1,1),
(1,'Khúc Nguyễn An Nghi','2000-11-08','964542311',7000000,'0978653213','phatphat@gmail.com','294 Nguyễn Tất Thành, Đà Nẵng',2,2,3),
(1,'Nguyễn Hữu Hà','1993-01-01','534323231',8000000,'0941234553','anghi20@gmail.com','4 Nguyễn Chí Thanh, Huế',2,3,2),
(1,'Nguyễn Hà Đông','1989-09-03','234414123',9000000,'0642123111','nhh0101@gmail.com','111 Hùng Vương, Hà Nội',2,4,4),
(1,'Tòng Hoang','1982-09-03','256781231',6000000,'0245144444','donghanguyen2gmail.com','213 Hàm Nghi, Đà Nẵng',2,4,4),
(1,'Nguyễn Công Đạo','1994-01-08','755434343',8000000,'0988767111','nguyencongdao@gmail.com','6 Hoà Khánh, Đồng Nai',2,3,2);
    
CREATE TABLE IF NOT EXISTS `loai_dich_vu` (
    `ma_loai_dich_vu` INT PRIMARY KEY,
    ten_loai_dich_vu VARCHAR(45)
);

insert into loai_dich_vu
values
(1,'Villa'),
(2,'House'),
(3,'Room');
    
CREATE TABLE IF NOT EXISTS `kieu_thue` (
    `ma_kieu_thue` INT PRIMARY KEY,
    `ten_kieu_thue` VARCHAR(45)
);

insert into kieu_thue
values
(1,'year'),
(2,'month'),
(3,'day'),
(4,'hour');
    
CREATE TABLE IF NOT EXISTS `dich_vu` (
    `ma_dich_vu` INT PRIMARY KEY,
    `ten_dich_vu` VARCHAR(45),
    `dien_tich` INT,
    `chi_phi_thue` DOUBLE,
    `so_nguoi_toi_da` INT,
    `ma_kieu_thue` INT,
    foreign key(`ma_kieu_thue`) REFERENCES `kieu_thue` (`ma_kieu_thue`),
    `ma_loai_dich_vu` INT,
    foreign key(`ma_loai_dich_vu`) REFERENCES `loai_dich_vu` (`ma_loai_dich_vu`),
    `tieu_chuan_phong` VARCHAR(45),
    `mo_ta_tien_nghi_khac` VARCHAR(45),
    `dien_tich_ho_boi` DOUBLE,
    `so_tang` INT,
    `dich_vu_mien_phi_di_kem` TEXT
);
    
  create table if not exists `hop_dong`(
	`ma_hop_dong` int primary key,
    `ngay_lam_hop_dong` datetime,
    `ngay_ket_thuc` datetime,
    `tien_dat_coc` double,
    `ma_nhan_vien` int,
    foreign key(`ma_nhan_vien`) references `nhan_vien`(`ma_nhan_vien`),
    `ma_khach_hang` int,
    foreign key(`ma_khach_hang`) references `khach_hang`(`ma_khach_hang`),
    `ma_dich_vu` int,
    foreign key(`ma_dich_vu`) references `dich_vu`(`ma_dich_vu`)
  );
  
CREATE TABLE IF NOT EXISTS `dich_vu_di_kem` (
    `ma_dich_vu_di_kem` INT PRIMARY KEY,
    `ten_dich_vu_di_kem` VARCHAR(45),
    `gia` DOUBLE,
    `don_vi` VARCHAR(10),
    `trang_thai` VARCHAR(45)
);
 
CREATE TABLE IF NOT EXISTS `hop_dong_chi_tiet` (
    `ma_hop_dong_chi_tiet` INT PRIMARY KEY,
    `ma_hop_dong` INT,
    foreign key(`ma_hop_dong`) REFERENCES `hop_dong` (`ma_hop_dong`),
    `ma_dich_vu_di_kem` INT,
    foreign key(`ma_dich_vu_di_kem`) REFERENCES `dich_vu_di_kem` (`ma_dich_vu_di_kem`),
    `so_luong` INT
);