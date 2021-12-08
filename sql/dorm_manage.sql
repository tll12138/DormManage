/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.54 : Database - dorm_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dorm_manage` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dorm_manage`;

/*Table structure for table `beds_manager` */

DROP TABLE IF EXISTS `beds_manager`;

CREATE TABLE `beds_manager` (
  `dorm_id` int(8) NOT NULL,
  `bedA` varchar(64) NOT NULL DEFAULT '',
  `bedB` varchar(64) NOT NULL DEFAULT '',
  `bedC` varchar(64) NOT NULL DEFAULT '',
  `bedD` varchar(64) NOT NULL DEFAULT '',
  PRIMARY KEY (`dorm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `beds_manager` */

insert  into `beds_manager`(`dorm_id`,`bedA`,`bedB`,`bedC`,`bedD`) values (1,'ddd','','tll',''),(2,'','','','汤'),(4,'','','',''),(6,'小灰','小蓝','小绿','小白'),(7,'富强','民主','文明','和谐'),(8,'','','','');

/*Table structure for table `build_manage` */

DROP TABLE IF EXISTS `build_manage`;

CREATE TABLE `build_manage` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `building_no` varchar(64) NOT NULL DEFAULT '',
  `dorm_manager_id` int(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `build_manage` */

insert  into `build_manage`(`id`,`building_no`,`dorm_manager_id`) values (1,'1',1),(2,'2',2),(3,'3',1);

/*Table structure for table `dorm` */

DROP TABLE IF EXISTS `dorm`;

CREATE TABLE `dorm` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `building_no` varchar(64) NOT NULL DEFAULT '',
  `dormitory_no` int(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `dorm` */

insert  into `dorm`(`id`,`building_no`,`dormitory_no`) values (1,'2',319),(2,'2',333),(4,'1',319),(6,'1',111),(7,'3',311),(8,'2',123);

/*Table structure for table `dorm_maintenance` */

DROP TABLE IF EXISTS `dorm_maintenance`;

CREATE TABLE `dorm_maintenance` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `dorm_id` int(8) NOT NULL DEFAULT '0',
  `matter` varchar(256) NOT NULL DEFAULT '',
  `state` varchar(64) NOT NULL DEFAULT '未完成' COMMENT '未完成/已完成',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `dorm_maintenance` */

insert  into `dorm_maintenance`(`id`,`dorm_id`,`matter`,`state`) values (1,1,'1','完成'),(2,1,'灯坏了','未完成');

/*Table structure for table `dorm_manager` */

DROP TABLE IF EXISTS `dorm_manager`;

CREATE TABLE `dorm_manager` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '6位',
  `password` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(64) NOT NULL DEFAULT '',
  `building_no` varchar(64) NOT NULL DEFAULT '',
  `permission` int(2) NOT NULL DEFAULT '1' COMMENT '0维修员1寝室管理员2后勤管理员，其他学生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `dorm_manager` */

insert  into `dorm_manager`(`id`,`username`,`password`,`name`,`building_no`,`permission`) values (1,'100001','NjY2NjY2','宿管1','1',1),(2,'100002','NjY2NjY2','宿管2','2',1),(3,'100003','NjY2NjY2','宿管3','3',1);

/*Table structure for table `logistics_manager` */

DROP TABLE IF EXISTS `logistics_manager`;

CREATE TABLE `logistics_manager` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '5位',
  `password` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(64) NOT NULL DEFAULT '',
  `permission` int(2) NOT NULL DEFAULT '2' COMMENT '0维修员1寝室管理员2后勤管理员，其他学生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `logistics_manager` */

insert  into `logistics_manager`(`id`,`username`,`password`,`name`,`permission`) values (1,'admin','YWRtaW4=','管理员',2);

/*Table structure for table `outsider` */

DROP TABLE IF EXISTS `outsider`;

CREATE TABLE `outsider` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `building_no` varchar(64) NOT NULL DEFAULT '',
  `dorm_no` int(8) NOT NULL DEFAULT '-1',
  `matter` varchar(256) NOT NULL DEFAULT '',
  `access_time` date NOT NULL DEFAULT '0000-01-01' COMMENT '来访时间',
  `handler` varchar(32) NOT NULL DEFAULT '' COMMENT '处理人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `outsider` */

insert  into `outsider`(`id`,`name`,`building_no`,`dorm_no`,`matter`,`access_time`,`handler`) values (1,'急急急','2',319,'随便看看','2021-12-08','admin'),(2,'噜噜噜','2',311,'哇啊啊啊啊','2021-12-08','admin');

/*Table structure for table `service_man` */

DROP TABLE IF EXISTS `service_man`;

CREATE TABLE `service_man` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '4位数',
  `password` varchar(128) NOT NULL DEFAULT '',
  `name` varchar(64) NOT NULL DEFAULT '',
  `permission` int(2) NOT NULL DEFAULT '0' COMMENT '0维修员1寝室管理员2后勤管理员，其他学生',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `service_man` */

insert  into `service_man`(`id`,`username`,`password`,`name`,`permission`) values (2,'1002','NjY2NjY2','老王',0),(3,'1003','NjY2NjY2','老张',0);

/*Table structure for table `student_info` */

DROP TABLE IF EXISTS `student_info`;

CREATE TABLE `student_info` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `stu_id` varchar(32) NOT NULL DEFAULT '' COMMENT '学号，10位数',
  `name` varchar(64) NOT NULL DEFAULT '',
  `building_no` varchar(64) NOT NULL DEFAULT '' COMMENT '宿舍楼',
  `dormitory_no` int(8) NOT NULL DEFAULT '0' COMMENT '寝室号',
  `bed_no` varchar(8) NOT NULL DEFAULT '' COMMENT '床号',
  `check_in_time` date NOT NULL DEFAULT '0000-01-01' COMMENT '入住时间',
  `check_out_time` date NOT NULL DEFAULT '0000-01-01' COMMENT '退宿时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `student_info` */

insert  into `student_info`(`id`,`stu_id`,`name`,`building_no`,`dormitory_no`,`bed_no`,`check_in_time`,`check_out_time`) values (1,'203233Y133','tll','2',319,'C','2020-09-01','2022-06-15'),(2,'203233Y130','ddd','1',123,'D','2021-11-09','2021-12-18'),(3,'203233Y131','汤','1',111,'C','2021-10-28','2021-12-05');

/*Table structure for table `student_password` */

DROP TABLE IF EXISTS `student_password`;

CREATE TABLE `student_password` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL DEFAULT 'NjY2NjY2' COMMENT '默认密码666666',
  `stu_info_id` int(8) NOT NULL DEFAULT '0' COMMENT '对应学生信息表id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `student_password` */

insert  into `student_password`(`id`,`password`,`stu_info_id`) values (1,'MTIzNDU2',1),(2,'NjY2NjY2',2),(3,'NjY2NjY2',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
