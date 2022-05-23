-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 14, 2021 lúc 12:20 PM
-- Phiên bản máy phục vụ: 10.4.21-MariaDB
-- Phiên bản PHP: 7.3.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `tour_dulich`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tours`
--

CREATE TABLE `tours` (
  `tour_id` int(10) NOT NULL,
  `tour_ten` text COLLATE utf8_unicode_ci NOT NULL,
  `tour_mota` text COLLATE utf8_unicode_ci NOT NULL,
  `loai_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tours`
--

INSERT INTO `tours` (`tour_id`, `tour_ten`, `tour_mota`, `loai_id`) VALUES
(1, 'Tham quan di tích và tắm biển 1', 'Tham quan ở địa đạo củ chi và tắm biển ở vũng tàu', 2),
(2, 'Tham quan di tích và đi khu du lịch 1', 'Tham quan ở địa đạo củ chi và khu du lịch hổ mây', 2),
(3, 'Tham quan di tích 1', 'Tham quan ở địa đạo củ chi', 2),
(4, 'Tham quan phong cảnh 1', 'Tham quan đà lạt', 3),
(5, 'Bà rịa - vũng tàu 1', 'khu du lịch hồ mây và tắm biển ở vũng tàu', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_chiphi`
--

CREATE TABLE `tour_chiphi` (
  `chiphi_id` int(11) NOT NULL,
  `doan_id` int(11) NOT NULL,
  `chiphi_total` decimal(10,0) NOT NULL,
  `chiphi_chitiet` text COLLATE utf8_unicode_ci NOT NULL COMMENT 'lưu danh sách chi phí (json)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_chiphi`
--

INSERT INTO `tour_chiphi` (`chiphi_id`, `doan_id`, `chiphi_total`, `chiphi_chitiet`) VALUES
(1, 1, '300000', '-1--3--4--6--8-'),
(2, 2, '500000', '-1--3--4--5--6--8-'),
(3, 3, '1000000', '-2--3--4--5--6--7--8-'),
(4, 4, '0', '-2-');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_chitiet`
--

CREATE TABLE `tour_chitiet` (
  `ct_id` int(11) NOT NULL,
  `tour_id` int(11) NOT NULL,
  `dd_id` int(11) NOT NULL,
  `ct_thutu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_chitiet`
--

INSERT INTO `tour_chitiet` (`ct_id`, `tour_id`, `dd_id`, `ct_thutu`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 2),
(3, 2, 1, 1),
(4, 2, 5, 2),
(5, 3, 1, 1),
(6, 4, 3, 1),
(7, 5, 5, 1),
(8, 5, 2, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_diadiem`
--

CREATE TABLE `tour_diadiem` (
  `dd_id` int(11) NOT NULL,
  `dd_thanhpho` text COLLATE utf8_unicode_ci NOT NULL,
  `dd_ten` text COLLATE utf8_unicode_ci NOT NULL,
  `dd_mota` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_diadiem`
--

INSERT INTO `tour_diadiem` (`dd_id`, `dd_thanhpho`, `dd_ten`, `dd_mota`) VALUES
(1, 'TP.HCM', 'địa đạo củ chi', 'tham quan khu di tích địa đạo củ chi'),
(2, 'Bà Rịa - vũng tàu', 'bà rịa - vũng tàu tắm biển', 'tắm biển ở bà rịa vũng tàu'),
(3, 'Lâm Đồng', 'đà lạt', 'tham quan đà lạt'),
(4, 'Đồng Nai', 'khu du lịch suối mơ', 'tham quan và tham gia trò chơi'),
(5, 'Bà Rịa - Vũng tàu', 'khu du lịch hồ mây', 'tham quan và tham gia vui chơi ngoài trời'),
(6, 'test 13', 'test 14', 'test 15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_doan`
--

CREATE TABLE `tour_doan` (
  `doan_id` int(11) NOT NULL,
  `tour_id` int(10) NOT NULL,
  `doan_name` text COLLATE utf8_unicode_ci NOT NULL,
  `doan_ngaydi` date NOT NULL,
  `doan_ngayve` date NOT NULL,
  `gia_id` int(11) NOT NULL,
  `doan_chitietchuongtrinh` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_doan`
--

INSERT INTO `tour_doan` (`doan_id`, `tour_id`, `doan_name`, `doan_ngaydi`, `doan_ngayve`, `gia_id`, `doan_chitietchuongtrinh`) VALUES
(1, 1, 'Tour 1 năm 2020', '2020-12-05', '2020-12-08', 1, 'Chuyến tham quan địa đạo củ chi và đi tắm biển vũng tàu tháng 12 năm 2020'),
(2, 1, 'Tour 1 năm 2021', '2021-11-05', '2021-11-08', 2, 'Chuyến tham quan địa đạo củ chi và đi tắm biển vũng tàu tháng 11 năm 2021'),
(3, 5, 'Tour 5 năm 2021', '2021-11-05', '2021-11-10', 7, 'Chuyến nghĩ dưỡng bà rịa - vũng tàu tháng 11 năm 2021'),
(4, 3, 'test 123', '2021-12-10', '2021-12-15', 5, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_gia`
--

CREATE TABLE `tour_gia` (
  `gia_id` int(11) NOT NULL,
  `gia_sotien` decimal(10,0) NOT NULL,
  `tour_id` int(10) NOT NULL,
  `gia_tungay` date NOT NULL,
  `ghichu` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_gia`
--

INSERT INTO `tour_gia` (`gia_id`, `gia_sotien`, `tour_id`, `gia_tungay`, `ghichu`) VALUES
(1, '1200000', 1, '2020-11-21', 'Giá năm 2020'),
(2, '1500000', 1, '2021-10-21', 'Giá năm 2021'),
(3, '2000000', 2, '2021-10-21', 'Giá năm 2021'),
(4, '2100000', 2, '2021-11-21', 'Giá năm 2021'),
(5, '500000', 3, '2021-11-10', 'Giá năm 2021'),
(6, '1000000', 4, '2021-01-21', 'Giá năm 2021'),
(7, '2500000', 5, '2021-02-02', 'Giá năm 2021');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_khachhang`
--

CREATE TABLE `tour_khachhang` (
  `kh_id` int(11) NOT NULL,
  `kh_ten` text COLLATE utf8_unicode_ci NOT NULL,
  `kh_sdt` text COLLATE utf8_unicode_ci NOT NULL,
  `kh_ngaysinh` date NOT NULL,
  `kh_email` text COLLATE utf8_unicode_ci NOT NULL,
  `kh_cmnd` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_khachhang`
--

INSERT INTO `tour_khachhang` (`kh_id`, `kh_ten`, `kh_sdt`, `kh_ngaysinh`, `kh_email`, `kh_cmnd`) VALUES
(1, 'Triệu bích khai', '0338468925', '2000-12-31', 'trieubichkhai@gmail.com', '079200008365'),
(2, 'Thái Vĩnh Hưng', '0703261212', '2000-03-08', 'tvhung@gmail.com', '079200008368'),
(3, 'khách hàng ba', '0703261203', '2003-03-03', 'khachhang3@gmail.com', '079200008303'),
(4, 'khách hàng bốn', '0703261204', '2004-04-04', 'khachhang4@gmail.com', '079200008304'),
(5, 'khách hàng năm', '0703261205', '2005-05-05', 'khachhang5@gmail.com', '079200008305'),
(6, 'khách hàng sáu', '0703261206', '2006-06-06', 'khachhang6@gmail.com', '079200008306'),
(7, 'khách hàng bảy', '0703261207', '2007-07-07', 'khachhang7@gmail.com', '079200008307'),
(8, 'khách hàng tám', '0703261208', '2008-08-08', 'khachhang8@gmail.com', '079200008308'),
(9, 'khách hàng chín', '0703261209', '2009-09-09', 'khachhang9@gmail.com', '079200008309'),
(10, 'khách hàng mười', '0703261210', '2010-10-10', 'khachhang10@gmail.com', '079200008310');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_loai`
--

CREATE TABLE `tour_loai` (
  `loai_id` int(10) NOT NULL,
  `loai_ten` text COLLATE utf8_unicode_ci NOT NULL,
  `loai_mota` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_loai`
--

INSERT INTO `tour_loai` (`loai_id`, `loai_ten`, `loai_mota`) VALUES
(1, 'Khu du lịch', 'tham quan những khu du lịch'),
(2, 'Di tích lịch sử', 'tham quan di tích lịch sử'),
(3, 'du lịch bình thường', 'tham quan phong cảnh');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_loaichiphi`
--

CREATE TABLE `tour_loaichiphi` (
  `cp_id` int(11) NOT NULL,
  `cp_ten` text COLLATE utf8_unicode_ci NOT NULL,
  `cp_mota` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_loaichiphi`
--

INSERT INTO `tour_loaichiphi` (`cp_id`, `cp_ten`, `cp_mota`) VALUES
(1, '1 hướng dẫn', 'một người hướng dẫn viên'),
(2, '2 hướng dẫn', 'hai người hướng dẫn viên'),
(3, 'khách sạn', 'chi phí của khách sạn'),
(4, '3 bữa', 'chí phí 3 bữa cho mỗi ngày'),
(5, 'chai nước', 'chi phí chai nước cho mỗi khách hàng'),
(6, 'tài xế', 'một người tài xế'),
(7, 'vé công viên', '1 vé 1 người'),
(8, 'vé tham quan di tích', '1 người 1 vé'),
(9, '123', 'test');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_nguoidi`
--

CREATE TABLE `tour_nguoidi` (
  `nguoidi_id` int(11) NOT NULL,
  `doan_id` int(11) NOT NULL,
  `nguoidi_dsnhanvien` text COLLATE utf8_unicode_ci NOT NULL COMMENT 'lưu danh sách mã số nhân viên đi (json)',
  `nguoidi_dskhach` text COLLATE utf8_unicode_ci NOT NULL COMMENT 'lưu danh sách mã số khách hàng (json)'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_nguoidi`
--

INSERT INTO `tour_nguoidi` (`nguoidi_id`, `doan_id`, `nguoidi_dsnhanvien`, `nguoidi_dskhach`) VALUES
(1, 1, '-1--3-', '-5--8--9--10-'),
(2, 2, '-2--9-', '-4--6--8--10-'),
(3, 3, '-1--7--4--6-', '-1--2--3--4--5--10--9-'),
(4, 4, '', '-1-');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_nhanvien`
--

CREATE TABLE `tour_nhanvien` (
  `nv_id` int(11) NOT NULL,
  `nv_ten` text COLLATE utf8_unicode_ci NOT NULL,
  `nv_sdt` text COLLATE utf8_unicode_ci NOT NULL,
  `nv_ngaysinh` date NOT NULL,
  `nv_email` text COLLATE utf8_unicode_ci NOT NULL,
  `nv_nhiemvu` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_nhanvien`
--

INSERT INTO `tour_nhanvien` (`nv_id`, `nv_ten`, `nv_sdt`, `nv_ngaysinh`, `nv_email`, `nv_nhiemvu`) VALUES
(1, 'nhân viên một', '0335698758', '1996-01-01', 'nhanvienmot@gmail.com', 'lái xe'),
(2, 'nhân viên hai', '0335698702', '1998-02-01', 'nhanvienhai@gmail.com', 'lái xe'),
(3, 'nhân viên ba', '0335698756', '1986-03-27', 'nhanvienba@gmail.com', 'hướng dẫn viên'),
(4, 'nhân viên bốn', '0335698755', '1996-04-11', 'nhanvienbon@gmail.com', 'hướng dẫn viên'),
(5, 'nhân viên năm', '0335698789', '1996-05-10', 'nhanviennam@gmail.com', 'lái xe'),
(6, 'nhân viên sáu', '0335698777', '1999-06-01', 'nhanviensau@gmail.com', 'hướng dẫn viên'),
(7, 'nhân viên bảy', '0335698666', '2000-07-07', 'nhanvienbay@gmail.com', 'phụ xe'),
(8, 'nhân viên tám', '0335698788', '1999-07-08', 'nhanvientam@gmail.com', 'lái xe'),
(9, 'nhân viên chín', '0335698769', '1995-05-05', 'nhanvienchin@gmail.com', 'hướng dẫn viên'),
(10, 'nhân viên mười', '0335698711', '2001-01-09', 'nhanvienmuoi@gmail.com', 'lái xe');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tours`
--
ALTER TABLE `tours`
  ADD PRIMARY KEY (`tour_id`),
  ADD KEY `loai_id` (`loai_id`);

--
-- Chỉ mục cho bảng `tour_chiphi`
--
ALTER TABLE `tour_chiphi`
  ADD PRIMARY KEY (`chiphi_id`,`doan_id`),
  ADD KEY `doan_id` (`doan_id`);

--
-- Chỉ mục cho bảng `tour_chitiet`
--
ALTER TABLE `tour_chitiet`
  ADD PRIMARY KEY (`ct_id`),
  ADD KEY `tour_id` (`tour_id`,`dd_id`),
  ADD KEY `dd_id` (`dd_id`);

--
-- Chỉ mục cho bảng `tour_diadiem`
--
ALTER TABLE `tour_diadiem`
  ADD PRIMARY KEY (`dd_id`);

--
-- Chỉ mục cho bảng `tour_doan`
--
ALTER TABLE `tour_doan`
  ADD PRIMARY KEY (`doan_id`),
  ADD KEY `tour_id` (`tour_id`);

--
-- Chỉ mục cho bảng `tour_gia`
--
ALTER TABLE `tour_gia`
  ADD PRIMARY KEY (`gia_id`),
  ADD KEY `tour_id` (`tour_id`);

--
-- Chỉ mục cho bảng `tour_khachhang`
--
ALTER TABLE `tour_khachhang`
  ADD PRIMARY KEY (`kh_id`);

--
-- Chỉ mục cho bảng `tour_loai`
--
ALTER TABLE `tour_loai`
  ADD PRIMARY KEY (`loai_id`);

--
-- Chỉ mục cho bảng `tour_loaichiphi`
--
ALTER TABLE `tour_loaichiphi`
  ADD PRIMARY KEY (`cp_id`);

--
-- Chỉ mục cho bảng `tour_nguoidi`
--
ALTER TABLE `tour_nguoidi`
  ADD PRIMARY KEY (`nguoidi_id`,`doan_id`),
  ADD KEY `doan_id` (`doan_id`);

--
-- Chỉ mục cho bảng `tour_nhanvien`
--
ALTER TABLE `tour_nhanvien`
  ADD PRIMARY KEY (`nv_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tours`
--
ALTER TABLE `tours`
  MODIFY `tour_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `tour_chiphi`
--
ALTER TABLE `tour_chiphi`
  MODIFY `chiphi_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `tour_chitiet`
--
ALTER TABLE `tour_chitiet`
  MODIFY `ct_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `tour_diadiem`
--
ALTER TABLE `tour_diadiem`
  MODIFY `dd_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `tour_doan`
--
ALTER TABLE `tour_doan`
  MODIFY `doan_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `tour_gia`
--
ALTER TABLE `tour_gia`
  MODIFY `gia_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `tour_khachhang`
--
ALTER TABLE `tour_khachhang`
  MODIFY `kh_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `tour_loai`
--
ALTER TABLE `tour_loai`
  MODIFY `loai_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `tour_loaichiphi`
--
ALTER TABLE `tour_loaichiphi`
  MODIFY `cp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `tour_nguoidi`
--
ALTER TABLE `tour_nguoidi`
  MODIFY `nguoidi_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `tour_nhanvien`
--
ALTER TABLE `tour_nhanvien`
  MODIFY `nv_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tours`
--
ALTER TABLE `tours`
  ADD CONSTRAINT `tours_ibfk_1` FOREIGN KEY (`loai_id`) REFERENCES `tour_loai` (`loai_id`);

--
-- Các ràng buộc cho bảng `tour_chiphi`
--
ALTER TABLE `tour_chiphi`
  ADD CONSTRAINT `tour_chiphi_ibfk_1` FOREIGN KEY (`doan_id`) REFERENCES `tour_doan` (`doan_id`);

--
-- Các ràng buộc cho bảng `tour_chitiet`
--
ALTER TABLE `tour_chitiet`
  ADD CONSTRAINT `tour_chitiet_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`),
  ADD CONSTRAINT `tour_chitiet_ibfk_2` FOREIGN KEY (`dd_id`) REFERENCES `tour_diadiem` (`dd_id`);

--
-- Các ràng buộc cho bảng `tour_doan`
--
ALTER TABLE `tour_doan`
  ADD CONSTRAINT `tour_doan_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`);

--
-- Các ràng buộc cho bảng `tour_gia`
--
ALTER TABLE `tour_gia`
  ADD CONSTRAINT `tour_gia_ibfk_1` FOREIGN KEY (`tour_id`) REFERENCES `tours` (`tour_id`);

--
-- Các ràng buộc cho bảng `tour_nguoidi`
--
ALTER TABLE `tour_nguoidi`
  ADD CONSTRAINT `tour_nguoidi_ibfk_1` FOREIGN KEY (`doan_id`) REFERENCES `tour_doan` (`doan_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
