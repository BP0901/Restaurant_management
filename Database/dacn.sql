-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 31, 2022 lúc 12:35 PM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dacn`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categories`
--

CREATE TABLE `categories` (
  `ID` int(11) NOT NULL,
  `category` varchar(45) COLLATE utf8_vietnamese_ci NOT NULL,
  `picture` varchar(100) COLLATE utf8_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `categories`
--

INSERT INTO `categories` (`ID`, `category`, `picture`) VALUES
(17, 'Burger', 'Burger.jpg'),
(18, 'Pizza', 'pizza.jpg'),
(22, 'Lẩu', 'hotpot.jpg'),
(23, 'Nước', 'drink.jpg'),
(24, 'Món Chiên', 'fry.jpg'),
(83, 'Soup', 'IMG1178547354.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `foods`
--

CREATE TABLE `foods` (
  `id` int(11) NOT NULL,
  `namefood` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL,
  `price` float NOT NULL,
  `discount` int(11) NOT NULL,
  `picture` varchar(100) COLLATE utf8_vietnamese_ci DEFAULT NULL,
  `category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `foods`
--

INSERT INTO `foods` (`id`, `namefood`, `price`, `discount`, `picture`, `category`) VALUES
(1, 'Coca-Cola', 12000, 0, 'coca-cola.jpg', 23),
(2, 'Number 1', 12000, 0, 'Number1.jpg', 23),
(4, 'Burger Bò', 30000, 0, 'burgerbo .jpg', 17),
(5, 'Buger gà', 30000, 28000, 'burgerbo .jpg', 17),
(6, 'Pizza hải sản', 200000, 190000, 'pizza-hai-san.jpg', 18),
(7, 'Pizza Cheese Lover', 180000, 0, 'pizza-cheese-lover.jpg', 18),
(8, 'Lầu bò', 150000, 0, 'lau-bo-ti-chuot-150597.jpg', 22),
(9, 'Đùi gà chiên', 35000, 30000, 'dui-ga-chien.jpg', 24),
(10, 'Cách gà chiên', 30000, 0, 'canh-ga-chien-xi-dau.jpg', 24),
(11, 'Burger trứng', 25000, 0, 'burger-ga-trung.jpg', 17),
(12, 'Lẩu cá', 160000, 150000, 'lau-ca-hoi.jpg', 22),
(13, 'Pizza xúc xích', 210000, 0, 'pizza-xuc-xich-1.jpg', 18),
(14, 'Pepsi', 12000, 0, 'pepsi.jpg', 23);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tablefood`
--

CREATE TABLE `tablefood` (
  `id` int(11) NOT NULL,
  `TableName` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `tablefood`
--

INSERT INTO `tablefood` (`id`, `TableName`) VALUES
(1, 'Table 1'),
(2, 'Table 2'),
(3, 'Table 3'),
(4, 'Table 4'),
(5, 'Table 5'),
(6, 'Table 6'),
(7, 'Table 7'),
(8, 'Table 8'),
(9, 'Table 9'),
(10, 'Table 10'),
(11, 'Table 11'),
(12, 'Table 12'),
(13, 'Table 13'),
(14, 'Table 14'),
(15, 'Table 15');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `accounttype` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`username`, `pass`, `accounttype`) VALUES
('admin', 'admin', 0),
('staff1', 'staff1', 1),
('staff2', 'staff2', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `foods`
--
ALTER TABLE `foods`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Food_Category_FK` (`category`);

--
-- Chỉ mục cho bảng `tablefood`
--
ALTER TABLE `tablefood`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `categories`
--
ALTER TABLE `categories`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT cho bảng `foods`
--
ALTER TABLE `foods`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `tablefood`
--
ALTER TABLE `tablefood`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `foods`
--
ALTER TABLE `foods`
  ADD CONSTRAINT `Food_Category_FK` FOREIGN KEY (`category`) REFERENCES `categories` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
