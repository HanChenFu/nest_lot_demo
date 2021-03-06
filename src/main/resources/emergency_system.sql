/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : emergency_system

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 13/12/2019 17:44:44
*/
CREATE DATABASE `emergency_system` CHARACTER SET utf8 COLLATE utf8_general_ci;

use emergency_system;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_role_id` int(11) NULL DEFAULT NULL COMMENT '权限',
  `tb_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '帐号',
  `tb_phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `tb_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `tb_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `tb_head_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像路径',
  `tb_email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime NULL DEFAULT NULL,
  `tb_uuid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'UUID',
  PRIMARY KEY (`tb_id`) USING BTREE,
  INDEX `tb_role_id`(`tb_role_id`) USING BTREE,
  CONSTRAINT `tb_admin_ibfk_1` FOREIGN KEY (`tb_role_id`) REFERENCES `tb_role` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES (1, 1, '思曼', '18122711575', 'E10ADC3949BA59ABBE56E057F20F883E', '思曼', './head/sm.jpg', NULL, NULL, '2019-12-11 11:58:08', NULL);
INSERT INTO `tb_admin` VALUES (2, 2, '李云', '18122711575', 'E10ADC3949BA59ABBE56E057F20F883E', '李云', './head/ly.jpg', NULL, NULL, '2019-12-11 11:59:29', NULL);

-- ----------------------------
-- Table structure for tb_area_dynamics
-- ----------------------------
DROP TABLE IF EXISTS `tb_area_dynamics`;
CREATE TABLE `tb_area_dynamics`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `tb_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `tb_figure_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `tb_html_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地区动态表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_area_dynamics
-- ----------------------------
INSERT INTO `tb_area_dynamics` VALUES (1, '“滥用职权”“妨碍国会”', '美国众议院司法委员会主席10日宣布，打算以两项罪名弹劾总统唐纳德·特朗普。\r\n\r\n司法委员会主席、民主党籍议员杰罗尔德·纳德勒在国会说，“司法委员会宣布两项弹劾条款”，分别是“滥用职权”和“妨碍国会”。\r\n\r\n他说，民主党议员占多数的司法委员会本周内将讨论这两项罪名，继而交给全体众议员讨论表决。\r\n\r\n特朗普在社交媒体“推特”回应：“荒谬。他（纳德勒）知道那不是真的。', NULL, NULL, '2019-12-11 12:01:44', 'https://3w.huanqiu.com/a/c36dc8/9CaKrnKogtf?agt=8');
INSERT INTO `tb_area_dynamics` VALUES (2, '伊朗外交部警告公民：别去美国，有被捕风险', '海外网12月11日电 当地时间10日，伊朗警告公民，不要前往美国，有被捕风险。\r\n\r\n据路透社报道，伊朗外交部在其官方网站发布旅游警告，“伊朗公民，特别是精英和科学家，被要求勿前往美国，即便是参加科学会议或者是受邀参加其他活动。”\r\n\r\n其内文中也解释了原因，即“美国对伊朗人，特别是对伊朗精英实施残酷和单方面的法律，在完全不人道的情况下，任意地长期将其拘留。”', NULL, NULL, '2019-12-11 12:01:56', 'https://3w.huanqiu.com/a/c36dc8/9CaKrnKogtf?agt=8');
INSERT INTO `tb_area_dynamics` VALUES (3, '私藏枪弹！贵州六盘水一公安分局原政委被双开', '日前，经六盘水市委批准，六盘水市纪委监委对六盘水市公安局水城经济开发区分局原政委漆龙严重违纪违法问题进行了立案审查调查。\r\n\r\n经查，漆龙违反组织纪律，不如实报告个人有关事项；违反廉洁纪律，违规放贷并获取巨额利息；违反生活纪律；违反国家法律法规，私藏手枪子弹；利用职务便利，为他人谋取利益，索取、收受他人财物，涉嫌受贿犯罪。', NULL, NULL, '2019-12-11 12:02:05', 'https://3w.huanqiu.com/a/c36dc8/9CaKrnKogtf?agt=8');
INSERT INTO `tb_area_dynamics` VALUES (4, '香港教育局要对纵暴教师出手', '秦汉堂公司的工作人员仅仅看了照片就给出了800万的估价，这让周女士欣喜若狂。于是，她立即赶到了这家位于杨浦区五角场镇上的拍卖公司。接待周女士的是一名自称王经理的年轻女子。\r\n\r\n受害人 周女士：她自己拿手里看，她说你这东西，我拿在手里手都在抖，太真了，太好了。', NULL, NULL, '2019-12-11 12:05:19', 'https://3w.huanqiu.com/a/c36dc8/9CaKrnKogtf?agt=8');

-- ----------------------------
-- Table structure for tb_ask_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_ask_record`;
CREATE TABLE `tb_ask_record`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_admin_id` int(11) NULL DEFAULT NULL,
  `tb_user_id` int(11) NULL DEFAULT NULL,
  `tb_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件编号',
  `tb_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `tb_enclosure` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
  `tb_history` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '历史文件',
  `tb_draft` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '草稿箱',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `tb_desc` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`tb_id`) USING BTREE,
  INDEX `tb_user_id`(`tb_user_id`) USING BTREE,
  CONSTRAINT `tb_ask_record_ibfk_1` FOREIGN KEY (`tb_user_id`) REFERENCES `tb_user` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_ask_record_ibfk_2` FOREIGN KEY (`tb_admin_id`) REFERENCES `tb_admin` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '询问笔录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_ask_record
-- ----------------------------
INSERT INTO `tb_ask_record` VALUES (1, 1,1, 'DC987654345', 'xx通话记录', NULL, NULL, NULL, NULL, NULL,'2019-12-11 14:06:36', NULL);

-- ----------------------------
-- Table structure for tb_call_center
-- ----------------------------
DROP TABLE IF EXISTS `tb_call_center`;
CREATE TABLE `tb_call_center`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_user_id` int(11) NULL DEFAULT NULL,
  `tb_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号(档案号)',
  `tb_type` int(11) NULL DEFAULT NULL COMMENT '呼叫类型(1 表示呼入 2表示呼出)',
  `tb_handle_type` int(3) NULL DEFAULT NULL COMMENT '处理结果即分类 （0 表示未分类 1表示维修）',
  `tb_state` int(3) NULL DEFAULT NULL COMMENT '接听状态(0 表示无法接通 1表示接通)',
  `tb_duration` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通话时长',
  `sound_record_file` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '录音文件路径',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  INDEX `tb_user_id`(`tb_user_id`) USING BTREE,
  CONSTRAINT `tb_call_center_ibfk_1` FOREIGN KEY (`tb_user_id`) REFERENCES `tb_user` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '呼叫中心表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_call_center
-- ----------------------------
INSERT INTO `tb_call_center` VALUES (1, 1, 'DC1716151541', 1, 1, 1, '2分30秒', './file/hh.mp3', NULL, '2019-12-11 14:08:47');

-- ----------------------------
-- Table structure for tb_case
-- ----------------------------
DROP TABLE IF EXISTS `tb_case`;
CREATE TABLE `tb_case`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号(档案号)',
  `tb_user_id` int(11) NULL DEFAULT NULL,
  `tb_case_type_id` int(11) NULL DEFAULT NULL,
  `tb_filing_area_id` int(11) NULL DEFAULT NULL,
  `tb_size` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '事件大小',
  `tb_star` int(11) NULL DEFAULT NULL COMMENT '关注星级',
  `tb_report_address` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '案件地址',
  `tb_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '案件地址(详细)',
  `tb_longitude` decimal(10, 7) NULL DEFAULT NULL COMMENT '经度',
  `tb_latitude` decimal(10, 7) NULL DEFAULT NULL COMMENT '纬度',
  `tb_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '案件经过',
  `tb_remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '案件备注',
  `tb_images` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关键图片',
  `case_time` datetime NULL DEFAULT NULL COMMENT '案件发生时间',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  INDEX `tb_case_type_id`(`tb_case_type_id`) USING BTREE,
  INDEX `tb_filing_area_id`(`tb_filing_area_id`) USING BTREE,
  INDEX `tb_user_id`(`tb_user_id`) USING BTREE,
  CONSTRAINT `tb_case_ibfk_1` FOREIGN KEY (`tb_case_type_id`) REFERENCES `tb_case_type` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_case_ibfk_2` FOREIGN KEY (`tb_filing_area_id`) REFERENCES `tb_filing_area` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_case_ibfk_3` FOREIGN KEY (`tb_user_id`) REFERENCES `tb_user` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '案件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_case
-- ----------------------------
INSERT INTO `tb_case`(`tb_number`, `tb_user_id`, `tb_case_type_id`, `tb_filing_area_id`, `tb_size`, `tb_star`, `tb_report_address`, `tb_address`, `tb_longitude`, `tb_latitude`, `tb_desc`, `tb_remarks`, `tb_images`, `case_time`, `del_time`, `create_time`) VALUES ('DC12345678', 1, 1, 1, '4', 3, '深圳市马田爱神的箭撒谎2222', '深圳市马田爱神的箭撒谎一首歌广寒深宫222', 114.0933880, 22.5304689, '帕库拉时间冻结哈市222', '仅仅是计算机222222', 'case_pic/20191226/11aa21137a314cdfaceb5ee7aebde7ce.jpg', '2019-12-27 11:55:27', NULL, '2019-12-11 13:45:13');

-- ----------------------------
-- Table structure for tb_case_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_case_type`;
CREATE TABLE `tb_case_type`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '案件名称',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '案件类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_case_type
-- ----------------------------
INSERT INTO `tb_case_type` VALUES (1, '刑事案件', NULL, '2019-12-10 14:04:46');
INSERT INTO `tb_case_type` VALUES (2, '森林火灾', NULL, '2019-12-10 14:05:57');
INSERT INTO `tb_case_type` VALUES (3, '普通案件', NULL, '2019-12-10 14:06:13');
INSERT INTO `tb_case_type` VALUES (4, '安全生产', NULL, '2019-12-10 14:06:27');

-- ----------------------------
-- Table structure for tb_emergency_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_emergency_news`;
CREATE TABLE `tb_emergency_news`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `tb_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `tb_figure_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `tb_html_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '应急要闻表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_emergency_news
-- ----------------------------
INSERT INTO `tb_emergency_news` VALUES (1, '女子陷入鉴宝骗局:10块钱金币“专家”估价1100万', '通过网络，周女士找到了上海一家叫“秦汉堂”的拍卖公司。根据网上介绍，这家公司业务遍布海内外。\r\n\r\n\r\n受害人 周女士：宣传特别好，拍卖资格都有，他们老板特别有背景，就是能找到特别特别有钱的人，说百分之百能给卖出去。', NULL, NULL, '2019-12-11 12:07:15', 'https://3w.huanqiu.com/a/98a920/9CaKrnKogrO?agt=8');

-- ----------------------------
-- Table structure for tb_filing_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_filing_area`;
CREATE TABLE `tb_filing_area`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归档所在地名称',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '归档所在地表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_filing_area
-- ----------------------------
INSERT INTO `tb_filing_area` VALUES (1, '马石警局', NULL, '2019-12-11 11:55:05');
INSERT INTO `tb_filing_area` VALUES (2, '龙华警局', NULL, '2019-12-11 11:55:26');
INSERT INTO `tb_filing_area` VALUES (3, '龙岗警局', NULL, '2019-12-11 11:55:42');
INSERT INTO `tb_filing_area` VALUES (4, '南山警局', NULL, '2019-12-11 13:34:04');

-- ----------------------------
-- Table structure for tb_letter
-- ----------------------------
DROP TABLE IF EXISTS `tb_letter`;

CREATE TABLE `tb_letter`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_admin_id` int(11) NULL DEFAULT NULL,
  `tb_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号(档案号)',
  `tb_user_id` int(11) NULL DEFAULT NULL,
  `target` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标用户邮箱',
  `tb_send_mess_id` int(11) NULL DEFAULT NULL COMMENT '发送内容表id',
  `sending_state` int(11) NULL DEFAULT 0 COMMENT '0表示未发送 1表示发送失败 2表示已经成功',
  `appendix_title` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件标题',
  `appendix_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送邮件的附件',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE, 
  INDEX `tb_user_id`(`tb_user_id`) USING BTREE,
  INDEX `tb_letter_ibfk_3`(`tb_send_mess_id`) USING BTREE,
  CONSTRAINT `tb_letter_ibfk_1` FOREIGN KEY (`tb_user_id`) REFERENCES `tb_user` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_letter_ibfk_3` FOREIGN KEY (`tb_send_mess_id`) REFERENCES `tb_send_mess` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_letter_ibfk_4` FOREIGN KEY (`tb_admin_id`) REFERENCES `tb_admin` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_letter
-- ----------------------------
INSERT INTO `tb_letter`(`tb_id`, `tb_admin_id`, `tb_number`, `tb_user_id`, `target`, `tb_send_mess_id`, `appendix_title`, `appendix_path`, `sending_state`, `del_time`, `create_time`) VALUES (1, '20191223184216003', 5, 'wkr9606@sina.com', 282, NULL, NULL, 2, NULL, '2019-12-23 18:42:21');


-- ----------------------------
-- Table structure for tb_letter
-- ----------------------------
DROP TABLE IF EXISTS `tb_short_mess`;

CREATE TABLE `tb_short_mess`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_admin_id` int(11) NULL DEFAULT NULL,
  `tb_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号(档案号)',
  `tb_user_id` int(11) NULL DEFAULT NULL,
  `target` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标用户手机',
  `tb_send_mess_id` int(11) NULL DEFAULT NULL COMMENT '发送内容表id',
  `sending_state` int(11) NULL DEFAULT 0 COMMENT '0表示未发送 1表示发送失败 2表示已经成功',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE, 
  INDEX `tb_user_id`(`tb_user_id`) USING BTREE,
  INDEX `tb_letter_ibfk_3`(`tb_send_mess_id`) USING BTREE,
  CONSTRAINT `tb_short_mess_ibfk_1` FOREIGN KEY (`tb_user_id`) REFERENCES `tb_user` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_short_mess_ibfk_3` FOREIGN KEY (`tb_send_mess_id`) REFERENCES `tb_send_mess` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_short_mess_ibfk_4` FOREIGN KEY (`tb_admin_id`) REFERENCES `tb_admin` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发送短信内容表' ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for tb_letter_follow
-- ----------------------------
DROP TABLE IF EXISTS `tb_short_mess_follow`;

CREATE TABLE `tb_short_mess_follow`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_admin_id` int(11) NULL DEFAULT NULL,
  `tb_short_mess_id` int(11) NULL DEFAULT NULL,
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  INDEX `tb_admin_id`(`tb_admin_id`) USING BTREE,
  INDEX `tb_short_mess_id`(`tb_short_mess_id`) USING BTREE,
  CONSTRAINT `tb_short_mess_follow_ibfk_1` FOREIGN KEY (`tb_admin_id`) REFERENCES `tb_admin` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_short_mess_follow_ibfk_2` FOREIGN KEY (`tb_short_mess_id`) REFERENCES `tb_short_mess` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发送短信关注表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_letter_follow
-- ----------------------------
DROP TABLE IF EXISTS `tb_letter_follow`;
CREATE TABLE `tb_letter_follow`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_admin_id` int(11) NULL DEFAULT NULL,
  `tb_letter_id` int(11) NULL DEFAULT NULL,
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  INDEX `tb_admin_id`(`tb_admin_id`) USING BTREE,
  INDEX `tb_letter_id`(`tb_letter_id`) USING BTREE,
  CONSTRAINT `tb_letter_follow_ibfk_1` FOREIGN KEY (`tb_admin_id`) REFERENCES `tb_admin` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_letter_follow_ibfk_2` FOREIGN KEY (`tb_letter_id`) REFERENCES `tb_letter` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件关注表(表示后台管理人员有无关注该信件)' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_letter_follow
-- ----------------------------
INSERT INTO `tb_letter_follow` VALUES (1, 1, 1, NULL, '2019-12-11 13:54:25');
INSERT INTO `tb_letter_follow` VALUES (2, 2, 1, NULL, '2019-12-11 13:54:36');

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `tb_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `tb_figure_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `tb_html_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES (1, '任正非：美国帮我们宣传', '华为创始人任正非12月2日在深圳接受加拿大《环球邮报》采访。任正非在回答记者提问时表示，5月份以后，我们认为美国的最终目的是要消灭华为公司，孟晚舟事件只是起头。只有加紧把自己内部的结构性调整做好，使产品开发体系适应制裁环境，坚决让公司生存下来，才能有解决问题的方案。因此，5月份以后（华为）就有一些变化，要努力做好业务连续性。\r\n\r\n任正非说：“华为今年这一年不仅没有赤字，而且收益非常大，因为美国政府这么强大的力量在全世界帮我们宣传。如果说过去有一些国家对华为将信将疑，美国一打击，他们更信任了‘原来你们这么厉害’。今年到我们公司访问的客人数量增长了69%，来看到我们生产线生产的版本没有美国器件了，他们就拿回去测试，发现非常好，他们建立了信心。', NULL, NULL, '2019-12-11 12:13:16', 'http://baijiahao.baidu.com/s?id=1652585635115233897');

-- ----------------------------
-- Table structure for tb_robot_response
-- ----------------------------
DROP TABLE IF EXISTS `tb_robot_response`;
CREATE TABLE `tb_robot_response`  (
  `tb_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tb_key` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关键字',
  `tb_respose` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '响应',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机器人回复表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_robot_response
-- ----------------------------
INSERT INTO `tb_robot_response` VALUES (1, '开发者', 'Facebook已经全力投身对话式商务浪潮了，意欲将其广为流行的Messenger应用打造成商务通讯平台。该公司先是在2015年将点对点支付整合到Messenger，后又推出完整的聊天机器人API，使得商户能够直接在Facebook Messenger应用内与顾客进行互动。通过Messenger对话框，你可以直接从1?800-Flowers订购花束，从Spring选购最新的时装，以及召唤Uber司机。', NULL, '2019-12-11 13:57:28');
INSERT INTO `tb_robot_response` VALUES (2, '问下', '有问题，右门进二楼', NULL, '2019-12-11 13:57:59');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `tb_role_desc` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, '系统管理员', '系统管理员', NULL, '2019-12-10 13:50:43');
INSERT INTO `tb_role` VALUES (2, '管理员', '管理员', NULL, '2019-12-10 13:54:48');
INSERT INTO `tb_role` VALUES (3, '普通用户', '普通用户', NULL, '2019-12-10 13:55:29');

-- ----------------------------
-- Table structure for tb_role_action
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_action`;
CREATE TABLE `tb_role_action`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_role_id` int(11) NULL DEFAULT NULL,
  `tb_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `tb_desc` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  INDEX `tb_role_id`(`tb_role_id`) USING BTREE,
  CONSTRAINT `tb_role_action_ibfk_1` FOREIGN KEY (`tb_role_id`) REFERENCES `tb_role` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role_action
-- ----------------------------
INSERT INTO `tb_role_action` VALUES (1, 1, '查询', NULL, NULL, '2019-12-10 13:57:52');
INSERT INTO `tb_role_action` VALUES (2, 1, '修改', NULL, NULL, '2019-12-10 13:58:09');
INSERT INTO `tb_role_action` VALUES (3, 1, '新增', NULL, NULL, '2019-12-10 13:58:21');
INSERT INTO `tb_role_action` VALUES (4, 1, '删除', NULL, NULL, '2019-12-10 13:58:33');
INSERT INTO `tb_role_action` VALUES (5, 2, '查询', NULL, NULL, '2019-12-10 13:58:58');
INSERT INTO `tb_role_action` VALUES (6, 2, '修改', NULL, NULL, '2019-12-10 13:59:20');
INSERT INTO `tb_role_action` VALUES (7, 2, '新增', NULL, NULL, '2019-12-10 13:59:31');
INSERT INTO `tb_role_action` VALUES (8, 3, '查询', NULL, NULL, '2019-12-10 13:59:46');

-- ----------------------------
-- Table structure for tb_send_mess
-- ----------------------------
DROP TABLE IF EXISTS `tb_send_mess`;
CREATE TABLE `tb_send_mess`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `tb_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发送信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_send_mess
-- ----------------------------
INSERT INTO `tb_send_mess` VALUES (1, '<<Hello word>>', '这个是一个测试的发送信息', NULL, '2019-12-13 17:37:06');

-- ----------------------------
-- Table structure for tb_system_set
-- ----------------------------
DROP TABLE IF EXISTS `tb_system_set`;
CREATE TABLE `tb_system_set`  (
  `tb_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tb_admin_id` int(11) NULL DEFAULT NULL,
  `automatic_operation` int(2) NULL DEFAULT NULL COMMENT '开机自动运行START UP',
  `system_move_position` int(2) NULL DEFAULT NULL COMMENT '记忆系统移动位置',
  `no_dropping_line` int(2) NULL DEFAULT NULL COMMENT '保证不掉线',
  `tb_default_open` int(2) NULL DEFAULT 0 COMMENT '表示通知的默认开启状态  0 表示关闭 1表示打开',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  INDEX `tb_admin_id`(`tb_admin_id`) USING BTREE,
  CONSTRAINT `tb_system_set_ibfk_1` FOREIGN KEY (`tb_admin_id`) REFERENCES `tb_admin` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统设置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for tb_technical_documentation
-- ----------------------------
DROP TABLE IF EXISTS `tb_technical_documentation`;
CREATE TABLE `tb_technical_documentation`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_mess` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文档内容',
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '技术文档表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_technical_documentation
-- ----------------------------
INSERT INTO `tb_technical_documentation` VALUES (1, '百度连接开放平台基于业界最先进的OAuth2.0授权协议，面向所有第三方开放百度的帐号体系、好友关系链及相关产品线核心数据接口，基于这些开放接口所能实现的功能包括：\r\n\r\n所有第三方网站都可以使用百度帐号登录其网站，以降低其用户的登录、注册成本，提升用户登录量和网站流量；\r\n百度应用开放平台的开发者可以实现在其IFrame应用中获取百度登录用户的基本资料和好友关系等数据。\r\n        在接下去的一段时间内，我们还会开放百度统一的Feed系统接口，以支持第三方应用的各种动态信息推送、传播，从而为第三方导入更多流量。后续我们还将会开放百度相关核心产品的数据接口供第三方使用，敬请期待！\r\n\r\n        与百度应用开放平台所不同的是，接入到百度连接开放平台的应用都是不需要内嵌在百度页面中，该平台支持任何类型的第三方应用，包括第三方Web/Wap网站、PC桌面客户端应用、手机客户端应用、各种浏览器插件应用以及各种开源建站系统的插件应用等，唯一的前提是，接入的应用都必须支持使用百度帐号登录其应用，否则我们将不会成功对接。\r\n\r\n        百度应用开放平台的开发者可以直接使用百度连接开放平台，无需额外注册，反之亦然。', NULL, '2019-12-11 14:03:02');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `wpopenid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信公众号id',
  `minopenid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小程序openid',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `tb_email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `password_MD5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码（MD5加密）(暂时不用)',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名（真实姓名）',
  `sex` int(11) NULL DEFAULT 1 COMMENT '性别（1男2女）',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `height` int(11) NULL DEFAULT NULL COMMENT '身高（单位cm）',
  `tb_province_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省名称',
  `tb_province_id` int(11) NULL DEFAULT NULL COMMENT '省id',
  `tb_city_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市名称',
  `tb_city_id` int(11) NULL DEFAULT NULL COMMENT '市id',
  `tb_area_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区名称',
  `tb_area_id` int(11) NULL DEFAULT NULL COMMENT '区id',
  `detailed_address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `head_portrait` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `login_time` datetime NULL DEFAULT NULL,
  `del_time` datetime NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `tb_uuid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'UUID',
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, NULL, NULL, '18122711575', '280888608@qq.com', '3c051636d93b96ea98fcf29aac9b251b', '令华', NULL, 1, '1993-02-28 13:36:33', NULL, NULL, 441400, NULL, 5965, NULL, 5965, NULL, NULL, NULL, NULL, '2019-12-11 13:39:21', NULL);

-- ----------------------------
-- Table structure for tb_work_dynamics
-- ----------------------------
DROP TABLE IF EXISTS `tb_work_dynamics`;
CREATE TABLE `tb_work_dynamics`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `tb_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `tb_figure_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime NULL DEFAULT NULL,
  `tb_html_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '工作动态表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_work_dynamics
-- ----------------------------
INSERT INTO `tb_work_dynamics` VALUES (1, '维护国家安全是我们共同的利益', '人们对西方的立法机构总有一种印象，就是辩论与争吵不断。在“一国两制”框架下，澳门特别行政区实行资本主义制度，特区立法会在议事时同样也有争论。但澳门立法会有一个最大的特点，即立法事项一旦涉及国家安全层面，总是能快速、顺利地取得一致。\r\n\r\n　　“在涉及国家安全的时候，我们都有一个共识，就是要维护国家安全和利益，在这样的共识下，涉及国家安全的法案都会在立法会比较快速顺利地通过。”在澳门回归祖国20周年之际，澳门特区立法会主席高开贤受访时说,“维护国家安全是我们共同的利益。', NULL, NULL, '2019-12-11 14:05:47', 'http://www.xinhuanet.com//gangao/2019-12/10/c_1125331147.htm');

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `tb_email_task`;

CREATE TABLE `tb_email_task`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标用户邮箱',
  `tb_send_mess_id` int(11) NULL DEFAULT NULL COMMENT '发送内容表id',
  `appendix_title` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件标题',
  `appendix_path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送邮件的附件',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件任务表' ROW_FORMAT = Compact;



DROP TABLE IF EXISTS `tb_email_timing_task`;

CREATE TABLE `tb_email_timing_task`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_admin_id` int(11) NULL DEFAULT NULL,
  `tb_email_task_id` int(11) NULL DEFAULT NULL,
  `tb_jobName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `tb_jobGroup` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `tb_jobDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `tb_jobStatus` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `tb_number` int(11) NULL DEFAULT 1 COMMENT '表示发送的次数',
  `tb_cron` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务表达式',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  CONSTRAINT `tb_timing_task_1` FOREIGN KEY (`tb_admin_id`) REFERENCES `tb_admin` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_timing_task_2` FOREIGN KEY (`tb_email_task_id`) REFERENCES `tb_email_task` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '邮件定时任务表' ROW_FORMAT = Compact;

DROP TABLE IF EXISTS `tb_letter_task`;

CREATE TABLE `tb_letter_task`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标用户手机',
  `tb_send_mess_id` int(11) NULL DEFAULT NULL COMMENT '发送内容表id',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信任务表' ROW_FORMAT = Compact;


DROP TABLE IF EXISTS `tb_letter_timing_task`;

CREATE TABLE `tb_letter_timing_task`  (
  `tb_id` int(11) NOT NULL AUTO_INCREMENT,
  `tb_admin_id` int(11) NULL DEFAULT NULL,
  `tb_letter_task_id` int(11) NULL DEFAULT NULL,
  `tb_jobName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `tb_jobGroup` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务分组',
  `tb_jobDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `tb_jobStatus` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
  `tb_number` int(11) NULL DEFAULT 1 COMMENT '表示发送的次数',
  `tb_cron` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务表达式',
  `del_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`tb_id`) USING BTREE,
  CONSTRAINT `tb_timing_task_5` FOREIGN KEY (`tb_admin_id`) REFERENCES `tb_admin` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `tb_timing_task_6` FOREIGN KEY (`tb_letter_task_id`) REFERENCES `tb_letter_task` (`tb_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短信定时任务表' ROW_FORMAT = Compact;





