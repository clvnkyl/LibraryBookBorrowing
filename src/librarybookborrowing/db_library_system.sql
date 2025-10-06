-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 06, 2025 at 03:29 PM
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
(1, 'kylevin', 'cc7d240bed51883786b24756316eddf520680f1686fba69e6e11e07e4e389d5e', 'Admin', 1),
(2, 'strawhat', 'be99ef4cb48a502f26683edc7641d04e82a8e50be64844f5395782f858e8f321', 'Librarian', 2);

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
(1, 'Harry Potter and the Sorcerer’s Stone', 'J.K. Rowling', 'Bloomsburry', 1997, 15),
(2, 'Harry Potter and the Chamber of Secrets ', 'J.K. Rowling', 'Bloomsburry', 1998, 4),
(3, 'Harry Potter and the Prisoner of Azkaban ', 'J.K. Rowling', 'Bloomsburry', 1999, 5),
(4, 'Harry Potter and the Goblet of Fire', 'J.K. Rowling', 'Bloomsburry', 2000, 3),
(5, 'Harry Potter and the Order of the Phoenix', 'J.K. Rowling', 'Bloomsburry', 2003, 4),
(6, 'Harry Potter and the Half-Blood Prince ', 'J.K. Rowling', 'Bloomsburry', 2005, 4),
(7, 'Harry Potter and the Deathly Hallows', 'J.K. Rowling', 'Bloomsburry', 2007, 5),
(8, 'Harry Potter and the Cursed Child', 'J.K. Rowling', 'Bloomsburry', 2016, 2),
(17, 'Clean code', 'Uncle Bob', 'Pearson', 2008, 9),
(18, 'To Kill a Mockingbird', 'Harper Lee', 'J.B. Lippincott & Co.', 1960, 10),
(19, '1984', 'George Orwell', 'Secker & Warburg', 1949, 6),
(20, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Charles Scribner’s Sons', 1925, 12),
(21, 'Pride and Prejudice', 'Jane Austen', 'T. Egerton', 1813, 8),
(22, 'The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', 1951, 9),
(23, 'The Hobbit', 'J.R.R. Tolkien', 'George Allen & Unwin', 1937, 14),
(24, 'Fahrenheit 451', 'Ray Bradbury', 'Ballantine Books', 1953, 7),
(25, 'The Lord of the Rings', 'J.R.R. Tolkien', 'George Allen & Unwin', 1954, 11),
(26, 'Animal Farm', 'George Orwell', 'Secker & Warburg', 1945, 5),
(27, 'The Lion, the Witch and the Wardrobe', 'C.S. Lewis', 'Geoffrey Bles', 1950, 13),
(28, 'Moby-Dick', 'Herman Melville', 'Harper & Brothers', 1851, 4),
(29, 'The Alchemist', 'Paulo Coelho', 'HarperTorch', 1988, 8),
(30, 'Harry Potter and the Sorcerer’s Stone', 'J.K. Rowling', 'Bloomsbury', 1997, 15),
(31, 'The Da Vinci Code', 'Dan Brown', 'Doubleday', 2003, 9),
(32, 'The Hunger Games', 'Suzanne Collins', 'Scholastic Press', 2008, 6),
(33, 'The Fault in Our Stars', 'John Green', 'Dutton Books', 2012, 10),
(34, 'The Kite Runner', 'Khaled Hosseini', 'Riverhead Books', 2003, 7),
(35, 'The Girl on the Train', 'Paula Hawkins', 'Riverhead Books', 2015, 12),
(36, 'The Book Thief', 'Markus Zusak', 'Picador', 2005, 5),
(37, 'The Shining', 'Stephen King', 'Doubleday', 1977, 9),
(38, 'Clean Code: A Handbook of Agile Software Craftsmanship', 'Robert C. Martin', 'Prentice Hall', 2008, 10),
(39, 'The Pragmatic Programmer: Your Journey to Mastery', 'Andrew Hunt', 'Addison-Wesley', 1999, 8),
(40, 'Code Complete: A Practical Handbook of Software Construction', 'Steve McConnell', 'Microsoft Press', 2004, 12),
(41, 'Design Patterns: Elements of Reusable Object-Oriented Software', 'Erich Gamma', 'Addison-Wesley', 1994, 5),
(42, 'Refactoring: Improving the Design of Existing Code', 'Martin Fowler', 'Addison-Wesley', 1999, 9),
(43, 'Head First Design Patterns', 'Eric Freeman', 'O’Reilly Media', 2004, 14),
(44, 'Introduction to Algorithms', 'Thomas H. Cormen', 'MIT Press', 1990, 7),
(45, 'The Clean Coder: A Code of Conduct for Professional Programmers', 'Robert C. Martin', 'Prentice Hall', 2011, 11),
(46, 'Cracking the Coding Interview', 'Gayle Laakmann McDowell', 'CareerCup', 2015, 13),
(47, 'Structure and Interpretation of Computer Programs', 'Harold Abelson', 'MIT Press', 1985, 6),
(48, 'You Don’t Know JS Yet: Get Started', 'Kyle Simpson', 'O’Reilly Media', 2020, 15),
(49, 'Effective Java', 'Joshua Bloch', 'Addison-Wesley', 2001, 4),
(50, 'Python Crash Course', 'Eric Matthes', 'No Starch Press', 2015, 9),
(51, 'Automate the Boring Stuff with Python', 'Al Sweigart', 'No Starch Press', 2015, 8),
(52, 'The Art of Computer Programming, Volume 1: Fundamental Algorithms', 'Donald E. Knuth', 'Addison-Wesley', 1968, 5);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_member`
--

CREATE TABLE `tbl_member` (
  `fld_member_id` int(11) NOT NULL,
  `fld_first_name` varchar(45) NOT NULL,
  `fld_middle_name` varchar(45) DEFAULT NULL,
  `fld_last_name` varchar(45) NOT NULL,
  `fld_phone_number` varchar(15) DEFAULT NULL,
  `fld_email` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_member`
--

INSERT INTO `tbl_member` (`fld_member_id`, `fld_first_name`, `fld_middle_name`, `fld_last_name`, `fld_phone_number`, `fld_email`) VALUES
(1, 'Calvin', 'F', 'Nocon', '09987651234', 'sj10.cknocon24@joysistvi.edu.ph'),
(2, 'Kyle', 'Calvin', 'Nocon', '09321459867', 'sj10.cknocon24@joysistvi.edu.ph1'),
(3, 'Jin', 'Woo', 'Sung', '09987651234', 'sungjinwoo@email.com'),
(4, 'Chae', 'Hae', 'In', '09123456789', 'mschae@email.com');

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
(2, 'Luffy', 'D', 'Monkey', 'pirateking@email.com');

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
(1, '20251006XQEGT', 4, 1, 27, '2025-10-06 21:10:25', '2025-10-06 21:10:00', '2025-10-06 21:21:08', 1, 'Returned'),
(2, '20251006XQEGT', 4, 1, 26, '2025-10-06 21:10:25', '2025-10-06 21:10:00', '2025-10-06 21:21:11', 1, 'Returned'),
(3, '20251006GTKNW', 3, 1, 41, '2025-10-06 21:10:46', '2025-10-06 21:10:00', '2025-10-06 21:21:03', 1, 'Returned'),
(4, '20251006GTKNW', 3, 1, 50, '2025-10-06 21:10:46', '2025-10-06 21:10:00', '2025-10-06 21:21:05', 1, 'Returned');

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
  ADD PRIMARY KEY (`fld_transaction_id`),
  ADD KEY `fk_transaction_book` (`fld_book_id`),
  ADD KEY `fk_transaction_issuer_staff` (`fld_issuer_staff_id`),
  ADD KEY `fk_transaction_member` (`fld_member_id`),
  ADD KEY `fk_transaction_receiver_staff` (`fld_receiver_staff_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_accounts`
--
ALTER TABLE `tbl_accounts`
  MODIFY `fld_account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_book`
--
ALTER TABLE `tbl_book`
  MODIFY `fld_book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `tbl_member`
--
ALTER TABLE `tbl_member`
  MODIFY `fld_member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_staff`
--
ALTER TABLE `tbl_staff`
  MODIFY `fld_staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

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

--
-- Constraints for table `tbl_transaction`
--
ALTER TABLE `tbl_transaction`
  ADD CONSTRAINT `fk_transaction_book` FOREIGN KEY (`fld_book_id`) REFERENCES `tbl_book` (`fld_book_id`),
  ADD CONSTRAINT `fk_transaction_issuer_staff` FOREIGN KEY (`fld_issuer_staff_id`) REFERENCES `tbl_staff` (`fld_staff_id`),
  ADD CONSTRAINT `fk_transaction_member` FOREIGN KEY (`fld_member_id`) REFERENCES `tbl_member` (`fld_member_id`),
  ADD CONSTRAINT `fk_transaction_receiver_staff` FOREIGN KEY (`fld_receiver_staff_id`) REFERENCES `tbl_staff` (`fld_staff_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
