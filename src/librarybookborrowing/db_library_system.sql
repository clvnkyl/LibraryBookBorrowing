-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 30, 2025 at 05:08 PM
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
  `fld_title` varchar(30) NOT NULL,
  `fld_author` varchar(45) NOT NULL,
  `fld_publisher` varchar(45) NOT NULL,
  `fld_year_published` year(4) NOT NULL,
  `fld_quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_member`
--

CREATE TABLE `tbl_member` (
  `fld_member_id` int(11) NOT NULL,
  `fld_first_name` varchar(45) NOT NULL,
  `fld_middle_name` varchar(45) NOT NULL,
  `fld_last_name` varchar(45) NOT NULL,
  `fld_phone_number` varchar(15) NOT NULL,
  `fld_email` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
  `fld_reference_id` varchar(8) NOT NULL,
  `fld_member_id` int(11) NOT NULL,
  `fld_staff_id` int(11) NOT NULL,
  `fld_book_id` int(11) NOT NULL,
  `fld_borrow_date` date NOT NULL,
  `fld_due_date` date NOT NULL,
  `fld_return_date` datetime DEFAULT NULL,
  `fld_status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_transaction`
--

INSERT INTO `tbl_transaction` (`fld_transaction_id`, `fld_reference_id`, `fld_member_id`, `fld_staff_id`, `fld_book_id`, `fld_borrow_date`, `fld_due_date`, `fld_return_date`, `fld_status`) VALUES
(3, '84TCMO5H', 12, 5, 7, '2025-09-30', '2025-10-07', NULL, 'Borrowed'),
(4, '0STB4ZEP', 11, 6, 8, '2025-09-30', '2025-10-07', NULL, 'Borrowed');

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
  MODIFY `fld_book_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `fld_member_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_staff`
--
ALTER TABLE `tbl_staff`
  MODIFY `fld_staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  MODIFY `fld_transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
