-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: kidcontrol
-- ------------------------------------------------------
-- Server version	5.5.11

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
-- Current Database: `kidcontrol`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `kidcontrol` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `kidcontrol`;

--
-- Table structure for table `associa`
--

DROP TABLE IF EXISTS `associa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `associa` (
  `DISPOSITIVO_idDISPOSITIVO` int(10) unsigned NOT NULL,
  `POSIZIONE_idPOSIZIONE` int(10) unsigned NOT NULL,
  PRIMARY KEY (`DISPOSITIVO_idDISPOSITIVO`,`POSIZIONE_idPOSIZIONE`),
  KEY `fk_DISPOSITIVO_has_POSIZIONE_POSIZIONE1` (`POSIZIONE_idPOSIZIONE`),
  KEY `fk_DISPOSITIVO_has_POSIZIONE_DISPOSITIVO1` (`DISPOSITIVO_idDISPOSITIVO`),
  CONSTRAINT `fk_DISPOSITIVO_has_POSIZIONE_DISPOSITIVO1` FOREIGN KEY (`DISPOSITIVO_idDISPOSITIVO`) REFERENCES `dispositivo` (`idDISPOSITIVO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DISPOSITIVO_has_POSIZIONE_POSIZIONE1` FOREIGN KEY (`POSIZIONE_idPOSIZIONE`) REFERENCES `posizione` (`idPOSIZIONE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `associa`
--

LOCK TABLES `associa` WRITE;
/*!40000 ALTER TABLE `associa` DISABLE KEYS */;
INSERT INTO `associa` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `associa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `controlla`
--

DROP TABLE IF EXISTS `controlla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `controlla` (
  `UTENTE_idUtente` int(10) unsigned NOT NULL,
  `DISPOSITIVO_idDISPOSITIVO` int(10) unsigned NOT NULL,
  PRIMARY KEY (`UTENTE_idUtente`,`DISPOSITIVO_idDISPOSITIVO`),
  KEY `fk_controlla_UTENTE1` (`UTENTE_idUtente`),
  KEY `fk_controlla_DISPOSITIVO1` (`DISPOSITIVO_idDISPOSITIVO`),
  CONSTRAINT `fk_controlla_DISPOSITIVO1` FOREIGN KEY (`DISPOSITIVO_idDISPOSITIVO`) REFERENCES `dispositivo` (`idDISPOSITIVO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_controlla_UTENTE1` FOREIGN KEY (`UTENTE_idUtente`) REFERENCES `utente` (`idUtente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Un utente può controllare più device identificato con i rela';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controlla`
--

LOCK TABLES `controlla` WRITE;
/*!40000 ALTER TABLE `controlla` DISABLE KEYS */;
INSERT INTO `controlla` VALUES (1,1),(1,6),(2,2),(3,3);
/*!40000 ALTER TABLE `controlla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivo`
--

DROP TABLE IF EXISTS `dispositivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dispositivo` (
  `idDISPOSITIVO` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `imei` varchar(45) NOT NULL,
  `LUOGO_idLUOGO` int(11) DEFAULT NULL,
  `STATO_idSTATO` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`idDISPOSITIVO`),
  UNIQUE KEY `imei_UNIQUE` (`imei`),
  UNIQUE KEY `idDISPOSITIVO_UNIQUE` (`idDISPOSITIVO`),
  KEY `fk_DISPOSITIVO_LUOGO1` (`LUOGO_idLUOGO`),
  KEY `fk_DISPOSITIVO_STATO1` (`STATO_idSTATO`),
  CONSTRAINT `fk_DISPOSITIVO_LUOGO1` FOREIGN KEY (`LUOGO_idLUOGO`) REFERENCES `luogo` (`idLUOGO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_DISPOSITIVO_STATO1` FOREIGN KEY (`STATO_idSTATO`) REFERENCES `stato` (`idSTATO`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='Tabella per registrare i dispositivi da controllare';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivo`
--

LOCK TABLES `dispositivo` WRITE;
/*!40000 ALTER TABLE `dispositivo` DISABLE KEYS */;
INSERT INTO `dispositivo` VALUES (1,'bb0011',1,3),(2,'bbcf011',NULL,1),(3,'rb0011',NULL,1),(6,'paviliondv5',NULL,1);
/*!40000 ALTER TABLE `dispositivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `luogo`
--

DROP TABLE IF EXISTS `luogo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `luogo` (
  `idLUOGO` int(11) NOT NULL AUTO_INCREMENT,
  `latitudine` varchar(20) NOT NULL,
  `longitudine` varchar(20) NOT NULL,
  `raggio` int(11) NOT NULL,
  PRIMARY KEY (`idLUOGO`),
  UNIQUE KEY `idLUOGO_UNIQUE` (`idLUOGO`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='Luogo predefinito e raggio di azione scelto dal quale il dev';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `luogo`
--

LOCK TABLES `luogo` WRITE;
/*!40000 ALTER TABLE `luogo` DISABLE KEYS */;
INSERT INTO `luogo` VALUES (1,'12','13',4);
/*!40000 ALTER TABLE `luogo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posizione`
--

DROP TABLE IF EXISTS `posizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posizione` (
  `idPOSIZIONE` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `latitudine` varchar(20) NOT NULL,
  `longitudine` varchar(20) NOT NULL,
  `dataPosizione` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idPOSIZIONE`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='Posizione corrente del device aggiornata periodicamente ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posizione`
--

LOCK TABLES `posizione` WRITE;
/*!40000 ALTER TABLE `posizione` DISABLE KEYS */;
INSERT INTO `posizione` VALUES (1,'15','16','2011-12-28 10:18:41'),(2,'17','14','2011-12-28 10:23:14');
/*!40000 ALTER TABLE `posizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stato`
--

DROP TABLE IF EXISTS `stato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stato` (
  `idSTATO` int(10) unsigned NOT NULL,
  `descrizione` varchar(45) NOT NULL,
  PRIMARY KEY (`idSTATO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabella che prevede i possibili stati del dispositivo quali ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stato`
--

LOCK TABLES `stato` WRITE;
/*!40000 ALTER TABLE `stato` DISABLE KEYS */;
INSERT INTO `stato` VALUES (1,'disattivato'),(2,'fuori'),(3,'entrato'),(4,'fuggitivo');
/*!40000 ALTER TABLE `stato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente` (
  `idUtente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `email_notifica` varchar(45) DEFAULT NULL,
  `sms` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`idUtente`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `idUtente_UNIQUE` (`idUtente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='Tabella utilizzata per contenere le informazioni di un utent';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'maur@live.it','23agf','mau@live.it',NULL),(2,'erny@live.it','kkk','erny@live.it',NULL),(3,'roby@live.it','rffgf',NULL,333333);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'kidcontrol'
--
/*!50003 DROP PROCEDURE IF EXISTS `getLocation` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `getLocation`(imeid varchar(45))
BEGIN
declare idP int;
set idP=(select LUOGO_idLUOGO from kidcontrol.dispositivo where imei=imeid);
if(idP is not null)
then
select latitudine, longitudine,raggio
from kidcontrol.luogo
where idLUOGO=idP;
end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getPosition` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `getPosition`(imeid varchar(45))
BEGIN
declare idD int;
declare idP int;
set idD=(select idDISPOSITIVO from kidcontrol.dispositivo where imei=imeid);
set idP=(select max(POSIZIONE_idPOSIZIONE) as Posizione_attuale 
from kidcontrol.associa where DISPOSITIVO_idDISPOSITIVO=idD);
select latitudine,longitudine,dataPosizione
from kidcontrol.posizione
where idPOSIZIONE=idP;


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getState` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `getState`(imeid varchar(45))
BEGIN
select STATO_idSTATO from kidcontrol.dispositivo
where imei=imeid;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertUser` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `insertUser`(emaild varchar(45),pass varchar(20),email_notify varchar(45),sms int,imei varchar(45))
BEGIN
declare idUser int;
declare idDevice int;

if((select idUtente from `kidControl`.`UTENTE`
    where email=emaild)is not null)
then
    set idUser=(select idUtente from `kidControl`.`UTENTE`
    where email=emaild);
else
if (email_notify is not null)
    then 
    insert into `kidControl`.`UTENTE`(email,pass,email_notifica)
    values(emaild,pass,email_notify);
    set idUser=last_insert_id();
else
    insert into `kidControl`.`UTENTE`(email,pass,sms)
    values(emaild,pass,sms);
    set idUser=last_insert_id();
end if;
end if;
insert into `kidControl`.`DISPOSITIVO`(imei)
values(imei);
set idDevice=last_insert_id();
insert into `kidControl`.`Controlla`
values(idUser,idDevice);
END */;;
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
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `login`(emailu varchar(45),passu varchar(20),imeid varchar(45))
BEGIN
declare idU int;
declare idD int;
set idU=null;
set idD=null;
if((select idUtente from kidcontrol.utente
    where email=emailu and pass=passu)is not null)
then set idU=(select idUtente from kidcontrol.utente
    where email=emailu);
    
if((select idDispositivo from kidcontrol.dispositivo
    where imei=imeid)is not null)
then
    set idD=(select idDispositivo from kidcontrol.dispositivo
    where imei=imeid);
end if;
end if;

select UTENTE_idUtente from kidcontrol.controlla
where UTENTE_idUtente=idU and DISPOSITIVO_idDISPOSITIVO=idD;


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sendNotify` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `sendNotify`(imeid varchar(45))
BEGIN
declare idD int;
declare idU int;
set idD=(select idDISPOSITIVO from kidcontrol.dispositivo where imei=imeid);
set idU=(select UTENTE_idUTENTE from kidcontrol.controlla
where DISPOSITIVO_idDISPOSITIVO=idD);
if((select email_notifica from kidcontrol.utente where idUTENTE=idU)is not null)
then
select email_notifica from kidcontrol.utente where idUTENTE=idU;
else
select sms from kidcontrol.utente where idUTENTE=idU;
end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `setLocation` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `setLocation`(imeid varchar(45),latitudine varchar(20),longitudine varchar(20),raggio int)
BEGIN
declare id int;
insert into kidcontrol.luogo(latitudine,longitudine,raggio)
value(latitudine,longitudine,raggio);
set id=last_insert_id();
update kidcontrol.dispositivo
set LUOGO_idLUOGO=id,STATO_idSTATO=2
where imei=imeid;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `setPosition` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `setPosition`(imeid varchar(45),latitudine varchar(45),longitudine varchar(45))
BEGIN
declare idP int;
declare idD int;
insert into kidcontrol.posizione (latitudine,longitudine)
value (latitudine,longitudine);
set idP=last_insert_id();
set idD=(select idDISPOSITIVO from kidcontrol.dispositivo where imei=imeid);
insert into kidcontrol.associa
value(idD,idP);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `setState` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `setState`(imeid varchar(45),stato int)
BEGIN

update kidcontrol.dispositivo
set STATO_idSTATO=stato
where imei=imeid;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-12-28 12:43:06
