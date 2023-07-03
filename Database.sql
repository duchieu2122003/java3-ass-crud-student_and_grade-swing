
CREATE DATABASE JAVA3

GO
CREATE TABLE USERS(
	username nvarchar(50) not null PRIMARY KEY,
	password nvarchar(50) null,
	role nvarchar(50) null
)
GO
CREATE TABLE STUDENTS(
	MASV nvarchar(50) not null PRIMARY KEY,
	Hoten nvarchar(50) null,
	Email nvarchar(50) null,
	SoDT nvarchar(50) null,
	Gioitinh bit null,
	Diachi nvarchar(50) null,
	Hinh nvarchar(50) null
)
GO
CREATE TABLE GRADE(
	ID int IDENTITY(0,1) not null PRIMARY KEY,
	MASV nvarchar(50) null,
	Tienganh int null,
	Tinhoc int null,
	GDTC int null,
	FOREIGN KEY(MASV) REFERENCES STUDENTS(MASV)
)
--DROP TABLE GRADE
GO
INSERT INTO USERS VALUES
('gv','12',N'Giảng viên'),
('cbdt','123',N'Cán bộ đào tạo')
--delete from USERS
--select * from USERS
	/*SELECT dbo.GRADE.id,dbo.STUDENTS.MASV, dbo.STUDENTS.Hoten, dbo.GRADE.Tienganh, dbo.GRADE.Tinhoc, dbo.GRADE.GDTC,dbo.STUDENTS.Email, dbo.STUDENTS.SoDT,
dbo.STUDENTS.Gioitinh, dbo.STUDENTS.Diachi, dbo.STUDENTS.Hinh 
FROM dbo.GRADE  INNER JOIN dbo.STUDENTS ON dbo.GRADE.MASV = dbo.STUDENTS.MASV 
ORDER BY ((dbo.GRADE.Tienganh + dbo.GRADE.Tinhoc + dbo.GRADE.GDTC)/3) DESC OFFSET 6 ROW FETCH NEXT 3 ROW ONLY; */
GO
INSERT INTO STUDENTS VALUES
('PH001',N'Nguyễn Đức Hiệu','hieundph25894@fpt.edu.vn','0334800200',1,N'Nam Định',N'anh1cha.jpg'),
('PH002',N'Phạm Minh Thư','thu@gmail.com','0333800200',0,N'Nam Định',N'anh6.jpg'),
('PH003',N'Phùng Việt Hùng','hung@fpt.edu.vn','0335800201',1,N'Thanh Hóa',N'anh3.jpg'),
('PH004',N'Vũ Quang Huy','huy@fpt.edu.vn','0335810202',0,N'Nam Định',N'anh7.jpg'),
('PH005',N'Nguyễn Thị A','thia@gmail.com','0335810203',0,N'80 Xuân Phương - Hà Nội',N'anh4.jpg'),
('PH006',N'Nguyễn Thị B','thib@fpt.edu.vn','0334800200',0,N'Nam Định',N'anh5.jpg'),
('PH007',N'Nguyễn Thái Dương','thaiduong@fpt.edu.vn','0335886868',1,N'Giao Thủy - Nam Định',N'null'),
('PH008',N'Nguyễn Đức C','ducc@fpt.edu.vn','0335818286',1,N'Phú Xuyên - Nam Định',N'anh10chaeun.jpg'),
('PH009',N'Phan Văn Din','jin@fpt.edu.vn','0325810352',1,N'Thanh Xuân - Hà Nội',N'anh8jin.jpg'),
('PH010',N'Đỗ Văn Hun','teahyung@fpt.edu.vn','0334822200',1,N'Giao Thủy - Nam Định',N'null')

GO
INSERT INTO GRADE VALUES
('PH010',9,5,7),
('PH009',5,5,5),
('PH008',8,8,8),
('PH001',10,10,10),
('PH002',10,9,10),
('PH003',9,8,7)