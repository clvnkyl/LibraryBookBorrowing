-- MySQL dump 10.13  Distrib 8.0.x (compatible)
-- Host: 127.0.0.1
-- Generation Time: Oct 05, 2025 at 03:37 PM
-- Server version: 10.4.32-MariaDB (compatible)
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

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

-- ------------------------------------------------------
-- Database: db_library_system
-- ------------------------------------------------------

/* =========================
   DROP TABLES (child -> parent)
   ========================= */
DROP TABLE IF EXISTS `tbl_transaction`;
DROP TABLE IF EXISTS `tbl_accounts`;
DROP TABLE IF EXISTS `tbl_book`;
DROP TABLE IF EXISTS `tbl_member`;
DROP TABLE IF EXISTS `tbl_staff`;

/* =========================
   CREATE TABLES (parent -> child)
   ========================= */

-- Staff
CREATE TABLE `tbl_staff` (
  `fld_staff_id` INT NOT NULL AUTO_INCREMENT,
  `fld_first_name` VARCHAR(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_middle_name` VARCHAR(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fld_last_name` VARCHAR(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_email` VARCHAR(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`fld_staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Members
CREATE TABLE `tbl_member` (
  `fld_member_id` INT NOT NULL AUTO_INCREMENT,
  `fld_first_name` VARCHAR(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_middle_name` VARCHAR(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `fld_last_name` VARCHAR(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_phone_number` VARCHAR(15) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_email` VARCHAR(300) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`fld_member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Books
CREATE TABLE `tbl_book` (
  `fld_book_id` INT NOT NULL AUTO_INCREMENT,
  `fld_title` VARCHAR(300) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_author` VARCHAR(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_publisher` VARCHAR(45) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_year_published` INT DEFAULT NULL,
  `fld_quantity` INT NOT NULL,
  PRIMARY KEY (`fld_book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Accounts (supports both staff and member logins)
CREATE TABLE `tbl_accounts` (
  `fld_account_id` INT NOT NULL AUTO_INCREMENT,
  `fld_username`   VARCHAR(50)  COLLATE utf8mb4_general_ci NOT NULL,
  `fld_password`   VARCHAR(255) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_role`       VARCHAR(20)  COLLATE utf8mb4_general_ci NOT NULL,
  `fld_staff_id`   INT DEFAULT NULL,
  `fld_member_id`  INT DEFAULT NULL,
  PRIMARY KEY (`fld_account_id`),
  UNIQUE KEY `ux_accounts_username` (`fld_username`),
  KEY `fk_accounts_staff`  (`fld_staff_id`),
  KEY `fk_accounts_member` (`fld_member_id`),
  CONSTRAINT `fk_accounts_staff`  FOREIGN KEY (`fld_staff_id`)  REFERENCES `tbl_staff`  (`fld_staff_id`)  ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_accounts_member` FOREIGN KEY (`fld_member_id`) REFERENCES `tbl_member` (`fld_member_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Transactions
CREATE TABLE `tbl_transaction` (
  `fld_transaction_id` INT NOT NULL AUTO_INCREMENT,
  `fld_reference_id`   VARCHAR(15) COLLATE utf8mb4_general_ci NOT NULL,
  `fld_member_id`      INT NOT NULL,
  `fld_issuer_staff_id`   INT NOT NULL,
  `fld_book_id`        INT NOT NULL,
  `fld_borrow_date`    DATETIME NOT NULL,
  `fld_due_date`       DATETIME NOT NULL,
  `fld_return_date`    DATETIME DEFAULT NULL,
  `fld_receiver_staff_id` INT DEFAULT NULL,
  `fld_status`         VARCHAR(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`fld_transaction_id`),
  KEY `idx_trans_ref`      (`fld_reference_id`),
  KEY `fk_trans_member`    (`fld_member_id`),
  KEY `fk_trans_book`      (`fld_book_id`),
  KEY `fk_trans_issuer`    (`fld_issuer_staff_id`),
  KEY `fk_trans_receiver`  (`fld_receiver_staff_id`),
  CONSTRAINT `fk_trans_member`   FOREIGN KEY (`fld_member_id`)         REFERENCES `tbl_member` (`fld_member_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_trans_book`     FOREIGN KEY (`fld_book_id`)           REFERENCES `tbl_book`   (`fld_book_id`)   ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_trans_issuer`   FOREIGN KEY (`fld_issuer_staff_id`)   REFERENCES `tbl_staff`  (`fld_staff_id`)  ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_trans_receiver` FOREIGN KEY (`fld_receiver_staff_id`) REFERENCES `tbl_staff`  (`fld_staff_id`)  ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* =========================
   SEED DATA
   ========================= */

-- Staff
LOCK TABLES `tbl_staff` WRITE;
/*!40000 ALTER TABLE `tbl_staff` DISABLE KEYS */;
INSERT INTO `tbl_staff` (`fld_staff_id`,`fld_first_name`,`fld_middle_name`,`fld_last_name`,`fld_email`) VALUES
(1,'Calvin Kyle','Fortaleza','Nocon','sj10.cknocon24@joysistvi.edu.ph'),
(2,'Libby',NULL,'Librarian','libby.librarian@example.com');
/*!40000 ALTER TABLE `tbl_staff` ENABLE KEYS */;
UNLOCK TABLES;

-- Members
LOCK TABLES `tbl_member` WRITE;
/*!40000 ALTER TABLE `tbl_member` DISABLE KEYS */;
INSERT INTO `tbl_member` (`fld_member_id`,`fld_first_name`,`fld_middle_name`,`fld_last_name`,`fld_phone_number`,`fld_email`) VALUES
(1,'Calvin','F','Nocon','09987651234','sj10.cknocon24@joysistvi.edu.ph'),
(2,'Kyle','Calvin','Nocon','09321459867','sj10.cknocon24@joysistvi.edu.ph1'),
(3,'Jin','Woo','Sung','09987651234','sungjinwoo@email.com');
/*!40000 ALTER TABLE `tbl_member` ENABLE KEYS */;
UNLOCK TABLES;

-- Books
LOCK TABLES `tbl_book` WRITE;
/*!40000 ALTER TABLE `tbl_book` DISABLE KEYS */;
INSERT INTO `tbl_book`
(`fld_book_id`,`fld_title`,`fld_author`,`fld_publisher`,`fld_year_published`,`fld_quantity`) VALUES
(1,'Harry Potter and the Sorcerer’s Stone','J.K. Rowling','Bloomsburry',1997,5),
(2,'Harry Potter and the Chamber of Secrets','J.K. Rowling','Bloomsburry',1998,5),
(3,'Harry Potter and the Prisoner of Azkaban','J.K. Rowling','Bloomsburry',1999,5),
(4,'Harry Potter and the Goblet of Fire','J.K. Rowling','Bloomsburry',2000,4),
(5,'Harry Potter and the Order of the Phoenix','J.K. Rowling','Bloomsburry',2003,5),
(6,'Harry Potter and the Half-Blood Prince','J.K. Rowling','Bloomsburry',2005,4),
(7,'Harry Potter and the Deathly Hallows','J.K. Rowling','Bloomsburry',2007,5),
(8,'Harry Potter and the Cursed Child','J.K. Rowling','Bloomsburry',2016,3);
/*!40000 ALTER TABLE `tbl_book` ENABLE KEYS */;
UNLOCK TABLES;

-- Accounts
LOCK TABLES `tbl_accounts` WRITE;
/*!40000 ALTER TABLE `tbl_accounts` DISABLE KEYS */;
INSERT INTO `tbl_accounts`
(`fld_account_id`,`fld_username`,`fld_password`,`fld_role`,`fld_staff_id`,`fld_member_id`) VALUES
(1,'admin','5c06eb3d5a05a19f49476d694ca81a36344660e9d5b98e3d6a6630f31c2422e7','Admin',1,NULL),
(2,'librarian','e370f928794e5621660058a4247e92d0b6edcf39ef923b3167f4f2c3480d4d99','Librarian',2,NULL);
/*!40000 ALTER TABLE `tbl_accounts` ENABLE KEYS */;
UNLOCK TABLES;

-- Transactions (ONLY rows that match existing member/staff/book IDs; misspelled column fixed)
LOCK TABLES `tbl_transaction` WRITE;
/*!40000 ALTER TABLE `tbl_transaction` DISABLE KEYS */;
INSERT INTO `tbl_transaction`
(`fld_reference_id`,`fld_member_id`,`fld_issuer_staff_id`,`fld_book_id`,`fld_borrow_date`,`fld_due_date`,`fld_return_date`,`fld_receiver_staff_id`,`fld_status`) VALUES
('5B2VC0DO',1,1,1,'2025-10-01 00:00:00','2025-10-06 00:00:00',NULL,NULL,'Borrowed'),
('VM004PJM',1,1,1,'2025-10-01 00:00:00','2025-10-06 00:00:00',NULL,NULL,'Borrowed'),
('NVQM9CUL',1,1,1,'2025-10-01 00:00:00','2025-10-06 00:00:00',NULL,NULL,'Borrowed'),
('38R876P9',1,1,1,'2025-10-01 21:50:00','2025-10-06 21:50:00',NULL,NULL,'Borrowed'),
('AH9V3MMD',1,1,1,'2025-10-02 16:39:00','2025-10-07 16:39:00',NULL,NULL,'Borrowed'),
('HI67DOGC',3,1,1,'2025-10-02 17:05:09','2025-10-02 17:04:00',NULL,NULL,'Borrowed'),
('KS9UG8KF',3,1,8,'2025-10-02 19:38:38','2025-10-02 19:38:00',NULL,NULL,'Borrowed'),
('VAWIH5ZD',2,1,1,'2025-10-02 20:45:03','2025-10-02 20:44:00',NULL,NULL,'Borrowed'),
('43TJYI74',3,1,2,'2025-10-02 21:34:17','2025-10-02 21:33:00',NULL,NULL,'Borrowed'),
('WB47TYFA',1,1,3,'2025-10-02 21:35:34','2025-10-02 21:34:00',NULL,NULL,'Borrowed'),
('SAD5D7RK',3,1,2,'2025-10-02 21:38:53','2025-10-02 21:37:00',NULL,NULL,'Borrowed'),
('QSKZXSIL',3,1,1,'2025-10-02 21:54:56','2025-10-02 21:54:00',NULL,NULL,'Borrowed'),
('XLLVNTW0',2,1,2,'2025-10-02 21:58:39','2025-10-02 21:57:00',NULL,NULL,'Borrowed'),
('Z3L3ZZLY',1,1,1,'2025-10-02 22:17:15','2025-10-02 22:16:00',NULL,NULL,'Borrowed'),
('20251002YXWYS',3,1,2,'2025-10-02 23:35:32','2025-10-02 23:35:00',NULL,NULL,'Borrowed'),
('20251003QSAMJ',3,1,1,'2025-10-03 16:08:25','2025-10-03 16:08:00',NULL,NULL,'Borrowed'),
('20251003MSRPU',2,1,3,'2025-10-03 17:43:40','2025-10-03 17:43:00',NULL,NULL,'Borrowed'),
('20251004AZTVI',3,1,1,'2025-10-04 10:59:29','2025-10-04 10:59:00',NULL,NULL,'Borrowed'),
('20251004CPWCP',3,1,2,'2025-10-04 10:59:51','2025-10-04 10:59:00',NULL,NULL,'Borrowed'),
('20251004ROQYI',3,1,8,'2025-10-04 13:17:27','2025-10-04 13:17:00',NULL,NULL,'Borrowed'),
('20251004ECIGJ',3,1,2,'2025-10-04 16:46:16','2025-10-04 16:46:00',NULL,NULL,'Borrowed'),
('20251004UUOCN',3,1,4,'2025-10-04 16:46:37','2025-10-04 16:46:00',NULL,NULL,'Borrowed'),
('20251004IWLWQ',3,1,6,'2025-10-04 17:48:13','2025-10-04 17:47:00',NULL,NULL,'Borrowed'),
('20251004LMYZN',2,1,3,'2025-10-04 17:50:44','2025-10-04 17:49:00',NULL,NULL,'Borrowed'),
('20251004FQLWT',1,1,3,'2025-10-04 17:53:44','2025-10-04 17:53:00',NULL,NULL,'Borrowed'),
('20251004UHRUT',3,1,5,'2025-10-04 17:54:55','2025-10-04 17:54:00',NULL,NULL,'Borrowed'),
('20251004DYYXW',3,1,5,'2025-10-04 17:55:38','2025-10-04 17:55:00',NULL,NULL,'Borrowed'),
('20251004TFPBJ',3,1,5,'2025-10-04 18:03:38','2025-10-04 18:03:00',NULL,NULL,'Borrowed'),
('20251005XIGEN',3,1,4,'2025-10-05 12:26:25','2025-10-05 12:25:00',NULL,NULL,'Borrowed');
/*!40000 ALTER TABLE `tbl_transaction` ENABLE KEYS */;
UNLOCK TABLES;

/* =========================
   FINALIZE
   ========================= */

COMMIT;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
 /*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
 /*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
 /*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
 /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
 /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
 /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
 /*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;