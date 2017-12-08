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
-- Table structure for table `mov_pedido`
--

DROP TABLE IF EXISTS `mov_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mov_pedido` (
  `codPedido` int(11) NOT NULL AUTO_INCREMENT,
  `valorTotal` float NOT NULL,
  `valorEntrega` float NOT NULL,
  `valorDesconto` float NOT NULL,
  `codCliente` int(11) NOT NULL,
  `codEstabelecimento` int(11) NOT NULL,
  `dataPedido` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `statusPedido` varchar(45) NOT NULL,
  PRIMARY KEY (`codPedido`),
  KEY `codCliente_idx` (`codCliente`),
  KEY `codEstabelecimento_idx` (`codEstabelecimento`),
  CONSTRAINT `codCliente` FOREIGN KEY (`codCliente`) REFERENCES `cad_cliente` (`codCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `codEstabelecimento` FOREIGN KEY (`codEstabelecimento`) REFERENCES `cad_estabelecimento` (`codEstabelecimento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mov_pedido`
--

LOCK TABLES `mov_pedido` WRITE;
/*!40000 ALTER TABLE `mov_pedido` DISABLE KEYS */;
INSERT INTO `mov_pedido` VALUES (1,156.5,2.5,1,1,1,'2016-05-06 00:17:47','Realizado'),(2,84,4,0,2,1,'2016-05-06 00:36:13','Fechado');
/*!40000 ALTER TABLE `mov_pedido` ENABLE KEYS */;
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
