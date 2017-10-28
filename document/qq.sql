/*
Navicat MySQL Data Transfer

Source Server         : Zachary
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : qq

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-10-26 19:36:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `qqcode` int(11) NOT NULL,
  `nickname` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `sex` char(4) NOT NULL,
  `star` varchar(20) NOT NULL,
  `blood` varchar(10) DEFAULT NULL,
  `nation` varchar(30) DEFAULT NULL,
  `hobbit` varchar(100) DEFAULT NULL,
  `addr` varchar(20) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `onlinestatu` varchar(4) DEFAULT NULL,
  `headImage` varchar(50) NOT NULL,
  `birthyear` varchar(20) DEFAULT NULL,
  `birthmonth` varchar(20) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `birthcountry` varchar(20) DEFAULT NULL,
  `birthprovince` varchar(20) DEFAULT NULL,
  `birthcity` varchar(20) DEFAULT NULL,
  `birthcounty` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1709398555', '豆芽', '12345678', '帅哥', '射手座', 'A', '汉族', '游戏\n', '192.168.3.25', '50533', '我很帅哦', '离线', 'HeadImg/0.png', '1996', '1', '1', '中国', '江西', '赣州', '崇义');
INSERT INTO `account` VALUES ('1654875700', 'Zachary', '12345678', '帅哥', '射手座', 'A', '汉族', '游戏\n', '192.168.43.45', '58929', '爱你一万年', '离线', 'HeadImg/0.png', '1996', '1', '1', '中国', '江西', '赣州', '崇义');
INSERT INTO `account` VALUES ('1601542756', '墨章', '12345678', '帅哥', '射手座', 'A', '汉族', '游戏\n', '192.168.3.25', '45147', '爱你一万年', '离线', 'HeadImg/0.png', '1996', '1', '1', '中国', '江西', '赣州', '崇义');
INSERT INTO `account` VALUES ('1179144677', 'Tom', '12345678', '帅哥', '射手座', 'A', '汉族', '游戏\n', '192.168.3.25', '10636', '爱你一万年', '离线', 'HeadImg/0.png', '1996', '1', '1', '中国', '江西', '赣州', '崇义');

-- ----------------------------
-- Table structure for friends
-- ----------------------------
DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `friendId` int(11) NOT NULL AUTO_INCREMENT,
  `myQQcode` int(11) NOT NULL,
  `friendQQcode` int(11) NOT NULL,
  `groupName` varchar(20) NOT NULL,
  PRIMARY KEY (`friendId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friends
-- ----------------------------
INSERT INTO `friends` VALUES ('5', '1601542756', '1709398555', '好友');
INSERT INTO `friends` VALUES ('6', '1709398555', '1601542756', '同学');
INSERT INTO `friends` VALUES ('7', '1601542756', '1179144677', '好友');
INSERT INTO `friends` VALUES ('8', '1179144677', '1601542756', '好友');

-- ----------------------------
-- Table structure for offlinemsg
-- ----------------------------
DROP TABLE IF EXISTS `offlinemsg`;
CREATE TABLE `offlinemsg` (
  `msgId` int(11) NOT NULL AUTO_INCREMENT,
  `myQQcode` int(11) NOT NULL,
  `friendQQcode` int(11) NOT NULL,
  `cmd` int(11) NOT NULL,
  `msg` text NOT NULL,
  PRIMARY KEY (`msgId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of offlinemsg
-- ----------------------------
