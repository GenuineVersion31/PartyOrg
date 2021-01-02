/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : PartyOrg

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 09/09/2020 20:03:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_user
-- ----------------------------
DROP TABLE IF EXISTS `file_user`;
CREATE TABLE `file_user` (
  `id` int(11) NOT NULL,
  `file_name` varchar(100) NOT NULL,
  `path` varchar(100) NOT NULL,
  `uid` varchar(16) NOT NULL,
  `dsc` varchar(255) NOT NULL,
  `username` varchar(16) NOT NULL,
  `wait` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of file_user
-- ----------------------------
BEGIN;
INSERT INTO `file_user` VALUES (19, '李若阳 .xls', '/Users/test/', '5', 'cash', 'user', 1);
INSERT INTO `file_user` VALUES (20, '附件2：地区代码.XLSX', '/Users/test/', '5', '123', 'user', 0);
COMMIT;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
BEGIN;
INSERT INTO `hibernate_sequence` VALUES (26);
COMMIT;

-- ----------------------------
-- Table structure for information
-- ----------------------------
DROP TABLE IF EXISTS `information`;
CREATE TABLE `information` (
  `id` int(11) NOT NULL,
  `identificaion_number` varchar(30) DEFAULT '',
  `major` varchar(20) DEFAULT '',
  `name` varchar(10) DEFAULT '',
  `password` varchar(16) NOT NULL DEFAULT '',
  `race` varchar(10) DEFAULT '',
  `username` varchar(16) NOT NULL DEFAULT '',
  `classnumber` varchar(18) DEFAULT '',
  `roles` varchar(10) DEFAULT '',
  `gender` varchar(2) DEFAULT '',
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `password` (`password`),
  KEY `name` (`name`),
  KEY `identificaion_number` (`identificaion_number`),
  KEY `major` (`major`),
  KEY `classnumber` (`classnumber`),
  KEY `race` (`race`),
  KEY `roles` (`roles`),
  KEY `birthday` (`birthday`),
  KEY `id` (`id`,`username`,`password`,`roles`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of information
-- ----------------------------
BEGIN;
INSERT INTO `information` VALUES (4, 'xxxxxxxxxxxxxxxxxxxx', '计科171', '李若阳', 'admin', '土家族', 'admin', '17102040112', 'ADMIN', '男', '1998-12-14');
INSERT INTO `information` VALUES (5, 'zzzzzzzzzzzzzzzzzz', '电商161', '王小明', 'pass', '汉族', 'user', '16102040112', 'ROLE', '女', '1997-11-11');
COMMIT;

-- ----------------------------
-- Table structure for party_info
-- ----------------------------
DROP TABLE IF EXISTS `party_info`;
CREATE TABLE `party_info` (
  `id` int(11) NOT NULL,
  `birthday` date DEFAULT NULL,
  `in_party` int(1) DEFAULT '0',
  `wait` int(1) DEFAULT '0',
  `name` varchar(10) DEFAULT '',
  `classnumber` varchar(18) DEFAULT '',
  `gender` varchar(2) DEFAULT '',
  `identificaion_number` varchar(30) DEFAULT '',
  `major` varchar(20) DEFAULT '',
  `race` varchar(10) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `pname` (`name`),
  KEY `idcard` (`identificaion_number`),
  KEY `major` (`major`),
  KEY `classnumber` (`classnumber`),
  KEY `race` (`race`),
  KEY `birthday` (`birthday`),
  CONSTRAINT `birthday` FOREIGN KEY (`birthday`) REFERENCES `information` (`birthday`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `classnumber` FOREIGN KEY (`classnumber`) REFERENCES `information` (`classnumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idcard` FOREIGN KEY (`identificaion_number`) REFERENCES `information` (`identificaion_number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `major` FOREIGN KEY (`major`) REFERENCES `information` (`major`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pid` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pname` FOREIGN KEY (`name`) REFERENCES `information` (`name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `race` FOREIGN KEY (`race`) REFERENCES `information` (`race`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of party_info
-- ----------------------------
BEGIN;
INSERT INTO `party_info` VALUES (4, '1998-12-14', 1, 0, '李若阳', '17102040112', '男', 'xxxxxxxxxxxxxxxxxxxx', '计科171', '土家族');
INSERT INTO `party_info` VALUES (5, '1997-11-11', 0, 1, '王小明', '16102040112', '女', 'zzzzzzzzzzzzzzzzzz', '电商161', '汉族');
COMMIT;

-- ----------------------------
-- Table structure for porter
-- ----------------------------
DROP TABLE IF EXISTS `porter`;
CREATE TABLE `porter` (
  `id` int(11) NOT NULL,
  `create_time` varchar(40) NOT NULL,
  `porter` varchar(255) NOT NULL,
  `uid` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of porter
-- ----------------------------
BEGIN;
INSERT INTO `porter` VALUES (23, '2020-09-09 16:03:45', '成功吗', 4, '测试', 0);
INSERT INTO `porter` VALUES (24, '2020-09-09 16:04:13', '能否循环', 4, '再来一次', 0);
INSERT INTO `porter` VALUES (25, '2020-09-09 16:04:31', '起飞！！！', 4, '成功！！', 0);
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `password` varchar(16) NOT NULL DEFAULT '',
  `roles` varchar(10) NOT NULL DEFAULT '',
  `username` varchar(16) NOT NULL DEFAULT '',
  `active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `password` (`password`),
  KEY `roles` (`roles`),
  KEY `id` (`id`,`username`,`password`,`roles`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES (4, 'admin', 'ADMIN', 'admin', b'1');
INSERT INTO `users` VALUES (5, 'pass', 'ROLE', 'user', b'1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
