/*
SQLyog Ultimate v8.32 
MySQL - 5.1.47-community : Database - catchdoll
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`catchdoll` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `catchdoll`;

/*Table structure for table `dolls` */

DROP TABLE IF EXISTS `dolls`;

CREATE TABLE `dolls` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `dollName` varchar(100) DEFAULT NULL,
  `describ` varchar(2000) DEFAULT NULL,
  `outPrice` double DEFAULT NULL,
  `inPrice` double DEFAULT NULL,
  `imgUrl` varchar(200) DEFAULT NULL,
  `catchCount` int(11) DEFAULT NULL,
  `isDel` int(2) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dolls` */

/*Table structure for table `dorder` */

DROP TABLE IF EXISTS `dorder`;

CREATE TABLE `dorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNo` varchar(32) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `revAddress` varchar(500) DEFAULT NULL,
  `orderStatus` int(11) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `dollId` int(11) DEFAULT NULL,
  `orderDate` varchar(20) DEFAULT NULL,
  `deviceInfos` varchar(200) DEFAULT NULL,
  `isDel` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dorder` */

/*Table structure for table `duser` */

DROP TABLE IF EXISTS `duser`;

CREATE TABLE `duser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(50) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `thirdAccount` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `account` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `duser` */

insert  into `duser`(`id`,`nickName`,`tel`,`thirdAccount`,`email`,`pwd`,`account`) values (1,'一二三','18652768098','857340569','857340569@qq.com','123654',20.2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
