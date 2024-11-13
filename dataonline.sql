-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 20, 2024 lúc 08:44 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dataonline`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `iddonhang` int(11) NOT NULL,
  `idsp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `gia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `diachi` text NOT NULL,
  `sodienthoai` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `soluong` int(11) NOT NULL,
  `tongtien` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(100) NOT NULL,
  `hinhanh` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `hinhanh`) VALUES
(1, 'Trang chủ', '\r\nhttps://ngochieu.name.vn/img/home.png'),
(2, 'Điện thoại', 'https://ngochieu.name.vn/img/mobile.png'),
(3, 'LapTop', 'https://ngochieu.name.vn/img/laptop.png'),
(4, 'Liên Hệ', 'https://ngochieu.name.vn/img/contact.png'),
(5, 'Thông tin', 'https://ngochieu.name.vn/img/info.png'),
(6, 'Đơn hàng', 'https://ngochieu.name.vn/img/info.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanphammoi`
--

CREATE TABLE `sanphammoi` (
  `id` int(11) NOT NULL,
  `tensp` varchar(250) NOT NULL,
  `giasp` varchar(100) NOT NULL,
  `hinhanh` text NOT NULL,
  `mota` text NOT NULL,
  `loai` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanphammoi`
--

INSERT INTO `sanphammoi` (`id`, `tensp`, `giasp`, `hinhanh`, `mota`, `loai`) VALUES
(1, 'Laptop HP 15s-fq5080TU', '14.999.000', 'https://hanoicomputercdn.com/media/product/67862_hacom_hp_15_16.jpeg', '\"CPU: Intel Core i5 1235U\r\nRAM: 8GB\r\nỔ cứng: 256GB SSD\r\nVGA: Onboard\r\nMàn hình: 15.6 inch FHD\r\nHĐH: Win 11\"', 2),
(2, 'Laptop Asus VivoBook M513UA-EJ710W', '17.599.000₫ ', 'https://hanoicomputercdn.com/media/product/70200_laptop_asus_vivobook_m513ua_8.png', '\"CPU: AMD Ryzen 7 5700U (1.8Ghz upto 4.3GHz, 16MB l3, 4MB L2)\r\nRAM: 8GB DDR4 onboard + 8GB cắm rời\r\nỔ cứng: 512GB M.2 NVMe™ PCIe® 3.0 SSD (không hỗ trợ ổ 2.5)\r\nVGA: AMD Radeon Graphics\r\nMàn hình: 15.6 WUXGA (1920 x 1200) 16:10 , IPS, 300nits, 45% NTSC\r\nMàu sắc: Bạc\"', 2),
(3, 'Laptop Dell Vostro 3520', '16.299.000₫ ', 'https://hanoicomputercdn.com/media/product/69589_laptop_dell_vostro_3520_25.png', '\"CPU: Intel Core i5 1235U (upto 4.4Ghz/12MB cache)\r\nRAM: 8GB DDR4 2666Mhz (1*8GB)\r\nỔ cứng: 512GB PCIe NVMe SSD\r\nVGA: Intel UHD Graphics\r\nMàn hình: 15.6 inch FHD (1920 x 1080) 250 nits WVA Anti-Glare LED Backlit\r\nMàu sắc: Xám\"', 2),
(4, 'iPhone 13 Pro 128GB Vàng', '27.399.000', 'https://hanoicomputercdn.com/media/product/64690_iphone_13_pro_max_2.png', '\"Công nghệ màn hình: OLED\r\nĐộ phân giải: 1170 x 2532 Pixels, 2 camera 12 MP, 12 MP\r\nMàn hình rộng: 6.1\"\"\r\nHệ điều hành: iOS 14\r\nChip xử lý (CPU): Apple A14 Bionic 6 nhân\r\nBộ nhớ trong (ROM): 128GB\"', 2),
(5, 'Máy Điện Thoại Alcatel H3P', '2.399.000', 'https://hanoicomputercdn.com/media/product/68239_may_dien_thoai_alcatel_h3p__2_.jpg', '\"Điện thoại song công có màn hình B&W 2,8 inch có đèn nền\r\nCác linh kiện và CPU được chọn lọc để có hiệu suất mạnh mẽ\r\nTiêu thụ điện năng thấp\r\nNhiều phím dòng / có thể lập trình hơn và phím quay số nhanh\r\nCác cổng kết nối bên ngoài\r\nKhả năng tương thích phong phú có thể dễ dàng triển khai\"', 1),
(6, 'OPPO Reno8 T 5G 256GB', '2.399.000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/303575/xiaomi-redmi-12c-grey-thumb-600x600.jpg', '\"\"\"Màn hình 6.78 inch, AMOLED, FHD+, 2448 x 1080 Pixels\r\nCamera sau 50.0 MP + 13.0 MP + 5.0 MP\r\nCamera Selfie 12.0 MP\r\nRAM 12 GB\r\nBộ nhớ trong 256 GB\r\nCPU Snapdragon 8+ Gen 1\"\"\"', 1),
(7, 'Laptop HP 15s-fq5080TU', '14.999.000', 'https://hanoicomputercdn.com/media/product/67862_hacom_hp_15_16.jpeg', '\"CPU: Intel Core i5 1235U\r\nRAM: 8GB\r\nỔ cứng: 256GB SSD\r\nVGA: Onboard\r\nMàn hình: 15.6 inch FHD\r\nHĐH: Win 11\"', 2),
(8, 'Laptop Asus VivoBook M513UA-EJ710W', '17.599.000₫ ', 'https://hanoicomputercdn.com/media/product/70200_laptop_asus_vivobook_m513ua_8.png', '\"CPU: AMD Ryzen 7 5700U (1.8Ghz upto 4.3GHz, 16MB l3, 4MB L2)\r\nRAM: 8GB DDR4 onboard + 8GB cắm rời\r\nỔ cứng: 512GB M.2 NVMe™ PCIe® 3.0 SSD (không hỗ trợ ổ 2.5)\r\nVGA: AMD Radeon Graphics\r\nMàn hình: 15.6 WUXGA (1920 x 1200) 16:10 , IPS, 300nits, 45% NTSC\r\nMàu sắc: Bạc\"', 2),
(9, 'Laptop Dell Vostro 3520', '16.299.000₫ ', 'https://hanoicomputercdn.com/media/product/69589_laptop_dell_vostro_3520_25.png', '\"CPU: Intel Core i5 1235U (upto 4.4Ghz/12MB cache)\r\nRAM: 8GB DDR4 2666Mhz (1*8GB)\r\nỔ cứng: 512GB PCIe NVMe SSD\r\nVGA: Intel UHD Graphics\r\nMàn hình: 15.6 inch FHD (1920 x 1080) 250 nits WVA Anti-Glare LED Backlit\r\nMàu sắc: Xám\"', 2),
(10, 'iPhone 13 Pro 128GB Vàng', '27.399.000', 'https://hanoicomputercdn.com/media/product/64690_iphone_13_pro_max_2.png', '\"Công nghệ màn hình: OLED\r\nĐộ phân giải: 1170 x 2532 Pixels, 2 camera 12 MP, 12 MP\r\nMàn hình rộng: 6.1\"\"\r\nHệ điều hành: iOS 14\r\nChip xử lý (CPU): Apple A14 Bionic 6 nhân\r\nBộ nhớ trong (ROM): 128GB\"', 2),
(11, 'Máy Điện Thoại Alcatel H3P', '2.399.000', 'https://hanoicomputercdn.com/media/product/68239_may_dien_thoai_alcatel_h3p__2_.jpg', '\"Điện thoại song công có màn hình B&W 2,8 inch có đèn nền\r\nCác linh kiện và CPU được chọn lọc để có hiệu suất mạnh mẽ\r\nTiêu thụ điện năng thấp\r\nNhiều phím dòng / có thể lập trình hơn và phím quay số nhanh\r\nCác cổng kết nối bên ngoài\r\nKhả năng tương thích phong phú có thể dễ dàng triển khai\"', 1),
(12, 'OPPO Reno8 T 5G 256GB', '2.399.000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/303575/xiaomi-redmi-12c-grey-thumb-600x600.jpg', '\"\"\"Màn hình 6.78 inch, AMOLED, FHD+, 2448 x 1080 Pixels\r\nCamera sau 50.0 MP + 13.0 MP + 5.0 MP\r\nCamera Selfie 12.0 MP\r\nRAM 12 GB\r\nBộ nhớ trong 256 GB\r\nCPU Snapdragon 8+ Gen 1\"\"\"', 1),
(13, 'Laptop HP 15s-fq5080TU', '14.999.000', 'https://hanoicomputercdn.com/media/product/67862_hacom_hp_15_16.jpeg', '\"CPU: Intel Core i5 1235U\r\nRAM: 8GB\r\nỔ cứng: 256GB SSD\r\nVGA: Onboard\r\nMàn hình: 15.6 inch FHD\r\nHĐH: Win 11\"', 2),
(14, 'Laptop Asus VivoBook M513UA-EJ710W', '17.599.000₫ ', 'https://hanoicomputercdn.com/media/product/70200_laptop_asus_vivobook_m513ua_8.png', '\"CPU: AMD Ryzen 7 5700U (1.8Ghz upto 4.3GHz, 16MB l3, 4MB L2)\r\nRAM: 8GB DDR4 onboard + 8GB cắm rời\r\nỔ cứng: 512GB M.2 NVMe™ PCIe® 3.0 SSD (không hỗ trợ ổ 2.5)\r\nVGA: AMD Radeon Graphics\r\nMàn hình: 15.6 WUXGA (1920 x 1200) 16:10 , IPS, 300nits, 45% NTSC\r\nMàu sắc: Bạc\"', 2),
(15, 'Laptop Dell Vostro 3520', '16.299.000₫ ', 'https://hanoicomputercdn.com/media/product/69589_laptop_dell_vostro_3520_25.png', '\"CPU: Intel Core i5 1235U (upto 4.4Ghz/12MB cache)\r\nRAM: 8GB DDR4 2666Mhz (1*8GB)\r\nỔ cứng: 512GB PCIe NVMe SSD\r\nVGA: Intel UHD Graphics\r\nMàn hình: 15.6 inch FHD (1920 x 1080) 250 nits WVA Anti-Glare LED Backlit\r\nMàu sắc: Xám\"', 2),
(16, 'iPhone 13 Pro 128GB Vàng', '27.399.000', 'https://hanoicomputercdn.com/media/product/64690_iphone_13_pro_max_2.png', '\"Công nghệ màn hình: OLED\r\nĐộ phân giải: 1170 x 2532 Pixels, 2 camera 12 MP, 12 MP\r\nMàn hình rộng: 6.1\"\"\r\nHệ điều hành: iOS 14\r\nChip xử lý (CPU): Apple A14 Bionic 6 nhân\r\nBộ nhớ trong (ROM): 128GB\"', 2),
(17, 'Máy Điện Thoại Alcatel H3P', '2.399.000', 'https://hanoicomputercdn.com/media/product/68239_may_dien_thoai_alcatel_h3p__2_.jpg', '\"Điện thoại song công có màn hình B&W 2,8 inch có đèn nền\r\nCác linh kiện và CPU được chọn lọc để có hiệu suất mạnh mẽ\r\nTiêu thụ điện năng thấp\r\nNhiều phím dòng / có thể lập trình hơn và phím quay số nhanh\r\nCác cổng kết nối bên ngoài\r\nKhả năng tương thích phong phú có thể dễ dàng triển khai\"', 1),
(18, 'OPPO Reno8 T 5G 256GB', '2.399.000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/303575/xiaomi-redmi-12c-grey-thumb-600x600.jpg', '\"\"\"Màn hình 6.78 inch, AMOLED, FHD+, 2448 x 1080 Pixels\r\nCamera sau 50.0 MP + 13.0 MP + 5.0 MP\r\nCamera Selfie 12.0 MP\r\nRAM 12 GB\r\nBộ nhớ trong 256 GB\r\nCPU Snapdragon 8+ Gen 1\"\"\"', 1),
(19, 'Laptop HP 15s-fq5080TU', '14.999.000', 'https://hanoicomputercdn.com/media/product/67862_hacom_hp_15_16.jpeg', '\"CPU: Intel Core i5 1235U\r\nRAM: 8GB\r\nỔ cứng: 256GB SSD\r\nVGA: Onboard\r\nMàn hình: 15.6 inch FHD\r\nHĐH: Win 11\"', 2),
(20, 'Laptop Asus VivoBook M513UA-EJ710W', '17.599.000₫ ', 'https://hanoicomputercdn.com/media/product/70200_laptop_asus_vivobook_m513ua_8.png', '\"CPU: AMD Ryzen 7 5700U (1.8Ghz upto 4.3GHz, 16MB l3, 4MB L2)\r\nRAM: 8GB DDR4 onboard + 8GB cắm rời\r\nỔ cứng: 512GB M.2 NVMe™ PCIe® 3.0 SSD (không hỗ trợ ổ 2.5)\r\nVGA: AMD Radeon Graphics\r\nMàn hình: 15.6 WUXGA (1920 x 1200) 16:10 , IPS, 300nits, 45% NTSC\r\nMàu sắc: Bạc\"', 2),
(21, 'Laptop Dell Vostro 3520', '16.299.000₫ ', 'https://hanoicomputercdn.com/media/product/69589_laptop_dell_vostro_3520_25.png', '\"CPU: Intel Core i5 1235U (upto 4.4Ghz/12MB cache)\r\nRAM: 8GB DDR4 2666Mhz (1*8GB)\r\nỔ cứng: 512GB PCIe NVMe SSD\r\nVGA: Intel UHD Graphics\r\nMàn hình: 15.6 inch FHD (1920 x 1080) 250 nits WVA Anti-Glare LED Backlit\r\nMàu sắc: Xám\"', 2),
(22, 'iPhone 13 Pro 128GB Vàng', '27.399.000', 'https://hanoicomputercdn.com/media/product/64690_iphone_13_pro_max_2.png', '\"Công nghệ màn hình: OLED\r\nĐộ phân giải: 1170 x 2532 Pixels, 2 camera 12 MP, 12 MP\r\nMàn hình rộng: 6.1\"\"\r\nHệ điều hành: iOS 14\r\nChip xử lý (CPU): Apple A14 Bionic 6 nhân\r\nBộ nhớ trong (ROM): 128GB\"', 2),
(23, 'Máy Điện Thoại Alcatel H3P', '2.399.000', 'https://hanoicomputercdn.com/media/product/68239_may_dien_thoai_alcatel_h3p__2_.jpg', '\"Điện thoại song công có màn hình B&W 2,8 inch có đèn nền\r\nCác linh kiện và CPU được chọn lọc để có hiệu suất mạnh mẽ\r\nTiêu thụ điện năng thấp\r\nNhiều phím dòng / có thể lập trình hơn và phím quay số nhanh\r\nCác cổng kết nối bên ngoài\r\nKhả năng tương thích phong phú có thể dễ dàng triển khai\"', 1),
(24, 'OPPO Reno8 T 5G 256GB', '2.399.000', 'https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/42/303575/xiaomi-redmi-12c-grey-thumb-600x600.jpg', '\"\"\"Màn hình 6.78 inch, AMOLED, FHD+, 2448 x 1080 Pixels\r\nCamera sau 50.0 MP + 13.0 MP + 5.0 MP\r\nCamera Selfie 12.0 MP\r\nRAM 12 GB\r\nBộ nhớ trong 256 GB\r\nCPU Snapdragon 8+ Gen 1\"\"\"', 1),
(25, 'tesst1', '145000', 'chua co', 'chua co', 1),
(28, 'tesst2', '245000', 'chua co', 'chua co', 1),
(29, 'tesst3', '145000', 'chua co', 'chua co', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `email` varchar(250) NOT NULL,
  `pass` varchar(250) NOT NULL,
  `username` varchar(100) NOT NULL,
  `mobile` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `pass`, `username`, `mobile`) VALUES
(7, 'htoan6278@gmail.com', '1309', 'hoang toan', '0696969'),
(8, 'toan@gmail.com', '123', 't', '0797070'),
(9, 'teo@gmail.com', '123', 'teo', '0405457'),
(10, 'vy@gmail.com', '123', 'vy', '-8660563');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
