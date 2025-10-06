-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
<<<<<<< HEAD
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2025 at 03:37 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

=======
-- Host: localhost    Database: db_library_system
-- ------------------------------------------------------
-- Server version	8.0.42
>>>>>>> 99ac98c (Unify login & registration, refresh UI/UX, update DB script (tested))

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_accounts`
--

DROP TABLE IF EXISTS `tbl_accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_accounts` (
  `fld_account_id` int NOT NULL AUTO_INCREMENT,
  `fld_username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_role` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_staff_id` int NOT NULL,
  PRIMARY KEY (`fld_account_id`),
  UNIQUE KEY `ux_accounts_username` (`fld_username`),
  KEY `fk_accounts_staff` (`fld_staff_id`),
  CONSTRAINT `fk_accounts_staff` FOREIGN KEY (`fld_staff_id`) REFERENCES `tbl_staff` (`fld_staff_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_accounts`
--

LOCK TABLES `tbl_accounts` WRITE;
/*!40000 ALTER TABLE `tbl_accounts` DISABLE KEYS */;
INSERT INTO `tbl_accounts` VALUES (1,'admin','5c06eb3d5a05a19f49476d694ca81a36344660e9d5b98e3d6a6630f31c2422e7','Admin',1),(2,'librarian','e370f928794e5621660058a4247e92d0b6edcf39ef923b3167f4f2c3480d4d99','Librarian',2);
/*!40000 ALTER TABLE `tbl_accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_book`
--

DROP TABLE IF EXISTS `tbl_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_book` (
  `fld_book_id` int NOT NULL AUTO_INCREMENT,
  `fld_title` varchar(300) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_author` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_publisher` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_year_published` int DEFAULT NULL,
  `fld_quantity` int NOT NULL,
  PRIMARY KEY (`fld_book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_book`
--

<<<<<<< HEAD
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
=======
LOCK TABLES `tbl_book` WRITE;
/*!40000 ALTER TABLE `tbl_book` DISABLE KEYS */;
INSERT INTO `tbl_book` VALUES (1,'Harry Potter and the Sorcerer’s Stone','J.K. Rowling','Bloomsburry',1997,1),(2,'Harry Potter and the Chamber of Secrets ','J.K. Rowling','Bloomsburry',1998,2),(3,'Harry Potter and the Prisoner of Azkaban ','J.K. Rowling','Bloomsburry',1999,3),(4,'Harry Potter and the Goblet of Fire','J.K. Rowling','Bloomsburry',2000,1),(5,'Harry Potter and the Order of the Phoenix','J.K. Rowling','Bloomsburry',2003,5),(6,'Harry Potter and the Half-Blood Prince ','J.K. Rowling','Bloomsburry',2005,5),(7,'Harry Potter and the Deathly Hallows','J.K. Rowling','Bloomsburry',2007,0),(8,'Harry Potter and the Cursed Child','J.K. Rowling','Bloomsburry',2016,4);
/*!40000 ALTER TABLE `tbl_book` ENABLE KEYS */;
UNLOCK TABLES;
>>>>>>> 99ac98c (Unify login & registration, refresh UI/UX, update DB script (tested))

--
-- Table structure for table `tbl_member`
--

DROP TABLE IF EXISTS `tbl_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_member` (
  `fld_member_id` int NOT NULL AUTO_INCREMENT,
  `fld_first_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_middle_name` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fld_last_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_phone_number` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_email` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`fld_member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_member`
--

LOCK TABLES `tbl_member` WRITE;
/*!40000 ALTER TABLE `tbl_member` DISABLE KEYS */;
INSERT INTO `tbl_member` VALUES (1,'Calvin','F','Nocon','09987651234','sj10.cknocon24@joysistvi.edu.ph'),(2,'Kyle','Calvin','Nocon','09321459867','sj10.cknocon24@joysistvi.edu.ph1'),(3,'Jin','Woo','Sung','09987651234','sungjinwoo@email.com');
/*!40000 ALTER TABLE `tbl_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_staff`
--

DROP TABLE IF EXISTS `tbl_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_staff` (
  `fld_staff_id` int NOT NULL AUTO_INCREMENT,
  `fld_first_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_middle_name` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fld_last_name` varchar(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_email` varchar(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`fld_staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_staff`
--

LOCK TABLES `tbl_staff` WRITE;
/*!40000 ALTER TABLE `tbl_staff` DISABLE KEYS */;
INSERT INTO `tbl_staff` VALUES (1,'Calvin Kyle','Fortaleza','Nocon','sj10.cknocon24@joysistvi.edu.ph'),(2,'Libby',NULL,'Librarian','libby.librarian@example.com');
/*!40000 ALTER TABLE `tbl_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_transaction`
--

DROP TABLE IF EXISTS `tbl_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_transaction` (
<<<<<<< HEAD
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
=======
  `fld_transaction_id` int NOT NULL AUTO_INCREMENT,
  `fld_reference_id` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_member_id` int NOT NULL,
  `fld_issuer_staff_id` int NOT NULL,
  `fld_book_id` int NOT NULL,
  `fld_borrow_date` datetime NOT NULL,
  `fld_due_date` datetime NOT NULL,
  `fld_return_date` datetime DEFAULT NULL,
  `fld_receiver_staff_id` int DEFAULT NULL,
  `fld_status` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`fld_transaction_id`),
  KEY `idx_trans_ref` (`fld_reference_id`),
  KEY `fk_trans_member` (`fld_member_id`),
  KEY `fk_trans_book` (`fld_book_id`),
  KEY `fk_trans_issuer` (`fld_issuer_staff_id`),
  KEY `fk_trans_receiver` (`fld_receiver_staff_id`),
  CONSTRAINT `fk_trans_book` FOREIGN KEY (`fld_book_id`) REFERENCES `tbl_book` (`fld_book_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_trans_issuer` FOREIGN KEY (`fld_issuer_staff_id`) REFERENCES `tbl_staff` (`fld_staff_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_trans_member` FOREIGN KEY (`fld_member_id`) REFERENCES `tbl_member` (`fld_member_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_trans_receiver` FOREIGN KEY (`fld_receiver_staff_id`) REFERENCES `tbl_staff` (`fld_staff_id`) ON DELETE SET NULL ON UPDATE CASCADE
>>>>>>> 99ac98c (Unify login & registration, refresh UI/UX, update DB script (tested))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_transaction`
--

<<<<<<< HEAD
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
=======
LOCK TABLES `tbl_transaction` WRITE;
/*!40000 ALTER TABLE `tbl_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
>>>>>>> 99ac98c (Unify login & registration, refresh UI/UX, update DB script (tested))

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-06 17:24:11
