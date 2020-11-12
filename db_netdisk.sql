/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.28-log : Database - db_netdisk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_netdisk` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `db_netdisk`;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `userName` char(30) DEFAULT NULL,
  `userNum` char(20) NOT NULL,
  `user_tel` char(20) NOT NULL,
  `user_gender` char(5) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `Email` char(30) NOT NULL,
  `password` char(50) DEFAULT NULL,
  `Capacity` double DEFAULT '104857600',
  `usedCapacity` double DEFAULT '0',
  PRIMARY KEY (`userNum`,`user_tel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

LOCK TABLES `t_user` WRITE;

insert  into `t_user`(`userName`,`userNum`,`user_tel`,`user_gender`,`birth`,`Email`,`password`,`Capacity`,`usedCapacity`) values ('着迷','20191221154101','18371206897','男','2019-12-21','1534709914@qq.com','00cecc4e6a8660eeaea40ca806604822',103098203.9,1759396.1),('李斯','20191223100537','18327583117','女','1999-02-04','caoxingchi@outlook.com','826cca0eea8a606c4c24a06880e84e6a',102826673.60000001,2030926.4),('what','20191231181849','18871206897','女','1998-02-07','18371206897@163.com','826cca0eea8a606c4c24a06880e84e6a',104857600,0),('佳佳同学','20200102121715','18888888888','男','1999-02-28','1285511377@qq.com','826cca0eea8a606c4c24a06880e84e6a',104857600,0);

UNLOCK TABLES;

/*Table structure for table `userfiles` */

DROP TABLE IF EXISTS `userfiles`;

CREATE TABLE `userfiles` (
  `fileid` int(11) NOT NULL AUTO_INCREMENT,
  `userNum` char(20) DEFAULT NULL,
  `fileName` varchar(1000) NOT NULL,
  `fileType` char(20) DEFAULT NULL,
  `fileSize` double DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `fileFolderName` char(30) DEFAULT NULL,
  `fileLocalPath` varchar(1000) DEFAULT NULL,
  `filePath` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`fileid`,`fileName`),
  KEY `userNum` (`userNum`),
  CONSTRAINT `userfiles_ibfk_1` FOREIGN KEY (`userNum`) REFERENCES `t_user` (`userNum`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8;

/*Data for the table `userfiles` */

LOCK TABLES `userfiles` WRITE;

insert  into `userfiles`(`fileid`,`userNum`,`fileName`,`fileType`,`fileSize`,`updateTime`,`fileFolderName`,`fileLocalPath`,`filePath`) values (158,'20191221154101','海扁王2_BD中英双字.mp4','recycleBin',1219304.3,'2020-01-04 08:15:57','root','H:\\NetDiskFile\\NetDiskFile着迷\\海扁王2_BD中英双字.mp4',NULL),(159,'20191221154101','codes.rar','recycleBin',614645.3,'2020-01-04 08:16:28','root','H:\\NetDiskFile\\NetDiskFile着迷\\codes.rar',NULL),(160,'20191221154101','物理数据处理冰的溶解热1.xlsx','recycleBin',22.1,'2020-01-04 10:57:33','root','H:\\NetDiskFile\\NetDiskFile着迷\\物理数据处理冰的溶解热1.xlsx',NULL),(161,'20191221154101','数据结构复习题.docx','recycleBin',783.3,'2020-01-04 10:57:53','root','H:\\NetDiskFile\\NetDiskFile着迷\\数据结构复习题.docx',NULL),(162,'20191221154101','codes.rar','recycleBin',614645.3,'2020-01-04 10:58:03','root','H:\\NetDiskFile\\NetDiskFile着迷\\codes.rar',NULL),(163,'20191221154101','小l莉的h神大s.mp4','recycleBin',2163816.2,'2020-01-04 10:58:34','root','H:\\NetDiskFile\\NetDiskFile着迷\\小l莉的h神大s.mp4',NULL),(164,'20191221154101','数据结构复习题.docx','recycleBin',783.3,'2020-01-04 11:24:31','root','H:\\NetDiskFile\\NetDiskFile着迷\\数据结构复习题.docx',NULL),(165,'20191221154101','[迅雷下载www.2tu.cc]不一样的爸爸.BD1280高清中英双字.rmvb','video',1759396.1,'2020-01-04 11:26:22','root','H:\\NetDiskFile\\NetDiskFile着迷\\[迅雷下载www.2tu.cc]不一样的爸爸.BD1280高清中英双字.rmvb',NULL),(166,'20191223100537','物理数据处理冰的溶解热1.xlsx','document',22.1,'2020-01-04 11:34:57','root','H:\\NetDiskFile\\NetDiskFile李斯\\物理数据处理冰的溶解热1.xlsx',NULL),(167,'20191223100537','codes.rar','recycleBin',614645.3,'2020-01-04 11:35:14','root','H:\\NetDiskFile\\NetDiskFile李斯\\codes.rar',NULL),(168,'20191223100537','神mj星.mp4','video',2030904.3,'2020-01-04 11:36:44','root','H:\\NetDiskFile\\NetDiskFile李斯\\神mj星.mp4',NULL);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
