CREATE DATABASE  IF NOT EXISTS `deliverit` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `deliverit`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: deliverit
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `cad_fluxocaixa`
--

DROP TABLE IF EXISTS `cad_fluxocaixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cad_fluxocaixa` (
  `codFluxoCaixa` int(11) NOT NULL AUTO_INCREMENT,
  `data` timestamp(5) NOT NULL DEFAULT CURRENT_TIMESTAMP(5) ON UPDATE CURRENT_TIMESTAMP(5),
  `movimentacao` tinyint(1) DEFAULT NULL,
  `valor` float NOT NULL,
  `descricao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codFluxoCaixa`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cad_fluxocaixa`
--

LOCK TABLES `cad_fluxocaixa` WRITE;
/*!40000 ALTER TABLE `cad_fluxocaixa` DISABLE KEYS */;
INSERT INTO `cad_fluxocaixa` VALUES (6,'2016-05-05 19:34:19.47900',1,84,'Pedido Nº 2'),(7,'2016-05-05 23:46:12.85700',1,84,'Pedido Nº 2'),(8,'2016-05-05 23:50:11.45400',1,155.5,'Pedido Nº 1'),(9,'2016-05-05 23:57:53.60400',1,84,'Pedido Nº 2'),(10,'2016-05-06 00:10:49.41700',1,155.5,'Pedido Nº 1'),(11,'2016-05-06 00:36:13.47600',1,84,'Pedido Nº 2'),(12,'2016-05-06 00:39:46.66100',1,123,'Teste');
/*!40000 ALTER TABLE `cad_fluxocaixa` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-06 13:10:46