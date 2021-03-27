CREATE DATABASE  IF NOT EXISTS `registrar` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `registrar`;
-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: registrar
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Course` (
  `cid` varchar(255) COLLATE utf8_bin NOT NULL,
  `courseName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `location` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `startTime` time DEFAULT NULL,
  `endTime` time DEFAULT NULL,
  `day` int DEFAULT NULL,
  `lab1` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lab2` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `lab3` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES ('MPCS100','Intro to CS Lecture','Remote','17:30:00','19:30:00',1,'MPCS 100A','MPCS 100B','MPCS 100C'),('MPCS100A','Intro to CS Lab','Remote','17:30:00','19:30:00',2,NULL,NULL,NULL),('MPCS100B','Intro to CS Lab','Remote','17:30:00','19:30:00',2,NULL,NULL,NULL),('MPCS100C','Intro to CS Lab','Remote','17:30:00','19:30:00',2,NULL,NULL,NULL),('MPCS200','Intro to Algo Lecture','Remote','17:30:00','19:30:00',4,'MPCS 200A','MPCS 200B','MPCS 200C'),('MPCS200A','Intro to Algo Lab','Remote','17:30:00','19:30:00',3,NULL,NULL,NULL),('MPCS200B','Intro to Algo Lab','Remote','17:30:00','19:30:00',3,NULL,NULL,NULL),('MPCS200C','Intro to Algo Lab','Remote','17:30:00','19:30:00',3,NULL,NULL,NULL),('MPCS500','OOP Lecture','Remote','17:30:00','19:30:00',5,'MPCS 500A',NULL,NULL),('MPCS50001','Introduction to CS','Remote','14:30:00','16:30:00',3,NULL,NULL,NULL),('MPCS50002','Introduction to Programming','Remote','16:30:00','18:30:00',1,NULL,NULL,NULL),('MPCS500A','OOP Lab','Remote','17:30:00','19:30:00',4,NULL,NULL,NULL),('MPCS500B','OOP Lab','Remote','17:30:00','19:30:00',4,NULL,NULL,NULL),('MPCS51410','Object Oriented Programming: Lecture','Remote','17:30:00','19:30:00',4,'MPCS51410A','MPCS51410B',NULL),('MPCS51410A','Object Oriented Programming: Lab','Remote','17:30:00','19:30:00',3,NULL,NULL,NULL),('MPCS51410B','Object Oriented Programming: Lab','Remote','17:30:00','19:30:00',3,NULL,NULL,NULL),('MPCS550','Intro to Computer Security','Remote','14:30:00','16:30:00',4,NULL,NULL,NULL),('MPCS55001','Algorithms','Remote','14:30:00','16:30:00',3,NULL,NULL,NULL),('MPCS56511','Introduction to Computer Security','Remote','17:30:00','19:30:00',1,NULL,NULL,NULL),('MPCS650','Intro to Parellel Programming','Remote','16:30:00','18:30:00',2,NULL,NULL,NULL),('MPCS800','Advanced Data Science Lecture','Remote','17:30:00','19:30:00',5,'MPCS 800A',NULL,NULL),('MPCS800A','Advanced Data Science Lab','Remote','17:30:00','19:30:00',4,NULL,NULL,NULL);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CourseTeaching`
--

DROP TABLE IF EXISTS `CourseTeaching`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CourseTeaching` (
  `iid` int NOT NULL,
  `cid` varchar(255) COLLATE utf8_bin NOT NULL,
  `quarter` varchar(255) COLLATE utf8_bin NOT NULL,
  `year` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`cid`,`quarter`,`year`),
  KEY `iid` (`iid`),
  CONSTRAINT `courseteaching_ibfk_1` FOREIGN KEY (`iid`) REFERENCES `Instructor` (`iid`),
  CONSTRAINT `courseteaching_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `Course` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CourseTeaching`
--

LOCK TABLES `CourseTeaching` WRITE;
/*!40000 ALTER TABLE `CourseTeaching` DISABLE KEYS */;
INSERT INTO `CourseTeaching` VALUES (2,'MPCS200','spring','2019'),(2,'MPCS500','winter','2021'),(3,'MPCS500A','winter','2021'),(4,'MPCS500B','winter','2021'),(8,'MPCS100A','winter','2021'),(11,'MPCS50001','spring','2020'),(11,'MPCS51410','autumn','2019'),(11,'MPCS51410','winter','2021'),(12,'MPCS51410A','winter','2021'),(13,'MPCS51410B','winter','2021');
/*!40000 ALTER TABLE `CourseTeaching` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Enrollment`
--

DROP TABLE IF EXISTS `Enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Enrollment` (
  `sid` int NOT NULL,
  `cid` varchar(255) COLLATE utf8_bin NOT NULL,
  `quarter` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `year` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `grade` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`sid`,`cid`),
  KEY `cid` (`cid`),
  CONSTRAINT `enrollment_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `enrollment_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `Course` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enrollment`
--

LOCK TABLES `Enrollment` WRITE;
/*!40000 ALTER TABLE `Enrollment` DISABLE KEYS */;
INSERT INTO `Enrollment` VALUES (1,'MPCS100','autumn','2020','A'),(1,'MPCS100A','autumn','2020','A'),(1,'MPCS200','spring','2019','B'),(1,'MPCS200C','spring','2019','B'),(1,'MPCS500','winter','2021','IP'),(1,'MPCS500A','winter','2021','IP'),(1,'MPCS550','winter','2021','IP'),(1,'MPCS650','winter','2021','IP'),(4,'MPCS200','spring','2019','A'),(4,'MPCS200C','spring','2019','A'),(4,'MPCS800','winter','2021','IP'),(4,'MPCS800A','winter','2021','IP'),(6,'MPCS500','winter','2021','IP'),(6,'MPCS500A','winter','2021','IP'),(7,'MPCS500','winter','2021','IP'),(7,'MPCS500B','winter','2021','IP'),(8,'MPCS500','winter','2021','IP'),(8,'MPCS500B','winter','2021','IP'),(10,'MPCS50001','autumn','2020','A'),(10,'MPCS50002','autumn','2020','B'),(10,'MPCS51410','winter','2021','IP'),(10,'MPCS51410A','winter','2021','IP'),(10,'MPCS55001','winter','2021','IP'),(10,'MPCS56511','winter','2021','IP'),(13,'MPCS50001','spring','2020','A'),(13,'MPCS50002','spring','2020','A'),(13,'MPCS51410','autumn','2019','A'),(13,'MPCS55001','autumn','2020','P'),(13,'MPCS56511','autumn','2020','A'),(13,'MPCS800','winter','2021','IP'),(13,'MPCS800A','winter','2021','IP'),(14,'MPCS51410','winter','2021','IP'),(14,'MPCS51410B','winter','2021','IP');
/*!40000 ALTER TABLE `Enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Instructor`
--

DROP TABLE IF EXISTS `Instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Instructor` (
  `iid` int NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`iid`),
  CONSTRAINT `instructor_ibfk_1` FOREIGN KEY (`iid`) REFERENCES `Member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Instructor`
--

LOCK TABLES `Instructor` WRITE;
/*!40000 ALTER TABLE `Instructor` DISABLE KEYS */;
INSERT INTO `Instructor` VALUES (2,'Mark','mark@uchicago.edu'),(3,'John','john@uchicago.edu'),(4,'Jeff','jeff@uchicago.edu'),(8,'Mary Kreg','mary@uchicago.edu'),(11,'Mark Shacklette','marks@uchicago.edu'),(12,'John Hadidian-Baugher','john@uchicago.edu'),(13,'Alan Salkanović','alan@uchicago.edu');
/*!40000 ALTER TABLE `Instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Member`
--

DROP TABLE IF EXISTS `Member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Member` (
  `id` int NOT NULL,
  `cNetID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_cNetID` (`cNetID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Member`
--

LOCK TABLES `Member` WRITE;
/*!40000 ALTER TABLE `Member` DISABLE KEYS */;
INSERT INTO `Member` VALUES (1,'rpchiu','student'),(2,'mark','faculty'),(3,'johnoop','nonStudentTA'),(4,'jeffoop','studentTA'),(6,'amyq','student'),(7,'bob','student'),(8,'maryk','studentTA'),(10,'test_student','student'),(11,'test_faculty','faculty'),(12,'test_nsta','nonStudentTA'),(13,'test_sta','studentTA'),(14,'random_stud','student');
/*!40000 ALTER TABLE `Member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Restriction`
--

DROP TABLE IF EXISTS `Restriction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Restriction` (
  `rid` int NOT NULL,
  `sid` int NOT NULL,
  `cause` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dateIssued` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `hasLifted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `sid` (`sid`),
  CONSTRAINT `restriction_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Restriction`
--

LOCK TABLES `Restriction` WRITE;
/*!40000 ALTER TABLE `Restriction` DISABLE KEYS */;
INSERT INTO `Restriction` VALUES (100,1,'fail to pay tuition','02/05/2020',1),(101,1,'fail to fill out immunization','02/15/2020',0),(102,10,'fail to pay tuition','02/05/2020',0),(103,13,'fail to fill out immunization','02/15/2020',1);
/*!40000 ALTER TABLE `Restriction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student`
--

DROP TABLE IF EXISTS `Student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Student` (
  `sid` int NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `major` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `department` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`sid`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student`
--

LOCK TABLES `Student` WRITE;
/*!40000 ALTER TABLE `Student` DISABLE KEYS */;
INSERT INTO `Student` VALUES (1,'Ryan Chiu','Computer Science','Department of CS','ryanpchiu@gmail.com'),(4,'Jeff','Computer Science','Department of CS','jeff@uchicago.edu'),(6,'Amy Quin','Computer Science','Department of CS','amy@gmail.com'),(7,'Bobby John','Computer Science','Department of CS','bobby@gmail.com'),(8,'Mary Kreg','Computer Science','Department of CS','mary@gmail.com'),(10,'Ryan Chiu','Computer Science','Department of CS','ryanchiu@gmail.com'),(13,'Alan Salkanović','Computer Science','Department of CS','alan@uchicago.edu'),(14,'John Doe','Computer Science','Department of CS','jd@gmail.com');
/*!40000 ALTER TABLE `Student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-14 15:21:29
