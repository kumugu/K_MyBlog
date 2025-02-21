-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: localhost    Database: myblog_db
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blog_post`
--

DROP TABLE IF EXISTS `blog_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `author` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_post`
--

LOCK TABLES `blog_post` WRITE;
/*!40000 ALTER TABLE `blog_post` DISABLE KEYS */;
INSERT INTO `blog_post` VALUES (24,'john_doe','안녕하세요! 이것은 제 첫 번째 블로그 포스트입니다.','첫 번째 블로그 포스트'),(25,'jane_smith','스프링 부트를 시작하는 방법에 대해 알아보겠습니다.','스프링 부트 시작하기'),(26,'mike_wilson','자바 프로그래밍을 할 때 알아야 할 꿀팁들을 공유합니다.','자바 프로그래밍 팁'),(27,'sarah_brown','효율적인 데이터베이스 설계 방법에 대해 설명합니다.','데이터베이스 설계 방법'),(28,'david_lee','리액트에서 재사용 가능한 컴포넌트를 만드는 방법','리액트 컴포넌트 만들기'),(29,'emma_davis','깃허브를 사용하는 기본적인 방법에 대해 알아봅시다.','깃허브 사용법'),(30,'james_taylor','코딩 테스트 준비 방법과 주요 알고리즘 설명','코딩 테스트 준비하기'),(31,'lisa_anderson','웹 애플리케이션 보안에 대한 기본적인 이해','웹 보안의 기초'),(32,'robert_martin','더 나은 코드를 작성하기 위한 방법들','클린 코드 작성법'),(33,'amy_white','RESTful API 설계시 고려해야 할 원칙들','API 설계 원칙'),(34,'john_doe','JPA를 이용한 데이터베이스 관리','JPA 사용하기'),(35,'jane_smith','효과적인 단위 테스트 작성 방법','테스트 코드 작성법'),(36,'mike_wilson','도커를 시작하는 방법과 기본 명령어','도커 컨테이너 입문'),(37,'sarah_brown','마이크로서비스의 장단점과 구현 방법','마이크로서비스 아키텍처'),(38,'david_lee','웹 성능 최적화를 위한 프론트엔드 기술','프론트엔드 최적화'),(39,'emma_davis','지속적 통합과 배포 파이프라인 설정 방법','CI/CD 파이프라인 구축'),(40,'james_taylor','AWS, GCP, Azure의 주요 서비스 비교','클라우드 서비스 비교'),(41,'lisa_anderson','효과적인 코드 리뷰를 위한 가이드라인','코드 리뷰 가이드'),(42,'robert_martin','자주 사용되는 디자인 패턴 설명','디자인 패턴 활용'),(43,'amy_white','주니어 개발자를 위한 커리어 조언','개발자 커리어 조언');
/*!40000 ALTER TABLE `blog_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `role` varbinary(255) DEFAULT NULL,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (16,NULL,NULL,'john@example.com','hashedpass123',_binary 'USER','john_doe'),(17,NULL,NULL,'jane@example.com','hashedpass456',_binary 'USER','jane_smith'),(18,NULL,NULL,'mike@example.com','hashedpass789',_binary 'USER','mike_wilson'),(19,NULL,NULL,'sarah@example.com','hashedpass321',_binary 'USER','sarah_brown'),(20,NULL,NULL,'david@example.com','hashedpass654',_binary 'USER','david_lee'),(21,NULL,NULL,'emma@example.com','hashedpass987',_binary 'USER','emma_davis'),(22,NULL,NULL,'james@example.com','hashedpass147',_binary 'USER','james_taylor'),(23,NULL,NULL,'lisa@example.com','hashedpass258',_binary 'USER','lisa_anderson'),(24,NULL,NULL,'robert@example.com','hashedpass369',_binary 'USER','robert_martin'),(25,NULL,NULL,'amy@example.com','hashedpass741',_binary 'USER','amy_white'),(26,NULL,NULL,'asdf@asdf.asdf','asdf',_binary 'ADMIN','asdf');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-21 16:29:09
