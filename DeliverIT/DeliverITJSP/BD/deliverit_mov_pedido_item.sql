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
-- Table structure for table `mov_pedido_item`
--

DROP TABLE IF EXISTS `mov_pedido_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mov_pedido_item` (
  `codItem` int(11) NOT NULL AUTO_INCREMENT,
  `codProduto` int(11) NOT NULL,
  `codPedido` int(11) NOT NULL,
  `qtdProduto` int(11) NOT NULL,
  PRIMARY KEY (`codItem`),
  KEY `codPedido_idx` (`codPedido`),
  KEY `codProduto_idx` (`codProduto`),
  CONSTRAINT `codPedido` FOREIGN KEY (`codPedido`) REFERENCES `mov_pedido` (`codPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codProduto` FOREIGN KEY (`codProduto`) REFERENCES `cad_produto` (`codProduto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mov_pedido_item`
--

LOCK TABLES `mov_pedido_item` WRITE;
/*!40000 ALTER TABLE `mov_pedido_item` DISABLE KEYS */;
INSERT INTO `mov_pedido_item` VALUES (1,16,1,12),(2,19,1,2),(3,21,1,1),(4,12,2,2),(5,17,2,1),(6,16,2,12);
/*!40000 ALTER TABLE `mov_pedido_item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-06 13:10:47
