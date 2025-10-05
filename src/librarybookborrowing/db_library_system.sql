-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2025 at 03:37 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_library_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_accounts`
--

CREATE TABLE `tbl_accounts` (
  `fld_account_id` int(11) NOT NULL,
  `fld_username` varchar(50) NOT NULL,
  `fld_password` varchar(255) NOT NULL,
  `fld_role` varchar(10) NOT NULL,
  `fld_staff_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_accounts`
--

INSERT INTO `tbl_accounts` (`fld_account_id`, `fld_username`, `fld_password`, `fld_role`, `fld_staff_id`) VALUES
(1, 'kylevin', 'cc7d240bed51883786b24756316eddf520680f1686fba69e6e11e07e4e389d5e', 'Admin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_book`
--

CREATE TABLE `tbl_book` (
  `fld_book_id` int(11) NOT NULL,
  `fld_title` varchar(300) NOT NULL,
  `fld_author` varchar(45) NOT NULL,
  `fld_publisher` varchar(45) NOT NULL,
  `fld_year_published` int(4) DEFAULT NULL,
  `fld_quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_book`
--

INSERT INTO `tbl_book` (`fld_book_id`, `fld_title`, `fld_author`, `fld_publisher`, `fld_year_published`, `fld_quantity`) VALUES
(1, 'Harry Potter and the Sorcerer’s Stone', 'J.K. Rowling', 'Bloomsburry', 1997, 5),
(2, 'Harry Potter and the Chamber of Secrets ', 'J.K. Rowling', 'Bloomsburry', 1998, 5),
(3, 'Harry Potter and the Prisoner of Azkaban ', 'J.K. Rowling', 'Bloomsburry', 1999, 5),
(4, 'Harry Potter and the Goblet of Fire', 'J.K. Rowling', 'Bloomsburry', 2000, 4),
(5, 'Harry Potter and the Order of the Phoenix', 'J.K. Rowling', 'Bloomsburry', 2003, 5),
(6, 'Harry Potter and the Half-Blood Prince ', 'J.K. Rowling', 'Bloomsburry', 2005, 4),
(7, 'Harry Potter and the Deathly Hallows', 'J.K. Rowling', 'Bloomsburry', 2007, 5),
(8, 'Harry Potter and the Cursed Child', 'J.K. Rowling', 'Bloomsburry', 2016, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_member`
--

CREATE TABLE `tbl_member` (
  `fld_member_id` int(11) NOT NULL,
  `fld_first_name` varchar(45) NOT NULL,
  `fld_middle_name` varchar(45) DEFAULT NULL,
  `fld_last_name` varchar(45) NOT NULL,
  `fld_phone_number` varchar(15) NOT NULL,
  `fld_email` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_member`
--

INSERT INTO `tbl_member` (`fld_member_id`, `fld_first_name`, `fld_middle_name`, `fld_last_name`, `fld_phone_number`, `fld_email`) VALUES
(1, 'Calvin', 'F', 'Nocon', '09987651234', 'sj10.cknocon24@joysistvi.edu.ph'),
(2, 'Kyle', 'Calvin', 'Nocon', '09321459867', 'sj10.cknocon24@joysistvi.edu.ph1'),
(3, 'Jin', 'Woo', 'Sung', '09987651234', 'sungjinwoo@email.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_staff`
--

CREATE TABLE `tbl_staff` (
  `fld_staff_id` int(11) NOT NULL,
  `fld_first_name` varchar(45) NOT NULL,
  `fld_middle_name` varchar(45) DEFAULT NULL,
  `fld_last_name` varchar(45) NOT NULL,
  `fld_email` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_staff`
--

INSERT INTO `tbl_staff` (`fld_staff_id`, `fld_first_name`, `fld_middle_name`, `fld_last_name`, `fld_email`) VALUES
(1, 'Calvin Kyle', 'Fortaleza', 'Nocon', 'sj10.cknocon24@joysistvi.edu.ph');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaction`
--

CREATE TABLE `tbl_transaction` (
  `fld_transaction_id` int(11) NOT NULL,
  `fld_reference_id` varchar(15) NOT NULL,
  `fld_member_id` int(11) NOT NULL,
  `fld_issuer_staff_id` int(11) NOT NULL,
  `fld_book_id` int(11) NOT NULL,
  `fld_borrow_date` datetime NOT NULL,
  `fld_due_date` datetime NOT NULL,
  `fld_return_date` datetime DEFAULT NULL,
  `fld_reveiver_staff_id` int(11) DEFAULT NULL,
  `fld_status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_transaction`
--

INSERT INTO `tbl_transaction` (`fld_transaction_id`, `fld_reference_id`, `fld_member_id`, `fld_issuer_staff_id`, `fld_book_id`, `fld_borrow_date`, `fld_due_date`, `fld_return_date`, `fld_reveiver_staff_id`, `fld_status`) VALUES
(3, '84TCMO5H', 12, 5, 7, '2025-09-30 00:00:00', '2025-10-07 00:00:00', NULL, NULL, 'Borrowed'),
(4, '0STB4ZEP', 11, 6, 8, '2025-09-30 00:00:00', '2025-10-07 00:00:00', NULL, NULL, 'Borrowed'),
(5, '5B2VC0DO', 1, 1, 1, '2025-10-01 00:00:00', '2025-10-06 00:00:00', NULL, NULL, 'Borrowed'),
(6, 'VM004PJM', 1, 1, 1, '2025-10-01 00:00:00', '2025-10-06 00:00:00', NULL, NULL, 'Borrowed'),
(7, 'NVQM9CUL', 1, 1, 1, '2025-10-01 00:00:00', '2025-10-06 00:00:00', NULL, NULL, 'Borrowed'),
(8, 'NVQM9CUL', 1, 1, 1, '2025-10-01 00:00:00', '2025-10-06 00:00:00', NULL, NULL, 'Borrowed'),
(9, '38R876P9', 1, 1, 1, '2025-10-01 21:50:00', '2025-10-06 21:50:00', NULL, NULL, 'Borrowed'),
(10, 'AH9V3MMD', 1, 1, 1, '2025-10-02 16:39:00', '2025-10-07 16:39:00', NULL, NULL, 'Borrowed'),
(11, 'AH9V3MMD', 1, 1, 1, '2025-10-02 16:39:00', '2025-10-07 16:39:00', NULL, NULL, 'Borrowed'),
(12, 'AH9V3MMD', 1, 1, 1, '2025-10-02 16:39:00', '2025-10-07 16:39:00', NULL, NULL, 'Borrowed'),
(13, 'AH9V3MMD', 1, 1, 1, '2025-10-02 16:39:00', '2025-10-07 16:39:00', NULL, NULL, 'Borrowed'),
(14, 'HI67DOGC', 3, 1, 1, '2025-10-02 17:05:09', '2025-10-02 17:04:00', NULL, NULL, 'Borrowed'),
(15, 'HI67DOGC', 3, 1, 1, '2025-10-02 17:05:09', '2025-10-02 17:04:00', NULL, NULL, 'Borrowed'),
(16, 'P37EOXOW', 0, 1, 1, '2025-10-02 19:09:12', '2025-10-02 19:08:00', NULL, NULL, 'Borrowed'),
(17, 'KS9UG8KF', 3, 1, 8, '2025-10-02 19:38:38', '2025-10-02 19:38:00', NULL, NULL, 'Borrowed'),
(18, 'KS9UG8KF', 3, 1, 8, '2025-10-02 19:38:38', '2025-10-02 19:38:00', NULL, NULL, 'Borrowed'),
(21, 'VAWIH5ZD', 2, 1, 1, '2025-10-02 20:45:03', '2025-10-02 20:44:00', NULL, NULL, 'Borrowed'),
(22, 'VAWIH5ZD', 2, 1, 8, '2025-10-02 20:45:03', '2025-10-02 20:44:00', NULL, NULL, 'Borrowed'),
(23, '43TJYI74', 3, 1, 2, '2025-10-02 21:34:17', '2025-10-02 21:33:00', NULL, NULL, 'Borrowed'),
(24, '16FJTO87', 3, 1, 2, '2025-10-02 21:34:21', '2025-10-02 21:33:00', NULL, NULL, 'Borrowed'),
(25, 'WB47TYFA', 1, 1, 3, '2025-10-02 21:35:34', '2025-10-02 21:34:00', NULL, NULL, 'Borrowed'),
(26, 'WB47TYFA', 1, 1, 8, '2025-10-02 21:35:34', '2025-10-02 21:34:00', NULL, NULL, 'Borrowed'),
(27, 'WB47TYFA', 1, 1, 4, '2025-10-02 21:35:34', '2025-10-02 21:34:00', NULL, NULL, 'Borrowed'),
(28, 'WB47TYFA', 1, 1, 4, '2025-10-02 21:35:34', '2025-10-02 21:34:00', NULL, NULL, 'Borrowed'),
(29, '9F32WSWT', 2, 1, 2, '2025-10-02 21:37:02', '2025-10-02 21:36:00', NULL, NULL, 'Borrowed'),
(30, 'O3O0K6CY', 3, 1, 2, '2025-10-02 21:38:10', '2025-10-02 21:37:00', NULL, NULL, 'Borrowed'),
(31, 'O3O0K6CY', 3, 1, 2, '2025-10-02 21:38:10', '2025-10-02 21:37:00', NULL, NULL, 'Borrowed'),
(32, 'SAD5D7RK', 3, 1, 2, '2025-10-02 21:38:53', '2025-10-02 21:37:00', NULL, NULL, 'Borrowed'),
(33, 'QSKZXSIL', 3, 1, 1, '2025-10-02 21:54:56', '2025-10-02 21:54:00', NULL, NULL, 'Borrowed'),
(34, 'QSKZXSIL', 3, 1, 4, '2025-10-02 21:54:56', '2025-10-02 21:54:00', NULL, NULL, 'Borrowed'),
(35, 'XLLVNTW0', 2, 1, 2, '2025-10-02 21:58:39', '2025-10-02 21:57:00', NULL, NULL, 'Borrowed'),
(36, 'Z3L3ZZLY', 1, 1, 1, '2025-10-02 22:17:15', '2025-10-02 22:16:00', NULL, NULL, 'Borrowed'),
(37, 'Z3L3ZZLY', 1, 1, 2, '2025-10-02 22:17:15', '2025-10-02 22:16:00', NULL, NULL, 'Borrowed'),
(38, 'Z3L3ZZLY', 1, 1, 3, '2025-10-02 22:17:15', '2025-10-02 22:16:00', NULL, NULL, 'Borrowed'),
(39, 'Z3L3ZZLY', 1, 1, 4, '2025-10-02 22:17:15', '2025-10-02 22:16:00', NULL, NULL, 'Borrowed'),
(40, '9ON07KP7', 2, 1, 1, '2025-10-02 23:07:47', '2025-10-02 23:06:00', NULL, NULL, 'Borrowed'),
(41, '20251002YXWYS', 3, 1, 2, '2025-10-02 23:35:32', '2025-10-02 23:35:00', NULL, NULL, 'Borrowed'),
(42, '20251003QSAMJ', 3, 1, 1, '2025-10-03 16:08:25', '2025-10-03 16:08:00', NULL, NULL, 'Borrowed'),
(43, '20251003MSRPU', 2, 1, 3, '2025-10-03 17:43:40', '2025-10-03 17:43:00', NULL, NULL, 'Borrowed'),
(44, '20251004AZTVI', 3, 1, 1, '2025-10-04 10:59:29', '2025-10-04 10:59:00', NULL, NULL, 'Borrowed'),
(45, '20251004CPWCP', 3, 1, 2, '2025-10-04 10:59:51', '2025-10-04 10:59:00', NULL, NULL, 'Borrowed'),
(46, '20251004ROQYI', 3, 1, 8, '2025-10-04 13:17:27', '2025-10-04 13:17:00', NULL, NULL, 'Borrowed'),
(47, '20251004ECIGJ', 3, 1, 2, '2025-10-04 16:46:16', '2025-10-04 16:46:00', NULL, NULL, 'Borrowed'),
(48, '20251004UUOCN', 3, 1, 4, '2025-10-04 16:46:37', '2025-10-04 16:46:00', NULL, NULL, 'Borrowed'),
(49, '20251004UUOCN', 3, 1, 4, '2025-10-04 16:46:37', '2025-10-04 16:46:00', NULL, NULL, 'Borrowed'),
(50, '20251004IWLWQ', 3, 1, 6, '2025-10-04 17:48:13', '2025-10-04 17:47:00', NULL, NULL, 'Borrowed'),
(51, '20251004IWLWQ', 3, 1, 4, '2025-10-04 17:48:13', '2025-10-04 17:47:00', NULL, NULL, 'Borrowed'),
(52, '20251004LMYZN', 2, 1, 3, '2025-10-04 17:50:44', '2025-10-04 17:49:00', NULL, NULL, 'Borrowed'),
(53, '20251004FQLWT', 1, 1, 3, '2025-10-04 17:53:44', '2025-10-04 17:53:00', NULL, NULL, 'Borrowed'),
(54, '20251004UHRUT', 3, 1, 5, '2025-10-04 17:54:55', '2025-10-04 17:54:00', NULL, NULL, 'Borrowed'),
(55, '20251004UHRUT', 3, 1, 5, '2025-10-04 17:54:55', '2025-10-04 17:54:00', NULL, NULL, 'Borrowed'),
(56, '20251004DYYXW', 3, 1, 5, '2025-10-04 17:55:38', '2025-10-04 17:55:00', NULL, NULL, 'Borrowed'),
(57, '20251004TFPBJ', 3, 1, 5, '2025-10-04 18:03:38', '2025-10-04 18:03:00', NULL, NULL, 'Borrowed'),
(58, '20251005XIGEN', 3, 1, 4, '2025-10-05 12:26:25', '2025-10-05 12:25:00', NULL, NULL, 'Borrowed');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  ADD PRIMARY KEY (`fld_account_id`),
  ADD KEY `fld_staff_id` (`fld_staff_id`);

--
-- Indexes for table `tbl_book`
--
ALTER TABLE `tbl_book`
  ADD PRIMARY KEY (`fld_book_id`);

--
-- Indexes for table `tbl_member`
--
ALTER TABLE `tbl_member`
  ADD PRIMARY KEY (`fld_member_id`);

--
-- Indexes for table `tbl_staff`
--
ALTER TABLE `tbl_staff`
  ADD PRIMARY KEY (`fld_staff_id`);

--
-- Indexes for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  ADD PRIMARY KEY (`fld_transaction_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  MODIFY `fld_account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_book`
--
ALTER TABLE `tbl_book`
  MODIFY `fld_book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `fld_member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_staff`
--
ALTER TABLE `tbl_staff`
  MODIFY `fld_staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  MODIFY `fld_transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  ADD CONSTRAINT `tbl_accounts_ibfk_1` FOREIGN KEY (`fld_staff_id`) REFERENCES `tbl_staff` (`fld_staff_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
