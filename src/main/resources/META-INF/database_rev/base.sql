DROP DATABASE IF EXISTS `teamtable`;
CREATE DATABASE  IF NOT EXISTS `teamtable` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `teamtable`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: teamtable
-- ------------------------------------------------------
-- Server version	5.7.20

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` int(11) NOT NULL,
  `assignee` int(11) NOT NULL,
  `event` int(11) NOT NULL,
  `activity_type` varchar(20) COLLATE utf8_bin NOT NULL DEFAULT 'TASK',
  `description` varchar(145) COLLATE utf8_bin DEFAULT NULL,
  `expiration_date` timestamp NULL DEFAULT NULL,
  `status` varchar(15) COLLATE utf8_bin NOT NULL DEFAULT 'OPEN',
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `completion_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`activity_id`),
  KEY `STATUS_IDX` (`status`),
  KEY `EVENT_FK_idx` (`event`),
  KEY `EVENT_CREATOR_FK_idx` (`creator`),
  KEY `ASSIGNEE_FK_idx` (`assignee`),
  CONSTRAINT `ASSIGNEE_FK` FOREIGN KEY (`assignee`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `EVENT_CREATOR_FK` FOREIGN KEY (`creator`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `EVENT_FK` FOREIGN KEY (`event`) REFERENCES `event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` int(11) NOT NULL,
  `practice` int(11) NOT NULL,
  `event_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `description` varchar(145) COLLATE utf8_bin DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`event_id`),
  KEY `PRACTICE_FK_idx` (`practice`),
  KEY `CREATOR_FK_idx` (`creator`),
  KEY `PRACTICE_CREATOR_FK_idx` (`creator`),
  CONSTRAINT `PRACTICE_CREATOR_FK` FOREIGN KEY (`creator`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `PRACTICE_FK` FOREIGN KEY (`practice`) REFERENCES `legal_practice` (`practice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legal_practice`
--

DROP TABLE IF EXISTS `legal_practice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `legal_practice` (
  `practice_id` int(11) NOT NULL AUTO_INCREMENT,
  `creator` int(11) NOT NULL,
  `description` varchar(145) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`practice_id`),
  KEY `CREATOR_FK_idx` (`creator`),
  CONSTRAINT `CREATOR_FK` FOREIGN KEY (`creator`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legal_practice`
--

LOCK TABLES `legal_practice` WRITE;
/*!40000 ALTER TABLE `legal_practice` DISABLE KEYS */;
/*!40000 ALTER TABLE `legal_practice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL,
  `description` varchar(145) COLLATE utf8_bin DEFAULT NULL,
  `lft` int(11) NOT NULL DEFAULT '0',
  `rgt` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(145) COLLATE utf8_bin NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_access` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `team` int(11) NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `TEAM_IDX` (`team`),
  CONSTRAINT `TEAM_FK` FOREIGN KEY (`team`) REFERENCES `team` (`team_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2017-10-26  9:57:34
alter table activity change activity_id activity_id int(10) AUTO_INCREMENT;

alter table legal_practice add `name` varchar(140);

CREATE TABLE `teamtable`.`client` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `client` VARCHAR(45) NOT NULL,
  `api_key` VARCHAR(245) NOT NULL,
  `secret_key` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `api_key_UNIQUE` (`api_key` ASC),
  INDEX `secret_key_INDEX` (`secret_key` ASC));


ALTER TABLE `teamtable`.`user`
ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC);

alter table activity add column name varchar(100);

insert into user (user_id, password, creation_date, last_access, team, username)
values(null, '308a0f16fccfb08ff379cef41f40a86abc442e1aed4538cb305b0417b71adc37+crypto',
null, null, 1, 'god');

insert into client values(null, 'TeamTable FE', 'api_key', 'secret_key');

update client set secret_key = 'a51a3f0a0f5b8233f0e5b71740ef57806f5567c4e565ebd2a1fccd1e52d6a809b4c5e40da1cf83d566c4df13fac55185435c11b109e1a4c58d6e50e62429943a', api_key = '395390b2a282e137a70308be80cb0c85cdccaf50a0b39956b7a0bdd22c6db9bf';

ALTER TABLE `teamtable`.`activity`
CHANGE COLUMN `completion_date` `completion_date` TIMESTAMP NULL DEFAULT NULL ;


alter table legal_practice add archived int(1) default 0;
alter table legal_practice add archived_by int;
alter table legal_practice add archived_on timestamp null default null;

alter table activity add archived int(1) default 0;
alter table activity add archived_by int;
alter table activity add archived_on timestamp null default null;

alter table event add archived int(1) default 0;
alter table event add archived_by int;
alter table event add archived_on timestamp null default null;

ALTER TABLE `teamtable`.`legal_practice`
ADD INDEX `FK_ARCHIVED_BY_idx` (`archived_by` ASC);
ALTER TABLE `teamtable`.`legal_practice`
ADD CONSTRAINT `FK_ARCHIVED_BY`
  FOREIGN KEY (`archived_by`)
  REFERENCES `teamtable`.`user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `teamtable`.`activity`
ADD INDEX `FK_ACT_ARCHIVED_BY_idx` (`archived_by` ASC);
ALTER TABLE `teamtable`.`activity`
ADD CONSTRAINT `FK_ACT_ARCHIVED_BY`
  FOREIGN KEY (`archived_by`)
  REFERENCES `teamtable`.`user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `teamtable`.`event`
ADD INDEX `FK_EVT_ARCHIVED_BY_idx` (`archived_by` ASC);
ALTER TABLE `teamtable`.`event`
ADD CONSTRAINT `FK_EVT_ARCHIVED_BY`
  FOREIGN KEY (`archived_by`)
  REFERENCES `teamtable`.`user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

SET FOREIGN_KEY_CHECKS=0;

DELETE FROM team;
INSERT INTO team (team_id, name, description, lft, rgt) VALUES (null, 'team 1', 'descrizione team 1', 0, 0);
INSERT INTO team (team_id, name, description, lft, rgt) VALUES (null, 'team 2', 'descrizione team 2', 0, 0);
INSERT INTO team (team_id, name, description, lft, rgt) VALUES (null, 'team 3', 'descrizione team 3', 0, 0);
INSERT INTO team (team_id, name, description, lft, rgt) VALUES (null, 'team 4', 'descrizione team 4', 0, 0);
INSERT INTO team (team_id, name, description, lft, rgt) VALUES (null, 'team 5', 'descrizione team 5', 0, 0);

INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 1', '', null, null, 1);
INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 2', '', null, null, 2);
INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 3', '', null, null, 1);
INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 4', '', null, null, 3);
INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 5', '', null, null, 2);
INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 6', '', null, null, 3);
INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 7', '', null, null, 4);
INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 8', '', null, null, 1);
INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 9', '', null, null, 5);
INSERT INTO user (user_id, username, password, creation_date, last_access, team) VALUES (null, 'utente 10', '', null, null, 4);

DELETE FROM legal_practice;
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ','BCM CONSULTING VS ROSSO LORENZO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure','MAURI RICCARDO VS RUGALLI WANDA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non','BARBIERI LILLY VS DAVOLI OLIMPIA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','PEREGO IMMOBILIARE SRL VS NEOS FINANCE');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','PHARMONY SERVICES SRL IN LIQUIDAZIONE VS ZILIANI VITTORIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','BUCCELLONI CARLA PENELOPE VS ROYAL');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ','AMIGHETTI GIANCARLA VS ZAMBIASI ROBERTO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure','SACEBI SRL VS SERTORI MARGHERITA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non','FERRARIO GIOVANNA VS AZIENDA COMUNALE DI SERVI');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','BOZZI MARIA CRISTINA VS AZZI FRANCESCO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','GUERRIERI MARCELLO VS CANTONI NICOLETTA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','CARDIN WILMA LUCIA VS BASSANI ELISABETTA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','CORRADI GIOVANNI VS MORICHELLI DALTEMPS CARLO ALBERTO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','BASTIANELLI ALFREDO VS MALANDRA RENATO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in','HUT INTERNATIONAL SRL VS CARDONE RAFFAELE');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?','PICK VIVIEN VS RAVENNA GUIDO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non','DARDANELLI PAOLO VS BJM SPA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','SPANGARO GIORGIO VS MOSCHIERI LAURA MARIA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','NUNZIATI TOMMASO VS GALBADINI MARINA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','NICCOLI PAOLO GIOVANNI VS GRESPAN RICCARDO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','SFULCINI VINCENZO VS TERZI GIORGIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','SINGH HARVINDER VS MASNERI ANDREA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ','DI PIETRO ANGELO VS BOSCHERI PIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure','MIRODDI NUNZIATO VS CHIAVARO GIANNI ANTONIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non','PASQUALIN STEFANO VS MENNITTI STEFANIA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','COLOMBO ADALGISO  VACCARI LIDIA VS LLOYD ADRIAT');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','TURTURRO MAURO VS VALIN FRANCO TRASP SRL');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','ROVINI DARIO VS CRUCIANI LUCA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','SABADINI PATRIZIA VS CUTELLE NUNZIELLA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','GHIRELLI RITA VS IANNUZZI CRESCENZO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in','DENITTO COSIMA VS LA GRECA A');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?','ROMANO ANDREA FEDERICO VS SIMARI GENNARO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non','MENEGHETTI GIORGIO VS SALVAFIORITA ARMANDO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','SANTORO ALESSANDRA VS RINALDI GIANCARLO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','CONDOMINIO FORLANINI 4446 VS GHIDOTTI SILVIA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','SAPI LIMMOBILIARE SNC VS TURCO VINCENZO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','VILLA ELEONORA VS GROUPAMA TRANSPORT SA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','PASQUALI DONATELLA VS TURCONI ANGELO LUCA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ','TESTA GIUSEPPE VS DI BENEDETTO PETRONILLA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure','BRIOSCHI FRANCO VS BAFELLI VINCENZO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non','RUSCONI ANNA MARIA VS QUITADAMO VINCENZO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','SHENG HUA SRL VS FORGIATURA MARCORA SRL');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','BOFFO MARIO VS NERA LUDOVICA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','FUSANI ANNA VS MONDIAL ASSISTANCE ITALIA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','SAGRAMOSO ANDREA VS DEL BUONO NICOLETTA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','ZETTI LUIGINA VS FUMAGALLI ROMARIO GIULIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in','ZAMMATARO NUNZIATINA VS ROLLI LORENZO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?','CHIARION EMANUELA VS BENNI GIOVANNA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','SERVI MANUELA VS DE FAZIO SIMONA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','LATTUADA RITA BARBARA VS ERRHILA MOHAMMED');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in','CORRIAS LUIGI ACHILLE VS CAPUTO ANTONIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?','CANADA 2000 SCARL VS DAL VERME ANNA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','CESA BIANCHI CARLO E CESA BIANCHI PAOLO VS DONAU VERSICHERUNG AG');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ','GALLASSO DAVIDE MASSIMILIANO VS COSSALI GIACOMO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure','ZACCARA GIULIO VS PETRARCA SILVANO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non','SACRISMI LORIS VS LCS SRL');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','BASILE ELENA VS PISOTTI');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','GDSM GLOBAL DISTRIBUTION SALES & MARKETI VS BARONE AURELIA ANNA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','PROVA EMISSIONE VS FANTINI LIVIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','ROMANO CLAUDIO VS TAVECCHIO GUIDO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','ZANOL MARCO VS ESSEELLEPI ASSAM');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in','BASCHIERI LAURA VS CASA GIRELLI');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?','ARAUJO ALMONTE MARIA DEL CARMEN VS GEODIS ZUST');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','TAFFURI CLAUDIO VS SALOMONI STEFANO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','MOTTA ELISA VS FERRARIO CESARINO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in','PEROZZIELLO CECILIA VS HOTEL MAGGIORE SNC');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?','GEROSA PIERA VS ROSSETTI ELDA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','BOTTAGISIO ANNAMARIA VS ARTESANI MARISA MANUELA SANDRA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','BERTOGLIO ALESSANDRO VS MAGGINI ALESSANDRA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','MAGGIONI CORRADO VS DE VITA ROBERTO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','ANGIULI ELIANA ROBERTA VS ALBERIO GIOVANNI');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','RAVENNA GUIDO VS VITALI MARCO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?','CAPELLINI NICCOLO FEDERICO VS MODUGNO ALESSANDRO GERARDO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','BRUNNER HELEN VS AGSANTA RITA REALE MUTUA TO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','LEONORI MASSIMO VS CAROLA SIMONE');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','FAVI UGO VS BONADONNA FRANCO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','PELLEGRI MARINO VS COLOMBO ROBERTO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','PEREDA MAURIZIO VS COLONELLO GIAN MARIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','FERRARIO MARCO VS BACCELLI GIORGIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?','BIANCHI MARIA VS MONISTIER MAURILIO ANTONIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','JUTTE THEODORA MARIA VS BEGHI GIANCARLO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','COLOMBO ROBERTO VS BERTOLINI VITTORIO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','MADASCHI ALBERTINA VS CASIRAGHI PAOLO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','CONCATO TOMMASO VS POMPETRAVAINI SPA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','MARRAPODI GIORGIO VS SALMOIRAGHI ENZO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','UCELLI ELENA VS DI PIETRO ANGELO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','CRIPPA ARIANNA RACHELE VS COQUIO MARCO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','NIGRO LUCIANO VS COOP SOCIALE SIMPATIA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','CHIEU GIUSEPPE VS BERETTA ENRICA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','CAMUSSI BOZZINI SILVANA VS BARBISOTTI ERMES');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,1,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','GIUSSANI VALERIA VS CAMUSSI BOZZINI SILVANA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,2,'tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem','VITTADINI ELENA MARIA VS GENERALI EX ASSITALIA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,3,'ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in','VITTADINI GIANLUIGI VS INA ASSICURA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,4,'ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?','FRUSCIANO DONATA GIROLAMA VS BOSIO LAURA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,5,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','SALOMIA CARMEN MIHAELA VS MANOUILIDOU DELLA VALLE CHRISTINA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,6,'Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi ','GIOVANELLI MARIO VS TADDEI SILVIA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,7,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque i','SYNTHOMER SRL VS KOVACS SIMONA');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,8,'psa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia ','DI VALENTINO CATERINA VS IARDONI MATTEO');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,9,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','OSTERIA DAL VERME SRLS VS LOCAFIT');
INSERT INTO legal_practice (practice_id, creator, description, name) VALUES (null,10,'voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. ','CASELLATO DANIELA VS CAMILLONI CARLO');

DELETE FROM event;
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,1,20171128,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,1,20171127,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,1,20171126,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,2,20171125,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,2,20171124,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,2,20171123,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,2,20171122,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,3,20171121,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,3,20171120,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,3,20171119,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,3,20171118,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,4,20171117,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,4,20171116,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,5,20171115,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,5,20171114,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,6,20171113,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,7,20171112,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,8,20171111,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,8,20171110,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,8,20171109,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,9,20171108,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,10,20171107,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,11,20171106,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,11,20171105,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,11,20171104,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,12,20171103,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,12,20171102,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,12,20171101,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,12,20171031,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,13,20171030,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,13,20171029,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,13,20171028,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,13,20171027,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,14,20171026,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,14,20171025,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,15,20171024,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,15,20171023,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,16,20171022,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,17,20171021,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,18,20171020,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,18,20171019,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,18,20171018,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,19,20171017,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,20,20171016,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,21,20171015,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,21,20171014,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,21,20171013,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,22,20171012,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,22,20171011,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,22,20171010,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,22,20171009,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,23,20171008,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,23,20171007,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,23,20171006,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,23,20171005,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,24,20171004,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,24,20171003,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,25,20171002,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,25,20171001,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,26,20170930,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,27,20170929,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,28,20170928,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,28,20170927,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,28,20170926,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,29,20170925,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,30,20170924,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,31,20170923,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,31,20170922,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,31,20170921,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,32,20170920,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,32,20170919,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,32,20170918,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,32,20170917,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,33,20170916,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,33,20170915,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,33,20170914,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,33,20170913,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,34,20170912,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,34,20170911,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,35,20170910,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,35,20170909,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,36,20170908,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,37,20170907,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,38,20170906,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,38,20170905,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,38,20170904,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,39,20170903,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,40,20170902,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,41,20170901,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,41,20170831,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,41,20170830,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,42,20170829,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,42,20170828,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,42,20170827,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,42,20170826,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,43,20170825,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,43,20170824,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,43,20170823,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,43,20170822,'dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20171128);
INSERT INTO event (event_id, creator, practice, event_date, description, creation_date) VALUES (null,1,44,20170821,'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20171128);

DELETE FROM activity;
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,1,'RIUNIONE','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180101,'OPEN',null,20171128,'Nome attività 1');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,1,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180102,'OPEN',null,20171128,'Nome attività 2');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,1,'MAIL','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180103,'OPEN',null,20171128,'Nome attività 3');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,2,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180104,'CLOSED',20171128,20171130,'Nome attività 4');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,6,2,'RIUNIONE','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180105,'OPEN',null,20171128,'Nome attività 5');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,2,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180106,'CLOSED',20171128,20171130,'Nome attività 6');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,2,'MAIL','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180107,'OPEN',null,20171128,'Nome attività 7');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,3,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180108,'OPEN',null,20171128,'Nome attività 8');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,3,'RIUNIONE','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180109,'OPEN',null,20171128,'Nome attività 9');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,9,3,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180110,'CLOSED',20171128,20171130,'Nome attività 10');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,8,3,'MAIL','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180111,'CLOSED',20171128,20171130,'Nome attività 11');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,4,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180112,'OPEN',null,20171128,'Nome attività 12');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,4,'RIUNIONE','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180113,'OPEN',null,20171128,'Nome attività 13');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,4,5,'TELEFONATA','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180114,'OPEN',null,20171128,'Nome attività 14');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,5,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180115,'CLOSED',20171128,20171130,'Nome attività 15');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,3,6,'INCONTRO','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180116,'OPEN',null,20171128,'Nome attività 16');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,6,7,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180117,'CLOSED',20171128,20171130,'Nome attività 17');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,4,8,'TELEFONATA','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180118,'OPEN',null,20171128,'Nome attività 18');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,8,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180119,'OPEN',null,20171128,'Nome attività 19');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,7,8,'INCONTRO','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180120,'OPEN',null,20171128,'Nome attività 20');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,9,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180121,'CLOSED',20171128,20171130,'Nome attività 21');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,10,'TELEFONATA','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180122,'CLOSED',20171128,20171130,'Nome attività 22');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,11,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180123,'OPEN',null,20171128,'Nome attività 23');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,11,'INCONTRO','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180124,'OPEN',null,20171128,'Nome attività 24');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,11,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180125,'OPEN',null,20171128,'Nome attività 25');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,12,'TELEFONATA','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180126,'CLOSED',20171128,20171130,'Nome attività 26');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,6,12,'MAIL','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180127,'OPEN',null,20171128,'Nome attività 27');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,12,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180128,'CLOSED',20171128,20171130,'Nome attività 28');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,12,'RIUNIONE','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180129,'OPEN',null,20171128,'Nome attività 29');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,13,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180130,'OPEN',null,20171128,'Nome attività 30');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,13,'MAIL','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180131,'OPEN',null,20171128,'Nome attività 31');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,9,13,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180201,'CLOSED',20171128,20171130,'Nome attività 32');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,8,13,'RIUNIONE','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180202,'CLOSED',20171128,20171130,'Nome attività 33');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,14,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180203,'OPEN',null,20171128,'Nome attività 34');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,14,'MAIL','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180204,'OPEN',null,20171128,'Nome attività 35');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,4,15,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180205,'OPEN',null,20171128,'Nome attività 36');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,15,'RIUNIONE','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180206,'CLOSED',20171128,20171130,'Nome attività 37');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,3,16,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180207,'OPEN',null,20171128,'Nome attività 38');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,6,17,'MAIL','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180208,'CLOSED',20171128,20171130,'Nome attività 39');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,4,18,'INCONTRO','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180209,'OPEN',null,20171128,'Nome attività 40');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,18,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180210,'OPEN',null,20171128,'Nome attività 41');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,7,18,'TELEFONATA','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180211,'OPEN',null,20171128,'Nome attività 42');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,19,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180212,'CLOSED',20171128,20171130,'Nome attività 43');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,20,'INCONTRO','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180213,'CLOSED',20171128,20171130,'Nome attività 44');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,21,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180214,'OPEN',null,20171128,'Nome attività 45');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,21,'TELEFONATA','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180215,'OPEN',null,20171128,'Nome attività 46');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,21,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180216,'OPEN',null,20171128,'Nome attività 47');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,22,'INCONTRO','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180217,'CLOSED',20171128,20171130,'Nome attività 48');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,6,22,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180218,'OPEN',null,20171128,'Nome attività 49');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,22,'TELEFONATA','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180219,'CLOSED',20171128,20171130,'Nome attività 50');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,22,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180220,'OPEN',null,20171128,'Nome attività 51');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,23,'INCONTRO','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180221,'OPEN',null,20171128,'Nome attività 52');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,23,'RIUNIONE','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180222,'OPEN',null,20171128,'Nome attività 53');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,9,23,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180223,'CLOSED',20171128,20171130,'Nome attività 54');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,8,23,'MAIL','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180224,'CLOSED',20171128,20171130,'Nome attività 55');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,24,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180225,'OPEN',null,20171128,'Nome attività 56');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,24,'RIUNIONE','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180226,'OPEN',null,20171128,'Nome attività 57');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,4,25,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180227,'OPEN',null,20171128,'Nome attività 58');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,25,'MAIL','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180228,'CLOSED',20171128,20171130,'Nome attività 59');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,3,26,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180301,'OPEN',null,20171128,'Nome attività 60');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,6,27,'RIUNIONE','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180302,'CLOSED',20171128,20171130,'Nome attività 61');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,4,28,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180303,'OPEN',null,20171128,'Nome attività 62');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,28,'MAIL','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180304,'OPEN',null,20171128,'Nome attività 63');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,7,28,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180305,'OPEN',null,20171128,'Nome attività 64');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,29,'RIUNIONE','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180306,'CLOSED',20171128,20171130,'Nome attività 65');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,30,'TELEFONATA','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180307,'CLOSED',20171128,20171130,'Nome attività 66');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,31,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180308,'OPEN',null,20171128,'Nome attività 67');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,31,'INCONTRO','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180309,'OPEN',null,20171128,'Nome attività 68');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,31,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180310,'OPEN',null,20171128,'Nome attività 69');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,32,'TELEFONATA','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180311,'CLOSED',20171128,20171130,'Nome attività 70');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,6,32,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180312,'OPEN',null,20171128,'Nome attività 71');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,32,'INCONTRO','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180313,'CLOSED',20171128,20171130,'Nome attività 72');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,32,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180314,'OPEN',null,20171128,'Nome attività 73');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,33,'TELEFONATA','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180315,'OPEN',null,20171128,'Nome attività 74');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,33,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180316,'OPEN',null,20171128,'Nome attività 75');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,9,33,'INCONTRO','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180317,'CLOSED',20171128,20171130,'Nome attività 76');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,8,33,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180318,'CLOSED',20171128,20171130,'Nome attività 77');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,34,'TELEFONATA','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180319,'OPEN',null,20171128,'Nome attività 78');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,34,'MAIL','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180320,'OPEN',null,20171128,'Nome attività 79');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,4,35,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180321,'OPEN',null,20171128,'Nome attività 80');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,35,'RIUNIONE','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180322,'CLOSED',20171128,20171130,'Nome attività 81');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,3,36,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180323,'OPEN',null,20171128,'Nome attività 82');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,6,37,'MAIL','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180324,'CLOSED',20171128,20171130,'Nome attività 83');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,4,38,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180325,'OPEN',null,20171128,'Nome attività 84');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,38,'RIUNIONE','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180326,'OPEN',null,20171128,'Nome attività 85');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,7,38,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180327,'OPEN',null,20171128,'Nome attività 86');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,39,'MAIL','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180328,'CLOSED',20171128,20171130,'Nome attività 87');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,40,'INCONTRO','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180329,'CLOSED',20171128,20171130,'Nome attività 88');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,41,'RIUNIONE','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180330,'OPEN',null,20171128,'Nome attività 89');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,41,'TELEFONATA','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180331,'OPEN',null,20171128,'Nome attività 90');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,41,'MAIL','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180401,'OPEN',null,20171128,'Nome attività 91');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,42,'INCONTRO','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180402,'CLOSED',20171128,20171130,'Nome attività 92');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,6,42,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180403,'OPEN',null,20171128,'Nome attività 93');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,42,'TELEFONATA','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180404,'CLOSED',20171128,20171130,'Nome attività 94');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,10,42,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180405,'OPEN',null,20171128,'Nome attività 95');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,43,'INCONTRO','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180406,'OPEN',null,20171128,'Nome attività 96');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,43,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180407,'OPEN',null,20171128,'Nome attività 97');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,9,43,'TELEFONATA','Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ',20180408,'CLOSED',20171128,20171130,'Nome attività 98');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,8,43,'MAIL','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180409,'CLOSED',20171128,20171130,'Nome attività 99');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,2,44,'INCONTRO','Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure',20180410,'OPEN',null,20171128,'Nome attività 100');
INSERT INTO activity (activity_id, creator, assignee, event, activity_type, description, expiration_date, status, creation_date, completion_date, name) VALUES (null,1,5,44,'RIUNIONE','dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non',20180411,'OPEN',null,20171128,'Nome attività 101');
SET FOREIGN_KEY_CHECKS=1;
