CREATE DATABASE  IF NOT EXISTS `mobile` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mobile`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: mobile
-- ------------------------------------------------------
-- Server version	5.7.4-m14-log

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
-- Table structure for table `allocation`
--

DROP TABLE IF EXISTS `allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allocation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `AREA` varchar(45) DEFAULT NULL,
  `LOCATION` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allocation`
--

LOCK TABLES `allocation` WRITE;
/*!40000 ALTER TABLE `allocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asn`
--

DROP TABLE IF EXISTS `asn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asn` (
  `COMPANY` varchar(20) NOT NULL,
  `ORG_CODE` varchar(20) NOT NULL,
  `WAREHOUSE_CODE` varchar(20) DEFAULT NULL,
  `BILL_TYPE` varchar(20) NOT NULL,
  `EXPECTED_ARRIVE_DATE` timestamp NULL DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `FLAG` varchar(30) NOT NULL,
  `RELATED_BILL1` varchar(30) DEFAULT NULL,
  `RELATED_BILL2` varchar(30) DEFAULT NULL,
  `RELATED_BILL3` varchar(30) DEFAULT NULL,
  `PROJECT_CODE` varchar(20) NOT NULL,
  `ORDER_DATE` timestamp NULL DEFAULT NULL,
  `DOCK` varchar(20) NOT NULL,
  `BUYER_NAME` varchar(30) DEFAULT NULL,
  `BUYER_EMAIL` varchar(30) DEFAULT NULL,
  `BUYER_FAX` varchar(30) DEFAULT NULL,
  `BUYER_PHONE` varchar(30) DEFAULT NULL,
  `MATERIALS_NAME` varchar(30) DEFAULT NULL,
  `MATERIALS_EMAIL` varchar(30) DEFAULT NULL,
  `MATERIALS_FAX` varchar(30) DEFAULT NULL,
  `MATERIALS_PHONE` varchar(30) DEFAULT NULL,
  `VENDOR_CODE` varchar(20) DEFAULT NULL,
  `VENDOR_NAME` varchar(240) DEFAULT NULL,
  `VENDOR_CONTACT_NAME` varchar(30) DEFAULT NULL,
  `VENDOR_CONTACT_EMAIL` varchar(30) DEFAULT NULL,
  `VENDOR_CONTACT_FAX` varchar(30) DEFAULT NULL,
  `VENDOR_CONTACT_PHONE` varchar(30) DEFAULT NULL,
  `CARRIER_NAME` varchar(240) DEFAULT NULL,
  `CARRIER_CONTACT_NAME` varchar(30) DEFAULT NULL,
  `CARRIER_CONTACT_EMAIL` varchar(30) DEFAULT NULL,
  `CARRIER_CONTACT_FAX` varchar(30) DEFAULT NULL,
  `CARRIER_CONTACT_PHONE` varchar(30) DEFAULT NULL,
  `DISPOSITION` varchar(240) DEFAULT NULL,
  `Paid` int(11) DEFAULT NULL,
  `Apply_Person` varchar(45) DEFAULT NULL,
  `Paid_Date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asn`
--

LOCK TABLES `asn` WRITE;
/*!40000 ALTER TABLE `asn` DISABLE KEYS */;
INSERT INTO `asn` VALUES ('IES','2521','','SET_IN_01','2016-03-17 16:00:00','VD-SH-2016030000263','N','2510-PO-2016020000931','','','B1621003','2016-03-04 16:00:00','','','','','','李玉峰','','','13598846854','10015781','宁波余大通信技术有限公司','解新菊','','','13777100257','宁波余大通信技术有限公司','徐建钢','','','13362879996','',0,'赵立昕','2016-04-17 05:38:54'),('IES','2521','','SET_IN_01','2016-03-09 16:00:00','VD-SH-2016030000421','N','2521-PO-2016010000296','','','C1521009','2016-03-08 16:00:00','','','','','','李玉峰','','','13598846854','10086206','北京融和创科技有限公司','谢长生','','','13501350572','安德物流','谢权胜','','','18600283944','',0,'陈庆伟','2016-04-17 05:38:54'),('IES','2521','','SET_IN_01',NULL,'VD-SH-2016040000037','N','2521-PO-2016030000237','','','C1521012','2016-03-31 16:00:00','','','','','','李玉峰','','','13598846854','10109759','浙江超前通信设备有限公司','陈筱敏','','','15267219171','汇发物流','谢巍','','','13606767395','',0,'尚晓娟','2016-04-17 05:38:54');
/*!40000 ALTER TABLE `asn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bom`
--

DROP TABLE IF EXISTS `bom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bom` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ASN_CODE` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_BOM_ASN1_idx` (`ASN_CODE`),
  CONSTRAINT `fk_BOM_ASN1` FOREIGN KEY (`ASN_CODE`) REFERENCES `asn` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bom`
--

LOCK TABLES `bom` WRITE;
/*!40000 ALTER TABLE `bom` DISABLE KEYS */;
INSERT INTO `bom` VALUES (30,'VD-SH-2016030000263'),(31,'VD-SH-2016030000421'),(32,'VD-SH-2016040000037'),(33,'VD-SH-2016040000037'),(34,'VD-SH-2016040000037'),(35,'VD-SH-2016040000037'),(36,'VD-SH-2016040000037'),(37,'VD-SH-2016040000037');
/*!40000 ALTER TABLE `bom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check`
--

DROP TABLE IF EXISTS `check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `check` (
  `MaterialCode` varchar(300) DEFAULT NULL,
  `Position` varchar(300) DEFAULT NULL,
  `TIME` timestamp NULL DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RealNum` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check`
--

LOCK TABLES `check` WRITE;
/*!40000 ALTER TABLE `check` DISABLE KEYS */;
/*!40000 ALTER TABLE `check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail`
--

DROP TABLE IF EXISTS `detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail` (
  `IS_BOM` varchar(30) DEFAULT NULL,
  `SALE_BOM_CODE` varchar(50) DEFAULT NULL,
  `ITEM_ERP_CODE` varchar(30) DEFAULT NULL,
  `ITEM_ERP_UNIT` varchar(30) DEFAULT NULL,
  `ITEM_CODE` varchar(30) DEFAULT NULL,
  `ITEM_NAME` varchar(240) DEFAULT NULL,
  `ITEM_UNIT_CODE` varchar(30) DEFAULT NULL,
  `EXPECTED_QUANTITY` int(11) DEFAULT NULL,
  `POS_APPLY_DOC_CODE` varchar(30) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `fk_DETAIL_POS1_idx` (`POS_APPLY_DOC_CODE`),
  CONSTRAINT `fk_DETAIL_POS1` FOREIGN KEY (`POS_APPLY_DOC_CODE`) REFERENCES `pos` (`APPLY_DOC_CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail`
--

LOCK TABLES `detail` WRITE;
/*!40000 ALTER TABLE `detail` DISABLE KEYS */;
INSERT INTO `detail` VALUES ('N','','10075465','个','','','个',10,'2521-REQ-2016040000003',35),('Y','2510TP000012961','','','','','',2,'2521-REQ-2016040000004',36);
/*!40000 ALTER TABLE `detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `ITEM_CODE` varchar(20) DEFAULT NULL,
  `ITEM_NAME` varchar(240) DEFAULT NULL,
  `CONFIG_QUANTITY` int(11) DEFAULT NULL,
  `ITEM_UNIT` varchar(30) DEFAULT NULL,
  `ITEMcol` varchar(45) DEFAULT NULL,
  `SALE_BOM_DETAIL_ID` int(11) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `fk_ITEM_ SALE_BOM_DETAIL_idx` (`SALE_BOM_DETAIL_ID`),
  CONSTRAINT `fk_ITEM_ SALE_BOM_DETAIL` FOREIGN KEY (`SALE_BOM_DETAIL_ID`) REFERENCES `sale_bom_detail` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES ('1009100PJ001227','传统型室外光缆交接箱288芯产品使用说明书',1,'套',NULL,4,37),('1009100PJ001230','传统型室外光缆交接箱288芯光缆接地装置',30,'套',NULL,4,38),('1009100PJ001233','传统型室外光缆交接箱288芯12芯熔配一体化模块',24,'套',NULL,4,39),('1009100PJ001235','传统型室外光缆交接箱288芯落地式双层不锈钢机座（含箱体地脚螺栓等固定件）',1,'套',NULL,4,40),('1009100PJ001223','传统型室外光缆交接箱288芯管孔堵漏材料',5,'包',NULL,4,41),('1009100PJ001234','传统型室外光缆交接箱288芯架空站台（含箱体等加固件）（单杆）',1,'套',NULL,4,42),('1009100PJ001237','传统型室外光缆交接箱288芯单芯光纤热缩管',300,'个',NULL,4,43),('1009100PJ001226','传统型室外光缆交接箱288芯束状或带状光缆开剥保护装置',30,'套',NULL,4,44),('1009100PJ001228','传统型室外光缆交接箱288芯12芯带状热熔缩管',24,'个',NULL,4,45),('1009100PJ001222','传统型室外光缆交接箱288芯光缆清洁纸巾',4,'包',NULL,4,46),('1009100PJ001229','传统型室外光缆交接箱288芯直熔模块',20,'套',NULL,4,47),('1009100PJ001231','传统型室外光缆交接箱288芯FC/UPC/APC、SC/UPC/APC光纤适配器',288,'个',NULL,4,48),('1009100PJ001232','\n                                                    传统型室外光缆交接箱288芯12芯束状/带状尾缆1.0米（0.9mm）(FC/UPC/APC)(SC/UPC/APC)\n                                                ',48,'条',NULL,4,49),('1009100PJ001224','传统型室外光缆交接箱288芯裸纤保护管',288,'条',NULL,4,50),('1009100PJ001236','传统型室外光缆交接箱288芯密封胶带',1,'包',NULL,4,51),('1009100PJ001238','传统型室外光缆交接箱288芯非凝固型防火泥',5,'包',NULL,4,52),('1009100PJ001221','传统型室外光缆交接箱288芯双层不锈钢箱体',1,'台',NULL,4,53),('1009100PJ001225','传统型室外光缆交接箱288芯线扎',48,'套',NULL,4,54),('1004743PJ000001','含安装费；型号：KFR-120LW/SD-JZ1(R2)',1,'台',NULL,5,55);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `TIME` timestamp NULL DEFAULT NULL,
  `OPERATION` varchar(45) DEFAULT NULL,
  `NOTE` varchar(100) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packing`
--

DROP TABLE IF EXISTS `packing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `packing` (
  `PACKING_CODE` varchar(50) DEFAULT NULL,
  `PACKING_NAME` varchar(240) DEFAULT NULL,
  `PACKING_COUNT` int(11) DEFAULT NULL,
  `PACKING_LEN_UNIT` varchar(25) DEFAULT NULL,
  `PACKING_LEN` double DEFAULT NULL,
  `PACKING_WIDTH` double DEFAULT NULL,
  `PACKING_HEIGHT` double DEFAULT NULL,
  `PACKING_CAPACITY_UOM` varchar(30) DEFAULT NULL,
  `PACKING_CAPACITY` double DEFAULT NULL,
  `PACKING_WEIGHT_UOM` varchar(30) DEFAULT NULL,
  `PACKING_WEIGHT` double DEFAULT NULL,
  `PACKING_BOM_ID` int(11) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `fk_PACKING_PACKING_BOM1_idx` (`PACKING_BOM_ID`),
  CONSTRAINT `fk_PACKING_PACKING_BOM1` FOREIGN KEY (`PACKING_BOM_ID`) REFERENCES `packing_bom` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packing`
--

LOCK TABLES `packing` WRITE;
/*!40000 ALTER TABLE `packing` DISABLE KEYS */;
INSERT INTO `packing` VALUES ('55605_2510TP000012961_10015781_01','1架/台',1,'？？',1.5,0.8,0.37,'？？？？？？',0.44,'？？',0.1,30,31),('40075_2510TX000009554_10086206/1/2','室内机',1,'套',1.98,0.545,0.793,'？？？？？？',0.86,'？？',0.08,31,32),('40075_2510TX000009554_10086206/2/2','室外机',1,'套',1.043,0.42,1.285,'？？？？？？',0.56,'？？',0.119,31,33),('38791_10075467_10109759_01','每箱6套接头盒',6,'米',0.56,0.465,0.425,'立方米',0.11,'吨',0.027,32,34),('38790_10075465_10109759_01','每箱6套接头盒',6,'米',0.56,0.465,0.425,'立方米',0.11,'吨',0.027,33,35),('47135_10075600_10109759_01','每个接头盒配6个专用安装配件',36,'米',0.56,0.465,0.425,'立方米',0.11,'吨',0.1,34,36),('38788_10075599_10109759_01','每个接头盒配2个专用安装配件',12,'米',0.56,0.465,0.425,'立方米',0.11,'吨',0.00001,35,37),('38784_10075601_10109759_01','每个接头盒内箱中配一把',6,'米',0.56,0.465,0.425,'立方米',0.11,'吨',0.33,36,38),('38785_10075598_10109759_01','每个接头盒配4个监测尾缆引出用组件',24,'米',0.56,0.465,0.425,'立方米',0.11,'吨',0.00001,37,39);
/*!40000 ALTER TABLE `packing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packing_bom`
--

DROP TABLE IF EXISTS `packing_bom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `packing_bom` (
  `PACKING_BOM_CODE` varchar(30) DEFAULT NULL,
  `PACKING_TYPE` varchar(30) DEFAULT NULL,
  `VENDOR_CODE` varchar(20) DEFAULT NULL,
  `VENDOR_NAME` varchar(240) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOM_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_PACKING_BOM_BOM1_idx` (`BOM_ID`),
  CONSTRAINT `fk_PACKING_BOM_BOM1` FOREIGN KEY (`BOM_ID`) REFERENCES `bom` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packing_bom`
--

LOCK TABLES `packing_bom` WRITE;
/*!40000 ALTER TABLE `packing_bom` DISABLE KEYS */;
INSERT INTO `packing_bom` VALUES ('55605_2510TP000012961_10015781','1','10015781','宁波余大通信技术有限公司',30,30),('40075_2510TX000009554_10086206','2','10086206','北京融和创科技有限公司',31,31),('38791_10075467_10109759','1','10109759','浙江超前通信设备有限公司',32,32),('38790_10075465_10109759','1','10109759','浙江超前通信设备有限公司',33,33),('47135_10075600_10109759','1','10109759','浙江超前通信设备有限公司',34,34),('38788_10075599_10109759','1','10109759','浙江超前通信设备有限公司',35,35),('38784_10075601_10109759','1','10109759','浙江超前通信设备有限公司',36,36),('38785_10075598_10109759','1','10109759','浙江超前通信设备有限公司',37,37);
/*!40000 ALTER TABLE `packing_bom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `picture`
--

DROP TABLE IF EXISTS `picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `picture` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `URL` varchar(45) DEFAULT NULL,
  `APPLY_DOC_CODE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `picture`
--

LOCK TABLES `picture` WRITE;
/*!40000 ALTER TABLE `picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pos`
--

DROP TABLE IF EXISTS `pos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pos` (
  `COMPANY` varchar(20) DEFAULT NULL,
  `WAREHOUSE_CODE` varchar(20) DEFAULT NULL,
  `BILL_TYPE` varchar(20) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  `APPLY_DOC_CODE` varchar(30) NOT NULL,
  `PROJECT_CODE` varchar(10) DEFAULT NULL,
  `APPLY_UNIT` varchar(240) DEFAULT NULL,
  `APPLY_PERSON` varchar(25) DEFAULT NULL,
  `APPLY_DATE` timestamp NULL DEFAULT NULL,
  `CONSTRUCTION_UNIT` varchar(240) DEFAULT NULL,
  `RECEIVER` varchar(25) DEFAULT NULL,
  `RECEIVER_UID_CODE` varchar(25) DEFAULT NULL,
  `APPLY_DOC_DESC` varchar(240) DEFAULT NULL,
  `EXPECTED_SHIP_DATE` timestamp NULL DEFAULT NULL,
  `DOCK_CODE` varchar(20) DEFAULT NULL,
  `DISPOSITION` varchar(240) DEFAULT NULL,
  `Sent` int(11) DEFAULT NULL,
  `Sent_Date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`APPLY_DOC_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pos`
--

LOCK TABLES `pos` WRITE;
/*!40000 ALTER TABLE `pos` DISABLE KEYS */;
INSERT INTO `pos` VALUES ('IES','A002','SET_OUT_01','2521-ZZE-OUT-2016040000005','2521-REQ-2016040000003','C1521012','郑州分公司\\工程建设中心','刘娜2','2016-04-14 16:00:00','郑州分公司\\工程建设中心','','','光缆接头盒\\双端\\24芯\\12芯\\2个','2016-04-14 16:00:00','','',0,'2016-04-17 06:23:54'),('IES','A002','SET_OUT_01','2521-ZZE-OUT-2016040000006','2521-REQ-2016040000004','B1621003','郑州分公司\\工程建设中心','刘娜2','2016-04-17 16:00:00','郑州分公司\\工程建设中心','','','WMS测试数据','2016-04-18 16:00:00','','',0,'2016-04-18 05:28:26');
/*!40000 ALTER TABLE `pos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rasn`
--

DROP TABLE IF EXISTS `rasn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rasn` (
  `COMPANY` varchar(20) DEFAULT NULL,
  `WAREHOUSE_CODE` varchar(20) DEFAULT NULL,
  `BILL_TYPE` varchar(20) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `RECEIVE_TYPE` varchar(20) DEFAULT NULL,
  `RECEIVE_USER` varchar(20) DEFAULT NULL,
  `RECEIVE_DATE` timestamp NULL DEFAULT NULL,
  `RASNcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rasn`
--

LOCK TABLES `rasn` WRITE;
/*!40000 ALTER TABLE `rasn` DISABLE KEYS */;
/*!40000 ALTER TABLE `rasn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rasndetail`
--

DROP TABLE IF EXISTS `rasndetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rasndetail` (
  `CARTON_ORDER_NUM` varchar(20) DEFAULT NULL,
  `RECEIVE_DATE` timestamp NULL DEFAULT NULL,
  `ITEM_CODE` varchar(30) DEFAULT NULL,
  `ITEM_NAME` varchar(240) DEFAULT NULL,
  `ITEM_UNIT_CODE` varchar(30) DEFAULT NULL,
  `RECEIVE_QUANTITY` int(11) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RASN_CODE` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_DETAIL_RASN1_idx` (`RASN_CODE`),
  CONSTRAINT `fk_DETAIL_RASN1` FOREIGN KEY (`RASN_CODE`) REFERENCES `rasn` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rasndetail`
--

LOCK TABLES `rasndetail` WRITE;
/*!40000 ALTER TABLE `rasndetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `rasndetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rdetail`
--

DROP TABLE IF EXISTS `rdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rdetail` (
  `SALE_BOM_CODE` varchar(20) DEFAULT NULL,
  `ITEM_ERP_CODE` varchar(30) DEFAULT NULL,
  `ITEM_ERP_UNIT` varchar(30) DEFAULT NULL,
  `ITEM_CODE` varchar(30) DEFAULT NULL,
  `ITEM_UNIT` varchar(240) DEFAULT NULL,
  `SHIP_QUANTITY` double DEFAULT NULL,
  `RPOS_CODE` varchar(30) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `fk_RDETAIL_RPOS1_idx` (`RPOS_CODE`),
  CONSTRAINT `fk_RDETAIL_RPOS1` FOREIGN KEY (`RPOS_CODE`) REFERENCES `rpos` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rdetail`
--

LOCK TABLES `rdetail` WRITE;
/*!40000 ALTER TABLE `rdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `rdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rpos`
--

DROP TABLE IF EXISTS `rpos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rpos` (
  `COMPANY` varchar(20) DEFAULT NULL,
  `WAREHOUSE_CODE` varchar(20) DEFAULT NULL,
  `BILL_TYPE` varchar(20) DEFAULT NULL,
  `CODE` varchar(30) NOT NULL,
  `SHIP_USER` varchar(20) DEFAULT NULL,
  `SHIP_DATE` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rpos`
--

LOCK TABLES `rpos` WRITE;
/*!40000 ALTER TABLE `rpos` DISABLE KEYS */;
/*!40000 ALTER TABLE `rpos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_bom`
--

DROP TABLE IF EXISTS `sale_bom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_bom` (
  `SALE_BOM_CODE` varchar(50) DEFAULT NULL,
  `SALE_BOM_NAME` varchar(240) DEFAULT NULL,
  `SALE_BOM_UNIT` varchar(30) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BOM_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_SALE_BOM_BOM1_idx` (`BOM_ID`),
  CONSTRAINT `fk_SALE_BOM_BOM1` FOREIGN KEY (`BOM_ID`) REFERENCES `bom` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_bom`
--

LOCK TABLES `sale_bom` WRITE;
/*!40000 ALTER TABLE `sale_bom` DISABLE KEYS */;
INSERT INTO `sale_bom` VALUES ('2510TP000012961','传统型光缆交接箱\\双层不锈钢\\满配架空型\\288芯','套',4,30),('2510TX000009554','通信用专用空调\\5HP柜式基站空调(12KW)\\冷暖','',5,31);
/*!40000 ALTER TABLE `sale_bom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale_bom_detail`
--

DROP TABLE IF EXISTS `sale_bom_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sale_bom_detail` (
  `ITEM_ERP_CODE` varchar(30) DEFAULT NULL,
  `ITEM_ERP_NAME` varchar(240) DEFAULT NULL,
  `ITEM_ERP_QUANTITY` int(11) DEFAULT NULL,
  `ITEM_ERP_UNIT` varchar(30) DEFAULT NULL,
  `ITEM_CODE` varchar(30) DEFAULT NULL,
  `ITEM_NAME` varchar(240) DEFAULT NULL,
  `CONFIG_QUANTITY` int(11) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SALE_BOMS_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_ SALE_BOM_DETAIL_SALE_BOMS1_idx` (`SALE_BOMS_ID`),
  CONSTRAINT `fk_ SALE_BOM_DETAIL_SALE_BOMS1` FOREIGN KEY (`SALE_BOMS_ID`) REFERENCES `sale_bom` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_bom_detail`
--

LOCK TABLES `sale_bom_detail` WRITE;
/*!40000 ALTER TABLE `sale_bom_detail` DISABLE KEYS */;
INSERT INTO `sale_bom_detail` VALUES ('10091008','传统型光缆交接箱\\双层不锈钢\\满配架空型\\288芯',1,'架','','',1,4,4),('10047432','通信用专用空调\\5HP柜式基站空调(12KW)\\冷暖',1,'台','','',1,5,5);
/*!40000 ALTER TABLE `sale_bom_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stagingsite`
--

DROP TABLE IF EXISTS `stagingsite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stagingsite` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ApplyPerson` varchar(45) DEFAULT NULL,
  `ConstructPos` varchar(60) DEFAULT NULL,
  `ConstructUnit` varchar(45) DEFAULT NULL,
  `Time` timestamp NULL DEFAULT NULL,
  `MaterialCode` varchar(300) DEFAULT NULL,
  `Num` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stagingsite`
--

LOCK TABLES `stagingsite` WRITE;
/*!40000 ALTER TABLE `stagingsite` DISABLE KEYS */;
/*!40000 ALTER TABLE `stagingsite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transport`
--

DROP TABLE IF EXISTS `transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transport` (
  `CHARGE` varchar(10) DEFAULT NULL,
  `TIME` timestamp NULL DEFAULT NULL,
  `POSITION` varchar(45) DEFAULT NULL,
  `TYPE` varchar(10) DEFAULT NULL,
  `POS_APPLY_DOC_CODE` varchar(30) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `fk_TRANSPORT_POS1_idx` (`POS_APPLY_DOC_CODE`),
  CONSTRAINT `fk_TRANSPORT_POS1` FOREIGN KEY (`POS_APPLY_DOC_CODE`) REFERENCES `pos` (`APPLY_DOC_CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport`
--

LOCK TABLES `transport` WRITE;
/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wms_detail`
--

DROP TABLE IF EXISTS `wms_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wms_detail` (
  `IS_BOM` varchar(30) DEFAULT NULL,
  `SALE_BOM_CODE` varchar(50) DEFAULT NULL,
  `PACKING_CODE` varchar(50) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `CARTON_ORDER_NUM` varchar(30) DEFAULT NULL,
  `CARTON_NUM` varchar(30) DEFAULT NULL,
  `ITEM_CODE` varchar(30) DEFAULT NULL,
  `ITEM_NAME` varchar(240) DEFAULT NULL,
  `ITEM_UNIT_CODE` varchar(30) DEFAULT NULL,
  `EXPECTED_QUANTITY` int(11) DEFAULT NULL,
  `ASN_CODE` varchar(30) NOT NULL,
  `ALLOCATION_id` int(11) DEFAULT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CartonNum` varchar(45) DEFAULT NULL,
  `ApplyDocCode` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_WMS_DETAIL_ASN1_idx` (`ASN_CODE`),
  CONSTRAINT `fk_WMS_DETAIL_ASN1` FOREIGN KEY (`ASN_CODE`) REFERENCES `asn` (`CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1276 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wms_detail`
--

LOCK TABLES `wms_detail` WRITE;
/*!40000 ALTER TABLE `wms_detail` DISABLE KEYS */;
INSERT INTO `wms_detail` VALUES ('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102099','201604000102099/1/1','','','',1,'VD-SH-2016030000263',0,1252,'2016041700000001',''),('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102100','201604000102100/1/1','','','',1,'VD-SH-2016030000263',0,1253,'2016041700000002',''),('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102101','201604000102101/1/1','','','',1,'VD-SH-2016030000263',0,1254,'2016041700000003',''),('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102102','201604000102102/1/1','','','',1,'VD-SH-2016030000263',0,1255,'2016041700000004',''),('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102103','201604000102103/1/1','','','',1,'VD-SH-2016030000263',0,1256,'2016041700000005',''),('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102104','201604000102104/1/1','','','',1,'VD-SH-2016030000263',0,1257,'2016041700000006',''),('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102105','201604000102105/1/1','','','',1,'VD-SH-2016030000263',0,1258,'2016041700000007',''),('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102106','201604000102106/1/1','','','',1,'VD-SH-2016030000263',0,1259,'2016041700000008',''),('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102107','201604000102107/1/1','','','',1,'VD-SH-2016030000263',0,1260,'2016041700000009',''),('Y','2510TP000012961','55605_2510TP000012961_10015781_01',0,'201604000102108','201604000102108/1/1','','','',1,'VD-SH-2016030000263',0,1261,'201604170000000a',''),('Y','2510TX000009554','40075_2510TX000009554_10086206/1/2',0,'201604000102197','201604000102197/1/2','','','',1,'VD-SH-2016030000421',0,1262,'201604170000000b',''),('Y','2510TX000009554','40075_2510TX000009554_10086206/2/2',0,'201604000102197','201604000102197/2/2','','','',1,'VD-SH-2016030000421',0,1263,'201604170000000c',''),('Y','2510TX000009554','40075_2510TX000009554_10086206/1/2',0,'201604000102198','201604000102198/1/2','','','',1,'VD-SH-2016030000421',0,1264,'201604170000000d',''),('Y','2510TX000009554','40075_2510TX000009554_10086206/2/2',0,'201604000102198','201604000102198/2/2','','','',1,'VD-SH-2016030000421',0,1265,'201604170000000e',''),('N','','38790_10075465_10109759_01',0,'201604000102964','201604000102964/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1266,'201604170000000f',''),('N','','38790_10075465_10109759_01',0,'201604000102965','201604000102965/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1267,'2016041700000010',''),('N','','38790_10075465_10109759_01',0,'201604000102966','201604000102966/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1268,'2016041700000011',''),('N','','38790_10075465_10109759_01',0,'201604000102967','201604000102967/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1269,'2016041700000012',''),('N','','38790_10075465_10109759_01',0,'201604000102968','201604000102968/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1270,'2016041700000013',''),('N','','38790_10075465_10109759_01',0,'201604000102969','201604000102969/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1271,'2016041700000014',''),('N','','38790_10075465_10109759_01',0,'201604000102970','201604000102970/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1272,'2016041700000015',''),('N','','38790_10075465_10109759_01',0,'201604000102971','201604000102971/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1273,'2016041700000016',''),('N','','38790_10075465_10109759_01',0,'201604000102972','201604000102972/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1274,'2016041700000017',''),('N','','38790_10075465_10109759_01',0,'201604000102973','201604000102973/1/1','10075465','光缆接头盒\\双端\\24芯\\12芯\\2个','个',6,'VD-SH-2016040000037',0,1275,'2016041700000018','');
/*!40000 ALTER TABLE `wms_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-18 13:35:18
