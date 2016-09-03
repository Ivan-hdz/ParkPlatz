CREATE DATABASE  IF NOT EXISTS `parkplatz` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `parkplatz`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: parkplatz
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
-- Table structure for table `asset`
--

DROP TABLE IF EXISTS `asset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asset` (
  `idasset` int(11) NOT NULL,
  `idTipoAsset` int(11) DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idasset`),
  KEY `asset` (`idTipoAsset`),
  CONSTRAINT `asset_ibfk_1` FOREIGN KEY (`idTipoAsset`) REFERENCES `cattipoasset` (`idcattipoasset`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asset`
--

LOCK TABLES `asset` WRITE;
/*!40000 ALTER TABLE `asset` DISABLE KEYS */;
INSERT INTO `asset` VALUES (1,1,'default'),(2,1,'default'),(3,1,'default'),(4,1,'default'),(5,1,'default'),(6,1,'default'),(7,1,'default'),(8,1,'default'),(9,1,'default'),(10,1,'/Users/UCER/Desktop/EDD/Parkplatz-master/Parkplatz-web/build/web/Imagenes/e2@e.com/3/icon_no_fav.png'),(11,1,'default'),(12,1,'default'),(13,1,'default'),(14,1,'C:\\Users\\Ivan\\Documents\\GitHub\\Parkplatz\\Parkplatz-web\\build\\web\\Imagenes\\EmmaParra@gmail.com\\7\\18447-green-galaxy-1680x1050-space-wallpaper.jpg'),(15,1,'default');
/*!40000 ALTER TABLE `asset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cat_menu`
--

DROP TABLE IF EXISTS `cat_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cat_menu` (
  `idMenu` int(11) NOT NULL,
  `nombreCampo` varchar(45) NOT NULL,
  `tipoCampo` varchar(45) NOT NULL,
  `nombreAMostrar` varchar(100) NOT NULL,
  KEY `id_menu` (`idMenu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cat_menu`
--

LOCK TABLES `cat_menu` WRITE;
/*!40000 ALTER TABLE `cat_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `cat_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogomodolugares`
--

DROP TABLE IF EXISTS `catalogomodolugares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogomodolugares` (
  `idcatalogomodolugares` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcatalogomodolugares`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogomodolugares`
--

LOCK TABLES `catalogomodolugares` WRITE;
/*!40000 ALTER TABLE `catalogomodolugares` DISABLE KEYS */;
INSERT INTO `catalogomodolugares` VALUES (0,'Sin asignar'),(1,'Disponible'),(2,'Ocupado'),(3,'Fuera de Servicio');
/*!40000 ALTER TABLE `catalogomodolugares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catprioridades`
--

DROP TABLE IF EXISTS `catprioridades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catprioridades` (
  `idcatprioridades` int(11) NOT NULL,
  `prioridad` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcatprioridades`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catprioridades`
--

LOCK TABLES `catprioridades` WRITE;
/*!40000 ALTER TABLE `catprioridades` DISABLE KEYS */;
INSERT INTO `catprioridades` VALUES (1,'Alta'),(2,'Moderada'),(3,'Aviso');
/*!40000 ALTER TABLE `catprioridades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cattipoasset`
--

DROP TABLE IF EXISTS `cattipoasset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cattipoasset` (
  `idcattipoasset` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcattipoasset`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cattipoasset`
--

LOCK TABLES `cattipoasset` WRITE;
/*!40000 ALTER TABLE `cattipoasset` DISABLE KEYS */;
INSERT INTO `cattipoasset` VALUES (1,'Imagen Estacionamiento');
/*!40000 ALTER TABLE `cattipoasset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cattipocoordenadas`
--

DROP TABLE IF EXISTS `cattipocoordenadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cattipocoordenadas` (
  `idcattipocoordenadas` int(11) NOT NULL,
  `proposito` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcattipocoordenadas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cattipocoordenadas`
--

LOCK TABLES `cattipocoordenadas` WRITE;
/*!40000 ALTER TABLE `cattipocoordenadas` DISABLE KEYS */;
INSERT INTO `cattipocoordenadas` VALUES (1,'De Estacionamiento'),(2,'De Lugar de Aparcamiento');
/*!40000 ALTER TABLE `cattipocoordenadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catubicacionesestacionamiento`
--

DROP TABLE IF EXISTS `catubicacionesestacionamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catubicacionesestacionamiento` (
  `idcatubicacionesestacionamiento` int(11) NOT NULL,
  `calle` varchar(45) DEFAULT NULL,
  `colonia` varchar(45) DEFAULT NULL,
  `delegacionMunicipio` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `idCoordenadas` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcatubicacionesestacionamiento`),
  KEY `idCoordenadas` (`idCoordenadas`),
  CONSTRAINT `catubicacionesestacionamiento_ibfk_1` FOREIGN KEY (`idCoordenadas`) REFERENCES `coordenadas` (`idcoordenadas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catubicacionesestacionamiento`
--

LOCK TABLES `catubicacionesestacionamiento` WRITE;
/*!40000 ALTER TABLE `catubicacionesestacionamiento` DISABLE KEYS */;
INSERT INTO `catubicacionesestacionamiento` VALUES (1,'Mar Mediterraneo 227','Popotla','Miguel Hidalgo','Ciudad de Mexico',1),(2,'popotla 23','popotla','miguel hidalgo','estado de mexico',2),(3,'salvador','centro','miguel','distrito federal',3),(4,'emiliano zapata 43','los reyes iztacala','tlane','estado de mexico',4),(5,'moliere 222','polanco','miguel hidalgo','df',5),(6,'presidente masaryk 419','polanco','miguel hidalgo','df',6),(7,'Privada medina 18','Pantitlan','Iztacalco','Ciudad de Mexico',14),(8,'Av. Camarones','Camarones','Azcapotzalco','Ciudad de Mexico',17);
/*!40000 ALTER TABLE `catubicacionesestacionamiento` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger del_coor after delete on catubicacionesestacionamiento for each row
begin
	delete from coordenadas where idcoordenadas = OLD.idCoordenadas;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `coordenadas`
--

DROP TABLE IF EXISTS `coordenadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coordenadas` (
  `idcoordenadas` int(11) NOT NULL,
  `cordenadaX` varchar(500) DEFAULT NULL,
  `coordenadaY` varchar(500) DEFAULT NULL,
  `idTipoCoordenadas` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcoordenadas`),
  KEY `idTipoCoordenadas` (`idTipoCoordenadas`),
  CONSTRAINT `coordenadas_ibfk_1` FOREIGN KEY (`idTipoCoordenadas`) REFERENCES `cattipocoordenadas` (`idcattipocoordenadas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordenadas`
--

LOCK TABLES `coordenadas` WRITE;
/*!40000 ALTER TABLE `coordenadas` DISABLE KEYS */;
INSERT INTO `coordenadas` VALUES (1,'19.4538726','-99.17493780000001',1),(2,'19.4553135','-99.1774092',1),(3,'19.4248944','-99.13934970000003',1),(4,'19.5299658','-99.19198469999998',1),(5,'19.4353653','-99.20221070000002',1),(6,'19.4321176','-99.19944709999999',1),(7,'203.0','261.0',2),(8,'316.0','152.0',2),(9,'151.0','115.0',2),(10,'308.0','335.0',2),(11,'0','0',2),(12,'253.0','248.0',2),(13,'249.0','232.0',2),(14,'19.411343455593908','-99.06449686850891',1),(15,'85.0','60.0',2),(16,'153.0','58.0',2),(17,'19.4770969','-99.18400789999998',1);
/*!40000 ALTER TABLE `coordenadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos`
--

DROP TABLE IF EXISTS `datos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datos` (
  `iddatos` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `aPaterno` varchar(45) NOT NULL,
  `aMaterno` varchar(45) NOT NULL,
  PRIMARY KEY (`iddatos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos`
--

LOCK TABLES `datos` WRITE;
/*!40000 ALTER TABLE `datos` DISABLE KEYS */;
INSERT INTO `datos` VALUES (1,'Octavio Ivan','Hdz','S.'),(2,'Ivan ','Hdz','S.'),(3,'IVan','Hdz','S'),(4,'Octavio','Hdz','S..'),(5,'A','B','C'),(6,'Juan','Jain','Vargas'),(7,'IVan','Hdz','S'),(8,'Ivan','Hdz','S'),(9,'Ivan','Hdz','S.'),(10,'Juan','Jain','Vargas'),(11,'Ivan','Hdz','Salinas'),(12,'peter','pet','pet'),(13,'admin','admin','admin'),(14,'gggg','mmm','mm'),(15,'lol','lol','lol'),(16,'lol','lol','lol'),(17,'pedro','pedr','pder'),(18,'f','f','f'),(19,'Yhael','Saed','Saedd'),(20,'Isui','Estrada','Pichardo'),(21,'Ivan','Hfz','S'),(22,'hh','hh','hhh'),(23,'Ivan','Hdz','S'),(24,'Emmanuel','Ramirez','Parra'),(25,'Emma','ramirez','zs'),(26,'Emma','ramirez','zs'),(27,'la','la','la'),(28,'la','la','la'),(29,'lalo','lalo','lalo'),(30,'lalo','lalo','lalo'),(31,'juan','cru','menxoza'),(32,'null','null','null'),(33,'Juan','J','J'),(34,'Administrador','De','ParkPlatz'),(35,'Administrador','De','ParkPlatz');
/*!40000 ALTER TABLE `datos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datosestacionamientos`
--

DROP TABLE IF EXISTS `datosestacionamientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `datosestacionamientos` (
  `iddatosestacionamientos` int(11) NOT NULL,
  `nombreEstacionamiento` varchar(45) DEFAULT NULL,
  `idUbicacion` int(11) DEFAULT NULL,
  `horarios` varchar(500) DEFAULT 'Escriba una descripcion',
  `tarifa` varchar(500) DEFAULT 'Escriba una tarifa para sus clientes',
  `alturaMaxima` float DEFAULT '0',
  `descripcion` varchar(500) DEFAULT 'Favor de agregar una descripcion',
  `idAsset` int(11) DEFAULT NULL,
  PRIMARY KEY (`iddatosestacionamientos`),
  KEY `datosestacionamientos` (`idUbicacion`),
  KEY `idAsset` (`idAsset`),
  CONSTRAINT `datosestacionamientos_ibfk_1` FOREIGN KEY (`idUbicacion`) REFERENCES `catubicacionesestacionamiento` (`idcatubicacionesestacionamiento`),
  CONSTRAINT `datosestacionamientos_ibfk_2` FOREIGN KEY (`idAsset`) REFERENCES `asset` (`idasset`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datosestacionamientos`
--

LOCK TABLES `datosestacionamientos` WRITE;
/*!40000 ALTER TABLE `datosestacionamientos` DISABLE KEYS */;
INSERT INTO `datosestacionamientos` VALUES (1,'Estacionamiento',1,NULL,NULL,NULL,NULL,8),(2,'Estacionamiento 1',2,'12:00-15:00','10 varos',2.5,'webos a todos',9),(3,'Estacion Normal ',3,NULL,NULL,NULL,NULL,10),(4,'Xasa de Yhael Saeed',4,'13:00- 20:00','un benito',2222,'VIVA TLANE',11),(5,'Palacio de Palacios',5,'1:00 10:00','1222',12,'NICE',12),(6,'Estacionamiento de Lujo',6,'13:00- 20:00\'','dos benitos',2,'De lujo',13),(7,'Estacionamiento de emmanuel',7,'7am - 7pm','12 pesos por hora',3.2,'Mucha seguridad ',14),(8,'Estacionamiento de Juan',8,NULL,NULL,NULL,NULL,15);
/*!40000 ALTER TABLE `datosestacionamientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estacionamientos`
--

DROP TABLE IF EXISTS `estacionamientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estacionamientos` (
  `idestacionamientos` int(11) NOT NULL,
  `idDatosEstacionamiento` int(11) DEFAULT NULL,
  `idDatos` int(11) DEFAULT NULL,
  PRIMARY KEY (`idestacionamientos`),
  KEY `estacionamientos` (`idDatosEstacionamiento`),
  KEY `idDatos` (`idDatos`),
  CONSTRAINT `estacionamientos_ibfk_1` FOREIGN KEY (`idDatosEstacionamiento`) REFERENCES `datosestacionamientos` (`iddatosestacionamientos`),
  CONSTRAINT `estacionamientos_ibfk_2` FOREIGN KEY (`idDatos`) REFERENCES `datos` (`iddatos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacionamientos`
--

LOCK TABLES `estacionamientos` WRITE;
/*!40000 ALTER TABLE `estacionamientos` DISABLE KEYS */;
INSERT INTO `estacionamientos` VALUES (1,1,9),(2,2,15),(3,3,16),(4,4,15),(5,5,16),(6,6,19),(7,7,24),(8,8,33);
/*!40000 ALTER TABLE `estacionamientos` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger del_datos after delete on estacionamientos for each row
begin
	delete from datosestacionamientos where iddatosestacionamientos = OLD.idDatosEstacionamiento;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `favoritos`
--

DROP TABLE IF EXISTS `favoritos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favoritos` (
  `idFavoritos` int(11) NOT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `idEstacionamiento` int(11) DEFAULT NULL,
  PRIMARY KEY (`idFavoritos`),
  KEY `favoritos` (`idUsuario`),
  KEY `idEstacionamiento` (`idEstacionamiento`),
  CONSTRAINT `favoritos_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `favoritos_ibfk_2` FOREIGN KEY (`idEstacionamiento`) REFERENCES `estacionamientos` (`idestacionamientos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favoritos`
--

LOCK TABLES `favoritos` WRITE;
/*!40000 ALTER TABLE `favoritos` DISABLE KEYS */;
INSERT INTO `favoritos` VALUES (1,12,1),(2,12,2),(3,12,3),(4,13,3),(5,18,5),(6,20,7),(7,17,7);
/*!40000 ALTER TABLE `favoritos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `idfeedback` int(11) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `idPrioridad` int(11) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idfeedback`),
  KEY `feedback` (`idUsuario`),
  KEY `idPrioridad` (`idPrioridad`),
  CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idusuario`),
  CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`idPrioridad`) REFERENCES `catprioridades` (`idcatprioridades`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,'Un feedback hecho por Emmanuel Lopez Montecinos',6,1,'2016-04-06 21:48:49'),(2,'p1',17,1,'2016-05-13 18:52:06'),(3,'p1jdjd',24,1,'2016-05-13 18:59:48'),(4,'p1jdjd',24,1,'2016-05-13 19:01:20'),(5,'1',24,1,'2016-05-13 19:39:38'),(6,'catman es la polla con cebolla',24,1,'2016-05-13 19:40:15');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lugares`
--

DROP TABLE IF EXISTS `lugares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lugares` (
  `idlugares` int(11) NOT NULL,
  `idModoLugar` int(11) DEFAULT NULL,
  `idCoordenadas` int(11) DEFAULT NULL,
  `piso` int(2) DEFAULT NULL,
  PRIMARY KEY (`idlugares`),
  KEY `lugares` (`idModoLugar`),
  KEY `idCoordenadas` (`idCoordenadas`),
  CONSTRAINT `lugares_ibfk_1` FOREIGN KEY (`idModoLugar`) REFERENCES `catalogomodolugares` (`idcatalogomodolugares`),
  CONSTRAINT `lugares_ibfk_2` FOREIGN KEY (`idCoordenadas`) REFERENCES `coordenadas` (`idcoordenadas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lugares`
--

LOCK TABLES `lugares` WRITE;
/*!40000 ALTER TABLE `lugares` DISABLE KEYS */;
INSERT INTO `lugares` VALUES (9985,1,13,1);
/*!40000 ALTER TABLE `lugares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recientes`
--

DROP TABLE IF EXISTS `recientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recientes` (
  `idrecientes` int(11) NOT NULL,
  `idEstacionamientos` int(11) DEFAULT NULL,
  `idUsuario` int(11) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idrecientes`),
  KEY `recientes` (`idEstacionamientos`),
  KEY `idUsuario` (`idUsuario`),
  CONSTRAINT `recientes_ibfk_1` FOREIGN KEY (`idEstacionamientos`) REFERENCES `estacionamientos` (`idestacionamientos`),
  CONSTRAINT `recientes_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recientes`
--

LOCK TABLES `recientes` WRITE;
/*!40000 ALTER TABLE `recientes` DISABLE KEYS */;
INSERT INTO `recientes` VALUES (1,2,12,'2016-04-25 09:07:38'),(2,3,12,'2016-04-25 09:07:49'),(3,1,12,'2016-04-25 09:07:52'),(4,4,15,'2016-05-03 15:13:32'),(5,5,18,'2016-05-06 17:17:32'),(6,7,18,'2016-05-06 17:29:40'),(7,4,18,'2016-05-06 17:34:04'),(8,7,17,'2016-05-06 18:25:23'),(9,8,24,'2016-05-12 19:16:45'),(10,5,24,'2016-05-12 19:20:44'),(11,7,24,'2016-05-13 18:29:32');
/*!40000 ALTER TABLE `recientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rel_servicios_estacionamientos`
--

DROP TABLE IF EXISTS `rel_servicios_estacionamientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_servicios_estacionamientos` (
  `idRelacion` int(11) NOT NULL AUTO_INCREMENT,
  `idEstacionamiento` int(11) NOT NULL,
  `idServicio` int(11) NOT NULL,
  PRIMARY KEY (`idRelacion`),
  KEY `indexEstacionamiento` (`idEstacionamiento`),
  KEY `indexServicio` (`idServicio`),
  CONSTRAINT `fk1_idEstacionamiento` FOREIGN KEY (`idEstacionamiento`) REFERENCES `estacionamientos` (`idestacionamientos`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk2_idServicio` FOREIGN KEY (`idServicio`) REFERENCES `servicios` (`idservicios`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_servicios_estacionamientos`
--

LOCK TABLES `rel_servicios_estacionamientos` WRITE;
/*!40000 ALTER TABLE `rel_servicios_estacionamientos` DISABLE KEYS */;
INSERT INTO `rel_servicios_estacionamientos` VALUES (1,7,5);
/*!40000 ALTER TABLE `rel_servicios_estacionamientos` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 trigger del_servicio after delete on rel_servicios_estacionamientos for each row
begin
	delete from servicios where idservicios = OLD.idServicio;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `rel_tipousuario_menu`
--

DROP TABLE IF EXISTS `rel_tipousuario_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rel_tipousuario_menu` (
  `idTipoUsuario` int(11) NOT NULL,
  `idMenu` int(11) NOT NULL,
  PRIMARY KEY (`idTipoUsuario`),
  KEY `idTipoUsuario` (`idTipoUsuario`),
  KEY `idMenu` (`idMenu`),
  CONSTRAINT `fk_menu` FOREIGN KEY (`idMenu`) REFERENCES `cat_menu` (`idMenu`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipoUsuario` FOREIGN KEY (`idTipoUsuario`) REFERENCES `tipousuario` (`idtipoUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rel_tipousuario_menu`
--

LOCK TABLES `rel_tipousuario_menu` WRITE;
/*!40000 ALTER TABLE `rel_tipousuario_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `rel_tipousuario_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relestalugar`
--

DROP TABLE IF EXISTS `relestalugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relestalugar` (
  `idrelEstaLugar` int(11) NOT NULL,
  `idEstacionamiento` int(11) NOT NULL,
  `idLugar` int(11) NOT NULL,
  PRIMARY KEY (`idrelEstaLugar`,`idEstacionamiento`,`idLugar`),
  KEY `relestalugar` (`idEstacionamiento`),
  KEY `idLugar` (`idLugar`),
  CONSTRAINT `relestalugar_ibfk_1` FOREIGN KEY (`idEstacionamiento`) REFERENCES `estacionamientos` (`idestacionamientos`),
  CONSTRAINT `relestalugar_ibfk_2` FOREIGN KEY (`idLugar`) REFERENCES `lugares` (`idlugares`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relestalugar`
--

LOCK TABLES `relestalugar` WRITE;
/*!40000 ALTER TABLE `relestalugar` DISABLE KEYS */;
INSERT INTO `relestalugar` VALUES (1,1,9985);
/*!40000 ALTER TABLE `relestalugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicios` (
  `idservicios` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `precio` float DEFAULT NULL,
  PRIMARY KEY (`idservicios`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios`
--

LOCK TABLES `servicios` WRITE;
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
INSERT INTO `servicios` VALUES (3,'Oferta 1','Oferta',1500),(4,'Mi oferta','Descripcion',1500),(5,'Lavado de autos','Se lavan los autos',25);
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipousuario` (
  `idtipoUsuario` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtipoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` VALUES (1,'Administrador'),(2,'Estacionamiento'),(3,'Conductor');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL,
  `idDatos` int(11) NOT NULL,
  `idTipoUsuario` int(11) NOT NULL,
  `password` text NOT NULL,
  `correo` varchar(45) NOT NULL,
  PRIMARY KEY (`idusuario`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  KEY `usuario` (`idDatos`),
  KEY `idTipoUsuario` (`idTipoUsuario`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idDatos`) REFERENCES `datos` (`iddatos`),
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`idDatos`) REFERENCES `datos` (`iddatos`),
  CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`idTipoUsuario`) REFERENCES `tipousuario` (`idtipoUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (3,6,1,'bxF1s3FumWw=','juan@parkplatz.com'),(4,9,2,'bxF1s3FumWw=','honter1997@gmail.com'),(5,10,1,'S3+0Nyzxvxw=','juanCP@parkplatz.com'),(6,11,3,'SnOwPBIwGfs=','h@hotmail.com'),(7,12,3,'FRXaKdShBvI=','p@p.com'),(8,13,1,'S3+0Nyzxvxw=','admin@parkplatz.com'),(9,14,3,'KuvmssILqJs=','m@m.m'),(10,15,2,'O8mnyK681Zk=','e1@e.com'),(11,16,2,'J6bzK3SoTuA=','e2@e.com'),(12,17,3,'KuvmssILqJs=','a@a.a'),(13,18,3,'qcMDy9Pv3RI=','f@f.f'),(14,19,2,'bbc9Up5gOjk=','e3@e.com'),(15,20,3,'xQq3kBlWGMA=','isui@gmail.com'),(16,21,3,'OaXDk67ZNKM=','asd@asd.com'),(17,22,3,'uP+UcRx1v0E=','lalo@lalo.com'),(18,23,3,'tXvM8vYIT5A=','IvanHdz@gmail.com'),(19,24,2,'tb8ZcSzM9zY=','EmmaParra@gmail.com'),(20,26,3,'POYTtgL6794+8CLB//Q0YA==','lal1o@lalo.com'),(21,28,3,'FRngnAr8ghk=','lalo@lal6o.com'),(22,30,3,'SnE+koIWZug=','sdddlalo@lalo.com'),(23,31,3,'LtsKuoGQ/No=','jm@ipn.mx'),(24,32,3,'GMwFg2VImPg=','c@c.com'),(25,33,2,'jjUd4Qfn0f8=','juanito@a.com'),(26,35,3,'wed36flatGQ=','support@ibexsoftworks.me');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `ver_tiposusuario`
--

DROP TABLE IF EXISTS `ver_tiposusuario`;
/*!50001 DROP VIEW IF EXISTS `ver_tiposusuario`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `ver_tiposusuario` AS SELECT 
 1 AS `ID`,
 1 AS `Rol del usuario`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `ver_usuarios`
--

DROP TABLE IF EXISTS `ver_usuarios`;
/*!50001 DROP VIEW IF EXISTS `ver_usuarios`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `ver_usuarios` AS SELECT 
 1 AS `idUsuario`,
 1 AS `correo`,
 1 AS `iddatos`,
 1 AS `descripcion`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `verestas`
--

DROP TABLE IF EXISTS `verestas`;
/*!50001 DROP VIEW IF EXISTS `verestas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `verestas` AS SELECT 
 1 AS `idEst`,
 1 AS `Nombre`,
 1 AS `calle`,
 1 AS `colonia`,
 1 AS `delegacionMunicipio`,
 1 AS `estado`,
 1 AS `url`,
 1 AS `x`,
 1 AS `y`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vermiesta`
--

DROP TABLE IF EXISTS `vermiesta`;
/*!50001 DROP VIEW IF EXISTS `vermiesta`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vermiesta` AS SELECT 
 1 AS `Nombre`,
 1 AS `idEsta`,
 1 AS `correo`,
 1 AS `url`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'parkplatz'
--

--
-- Dumping routines for database 'parkplatz'
--
/*!50003 DROP PROCEDURE IF EXISTS `actualizarFoto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarFoto`(in idEst int, in nombreArchivo varchar(500))
begin
	update asset set url = nombreArchivo where idasset = (select da.idAsset from datosestacionamientos da inner join estacionamientos e on da.`iddatosestacionamientos` = e.`idDatosEstacionamiento` where e.idestacionamientos = idEst);
	select 'Foto subida x2' as 'estado';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `actualizarServicio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarServicio`(in idServ int, in nuevoNombre varchar(45), in nuevaDesc varchar(500), in costo float)
begin
	update servicios set nombre = nuevoNombre, descripcion = nuevaDesc, precio = costo where idservicios = idServ;
    select 'Datos actualizados' as 'estado';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `actualizar_tipoUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizar_tipoUsuario`(in tipoViejo varchar(45), in tipoNuevo varchar(45))
begin
	if (select descripcion from tipousuario where descripcion = tipoViejo) = null then
		select 'El tipo de usuario a actualizar no existe';
	else
		update tipousuario set descripcion = tipoNuevo where descripcion = tipoViejo;
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `bajaLugar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `bajaLugar`(in idLugar int)
begin
	declare coorID int;
    set coorID = (select idCoordenadas from lugares l where l.idlugares = idLugar);
    delete from relestalugar where relestalugar.idLugar = idLugar;
    delete from lugares where idlugares = idLugar;
	delete from coordenadas where idcoordenadas = coorID;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_Asset` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_Asset`(in idAsset int)
begin
	if (select count(*) from asset where idasset = idAsset) != 0 then
		delete from asset where idasset = idAsset;
        select 'Exito' as 'Mensaje';
	else
		select 'Asset no existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_catPrioridad` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_catPrioridad`(in idPrio int)
begin
	if (select count(*) from catprioridades where idcatprioridades = idPrio) != 0 then
		delete from catprioridades where idcatprioridades = idPrio;
        select 'Exito' as 'Mensaje';
	else
		select 'Tal prioridad no existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_coordenada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_coordenada`(in idCoor int)
begin
	if (select count(*) from coordenadas where idcoordenadas = idCoor) != 0 then
		delete from coordenadas where idcoordenadas = idCoor;
        select 'Exito' as 'Mensaje';
	else
		select 'Tal coordenada no existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_favo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_favo`(in mail varchar(30), in idEst int)
begin
declare idUsr int;
set idUsr = (select idusuario from usuario where mail = correo);
delete from favoritos where idUsuario = idUsr and idEstacionamiento = idEst;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_favorito` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_favorito`(in idFav int, in idUsr int, in idEst int)
begin
	if (select count(*) from favoritos where idFavoritos = idFav) != 0 then
		delete from favoritos where idFavoritos = idFav;
        select 'Exito' as 'Mensaje';
	else
		select 'No Existe el fav' as 'Mensaje';
	end if;
	delete from favoritos where idUsuario = idUsr and isEstacionamiento = idEst;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_modoLugar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_modoLugar`(in idMod int)
begin
	if (select count(*) from catalogomodolugares where idcatalogomodolugares = idMod) != 0 then
		delete from catalogomodolugares where idcatalogomodolugares = idMod;
        select 'Exito' as 'Mensaje';
	else
		select 'Modo de lugar no existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_Servicio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_Servicio`(in idServ int)
begin
	if (select count(*) from servicios where idServicios = idServ) != 0 then
		delete from servicios where idservicios = idServ;
        select 'Exito' as 'Mensaje';
	else
		select 'No Existe tal servicio' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_tipoAsset` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_tipoAsset`(in idTip int)
begin
	if (select count(*) from cattipoasset where idcattipoasset = idTip) != 0 then
		delete from cattipoasset where idcattipoasset = idTip;
        select 'Exito' as 'Mensaje';
	else
		select 'Tipo de Asset no existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_tipoCoordenada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_tipoCoordenada`(in idTip int)
begin
	if (select count(*) from cattipocoordenadas where idcattipocoordenadas = idTip) != 0 then
		delete from cattipocoordenadas where idcattipocoordenadas = idTip;
        select 'Exito' as 'Mensaje';
	else
		select 'Tipo de coordenada no existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_tipoUsr` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_tipoUsr`(in idTip int)
begin
	if (select count(*) from tipousuario where idtipoUsuario = idTip) != 0 then
		delete from tipousuario where idtipoUsuario = idTip;
        select 'Exito' as 'Mensaje';
	else
		select 'Tipo de usuario no existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borrar_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borrar_usuario`(in correo varchar(45))
begin
	declare idDatos int;
    set idDatos = (select idDatos from usuario where usuario.correo = correo);
	if (select correo from usuario where usuario.correo = correo) != '' or null then
    delete from feedback where idUsuario = (select idusuario from usuario where usuario.correo = correo);
    delete from usuario where usuario.correo = correo;
    delete from datos where datos.iddatos = idDatos;
    select 'La cuenta se ha borrado satisfactoriamente';
    else
    select 'El usuario a eliminar no existe favor de verificarlo';
    end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `borra_estacionamiento` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `borra_estacionamiento`(in idEst int)
begin
	declare idServicioToDel int;
    declare idUbicacionToDel int;
    set idUbicacionToDel = (select idUbicacion from datosestacionamientos  where iddatosestacionamientos = (select idDatosEstacionamiento from estacionamientos where idestacionamientos = idEst));
    delete from rel_servicios_estacionamientos where idEstacionamiento = idEst;
	delete from estacionamientos where idestacionamientos = idEst;
    delete from catubicacionesestacionamiento where idcatubicacionesestacionamiento = idUbicacionToDel;
    delete from asset where idasset = (select idAsset from datosestacionamientos where iddatosestacionamientos = (select idDatosEstacionamiento from estacionamientos where idestacionamientos = idEst));
	select 'Se ha eliminado su estacionamiento exitosamente';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `cambiar_pass` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `cambiar_pass`(in correo varchar(45), in nvo_pass varchar(500))
begin
	
	update usuario set usuario.password = nvo_pass where usuario.correo=correo;
	select 'Contraseña cambiada' as 'Mensaje';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `dameAvisos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `dameAvisos`(in idEsta int)
begin
	declare idUser int;
    set idUser = (select idusuario from usuario u inner join estacionamientos e on u.idDatos = e.idDatos where e.idestacionamientos = idEsta);
	select fe.* from feedback fe inner join usuario us on fe.idUsuario = us.idusuario where us.idusuario = idUser and fe.idPrioridad = 3 order by fecha desc limit 10;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `detalles_Estacionamiento` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `detalles_Estacionamiento`(in idEst int)
begin
		SELECT c.cordenadaX as 'x', c.coordenadaY as 'y', 
		a.url, ue.calle, ue.colonia, ue.delegacionMunicipio as 'dele', ue.estado, e.idestacionamientos as 'idEsta' , de.nombreEstacionamiento as 'nombre',
        de.horarios, de.tarifa, de.alturaMaxima, de.descripcion FROM usuario u 
		INNER JOIN datos d ON u.idDatos = d.iddatos 
        INNER JOIN estacionamientos e ON e.iddatos = d.iddatos
        INNER JOIN datosestacionamientos de ON de.iddatosestacionamientos = e.idDatosEstacionamiento
        INNER JOIN asset a ON a.idasset = de.idasset
        INNER JOIN catubicacionesestacionamiento ue ON ue.idcatubicacionesestacionamiento = de.idUbicacion
        INNER JOIN coordenadas c ON c.idcoordenadas = ue.idCoordenadas WHERE e.idestacionamientos = idEst;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `editarDatosEsta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `editarDatosEsta`(in idEst int,in nombreEsta varchar(45), in hrario varchar(500), in tar varchar(500), in altura float, in descrip varchar(500), in calle varchar(45), in colonia varchar(45), in delMuni varchar(45), in estado varchar(45))
begin
	update datosestacionamientos set nombreEstacionamiento = nombreEsta, horarios = hrario, tarifa = tar, alturaMaxima = altura, descripcion = descrip where iddatosestacionamientos = (select idDatosEstacionamiento from estacionamientos where idestacionamientos = idEst);
    update catubicacionesestacionamiento cu set cu.calle = calle, cu.colonia = colonia, cu.delegacionMunicipio = delMuni, cu.estado = estado where idcatubicacionesestacionamiento = (select idUbicacion from datosestacionamientos de inner join estacionamientos e on de.iddatosestacionamientos = e.idDatosEstacionamiento where e.idestacionamientos = idEst);
	select 'Actualizacion exitosa!' as 'estado';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `editar_datos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `editar_datos`(in nvo_Nombre varchar(45), in nvo_aPaterno varchar(45), in nvo_aMaterno varchar(45), in idEsta int, in nvo_contra text)
begin
	update datos set nombre = nvo_Nombre, aPaterno = nvo_aPaterno, aMaterno = nvo_aMaterno where iddatos = (select idDatos from estacionamientos where idestacionamientos = idEsta);
    if nvo_contra != 'sinCambios' then
		update usuario set password = nvo_contra where idDatos = (select idDatos from estacionamientos where idestacionamientos = idEsta);
    end if;
    select 'Actualizacion exitosa!' as 'Estado';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminarServicio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminarServicio`(in id int)
begin
	delete from rel_servicios_estacionamientos where idServicio = id;
    delete from servicios where idservicios = id;
    select 'Eliminacion exitosa' as 'Estado';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_tipoUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_tipoUsuario`(in tipo varchar(45))
begin
	if (select descripcion from tipousuario where descripcion = tipo) = null  then
		select 'No se encontro tipo de usuario con esa descripcion';
	else
		delete from tipousuario where descripcion = tipo;
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getIdDatos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdDatos`(in email varchar(45))
begin
	select idDatos from usuario where correo = email;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getIdUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdUsuario`(in idEst int)
begin
	select idusuario as 'ID' from usuario inner join estacionamientos on usuario.idDatos = estacionamientos.idDatos where estacionamientos.idestacionamientos = idEst;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getIdUsuario_dos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getIdUsuario_dos`(in correoo varchar(45))
begin
	select idusuario as 'ID' from usuario where correo = correoo;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getMisLugares` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getMisLugares`(in idEsta int)
begin
	select lug.idlugares as 'ID', modo.descripcion as 'Status', coor.cordenadaX as 'X', coor.coordenadaY as 'Y',
    lug.piso as 'Piso' from relestalugar rl 
    inner join lugares lug on rl.idLugar = lug.idlugares
    inner join catalogomodolugares modo on modo.idcatalogomodolugares = lug.idModoLugar
    inner join coordenadas coor on lug.idCoordenadas = coor.idcoordenadas
    where rl.idEstacionamiento = idEsta;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getStatusLugares` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getStatusLugares`(in idEsta int)
begin
	select  modo.descripcion as 'Status', lug.piso as 'Piso', count(modo.descripcion)
    from relestalugar rl 
    inner join lugares lug on rl.idLugar = lug.idlugares
    inner join catalogomodolugares modo on modo.idcatalogomodolugares = lug.idModoLugar
    inner join coordenadas coor on lug.idCoordenadas = coor.idcoordenadas
    where rl.idEstacionamiento = idEsta
    group by lug.piso;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_favoritos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_favoritos`(in correoo varchar(45))
begin
	declare idUsr int;
    set idUsr = (select idUsuario from usuario where correo = correoo);
	SELECT f.idEstacionamiento as 'id', de.nombreEstacionamiento, a.url, u.calle, u.colonia, 
    u.delegacionMunicipio as 'dele', u.estado from favoritos f 
    INNER JOIN estacionamientos e ON f.idEstacionamiento = e.idestacionamientos
    INNER JOIN datosestacionamientos de ON de.iddatosestacionamientos = e.idDatosEstacionamiento
    INNER JOIN asset a ON a.idasset = de.idAsset
    INNER JOIN catubicacionesestacionamiento u ON u.idcatubicacionesestacionamiento = de.idUbicacion
    where idUsuario = idUsr;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_feedback` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_feedback`()
begin
	select f.*, u.correo from feedback f inner join usuario u on f.idUsuario = u.idusuario order by fecha;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_Recientes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_Recientes`(in correoo varchar(45))
begin
	declare idUsr int;
    set idUsr = (select idUsuario from usuario where correo = correoo);
	SELECT r.idEstacionamientos as 'id', fecha, de.nombreEstacionamiento 'nonbre' , a.url, u.calle, u.colonia, 
    u.delegacionMunicipio as 'dele', u.estado from recientes r 
    INNER JOIN estacionamientos e ON r.idEstacionamientos = e.idestacionamientos 
    INNER JOIN datosestacionamientos de ON de.iddatosestacionamientos = e.idDatosEstacionamiento
    INNER JOIN asset a ON a.idasset = de.idAsset
    INNER JOIN catubicacionesestacionamiento u ON u.idcatubicacionesestacionamiento = de.idUbicacion
    where idUsuario = idUsr;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `isfav` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `isfav`(in mail varchar(50), in id int)
begin
	select count(*) as c from favoritos inner join usuario 
		on favoritos.idUsuario = usuario.idusuario 
        where favoritos.idEstacionamiento = id and usuario.correo = mail;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `login` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login`(in correo varchar(45), in pass text)
begin
declare valid boolean;
	if (select usuario.password from usuario where usuario.correo = correo) = pass then
		set valid = true;
    else
	set valid = false;
    end if;
    select valid as 'Valido';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificar_Asset` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificar_Asset`(in id int, in idtipo int, in urls varchar(500))
begin
	declare existe int;
    set existe = (select count(*) from asset where idasset = id);
    if existe != 0 then
		update asset set idTipoAsset = idtipo, url = urls where idasset = id;
        select 'Exito' as 'Mensaje';
    else 
		select 'No existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificar_catPrioridad` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificar_catPrioridad`(in idPrio int, in descrip varchar(100))
begin
	declare existe int;
    set existe = (select count(*) from catprioridades where idcatprioridades = idPrio);
    if existe != 0 then
		update catprioridades set prioridad = descrip where idPrio = idcatprioridades;
        select 'Exito' as 'Mensaje';
    else 
		select 'No existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificar_coordenada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificar_coordenada`(in idCoor int, in idTip int, in x varchar(45), in y varchar(45))
begin
	if (select count(*) from coordenadas where idcoordenadas = idCoor) != 0 then
		update coordenadas set cordenadaX = x, coordenadaY = y, idTipoCoordenadas = idTip where idcoordenadas = idCoor;
        select 'Exito' as 'Mensaje';
	else
		select 'Tal coordenada no existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificar_modoLugar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificar_modoLugar`(in idMod int, in descrip varchar(100))
begin
	declare existe int;
    set existe = (select count(*) from catalogomodolugares where idcatalogomodolugares = idMod);
    if existe != 0 then
		update catalogomodolugares set descripcion = descrip where idMod = idcatalogomodolugares;
        select 'Exito' as 'Mensaje';
    else 
		select 'No existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificar_Servicio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificar_Servicio`(in idServi int, in nom varchar(100), in descrip varchar(500), in precio int)
begin
	declare existe int;    
    set existe = (select count(*) from servicios where idservicios = idServi);
    if(existe != 0) then
		update servicios set nombre = nom, descripcion = descrip, precio = precio where idservicios = idServi;
		select 'Exito' as 'Mensaje';
	else
		select 'No Existe tal servicio' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificar_tipoAsset` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificar_tipoAsset`(in idTip int, in descrip varchar(100))
begin
	declare existe int;
    set existe = (select count(*) from cattipoasset where idcattipoasset = idTip);
    if existe != 0 then
		update cattipoasset set descripcion = descrip where idTip = idcattipoasset;
        select 'Exito' as 'Mensaje';
    else 
		select 'No existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificar_tipoCoordenada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificar_tipoCoordenada`(in idTip int, in propo varchar(100))
begin
	declare existe int;
    set existe = (select count(*) from cattipocoordenadas where idcattipocoordenadas = idTip);
    if existe != 0 then
		update cattipocoordenadas set proposito = propo where idTip = idcattipocoordenadas;
        select 'Exito' as 'Mensaje';
    else 
		select 'No existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificar_tipoUsr` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificar_tipoUsr`(in idTip int, in descrip varchar(100))
begin
	declare existe int;
    set existe = (select count(*) from tipousuario where idtipoUsuario = idTip);
    if existe != 0 then
		update tipousuario set descripcion = descrip where idTip = idtipoUsuario;
        select 'Exito' as 'Mensaje';
    else 
		select 'No existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modifica_conductor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modifica_conductor`(in nvo_Nombre varchar(45), in nvo_aPaterno varchar(45), in nvo_aMaterno varchar(45), 
						in nvo_contra varchar(1000), in correos varchar(45))
begin
	declare idDat int;
    set idDat = (select iddatos from usuario where correo = correos);
	update datos d INNER JOIN usuario u On d.iddatos = u.idDatos 
    set nombre = nvo_Nombre, aPaterno = nvo_aPaterno, aMaterno = nvo_aMaterno, 
    correo = correos where d.iddatos = idDat;
    if nvo_contra != null then
		update usuario u set u.password = nvo_contra where idDatos = idDat;
    end if;
    select 'Actualizacion exitosa!' as 'Estado';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `new_feedback` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_feedback`(in descrip varchar(500),in idUsr int ,in idPrio int)
begin
	declare idFeed int; 
    set idFeed= (select ifnull(max(idfeedback), 0) from feedback) + 1; 
    insert feedback values(idFeed, descrip, idUsr, idPrio, current_timestamp);
    select 'Exito' as 'Mensaje';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevaRelServEst` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevaRelServEst`(in idEst int, in idServi int)
begin
	insert into rel_servicios_estacionamientos values(null, idEst, idServi);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevoLugar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevoLugar`(in idEsta int, in idLugar int, in idModoLugar int, in coorX varchar(500), in coorY varchar(500), in piso int)
begin
	declare existe int;
    declare maxIdCoor int;
    declare maxIdRel int;
    set maxIdRel = (select ifnull(max(idrelEstaLugar), 0) from relestalugar) + 1;
    set maxIdCoor = (select ifnull(max(idcoordenadas), 0) from coordenadas) + 1;
	 set existe = (select count(*) from lugares l where l.idlugares = idLugar);
     if(existe = 0) then
		insert into coordenadas value (maxIdCoor, coorX, coorY, 2);
		insert into lugares values (idLugar, idModoLugar, maxIdCoor, piso);
        insert into relestalugar values (maxIdRel, idEsta, idLugar); 
        select 'Se ha agregado el lugar con el ID satisfactoriamente!' as 'Estado';
     else
		select 'El ID ingresado ya esta siendo utilizado por otro lugar!';
     end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_Asset` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_Asset`(in idtipo varchar(45), in ur varchar(500))
begin
	declare existe int;
    declare idAsse int;
    set existe = (select count(*) from asset where url = ur);
    set idAsse = (select ifnull(max(idasset), 0) from asset) + 1;
    if existe = 0 then		
        insert into asset values(idAsse, idtipo, ur);
		select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;		
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_catPrioridad` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_catPrioridad`(in descrip varchar(100))
begin
	declare existe int;
    declare idTip int;
    set existe = (select count(*) from catprioridades where prioridad = descrip);
    set idTip = (select ifnull(max(idcatprioridades), 0) from catprioridades) + 1;
    if existe = 0 then
		insert into catprioridades values(idTip, descrip);
        select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_coordenada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_coordenada`(in idTip int, in x varchar(45), in y varchar(45))
begin
    declare idCoor int;
    set idCoor = (select ifnull(max(idcoordenadas), 0) from coordenadas) + 1;
    insert into coordenadas values(idCoor, x, y, idTip);
    select 'Exito' as 'Mensaje';
    end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_favorito` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_favorito`(in mail varchar(50), in idEst int)
begin
	declare existe int;
    declare idFav int;
    declare idUsr int;
    set idUsr = (select idusuario from usuario where correo = mail);
    set existe = (select count(*) from favoritos where idUsuario = idUsr and idEstacionamiento = idEst);
    set idFav = (select ifnull(max(idFavoritos), 0) from favoritos) + 1;
    if(existe = 0) then
		insert into favoritos values(idFav, idUsr, idEst);
        select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_feedback` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_feedback`(in descrip varchar(500), in idUsr int, in idPrio int)
begin
	declare idFeed int; 
    set idFeed= (select ifnull(max(idfeedback), 0) from feedback) + 1; 
    insert feedback values(idFeed, descrip, idUsr, idPrio, current_timestamp);
    select 'Exito' as 'Mensaje';
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_modoLugar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_modoLugar`(in descrip varchar(100))
begin
	declare existe int;
    declare idMod int;
    set existe = (select count(*) from catalogomodolugares where descripcion = descrip);
    set idMod = (select ifnull(max(idcatalogomodolugares), 0) from catalogomodolugares) + 1;
    if existe = 0 then
		insert into catalogomodolugares values(idMod, descrip);
        select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_reciente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_reciente`(in mail varchar(50), in idEst int)
begin
	declare existe int;
    declare idRec int;
    declare idUsr int;
    set idUsr = (select idusuario from usuario where correo = mail);
    set existe = (select count(*) from recientes where idUsuario = idUsr and idEstacionamientos = idEst);
    set idRec = (select ifnull(max(idrecientes), 0) from recientes) + 1;
    if(existe = 0) then
		insert into recientes values(idRec, idEst, idUsr, current_timestamp());
        select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_Servicio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_Servicio`(in nom varchar(100), in descrip varchar(500), in precio int, in idEst int)
begin
	declare existe int;    
    declare idServi  int;
    set existe = (select count(*) from servicios where nom = nombre);
    set idServi = (select ifnull(max(idservicios), 0) from servicios) + 1;
    if(existe = 0) then
		insert into servicios values(idServi, nom, descrip, precio);
        call nuevaRelServEst(idEst,idServi);
        select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_tipoAsset` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_tipoAsset`(in descrip varchar(45))
begin
	declare existe int;
    declare idTipo int;
    set existe = (select count(*) from cattipoasset where descripcion = descrip);
    set idTipo = (select ifnull(max(idcattipoasset), 0) from cattipoasset) + 1;
    if existe = 0 then		
        insert into cattipoasset values(idTipo, descrip);
		select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;		
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_tipoCoordenada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_tipoCoordenada`(in propo varchar(100))
begin
	declare existe int;
    declare idTip int;
    set existe = (select count(*) from cattipocoordenadas where proposito = propo);
    set idTip = (select ifnull(max(idcattipocoordenadas), 0) from cattipocoordenadas) + 1;
    if existe = 0 then
		insert into cattipocoordenadas values(idTip, propo);
        select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_tipoUsr` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_tipoUsr`(in descrip varchar(100))
begin
	declare existe int;
    declare idTip int;
    set existe = (select count(*) from tipousuario where descripcion = descrip);
    set idTip = (select ifnull(max(idtipoUsuario), 0) from tipousuario) + 1;
    if existe = 0 then
		insert into tipousuario values(idTip, descrip);
        select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_tipoUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_tipoUsuario`(in id int , in tipo varchar(45))
begin
	insert into tipousuario values (id, tipo);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `nuevo_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `nuevo_usuario`(in Nombre varchar(45), in aPaterno varchar(45), in aMaterno varchar(45), in tipoUsuario int, in correo varchar(45), in contra varchar(500))
begin
	declare id_Datos int;
    declare id_Usuario int;
    if (SELECT max(idusuario) from usuario) is null then
		set id_Usuario = 1;
    else
		set id_Usuario = (SELECT max(idusuario)+1 from usuario);
    end if;
    if (SELECT max(iddatos) from datos) is null then
		set id_Datos = 1;
    else
		set id_Datos = (SELECT max(iddatos)+1 from datos);
    end if;	
    insert into datos values (id_Datos, Nombre, aPaterno, aMaterno);
    insert into usuario values (id_Usuario, id_Datos, tipoUsuario, contra, correo);

  end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `obtener_datos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `obtener_datos`(in correo varchar(45))
begin
	select * from datos where iddatos = (select idDatos from usuario where usuario.correo = correo);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `prueba` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `prueba`()
begin
declare id int;
	if (select max(iddatos) from datos) is null then
   set id = (select max(iddatos) from datos);
   select 1;
    ELSE
    set id = (select max(iddatos) from datos);
   select id;
    end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `recuperameEsta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `recuperameEsta`(in correoo varchar(45),in idEst int)
begin
		SELECT d.nombre, d.aPaterno, d.aMaterno, c.cordenadaX as 'coorX', c.coordenadaY as 'coorY', 
		a.url, ue.calle, ue.colonia, ue.delegacionMunicipio as 'dele', ue.estado, e.idestacionamientos as 'idEst' , de.nombreEstacionamiento as 'nombreEsta',
        de.horarios, de.tarifa, de.alturaMaxima, de.descripcion FROM usuario u 
		INNER JOIN datos d ON u.idDatos = d.iddatos 
        INNER JOIN estacionamientos e ON e.iddatos = d.iddatos
        INNER JOIN datosestacionamientos de ON de.iddatosestacionamientos = e.idDatosEstacionamiento
        INNER JOIN asset a ON a.idasset = de.idasset
        INNER JOIN catubicacionesestacionamiento ue ON ue.idcatubicacionesestacionamiento = de.idUbicacion
        INNER JOIN coordenadas c ON c.idcoordenadas = ue.idCoordenadas WHERE u.correo = correoo and e.idestacionamientos = idEst;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registroEsta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registroEsta`(in correo varchar(45), in pass varchar(500), in idTipoUsr int, in nombre varchar(45),
								in apellidoP varchar(45), in apellidoM varchar(45), in coorX varchar(45), in coorY varchar(45),
                                in idTipoCoor int, in calle varchar(45), in colonia varchar(45), in dele varchar(45),in estado varchar(45), 
                                in url varchar(500), in idTipAsset int, in nombreEsta varchar(45))
begin
	declare existe int;
 	declare maxIdCoor int;
    declare maxIdDatos int;
    declare maxIdDatosEsta int;
    declare maxIdUbicacion int;
    declare maxIdUsr int;
    declare maxIdEstacionamiento int;
    declare maxIdAsset int;
    set existe = (select count(*) from coordenadas where cordenadaX = coorX and coordenadaY = coorY);
    
    if(existe = 0) then
		set maxIdDatos = (select ifnull(max(iddatos), 0) from datos) + 1;
		set maxIdDatosEsta = (select ifnull(max(iddatosestacionamientos), 0) from datosestacionamientos) + 1;
		set maxIdUbicacion = (select ifnull(max(idcatubicacionesestacionamiento), 0) from catubicacionesestacionamiento) + 1;
		set maxIdUsr = (select ifnull(max(idusuario), 0) from usuario) + 1;
		set maxIdEstacionamiento = (select ifnull(max(idestacionamientos), 0) from estacionamientos) + 1;
		set maxIdCoor = (select ifnull(max(idcoordenadas), 0) from coordenadas) + 1;
        set maxIdAsset = (select ifnull(max(idasset), 0) from asset) + 1;
		insert into coordenadas values(maxIdCoor, coorX, coorY, idTipoCoor);
        insert into datos values(maxIdDatos, nombre, apellidoP, apellidoM);
        insert into usuario values(maxIdUsr, maxIdDatos, idTipoUsr, pass, correo);
        insert into asset values(maxIdAsset, idTipAsset, url);
        insert into catubicacionesestacionamiento values(maxIdUbicacion, calle, colonia, dele, estado, maxIdCoor);
        insert into datosestacionamientos values(maxIdDatosEsta, nombreEsta, maxIdUbicacion, null, null, null, null, maxIdAsset);
        insert into estacionamientos values(maxIdEstacionamiento, maxIdDatosEsta, maxIdDatos);
        select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `registroEstaSinUsr` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `registroEstaSinUsr`(in idDatos int, in coorX varchar(45), in coorY varchar(45),
                                in idTipoCoor int, in calle varchar(45), in colonia varchar(45), in dele varchar(45),in estado varchar(45), 
                                in url varchar(500), in idTipAsset int, in nombreEstac varchar(45))
begin
	declare existe int;
 	declare maxIdCoor int;
    declare maxIdDatosEsta int;
    declare maxIdUbicacion int;
    declare maxIdEstacionamiento int;
    declare maxIdAsset int;
    set existe = (select count(*) from coordenadas where cordenadaX = coorX and coordenadaY = coorY);
    
    if(existe = 0) then
		set maxIdDatosEsta = (select ifnull(max(iddatosestacionamientos), 0) from datosestacionamientos) + 1;
		set maxIdUbicacion = (select ifnull(max(idcatubicacionesestacionamiento), 0) from catubicacionesestacionamiento) + 1;
		set maxIdEstacionamiento = (select ifnull(max(idestacionamientos), 0) from estacionamientos) + 1;
		set maxIdCoor = (select ifnull(max(idcoordenadas), 0) from coordenadas) + 1;
        set maxIdAsset = (select ifnull(max(idasset), 0) from asset) + 1;
		insert into coordenadas values(maxIdCoor, coorX, coorY, idTipoCoor);
        insert into asset values(maxIdAsset, idTipAsset, url);
        insert into catubicacionesestacionamiento values(maxIdUbicacion, calle, colonia, dele, estado, maxIdCoor);
        insert into datosestacionamientos values(maxIdDatosEsta, nombreEstac, maxIdUbicacion, null, null, null, null, maxIdAsset);
        insert into estacionamientos values(maxIdEstacionamiento, maxIdDatosEsta, idDatos);
        select 'Exito' as 'Mensaje';
	else
		select 'Ya existe' as 'Mensaje';
	end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `trae_Esta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `trae_Esta`(in nombre nvarchar(100))
begin
		declare estAbuscar nvarchar(500);
        set estAbuscar = concat('%',nombre,'%');
		SELECT 's' as 'msj', e.idestacionamientos as 'id', de.nombreEstacionamiento, u.calle, u.colonia, 
		u.delegacionMunicipio as 'dele', u.estado from estacionamientos e
		INNER JOIN datosestacionamientos de ON de.iddatosestacionamientos = e.idDatosEstacionamiento
		INNER JOIN catubicacionesestacionamiento u ON u.idcatubicacionesestacionamiento = de.idUbicacion
        where de.nombreEstacionamiento like estAbuscar;
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateLugar` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateLugar`(in idLugar int, in idStatus int, in coorX varchar(500), in coorY varchar(500))
begin
	declare idCoors int;
	set idCoors = (select c.idcoordenadas from coordenadas
    inner join lugares lug on lug.idCoordenadas = c.idcoordenadas
    where c.cordenadaX = coorX and c.coordenadaY = coorY and lug.idCoordenadas = c.idcoordenadas);
	update coordenadas c set c.cordenadaX = coorX, c.coordenadaY = coorY where c.idcoordenadas = idCoors;
    update lugares lug set lug.idModoLugar = idStatus where lug.idlugares = idLugar;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateLugarID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateLugarID`(in idLugViejo int, in idLugarNuevo int)
begin
	declare idRel int;
    declare idEst int;
    set idRel = (select idrelEstaLugar from relestalugar where idLugar = idLugViejo);
    set idEst = (select idEstacionamiento from relestalugar where idLugar = idLugViejo);
    delete from relestalugar where idLugar = idLugViejo;
    update lugares set idlugares = idLugarNuevo where idlugares = idLugViejo;
	insert into relestalugar values (idRel, idEst, idLugarNuevo);
    
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateLugarStatus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateLugarStatus`(in idLugar int, in idStatus int)
begin
	update lugares lug set lug.idModoLugar = idStatus where lug.idlugares = idLugar;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verContra` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verContra`(in email varchar(500))
begin
	select password from usuario where correo = email;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verServicios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verServicios`(in idEstacionamiento int)
begin
	select s.idservicios, s.nombre, s.descripcion, s.precio from servicios s inner join rel_servicios_estacionamientos on rel_servicios_estacionamientos.idServicio = s.idservicios where rel_servicios_estacionamientos.idEstacionamiento = idEstacionamiento;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verTodoEsta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verTodoEsta`()
begin
	select * from verEstas;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_cordenadasEst` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_cordenadasEst`(in idEstacionamiento int)
begin
	select cordenadaX as 'X', cordenadaX as 'Y' from coordenadas
    inner join `catubicacionesestacionamiento` on `catubicacionesestacionamiento`.idCoordenadas = coordenadas.idcoordenadas
    inner join `datosestacionamientos` on `datosestacionamientos`.idUbicacion = catubicacionesestacionamiento.idcatubicacionesestacionamiento
    inner join estacionamientos on estacionamientos.idDatosEstacionamiento = datosestacionamientos.iddatosestacionamientos
    where estacionamientos.idestacionamientos = idEstacionamiento;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_datosEstacionamiento` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_datosEstacionamiento`(in nombre varchar(100))
begin
	select datosestacionamientos.nombreEstacionamiento as 'Nombre', 
    datosestacionamientos.horarios as 'Horario', datosestacionamientos.tarifa as 'Tarifa',
    datosestacionamientos.alturaMaxima as 'Altura Máxima', datosestacionamientos.descripcion as 'Descripcion', catubicacionesestacionamiento.calle as 'Calle',
    catubicacionesestacionamiento.colonia as 'Colonia', catubicacionesestacionamiento.`delegacionMunicipio` as 'Delegacion o Municipio', 
    catubicacionesestacionamiento.estado as 'Estado', asset.url from datosestacionamientos
    inner join asset on datosestacionamientos.idAsset = asset.idasset 
    inner join catubicacionesestacionamiento on catubicacionesestacionamiento.idcatubicacionesestacionamiento = datosestacionamientos.idUbicacion
    where datosestacionamientos.nombreEstacionamiento like concat('%', nombre,'%')  order by `datosestacionamientos`.`nombreEstacionamiento` desc;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `ver_tiposusuario`
--

/*!50001 DROP VIEW IF EXISTS `ver_tiposusuario`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ver_tiposusuario` AS select `tipousuario`.`idtipoUsuario` AS `ID`,`tipousuario`.`descripcion` AS `Rol del usuario` from `tipousuario` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ver_usuarios`
--

/*!50001 DROP VIEW IF EXISTS `ver_usuarios`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ver_usuarios` AS select `usuario`.`idusuario` AS `idUsuario`,`usuario`.`correo` AS `correo`,`usuario`.`idDatos` AS `iddatos`,`tipousuario`.`descripcion` AS `descripcion` from (`usuario` join `tipousuario` on((`usuario`.`idTipoUsuario` = `tipousuario`.`idtipoUsuario`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `verestas`
--

/*!50001 DROP VIEW IF EXISTS `verestas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `verestas` AS select `e`.`idestacionamientos` AS `idEst`,`de`.`nombreEstacionamiento` AS `Nombre`,`ub`.`calle` AS `calle`,`ub`.`colonia` AS `colonia`,`ub`.`delegacionMunicipio` AS `delegacionMunicipio`,`ub`.`estado` AS `estado`,`ase`.`url` AS `url`,`co`.`cordenadaX` AS `x`,`co`.`coordenadaY` AS `y` from ((((`estacionamientos` `e` join `datosestacionamientos` `de` on((`e`.`idDatosEstacionamiento` = `de`.`iddatosestacionamientos`))) join `asset` `ase` on((`de`.`idAsset` = `ase`.`idasset`))) join `catubicacionesestacionamiento` `ub` on((`de`.`idUbicacion` = `ub`.`idcatubicacionesestacionamiento`))) join `coordenadas` `co` on((`ub`.`idCoordenadas` = `co`.`idcoordenadas`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vermiesta`
--

/*!50001 DROP VIEW IF EXISTS `vermiesta`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vermiesta` AS select `de`.`nombreEstacionamiento` AS `Nombre`,`e`.`idestacionamientos` AS `idEsta`,`u`.`correo` AS `correo`,`a`.`url` AS `url` from (((`estacionamientos` `e` join `datosestacionamientos` `de` on((`e`.`idDatosEstacionamiento` = `de`.`iddatosestacionamientos`))) join `usuario` `u` on((`u`.`idDatos` = `e`.`idDatos`))) join `asset` `a` on((`a`.`idasset` = `de`.`idAsset`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-13 21:09:45
