-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 07, 2025 at 03:16 AM
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
  `fld_role` varchar(20) NOT NULL,
  `fld_staff_id` int(11) DEFAULT NULL,
  `fld_member_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_accounts`
--

INSERT INTO `tbl_accounts` (`fld_account_id`, `fld_username`, `fld_password`, `fld_role`, `fld_staff_id`, `fld_member_id`) VALUES
(1, 'admin', '5c06eb3d5a05a19f49476d694ca81a36344660e9d5b98e3d6a6630f31c2422e7', 'Admin', 1, NULL),
(2, 'librarian', 'e370f928794e5621660058a4247e92d0b6edcf39ef923b3167f4f2c3480d4d99', 'Librarian', 2, NULL),
(3, 'joyboy', '7c1aa59f2f4cd87faf83e8185686e351c42996636abe184ab0e7eb8f56e2018b', 'Member', NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_book`
--

CREATE TABLE `tbl_book` (
  `fld_book_id` int(11) NOT NULL,
  `fld_title` varchar(300) NOT NULL,
  `fld_author` varchar(45) NOT NULL,
  `fld_publisher` varchar(45) NOT NULL,
  `fld_year_published` int(11) DEFAULT NULL,
  `fld_quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_book`
--

INSERT INTO `tbl_book` (`fld_book_id`, `fld_title`, `fld_author`, `fld_publisher`, `fld_year_published`, `fld_quantity`) VALUES
(1, 'Harry Potter and the Sorcerer’s Stone', 'J.K. Rowling', 'Bloomsburry', 1997, 16),
(2, 'Harry Potter and the Chamber of Secrets', 'J.K. Rowling', 'Bloomsburry', 1998, 11),
(3, 'Harry Potter and the Prisoner of Azkaban', 'J.K. Rowling', 'Bloomsburry', 1999, 9),
(4, 'Harry Potter and the Goblet of Fire', 'J.K. Rowling', 'Bloomsburry', 2000, 6),
(5, 'Harry Potter and the Order of the Phoenix', 'J.K. Rowling', 'Bloomsburry', 2003, 8),
(6, 'Harry Potter and the Half-Blood Prince', 'J.K. Rowling', 'Bloomsburry', 2005, 5),
(7, 'Harry Potter and the Deathly Hallows', 'J.K. Rowling', 'Bloomsburry', 2007, 5),
(8, 'Harry Potter and the Cursed Child', 'J.K. Rowling', 'Bloomsburry', 2016, 5),
(9, 'Clean Code: A Handbook of Agile Software Craftsmanship', 'Robert C. Martin', 'Prentice Hall', 2008, 10),
(10, 'To Kill a Mockingbird', 'Harper Lee', 'J.B. Lippincott & Co.', 1960, 9),
(11, 'The Pragmatic Programmer: Your Journey to Mastery', 'Andrew Hunt', 'Addison-Wesley', 1999, 8),
(12, '1984', 'George Orwell', 'Secker & Warburg', 1949, 7),
(13, 'Code Complete: A Practical Handbook of Software Construction', 'Steve McConnell', 'Microsoft Press', 2004, 12),
(14, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Charles Scribner’s Sons', 1925, 6),
(15, 'Design Patterns: Elements of Reusable Object-Oriented Software', 'Erich Gamma', 'Addison-Wesley', 1994, 5),
(16, 'Pride and Prejudice', 'Jane Austen', 'T. Egerton', 1813, 9),
(17, 'Refactoring: Improving the Design of Existing Code', 'Martin Fowler', 'Addison-Wesley', 1999, 11),
(18, 'The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', 1951, 10),
(19, 'Head First Design Patterns', 'Eric Freeman', 'O’Reilly Media', 2004, 14),
(20, 'The Hobbit', 'J.R.R. Tolkien', 'George Allen & Unwin', 1937, 8),
(21, 'Introduction to Algorithms', 'Thomas H. Cormen', 'MIT Press', 1990, 7),
(22, 'Fahrenheit 451', 'Ray Bradbury', 'Ballantine Books', 1953, 6),
(23, 'The Clean Coder: A Code of Conduct for Professional Programmers', 'Robert C. Martin', 'Prentice Hall', 2011, 9),
(24, 'The Lord of the Rings', 'J.R.R. Tolkien', 'George Allen & Unwin', 1954, 13),
(25, 'Cracking the Coding Interview', 'Gayle Laakmann McDowell', 'CareerCup', 2015, 12),
(26, 'Animal Farm', 'George Orwell', 'Secker & Warburg', 1945, 10),
(27, 'Structure and Interpretation of Computer Programs', 'Harold Abelson', 'MIT Press', 1985, 7),
(28, 'The Lion, the Witch and the Wardrobe', 'C.S. Lewis', 'Geoffrey Bles', 1950, 11),
(29, 'You Don’t Know JS Yet: Get Started', 'Kyle Simpson', 'O’Reilly Media', 2020, 15),
(30, 'Moby-Dick', 'Herman Melville', 'Harper & Brothers', 1851, 5),
(31, 'Effective Java', 'Joshua Bloch', 'Addison-Wesley', 2001, 8),
(32, 'The Alchemist', 'Paulo Coelho', 'HarperTorch', 1988, 12),
(33, 'Python Crash Course', 'Eric Matthes', 'No Starch Press', 2015, 9),
(34, 'Harry Potter and the Sorcerer’s Stone', 'J.K. Rowling', 'Bloomsbury', 1997, 15),
(35, 'Automate the Boring Stuff with Python', 'Al Sweigart', 'No Starch Press', 2015, 8),
(36, 'The Da Vinci Code', 'Dan Brown', 'Doubleday', 2003, 7),
(37, 'The Art of Computer Programming, Volume 1: Fundamental Algorithms', 'Donald E. Knuth', 'Addison-Wesley', 1968, 6),
(38, 'The Hunger Games', 'Suzanne Collins', 'Scholastic Press', 2008, 9),
(39, 'The Fault in Our Stars', 'John Green', 'Dutton Books', 2012, 10),
(40, 'The Kite Runner', 'Khaled Hosseini', 'Riverhead Books', 2003, 11),
(41, 'The Girl on the Train', 'Paula Hawkins', 'Riverhead Books', 2015, 13),
(42, 'The Book Thief', 'Markus Zusak', 'Picador', 2005, 8),
(43, 'The Shining', 'Stephen King', 'Doubleday', 1977, 9);

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
(3, 'Jin', 'Woo', 'Sung', '09987651234', 'sungjinwoo@email.com'),
(4, 'Luffy', 'D', 'Monkey', '09211230987', 'pirateking@email.com');

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
(1, 'Calvin Kyle', 'Fortaleza', 'Nocon', 'sj10.cknocon24@joysistvi.edu.ph'),
(2, 'Libby', NULL, 'Librarian', 'libby.librarian@example.com');

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
  `fld_receiver_staff_id` int(11) DEFAULT NULL,
  `fld_status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_transaction`
--

INSERT INTO `tbl_transaction` (`fld_transaction_id`, `fld_reference_id`, `fld_member_id`, `fld_issuer_staff_id`, `fld_book_id`, `fld_borrow_date`, `fld_due_date`, `fld_return_date`, `fld_receiver_staff_id`, `fld_status`) VALUES
(1, '5B2VC0DO', 1, 1, 1, '2025-10-01 00:00:00', '2025-10-06 00:00:00', '2025-10-07 08:47:01', 1, 'Returned'),
(2, 'VM004PJM', 1, 1, 1, '2025-10-01 00:00:00', '2025-10-06 00:00:00', '2025-10-07 09:13:05', 1, 'Returned'),
(3, 'NVQM9CUL', 1, 1, 1, '2025-10-01 00:00:00', '2025-10-06 00:00:00', '2025-10-07 09:13:07', 1, 'Returned'),
(4, '38R876P9', 1, 1, 1, '2025-10-01 21:50:00', '2025-10-06 21:50:00', '2025-10-07 09:13:03', 1, 'Returned'),
(5, 'AH9V3MMD', 1, 1, 1, '2025-10-02 16:39:00', '2025-10-07 16:39:00', '2025-10-07 09:13:00', 1, 'Returned'),
(6, 'HI67DOGC', 3, 1, 1, '2025-10-02 17:05:09', '2025-10-02 17:04:00', '2025-10-07 09:13:02', 1, 'Returned'),
(7, 'KS9UG8KF', 3, 1, 8, '2025-10-02 19:38:38', '2025-10-02 19:38:00', '2025-10-07 09:12:57', 1, 'Returned'),
(8, 'VAWIH5ZD', 2, 1, 1, '2025-10-02 20:45:03', '2025-10-02 20:44:00', '2025-10-07 09:12:17', 1, 'Returned'),
(9, '43TJYI74', 3, 1, 2, '2025-10-02 21:34:17', '2025-10-02 21:33:00', '2025-10-07 09:12:55', 1, 'Returned'),
(10, 'WB47TYFA', 1, 1, 3, '2025-10-02 21:35:34', '2025-10-02 21:34:00', '2025-10-07 09:12:51', 1, 'Returned'),
(11, 'SAD5D7RK', 3, 1, 2, '2025-10-02 21:38:53', '2025-10-02 21:37:00', '2025-10-07 09:12:47', 1, 'Returned'),
(12, 'QSKZXSIL', 3, 1, 1, '2025-10-02 21:54:56', '2025-10-02 21:54:00', '2025-10-07 09:12:49', 1, 'Returned'),
(13, 'XLLVNTW0', 2, 1, 2, '2025-10-02 21:58:39', '2025-10-02 21:57:00', '2025-10-07 09:07:41', 1, 'Returned'),
(14, 'Z3L3ZZLY', 1, 1, 1, '2025-10-02 22:17:15', '2025-10-02 22:16:00', '2025-10-07 09:12:37', 1, 'Returned'),
(15, '20251002YXWYS', 3, 1, 2, '2025-10-02 23:35:32', '2025-10-02 23:35:00', '2025-10-07 09:12:45', 1, 'Returned'),
(16, '20251003QSAMJ', 3, 1, 1, '2025-10-03 16:08:25', '2025-10-03 16:08:00', '2025-10-07 09:12:42', 1, 'Returned'),
(17, '20251003MSRPU', 2, 1, 3, '2025-10-03 17:43:40', '2025-10-03 17:43:00', '2025-10-07 08:47:15', 1, 'Returned'),
(18, '20251004AZTVI', 3, 1, 1, '2025-10-04 10:59:29', '2025-10-04 10:59:00', '2025-10-07 09:12:39', 1, 'Returned'),
(19, '20251004CPWCP', 3, 1, 2, '2025-10-04 10:59:51', '2025-10-04 10:59:00', '2025-10-07 09:12:35', 1, 'Returned'),
(20, '20251004ROQYI', 3, 1, 8, '2025-10-04 13:17:27', '2025-10-04 13:17:00', '2025-10-07 09:12:40', 1, 'Returned'),
(21, '20251004ECIGJ', 3, 1, 2, '2025-10-04 16:46:16', '2025-10-04 16:46:00', '2025-10-07 09:12:33', 1, 'Returned'),
(22, '20251004UUOCN', 3, 1, 4, '2025-10-04 16:46:37', '2025-10-04 16:46:00', '2025-10-07 09:12:31', 1, 'Returned'),
(23, '20251004IWLWQ', 3, 1, 6, '2025-10-04 17:48:13', '2025-10-04 17:47:00', '2025-10-07 09:12:28', 1, 'Returned'),
(24, '20251004LMYZN', 2, 1, 3, '2025-10-04 17:50:44', '2025-10-04 17:49:00', '2025-10-07 08:47:10', 1, 'Returned'),
(25, '20251004FQLWT', 1, 1, 3, '2025-10-04 17:53:44', '2025-10-04 17:53:00', '2025-10-07 09:07:22', 1, 'Returned'),
(26, '20251004UHRUT', 3, 1, 5, '2025-10-04 17:54:55', '2025-10-04 17:54:00', '2025-10-07 09:12:25', 1, 'Returned'),
(27, '20251004DYYXW', 3, 1, 5, '2025-10-04 17:55:38', '2025-10-04 17:55:00', '2025-10-07 09:06:07', 1, 'Returned'),
(28, '20251004TFPBJ', 3, 1, 5, '2025-10-04 18:03:38', '2025-10-04 18:03:00', '2025-10-07 08:53:46', 1, 'Returned'),
(29, '20251005XIGEN', 3, 1, 4, '2025-10-05 12:26:25', '2025-10-05 12:25:00', '2025-10-07 08:47:07', 1, 'Returned');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  ADD PRIMARY KEY (`fld_account_id`),
  ADD UNIQUE KEY `ux_accounts_username` (`fld_username`),
  ADD KEY `fk_accounts_staff` (`fld_staff_id`),
  ADD KEY `fk_accounts_member` (`fld_member_id`);

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
  ADD PRIMARY KEY (`fld_transaction_id`),
  ADD KEY `idx_trans_ref` (`fld_reference_id`),
  ADD KEY `fk_trans_member` (`fld_member_id`),
  ADD KEY `fk_trans_book` (`fld_book_id`),
  ADD KEY `fk_trans_issuer` (`fld_issuer_staff_id`),
  ADD KEY `fk_trans_receiver` (`fld_receiver_staff_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  MODIFY `fld_account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_book`
--
ALTER TABLE `tbl_book`
  MODIFY `fld_book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `fld_member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_staff`
--
ALTER TABLE `tbl_staff`
  MODIFY `fld_staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  MODIFY `fld_transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  ADD CONSTRAINT `fk_accounts_member` FOREIGN KEY (`fld_member_id`) REFERENCES `tbl_member` (`fld_member_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_accounts_staff` FOREIGN KEY (`fld_staff_id`) REFERENCES `tbl_staff` (`fld_staff_id`) ON UPDATE CASCADE;

--
-- Constraints for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  ADD CONSTRAINT `fk_trans_book` FOREIGN KEY (`fld_book_id`) REFERENCES `tbl_book` (`fld_book_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_trans_issuer` FOREIGN KEY (`fld_issuer_staff_id`) REFERENCES `tbl_staff` (`fld_staff_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_trans_member` FOREIGN KEY (`fld_member_id`) REFERENCES `tbl_member` (`fld_member_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_trans_receiver` FOREIGN KEY (`fld_receiver_staff_id`) REFERENCES `tbl_staff` (`fld_staff_id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
