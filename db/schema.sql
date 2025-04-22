CREATE DATABASE  IF NOT EXISTS `employeemanagement` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `employeemanagement`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: employeemanagement
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `AttendanceID` int NOT NULL AUTO_INCREMENT,
  `EmployeeID` int DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `CheckInTime` time DEFAULT NULL,
  `CheckOutTime` time DEFAULT NULL,
  `TotalHoursWorked` decimal(4,2) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`AttendanceID`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,1,'2024-04-01','09:00:00','17:00:00',8.00,'Present'),(2,3,'2024-04-01','09:15:00','17:10:00',7.90,'Present'),(3,6,'2024-04-01','08:50:00','17:00:00',8.10,'Present'),(4,2,'2024-04-01',NULL,NULL,0.00,'Absent'),(5,5,'2024-04-01','10:00:00','16:00:00',6.00,'Late'),(6,7,'2025-04-21','09:00:00','17:00:00',NULL,NULL),(7,1,'2025-04-21','09:00:00','17:00:00',NULL,NULL),(8,2,'2025-04-22','09:00:00','17:00:00',NULL,NULL);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `ClientID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `ContactPerson` varchar(100) DEFAULT NULL,
  `ContactNumber` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Address` text,
  `Industry` varchar(100) DEFAULT NULL,
  `DateAdded` date DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ClientID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'NextWave Solutions','Jeff Howard','555-111-2222','jeff.h@example.com','101 Business Rd, CA','Tech','2023-05-01','Active'),(2,'Greenleaf Co.','Sarah Lim','555-222-3333','sarah.l@example.com','202 Park Ave, TX','Environmental','2023-07-15','Active'),(3,'NovaBuild','Tom Hardy','555-333-4444','tom.h@example.com','400 Urban Way, NY','Construction','2022-11-20','On Hold'),(4,'SkyMedia','Emily Stone','555-444-5555','emily.s@example.com','123 Media St, WA','Entertainment','2023-01-10','Active');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `DepartmentID` int NOT NULL AUTO_INCREMENT,
  `DepartmentName` varchar(100) DEFAULT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `DepartmentManagerID` int DEFAULT NULL,
  PRIMARY KEY (`DepartmentID`),
  KEY `fk_department_manager` (`DepartmentManagerID`),
  CONSTRAINT `fk_department_manager` FOREIGN KEY (`DepartmentManagerID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Synergize Markets','New York',3),(2,'Brand Integration','Chicago',6),(3,'Innovate Synergies','Los Angeles',2);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `EmployeeID` int NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Gender` varchar(10) DEFAULT NULL,
  `ContactNumber` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Address` text,
  `HireDate` date DEFAULT NULL,
  `JobID` int DEFAULT NULL,
  `DepartmentID` int DEFAULT NULL,
  `ManagerID` int DEFAULT NULL,
  `JobTitle` varchar(50) DEFAULT NULL,
  `Salary` decimal(10,2) DEFAULT NULL,
  `EmploymentStatus` varchar(20) DEFAULT NULL,
  `EmploymentType` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`),
  KEY `JobID` (`JobID`),
  KEY `DepartmentID` (`DepartmentID`),
  KEY `ManagerID` (`ManagerID`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`JobID`) REFERENCES `jobposition` (`JobID`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`DepartmentID`) REFERENCES `department` (`DepartmentID`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`ManagerID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Nancy','Bolton','1997-07-27','Female','484-185-8398','nancy.bolton@example.com','2320 Alvarez Corners, CO','2024-06-20',5,1,3,'Gaffer',78075.00,'Active','Part-time'),(2,'Frances','Massey','1999-12-21','Male','833-969-4775','frances.m@example.com','2560 Krista Walk, ND','2016-07-12',1,3,4,'Musician',97389.00,'On Leave','Contractor'),(3,'Anthony','Reid','1972-06-02','Male','151-090-3217','anthony.r@example.com','2087 Candace Square, UT','2018-09-24',3,1,2,'Town planner',92830.00,'Active','Full-time'),(4,'Tammy','Brown','1975-01-15','Female','584-197-2076','tammy.b@example.com','150 Taylor Track, ME','2022-04-28',4,3,6,'Public house manager',51599.00,'On Leave','Part-time'),(5,'Maria','Miller','1977-05-11','Female','523-376-9606','maria.m@example.com','75470 Lopez Rd, TX','2020-08-25',5,3,1,'Gaffer',58400.00,'Terminated','Part-time'),(6,'Alex','Johnson','1988-04-10','Male','555-123-7890','alexj@example.com','123 Sunset Blvd, AZ','2017-03-18',2,2,3,'Surveyor',67000.00,'Active','Full-time'),(7,'Linda','Chen','1991-09-30','Female','555-456-7890','linda.chen@example.com','456 Morning Dr, AZ','2019-05-23',2,1,2,'Surveyor',71000.00,'Active','Full-time'),(8,'Steve','Williams','1985-11-12','Male','555-789-1234','stevew@example.com','789 Sunset Ave, AZ','2015-09-10',1,2,3,'Musician',68000.00,'Active','Contractor'),(9,'Tina','Garcia','1993-03-17','Female','555-321-6549','tinag@example.com','321 Desert Rd, AZ','2020-01-10',3,1,1,'Town planner',82000.00,'On Leave','Full-time');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobposition`
--

DROP TABLE IF EXISTS `jobposition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobposition` (
  `JobID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(100) DEFAULT NULL,
  `Description` text,
  `MinSalary` decimal(10,2) DEFAULT NULL,
  `MaxSalary` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`JobID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobposition`
--

LOCK TABLES `jobposition` WRITE;
/*!40000 ALTER TABLE `jobposition` DISABLE KEYS */;
INSERT INTO `jobposition` VALUES (1,'Musician','Plays and performs music.',42000.00,95000.00),(2,'Surveyor','Surveys land and construction sites.',45000.00,88000.00),(3,'Town planner','Plans development in cities.',48000.00,102000.00),(4,'Public house manager','Manages pubs and public spaces.',40000.00,87000.00),(5,'Gaffer','Lighting specialist in production.',46000.00,90000.00);
/*!40000 ALTER TABLE `jobposition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leavelog`
--

DROP TABLE IF EXISTS `leavelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leavelog` (
  `LogID` int NOT NULL AUTO_INCREMENT,
  `EmployeeID` int DEFAULT NULL,
  `LeaveType` varchar(50) DEFAULT NULL,
  `LogTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`LogID`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `leavelog_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leavelog`
--

LOCK TABLES `leavelog` WRITE;
/*!40000 ALTER TABLE `leavelog` DISABLE KEYS */;
INSERT INTO `leavelog` VALUES (1,2,'Medical','2025-04-11 03:35:52'),(2,5,'Personal','2025-04-11 03:35:52'),(3,9,'Vacation','2025-04-11 03:35:52'),(4,4,'Sick','2025-04-11 03:35:52');
/*!40000 ALTER TABLE `leavelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leaverequest`
--

DROP TABLE IF EXISTS `leaverequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leaverequest` (
  `LeaveID` int NOT NULL AUTO_INCREMENT,
  `EmployeeID` int DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `StartDate` date DEFAULT NULL,
  `EndDate` date DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `Reason` text,
  PRIMARY KEY (`LeaveID`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `leaverequest_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaverequest`
--

LOCK TABLES `leaverequest` WRITE;
/*!40000 ALTER TABLE `leaverequest` DISABLE KEYS */;
INSERT INTO `leaverequest` VALUES (1,2,'Medical','2024-04-01','2024-04-05','Approved','Flu recovery'),(2,5,'Personal','2024-04-03','2024-04-06','Approved','Family matters'),(3,9,'Vacation','2024-04-10','2024-04-15','Approved','Annual leave'),(4,4,'Sick','2024-03-25','2024-03-27','Approved','Fever and cold');
/*!40000 ALTER TABLE `leaverequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payroll`
--

DROP TABLE IF EXISTS `payroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payroll` (
  `PayrollID` int NOT NULL AUTO_INCREMENT,
  `EmployeeID` int DEFAULT NULL,
  `BasicSalary` decimal(10,2) DEFAULT NULL,
  `Bonuses` decimal(10,2) DEFAULT NULL,
  `Deductions` decimal(10,2) DEFAULT NULL,
  `NetSalary` decimal(10,2) DEFAULT NULL,
  `PaymentDate` date DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PayrollID`),
  KEY `EmployeeID` (`EmployeeID`),
  CONSTRAINT `payroll_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payroll`
--

LOCK TABLES `payroll` WRITE;
/*!40000 ALTER TABLE `payroll` DISABLE KEYS */;
INSERT INTO `payroll` VALUES (1,1,78075.00,3000.00,1500.00,79575.00,'2024-03-30','Processed'),(2,3,92830.00,1000.00,2000.00,91830.00,'2024-03-30','Processed'),(3,6,67000.00,2000.00,1000.00,68000.00,'2024-03-30','Processed'),(4,2,97389.00,2500.00,3000.00,96889.00,'2024-03-30','Pending'),(5,4,51599.00,1000.00,800.00,51799.00,'2024-03-30','Processed'),(8,5,20000.00,2000.00,1000.00,NULL,'2025-04-21',NULL);
/*!40000 ALTER TABLE `payroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performancereview`
--

DROP TABLE IF EXISTS `performancereview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performancereview` (
  `ReviewID` int NOT NULL AUTO_INCREMENT,
  `EmployeeID` int DEFAULT NULL,
  `ReviewerID` int DEFAULT NULL,
  `ReviewDate` date DEFAULT NULL,
  `Rating` int DEFAULT NULL,
  `Comments` text,
  PRIMARY KEY (`ReviewID`),
  KEY `EmployeeID` (`EmployeeID`),
  KEY `ReviewerID` (`ReviewerID`),
  CONSTRAINT `performancereview_ibfk_1` FOREIGN KEY (`EmployeeID`) REFERENCES `employee` (`EmployeeID`),
  CONSTRAINT `performancereview_ibfk_2` FOREIGN KEY (`ReviewerID`) REFERENCES `employee` (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performancereview`
--

LOCK TABLES `performancereview` WRITE;
/*!40000 ALTER TABLE `performancereview` DISABLE KEYS */;
INSERT INTO `performancereview` VALUES (1,1,3,'2024-03-01',4,'Consistent performance.'),(2,2,6,'2024-02-28',3,'Needs improvement in communication.'),(3,3,2,'2024-03-15',5,'Excellent leadership.'),(4,6,4,'2024-03-20',4,'Good technical knowledge.'),(5,4,3,'2024-01-25',3,'Average performance, time management needed.');
/*!40000 ALTER TABLE `performancereview` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-22  9:27:30
