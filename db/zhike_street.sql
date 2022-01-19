/*
SQLyog Ultimate v11.24 (64 bit)
MySQL - 5.5.58 : Database - zhike_street
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `zhike_street`;

/*Table structure for table `ad` */

DROP TABLE IF EXISTS `ad`;

CREATE TABLE `ad` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `LinkUrl` varchar(50) DEFAULT NULL COMMENT '广告链接地址',
  `AdCode ` varchar(50) DEFAULT NULL COMMENT '广告链接的表现,文字广告就是文字或图片和flash就是它们的地址',
  `BeginTime` datetime DEFAULT NULL COMMENT '广告开始时间',
  `EndTime` datetime DEFAULT NULL COMMENT '广告结束时间',
  `Contactor` varchar(20) DEFAULT NULL COMMENT '广告联系人',
  `Email ` varchar(20) DEFAULT NULL COMMENT '广告联系人邮箱',
  `Phone` varchar(20) DEFAULT NULL COMMENT '广告联系人电话',
  `TelePhone` varchar(20) DEFAULT NULL COMMENT '广告联系人电话',
  `ClickNum` int(5) NOT NULL DEFAULT '0' COMMENT '广告点击次数',
  `Enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '该广告是否关闭;1开启; 0关闭; 关闭后广告将不再有效',
  `Description` varchar(200) DEFAULT NULL,
  `CreatTime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `adminuser` */

DROP TABLE IF EXISTS `adminuser`;

CREATE TABLE `adminuser` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Account` varchar(20) DEFAULT NULL COMMENT '账户',
  `PasswordSalt` varchar(50) DEFAULT NULL COMMENT '密码',
  `PhoneValideCode` varchar(20) DEFAULT NULL COMMENT '验证码',
  `Phone` varchar(20) DEFAULT NULL COMMENT '移动号码',
  `QQ` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `Email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `NickName` varchar(50) CHARACTER SET utf8 DEFAULT '' COMMENT '昵称',
  `RealName` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '真名',
  `Avatar` varchar(50) DEFAULT NULL COMMENT '头像',
  `RegIp` varchar(25) DEFAULT NULL COMMENT '注册IP',
  `IsUse` tinyint(1) DEFAULT '0' COMMENT '是否冻结 ''是否冻结，0为不冻结，1为冻结''',
  `Description` varchar(50) DEFAULT NULL COMMENT '描述',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  `ModifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ArticleTypeId` bigint(20) NOT NULL,
  `ArticleTypeName` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '文章类型名称',
  `UserId` bigint(20) DEFAULT NULL COMMENT '所属用户ID',
  `TagIds` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '标签,多个以逗号分隔',
  `Tags` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '标签,多个以逗号分隔',
  `Title` varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '标题',
  `LinkUrl` varchar(150) CHARACTER SET latin1 DEFAULT NULL COMMENT '链接',
  `Cover` varchar(100) CHARACTER SET latin1 NOT NULL DEFAULT '' COMMENT '封面',
  `Navigation` text CHARACTER SET utf8 NOT NULL COMMENT '导航',
  `Summary` text CHARACTER SET utf8 COMMENT '摘要',
  `Content` text CHARACTER SET utf8 NOT NULL,
  `IsUp` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否置顶:0为否，1为是',
  `IsRecommend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否编辑推荐:0为否，1为是',
  `IsHot` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否热点:0=否,1=是',
  `OpenState` int(11) NOT NULL DEFAULT '0' COMMENT '开放状态：0=公开 1=会员可看 2=付费可看',
  `ScanNum` int(5) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `LikeNum` int(5) NOT NULL DEFAULT '0',
  `CommentNum` int(5) NOT NULL DEFAULT '0',
  `ForwardNum` int(5) NOT NULL DEFAULT '0',
  `IsSoftDelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否软删除',
  `Description` varchar(50) CHARACTER SET utf8 NOT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=gbk;

/*Table structure for table `article_access` */

DROP TABLE IF EXISTS `article_access`;

CREATE TABLE `article_access` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ArticleId` int(11) DEFAULT NULL COMMENT '文章Id',
  `UserId` int(11) DEFAULT '0' COMMENT '访客Id',
  `Action` int(2) DEFAULT '0' COMMENT '1=阅读2=点赞',
  `IP` varchar(25) DEFAULT '' COMMENT '访客IP',
  `CreateTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6370 DEFAULT CHARSET=utf8;

/*Table structure for table `article_type` */

DROP TABLE IF EXISTS `article_type`;

CREATE TABLE `article_type` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ParentId` int(11) NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `Description` varchar(25) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Table structure for table `carousel` */

DROP TABLE IF EXISTS `carousel`;

CREATE TABLE `carousel` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `Link` varchar(100) DEFAULT NULL,
  `Cover` varchar(100) DEFAULT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT '0' COMMENT '软删除',
  `IsActive` tinyint(1) DEFAULT '0' COMMENT '设置active',
  `carousel_item_class` varchar(20) DEFAULT NULL COMMENT 'carousel-item class',
  `Createtime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `Id` int(11) NOT NULL,
  `UserId` int(11) DEFAULT NULL COMMENT '评论者Id',
  `ArticleId` int(11) DEFAULT NULL COMMENT '评论文章Id',
  `Content` varchar(200) NOT NULL DEFAULT '' COMMENT '评论内容',
  `IP` varchar(20) DEFAULT '' COMMENT '评论时的IP',
  `CreateTime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `field` */

DROP TABLE IF EXISTS `field`;

CREATE TABLE `field` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `IsSoftDelete` tinyint(1) NOT NULL DEFAULT '0',
  `Description` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `CreatTime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Table structure for table `key_manager` */

DROP TABLE IF EXISTS `key_manager`;

CREATE TABLE `key_manager` (
  `Key` varchar(50) NOT NULL,
  `Value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Key`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Table structure for table `my_favorites` */

DROP TABLE IF EXISTS `my_favorites`;

CREATE TABLE `my_favorites` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) DEFAULT NULL COMMENT '用户Id',
  `FieldId` int(2) DEFAULT NULL COMMENT '领域',
  `CreatTime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `my_scan_article` */

DROP TABLE IF EXISTS `my_scan_article`;

CREATE TABLE `my_scan_article` (
  `Id` int(11) NOT NULL,
  `ArticleId` int(11) DEFAULT NULL COMMENT '文章ID',
  `IsLike` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否点赞',
  `IsForward` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否转发',
  `IsComment` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否评论',
  `CreateTime` datetime DEFAULT NULL,
  `ModdifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) DEFAULT NULL,
  `ProductType` tinyint(2) NOT NULL COMMENT '商品类型',
  `CreateTime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Table structure for table `record_log` */

DROP TABLE IF EXISTS `record_log`;

CREATE TABLE `record_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fun_module` varchar(50) NOT NULL DEFAULT '' COMMENT '功能模块',
  `fun_method` varchar(200) DEFAULT NULL COMMENT '方法名称',
  `fun_params` varchar(500) DEFAULT NULL COMMENT '入参',
  `ip` varchar(30) DEFAULT '' COMMENT ' IP地址',
  `cost_time` bigint(20) DEFAULT NULL COMMENT '花费时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `FieldId` int(2) DEFAULT NULL COMMENT '0=文章 1=任务 2=招聘 3=创意 4=书评',
  `Name` varchar(20) DEFAULT NULL,
  `IsSoftDelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否软删除',
  `Description` varchar(50) DEFAULT NULL,
  `CreateTime` datetime DEFAULT NULL,
  `ModifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Table structure for table `task` */

DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `TagIds` varchar(20) CHARACTER SET latin1 DEFAULT NULL COMMENT '标签Id,多个逗号分隔',
  `Tags` varchar(100) CHARACTER SET latin1 DEFAULT NULL COMMENT '标签 逗号分隔',
  `Title` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT '任务名称',
  `LinkUrl` varchar(50) CHARACTER SET latin1 DEFAULT NULL COMMENT '原文链接',
  `MessageTypeId` int(2) DEFAULT NULL COMMENT '消息类型ID',
  `Content` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `IsSoftDelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否软删除',
  `IsDelay` tinyint(1) NOT NULL COMMENT '是否已过期',
  `Description` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `CreatTime` datetime DEFAULT NULL COMMENT '创建时间',
  `ModifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
