-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
--
-- Host: localhost    Database: devdogs
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (6);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `join_date` date DEFAULT NULL,
  `member_role` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2021-04-10 14:49:53','2021-04-10 14:49:53','eastheat10@naver.com','2021-04-01',NULL,'윤동열','1718c24b10aeb899e3fc44960ab6949ab76a267352459f23ea1036bec382c2','01051791813','201733027'),(2,'2021-04-10 14:50:26','2021-04-10 14:50:26','her0807@naver.com','2021-04-01',NULL,'허수진','1718c24b10aeb899e3fc44960ab6949ab76a267352459f23ea1036bec382c2','01021674543','201732038'),(3,'2021-04-10 14:51:22','2021-04-10 14:51:22','a@a.com','2021-04-01',NULL,'주동석','1718c24b10aeb899e3fc44960ab6949ab76a267352459f23ea1036bec382c2','01012345678','201814099'),(4,'2021-04-10 14:51:47','2021-04-10 14:51:47','a@a.com','2021-04-01',NULL,'오혜성','1718c24b10aeb899e3fc44960ab6949ab76a267352459f23ea1036bec382c2','01012344321','201732099'),(5,'2021-04-22 11:35:52','2021-04-22 11:35:52','a@gmail.com','2021-04-01',NULL,'Dong Yeol Youn','1718c24b10aeb899e3fc44960ab6949ab76a267352459f23ea1036bec382c2','01051791813','201512341'),(6,'2021-04-22 11:36:37','2021-04-22 11:36:37','a@a.com','2021-04-01',NULL,'안녕','932f3c1b56257ce8539ac269d7aab42550dacf8818d075f0bdf199562aae3ef','01012341234','1234123');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `content` text,
  `title` varchar(255) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `post_category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FK83s99f4kx8oiqm3ro0sasmpww` (`member_id`),
  KEY `FK59u555l1rdj2adjmxswcsni84` (`post_category_id`),
  CONSTRAINT `FK59u555l1rdj2adjmxswcsni84` FOREIGN KEY (`post_category_id`) REFERENCES `post_category` (`post_category_id`),
  CONSTRAINT `FK83s99f4kx8oiqm3ro0sasmpww` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=254 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,NULL,NULL,'공지사항 공지사항','공지사항1',3,1),(2,NULL,NULL,'공지사항2 공지사항2','공지사항2',4,1),(3,NULL,NULL,'jpa 123','jpa',1,2),(4,NULL,NULL,'ios 개발','ios',3,2),(5,NULL,NULL,'엔빵엔빵','n빵!',2,3),(6,NULL,NULL,'하는중...','캡스톤',2,3),(7,NULL,NULL,'이렇게 진행했따!','목요일 전체회의',4,4),(8,NULL,NULL,'spring spring','spring',1,2),(9,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',4,1),(10,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',1,1),(11,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',3,1),(12,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',3,1),(13,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',4,1),(14,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,1),(15,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',4,1),(16,'2021-05-18 04:18:02','2021-05-18 04:18:02','world	','hello 	',1,1),(17,'2021-05-18 04:18:02','2021-05-18 04:18:02','world	\n','hello 	\n',5,1),(18,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\n','hello \n',2,1),(19,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,1),(20,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\r','hello \r',3,1),(21,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\r','hello \r',3,1),(22,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',4,1),(23,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',3,1),(24,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,1),(25,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',3,1),(26,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',4,1),(27,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,1),(28,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',1,1),(29,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',1,2),(30,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,2),(31,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',2,2),(32,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',3,2),(33,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\n','hello \n',5,2),(34,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',4,2),(35,'2021-05-18 04:18:02','2021-05-18 04:18:02','world	','hello 	',2,2),(36,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\n','hello \n',2,2),(37,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',4,2),(38,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',3,2),(39,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\r','hello \r',5,2),(40,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,2),(41,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\Z','hello \Z',1,2),(42,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,2),(43,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,2),(44,'2021-05-18 04:18:02','2021-05-18 04:18:02','world ','hello  ',4,2),(45,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\"','hello \"',3,2),(46,'2021-05-18 04:18:02','2021-05-18 04:18:02','world$','hello $',5,2),(47,'2021-05-18 04:18:02','2021-05-18 04:18:02','world&','hello &',4,2),(48,'2021-05-18 04:18:02','2021-05-18 04:18:02','world(','hello (',2,2),(49,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,3),(50,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',3,3),(51,'2021-05-18 04:18:02','2021-05-18 04:18:02','world	','hello 	',1,3),(52,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',2,3),(53,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',3,3),(54,'2021-05-18 04:18:02','2021-05-18 04:18:02','world	','hello 	',3,3),(55,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\n','hello \n',5,3),(56,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',5,3),(57,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',1,3),(58,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\r','hello \r',3,3),(59,'2021-05-18 04:18:02','2021-05-18 04:18:02','world!','hello !',2,3),(60,'2021-05-18 04:18:02','2021-05-18 04:18:02','world$','hello $',4,3),(61,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\'','hello \'',4,3),(62,'2021-05-18 04:18:02','2021-05-18 04:18:02','world*','hello *',1,3),(63,'2021-05-18 04:18:02','2021-05-18 04:18:02','world-','hello -',4,3),(64,'2021-05-18 04:18:02','2021-05-18 04:18:02','world0','hello 0',5,3),(65,'2021-05-18 04:18:02','2021-05-18 04:18:02','world3','hello 3',5,3),(66,'2021-05-18 04:18:02','2021-05-18 04:18:02','world6','hello 6',1,3),(67,'2021-05-18 04:18:02','2021-05-18 04:18:02','world9','hello 9',1,3),(68,'2021-05-18 04:18:02','2021-05-18 04:18:02','world<','hello <',4,3),(69,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',1,4),(70,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',4,4),(71,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',4,4),(72,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',2,4),(73,'2021-05-18 04:18:02','2021-05-18 04:18:02','world	','hello 	',2,4),(74,'2021-05-18 04:18:02','2021-05-18 04:18:02','world\n','hello \n',1,4),(75,'2021-05-18 04:18:02','2021-05-18 04:18:02','world','hello ',1,4),(76,'2021-05-18 04:18:02','2021-05-18 04:18:02','world ','hello  ',1,4),(77,'2021-05-18 04:18:02','2021-05-18 04:18:02','world$\r','hello $\r',2,4),(78,'2021-05-18 04:18:02','2021-05-18 04:18:02','world(','hello (',5,4),(79,'2021-05-18 04:18:02','2021-05-18 04:18:02','world,','hello ,',5,4),(80,'2021-05-18 04:18:02','2021-05-18 04:18:02','world0','hello 0',5,4),(81,'2021-05-18 04:18:02','2021-05-18 04:18:02','world4','hello 4',3,4),(82,'2021-05-18 04:18:02','2021-05-18 04:18:02','world8','hello 8',2,4),(83,'2021-05-18 04:18:02','2021-05-18 04:18:02','world<','hello <',1,4),(84,'2021-05-18 04:18:02','2021-05-18 04:18:02','world@','hello @',4,4),(85,'2021-05-18 04:18:02','2021-05-18 04:18:02','worldD','hello D',3,4),(86,'2021-05-18 04:18:02','2021-05-18 04:18:02','worldH','hello H',2,4),(87,'2021-05-18 04:18:02','2021-05-18 04:18:02','worldL','hello L',2,4),(88,'2021-05-18 04:18:02','2021-05-18 04:18:02','worldP','hello P',2,4),(89,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,1),(90,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',5,1),(91,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',3,1),(92,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',3,1),(93,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',2,1),(94,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',2,1),(95,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',5,1),(96,'2021-05-18 04:18:13','2021-05-18 04:18:13','world	','hello 	',3,1),(97,'2021-05-18 04:18:13','2021-05-18 04:18:13','world	\n','hello 	\n',1,1),(98,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\n','hello \n',1,1),(99,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',5,1),(100,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\r','hello \r',1,1),(101,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\r','hello \r',3,1),(102,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',2,1),(103,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',2,1),(104,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,1),(105,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',2,1),(106,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',3,1),(107,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',5,1),(108,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,1),(109,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',3,2),(110,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',1,2),(111,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',5,2),(112,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',3,2),(113,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\n','hello \n',2,2),(114,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',3,2),(115,'2021-05-18 04:18:13','2021-05-18 04:18:13','world	','hello 	',2,2),(116,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\n','hello \n',1,2),(117,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,2),(118,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',1,2),(119,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\r','hello \r',1,2),(120,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',3,2),(121,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\Z','hello \Z',1,2),(122,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',5,2),(123,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,2),(124,'2021-05-18 04:18:13','2021-05-18 04:18:13','world ','hello  ',1,2),(125,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\"','hello \"',2,2),(126,'2021-05-18 04:18:13','2021-05-18 04:18:13','world$','hello $',1,2),(127,'2021-05-18 04:18:13','2021-05-18 04:18:13','world&','hello &',3,2),(128,'2021-05-18 04:18:13','2021-05-18 04:18:13','world(','hello (',3,2),(129,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',5,3),(130,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',3,3),(131,'2021-05-18 04:18:13','2021-05-18 04:18:13','world	','hello 	',1,3),(132,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,3),(133,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,3),(134,'2021-05-18 04:18:13','2021-05-18 04:18:13','world	','hello 	',3,3),(135,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\n','hello \n',3,3),(136,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',2,3),(137,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',5,3),(138,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\r','hello \r',4,3),(139,'2021-05-18 04:18:13','2021-05-18 04:18:13','world!','hello !',2,3),(140,'2021-05-18 04:18:13','2021-05-18 04:18:13','world$','hello $',4,3),(141,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\'','hello \'',1,3),(142,'2021-05-18 04:18:13','2021-05-18 04:18:13','world*','hello *',3,3),(143,'2021-05-18 04:18:13','2021-05-18 04:18:13','world-','hello -',3,3),(144,'2021-05-18 04:18:13','2021-05-18 04:18:13','world0','hello 0',4,3),(145,'2021-05-18 04:18:13','2021-05-18 04:18:13','world3','hello 3',1,3),(146,'2021-05-18 04:18:13','2021-05-18 04:18:13','world6','hello 6',3,3),(147,'2021-05-18 04:18:13','2021-05-18 04:18:13','world9','hello 9',3,3),(148,'2021-05-18 04:18:13','2021-05-18 04:18:13','world<','hello <',2,3),(149,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,4),(150,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,4),(151,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',3,4),(152,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',5,4),(153,'2021-05-18 04:18:13','2021-05-18 04:18:13','world	','hello 	',4,4),(154,'2021-05-18 04:18:13','2021-05-18 04:18:13','world\n','hello \n',3,4),(155,'2021-05-18 04:18:13','2021-05-18 04:18:13','world','hello ',4,4),(156,'2021-05-18 04:18:13','2021-05-18 04:18:13','world ','hello  ',4,4),(157,'2021-05-18 04:18:13','2021-05-18 04:18:13','world$\r','hello $\r',1,4),(158,'2021-05-18 04:18:13','2021-05-18 04:18:13','world(','hello (',4,4),(159,'2021-05-18 04:18:13','2021-05-18 04:18:13','world,','hello ,',2,4),(160,'2021-05-18 04:18:13','2021-05-18 04:18:13','world0','hello 0',3,4),(161,'2021-05-18 04:18:13','2021-05-18 04:18:13','world4','hello 4',5,4),(162,'2021-05-18 04:18:13','2021-05-18 04:18:13','world8','hello 8',2,4),(163,'2021-05-18 04:18:13','2021-05-18 04:18:13','world<','hello <',5,4),(164,'2021-05-18 04:18:13','2021-05-18 04:18:13','world@','hello @',3,4),(165,'2021-05-18 04:18:13','2021-05-18 04:18:13','worldD','hello D',4,4),(166,'2021-05-18 04:18:13','2021-05-18 04:18:13','worldH','hello H',5,4),(167,'2021-05-18 04:18:13','2021-05-18 04:18:13','worldL','hello L',1,4),(168,'2021-05-18 04:18:13','2021-05-18 04:18:13','worldP','hello P',1,4),(169,'2021-05-18 04:22:29','2021-05-18 04:22:29','world','hello ',3,1),(170,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',2,1),(171,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',5,1),(172,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',2,1),(173,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',2,1),(174,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',2,1),(175,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',3,1),(176,'2021-05-18 04:22:30','2021-05-18 04:22:30','world	','hello 	',2,1),(177,'2021-05-18 04:22:30','2021-05-18 04:22:30','world	\n','hello 	\n',3,1),(178,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\n','hello \n',5,1),(179,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',1,1),(180,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\r','hello \r',2,1),(181,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\r','hello \r',3,1),(182,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',5,1),(183,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',4,1),(184,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',3,1),(185,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',4,1),(186,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',4,1),(187,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',5,1),(188,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',2,1),(189,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',3,2),(190,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',5,2),(191,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',4,2),(192,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',1,2),(193,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\n','hello \n',3,2),(194,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',1,2),(195,'2021-05-18 04:22:30','2021-05-18 04:22:30','world	','hello 	',2,2),(196,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\n','hello \n',1,2),(197,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',3,2),(198,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',1,2),(199,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\r','hello \r',1,2),(200,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',1,2),(201,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\Z','hello \Z',5,2),(202,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',1,2),(203,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',3,2),(204,'2021-05-18 04:22:30','2021-05-18 04:22:30','world ','hello  ',2,2),(205,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\"','hello \"',5,2),(206,'2021-05-18 04:22:30','2021-05-18 04:22:30','world$','hello $',1,2),(207,'2021-05-18 04:22:30','2021-05-18 04:22:30','world&','hello &',3,2),(208,'2021-05-18 04:22:30','2021-05-18 04:22:30','world(','hello (',2,2),(209,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',4,3),(210,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',4,3),(211,'2021-05-18 04:22:30','2021-05-18 04:22:30','world	','hello 	',5,3),(212,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',2,3),(213,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',4,3),(214,'2021-05-18 04:22:30','2021-05-18 04:22:30','world	','hello 	',4,3),(215,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\n','hello \n',3,3),(216,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',2,3),(217,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',3,3),(218,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\r','hello \r',5,3),(219,'2021-05-18 04:22:30','2021-05-18 04:22:30','world!','hello !',4,3),(220,'2021-05-18 04:22:30','2021-05-18 04:22:30','world$','hello $',2,3),(221,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\'','hello \'',2,3),(222,'2021-05-18 04:22:30','2021-05-18 04:22:30','world*','hello *',5,3),(223,'2021-05-18 04:22:30','2021-05-18 04:22:30','world-','hello -',4,3),(224,'2021-05-18 04:22:30','2021-05-18 04:22:30','world0','hello 0',3,3),(225,'2021-05-18 04:22:30','2021-05-18 04:22:30','world3','hello 3',2,3),(226,'2021-05-18 04:22:30','2021-05-18 04:22:30','world6','hello 6',5,3),(227,'2021-05-18 04:22:30','2021-05-18 04:22:30','world9','hello 9',1,3),(228,'2021-05-18 04:22:30','2021-05-18 04:22:30','world<','hello <',2,3),(229,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',3,4),(230,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',1,4),(231,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',5,4),(232,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',2,4),(233,'2021-05-18 04:22:30','2021-05-18 04:22:30','world	','hello 	',4,4),(234,'2021-05-18 04:22:30','2021-05-18 04:22:30','world\n','hello \n',4,4),(235,'2021-05-18 04:22:30','2021-05-18 04:22:30','world','hello ',1,4),(236,'2021-05-18 04:22:30','2021-05-18 04:22:30','world ','hello  ',2,4),(237,'2021-05-18 04:22:30','2021-05-18 04:22:30','world$\r','hello $\r',2,4),(238,'2021-05-18 04:22:30','2021-05-18 04:22:30','world(','hello (',2,4),(239,'2021-05-18 04:22:30','2021-05-18 04:22:30','world,','hello ,',5,4),(240,'2021-05-18 04:22:30','2021-05-18 04:22:30','world0','hello 0',1,4),(241,'2021-05-18 04:22:30','2021-05-18 04:22:30','world4','hello 4',5,4),(242,'2021-05-18 04:22:30','2021-05-18 04:22:30','world8','hello 8',5,4),(243,'2021-05-18 04:22:30','2021-05-18 04:22:30','world<','hello <',4,4),(244,'2021-05-18 04:22:30','2021-05-18 04:22:30','world@','hello @',5,4),(245,'2021-05-18 04:22:30','2021-05-18 04:22:30','worldD','hello D',3,4),(246,'2021-05-18 04:22:30','2021-05-18 04:22:30','worldH','hello H',5,4),(247,'2021-05-18 04:22:30','2021-05-18 04:22:30','worldL','hello L',3,4),(248,'2021-05-18 04:22:30','2021-05-18 04:22:30','worldP','hello P',2,4),(249,'2021-05-27 06:30:11','2021-05-27 06:30:11','18:30','오늘 몇시',1,1),(250,'2021-05-27 06:32:24','2021-05-27 06:32:24','글쎄','내일 몇시',1,1),(251,'2021-05-27 06:44:09','2021-05-27 06:44:09','만드는중~','개들 페이지',1,3),(252,'2021-05-27 06:45:28','2021-05-27 06:45:28','18:30 ','오늘 몇시에 회의?',1,1),(253,'2021-05-27 06:45:44','2021-05-27 06:45:44','파이팅','거의 다 했다',1,2);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_category`
--

DROP TABLE IF EXISTS `post_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_category` (
  `post_category_id` bigint NOT NULL AUTO_INCREMENT,
  `post_category_name` varchar(255) DEFAULT NULL,
  `post_category_ko_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`post_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_category`
--

LOCK TABLES `post_category` WRITE;
/*!40000 ALTER TABLE `post_category` DISABLE KEYS */;
INSERT INTO `post_category` VALUES (1,'notice','공지사항'),(2,'free','자유게시판'),(3,'project','프로젝트'),(4,'active','활동내역');
/*!40000 ALTER TABLE `post_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-27 15:54:01