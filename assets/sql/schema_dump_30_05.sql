-- MySQL dump 10.13  Distrib 5.7.22, for macos10.13 (x86_64)
--
-- Host: localhost    Database: symptomizer_db
-- ------------------------------------------------------
-- Server version	5.7.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `diseases`
--

DROP TABLE IF EXISTS `diseases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diseases` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_name_ru` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diseases`
--

LOCK TABLES `diseases` WRITE;
/*!40000 ALTER TABLE `diseases` DISABLE KEYS */;
/*!40000 ALTER TABLE `diseases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genders`
--

DROP TABLE IF EXISTS `genders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genders` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `genders__id_uindex` (`_id`),
  UNIQUE KEY `genders__code_uindex` (`_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genders`
--

LOCK TABLES `genders` WRITE;
/*!40000 ALTER TABLE `genders` DISABLE KEYS */;
/*!40000 ALTER TABLE `genders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `roles__id_uindex` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom_groups`
--

DROP TABLE IF EXISTS `symptom_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symptom_groups` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_name_ru` int(11) DEFAULT NULL,
  `_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `symptom_groups__id_uindex` (`_id`),
  UNIQUE KEY `symptom_groups__code_uindex` (`_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom_groups`
--

LOCK TABLES `symptom_groups` WRITE;
/*!40000 ALTER TABLE `symptom_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `symptom_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom_questions`
--

DROP TABLE IF EXISTS `symptom_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symptom_questions` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_name` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_name_ru` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `symptom_questions__id_uindex` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom_questions`
--

LOCK TABLES `symptom_questions` WRITE;
/*!40000 ALTER TABLE `symptom_questions` DISABLE KEYS */;
/*!40000 ALTER TABLE `symptom_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptom_questions_map`
--

DROP TABLE IF EXISTS `symptom_questions_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symptom_questions_map` (
  `question_id` bigint(20) NOT NULL,
  `symptom_id` bigint(20) NOT NULL,
  KEY `symptom_questions_map_symptom_questions__id_fk` (`question_id`),
  CONSTRAINT `symptom_questions_map_symptom_questions__id_fk` FOREIGN KEY (`question_id`) REFERENCES `symptom_questions` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptom_questions_map`
--

LOCK TABLES `symptom_questions_map` WRITE;
/*!40000 ALTER TABLE `symptom_questions_map` DISABLE KEYS */;
/*!40000 ALTER TABLE `symptom_questions_map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symptoms`
--

DROP TABLE IF EXISTS `symptoms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symptoms` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_code` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_name_ru` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `_group_id` bigint(20) NOT NULL,
  `_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `symptoms__id_uindex` (`_id`),
  KEY `symptoms_symptom_groups__id_fk` (`_group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symptoms`
--

LOCK TABLES `symptoms` WRITE;
/*!40000 ALTER TABLE `symptoms` DISABLE KEYS */;
/*!40000 ALTER TABLE `symptoms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `_username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `_gender_id` bigint(20) NOT NULL,
  `_created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `users__id_uindex` (`_id`),
  KEY `users_genders__id_fk` (`_gender_id`),
  CONSTRAINT `users_genders__id_fk` FOREIGN KEY (`_gender_id`) REFERENCES `genders` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `_user_id` bigint(20) NOT NULL,
  `_role_id` bigint(20) NOT NULL,
  KEY `users_roles_users__id_fk` (`_user_id`),
  KEY `users_roles_roles__id_fk` (`_role_id`),
  CONSTRAINT `users_roles_roles__id_fk` FOREIGN KEY (`_role_id`) REFERENCES `roles` (`_id`),
  CONSTRAINT `users_roles_users__id_fk` FOREIGN KEY (`_user_id`) REFERENCES `users` (`_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-30 23:02:33
