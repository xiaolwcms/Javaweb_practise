/*
 Navicat Premium Data Transfer

 Source Server         : mysql8
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : db_database_practise

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 30/07/2022 23:21:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department`  (
  `department_id` int(0) NOT NULL COMMENT '部门编号',
  `department_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门名称',
  `department_createtime` date NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES (100001, '信息服务部', '2022-07-13');
INSERT INTO `tb_department` VALUES (100002, '综合部', '2022-07-14');
INSERT INTO `tb_department` VALUES (100003, '人力资源部', '2022-07-15');

-- ----------------------------
-- Table structure for tb_staff
-- ----------------------------
DROP TABLE IF EXISTS `tb_staff`;
CREATE TABLE `tb_staff`  (
  `staff_id` int(0) NOT NULL COMMENT '员工编号',
  `staff_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工名称',
  `staff_post` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '普通员工' COMMENT '员工职务',
  `staff_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工电话',
  `staff_email` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工邮件',
  `staff_createtime` date NOT NULL COMMENT '创建时间',
  `staff_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工密码',
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_staff
-- ----------------------------
INSERT INTO `tb_staff` VALUES (10000101, '一号员工', '普通员工', '12345678910', '123456789@com', '2021-07-13', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000104, '四号员工', '普通员工', '12345678911', '123456789@com', '2022-07-13', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000107, '七号员工', '普通员工', '12345678912', '123456789@com', '2022-07-13', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000110, '十号员工', '项目经理', '12345678913', '123456789@com', '2019-07-11', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000111, '十一号员工', '项目经理', '12345678914', '123456789@com', '2019-07-11', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000112, '十二号员工', '部门经理', '12345678915', '123456789@com', '2019-07-11', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000113, '你好', '普通员工1', '12345678922', '123@outlook.com', '2022-07-29', '46e44aa0bc21d8a826d79344df38be4b');
INSERT INTO `tb_staff` VALUES (10000202, '二号员工', '项目经理', '12345678916', '123456789@com', '2020-07-12', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000205, '五号员工', '项目经理', '12345678917', '123456789@com', '2021-07-12', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000208, '八号员工', '项目经理', '12345678918', '123456789@com', '2021-07-12', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000303, '三号员工', '部门经理', '12345678919', '123456789@com', '2019-07-11', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000306, '六号员工', '部门经理', '12345678920', '123456789@com', '2020-07-11', '202cb962ac59075b964b07152d234b70');
INSERT INTO `tb_staff` VALUES (10000309, '九号员工', '部门经理', '12345678921', '123456789@com', '2020-07-11', '202cb962ac59075b964b07152d234b70');

-- ----------------------------
-- Table structure for tb_staff_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_staff_department`;
CREATE TABLE `tb_staff_department`  (
  `sd_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `sd_department_id` int(0) NOT NULL COMMENT '部门编号',
  `sd_staff_id` int(0) NOT NULL COMMENT '员工编号',
  PRIMARY KEY (`sd_id`) USING BTREE,
  INDEX `sb_department_id`(`sd_department_id`) USING BTREE,
  INDEX `sb_staff_id`(`sd_staff_id`) USING BTREE,
  CONSTRAINT `tb_staff_department_ibfk_1` FOREIGN KEY (`sd_department_id`) REFERENCES `tb_department` (`department_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_staff_department_ibfk_2` FOREIGN KEY (`sd_staff_id`) REFERENCES `tb_staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户-部门关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_staff_department
-- ----------------------------
INSERT INTO `tb_staff_department` VALUES (1, 100001, 10000101);
INSERT INTO `tb_staff_department` VALUES (2, 100002, 10000202);
INSERT INTO `tb_staff_department` VALUES (3, 100003, 10000303);
INSERT INTO `tb_staff_department` VALUES (4, 100001, 10000104);
INSERT INTO `tb_staff_department` VALUES (5, 100002, 10000205);
INSERT INTO `tb_staff_department` VALUES (6, 100003, 10000306);
INSERT INTO `tb_staff_department` VALUES (7, 100001, 10000107);
INSERT INTO `tb_staff_department` VALUES (8, 100002, 10000208);
INSERT INTO `tb_staff_department` VALUES (9, 100003, 10000309);
INSERT INTO `tb_staff_department` VALUES (10, 100001, 10000110);
INSERT INTO `tb_staff_department` VALUES (11, 100002, 10000110);
INSERT INTO `tb_staff_department` VALUES (12, 100002, 10000111);
INSERT INTO `tb_staff_department` VALUES (13, 100003, 10000111);
INSERT INTO `tb_staff_department` VALUES (14, 100001, 10000112);
INSERT INTO `tb_staff_department` VALUES (15, 100002, 10000112);
INSERT INTO `tb_staff_department` VALUES (16, 100003, 10000112);
INSERT INTO `tb_staff_department` VALUES (38, 100001, 10000113);

-- ----------------------------
-- Table structure for tb_staff_merits
-- ----------------------------
DROP TABLE IF EXISTS `tb_staff_merits`;
CREATE TABLE `tb_staff_merits`  (
  `sm_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '员工绩效表ID',
  `sm_staff_id` int(0) NOT NULL COMMENT '员工ID',
  `sm_merits_grade` enum('A','B','C','D') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'D' COMMENT '绩效等级',
  `sm_year` year NOT NULL COMMENT '年份',
  PRIMARY KEY (`sm_id`) USING BTREE,
  INDEX `sm_staff_id`(`sm_staff_id`) USING BTREE,
  CONSTRAINT `tb_staff_merits_ibfk_1` FOREIGN KEY (`sm_staff_id`) REFERENCES `tb_staff` (`staff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '员工绩效表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_staff_merits
-- ----------------------------
INSERT INTO `tb_staff_merits` VALUES (1, 10000101, 'D', 2022);
INSERT INTO `tb_staff_merits` VALUES (3, 10000101, 'A', 2021);
INSERT INTO `tb_staff_merits` VALUES (4, 10000202, 'B', 2022);
INSERT INTO `tb_staff_merits` VALUES (5, 10000202, 'A', 2021);
INSERT INTO `tb_staff_merits` VALUES (6, 10000202, 'A', 2020);
INSERT INTO `tb_staff_merits` VALUES (7, 10000303, 'C', 2022);
INSERT INTO `tb_staff_merits` VALUES (8, 10000303, 'B', 2021);
INSERT INTO `tb_staff_merits` VALUES (9, 10000303, 'A', 2020);
INSERT INTO `tb_staff_merits` VALUES (10, 10000303, 'A', 2019);
INSERT INTO `tb_staff_merits` VALUES (11, 10000104, 'A', 2022);
INSERT INTO `tb_staff_merits` VALUES (12, 10000104, 'A', 2021);
INSERT INTO `tb_staff_merits` VALUES (13, 10000205, 'B', 2022);
INSERT INTO `tb_staff_merits` VALUES (14, 10000205, 'A', 2021);
INSERT INTO `tb_staff_merits` VALUES (15, 10000205, 'A', 2020);
INSERT INTO `tb_staff_merits` VALUES (16, 10000306, 'C', 2022);
INSERT INTO `tb_staff_merits` VALUES (17, 10000306, 'B', 2021);
INSERT INTO `tb_staff_merits` VALUES (18, 10000306, 'A', 2020);
INSERT INTO `tb_staff_merits` VALUES (19, 10000306, 'A', 2019);
INSERT INTO `tb_staff_merits` VALUES (20, 10000107, 'A', 2022);
INSERT INTO `tb_staff_merits` VALUES (21, 10000107, 'A', 2021);
INSERT INTO `tb_staff_merits` VALUES (22, 10000208, 'B', 2022);
INSERT INTO `tb_staff_merits` VALUES (23, 10000208, 'A', 2021);
INSERT INTO `tb_staff_merits` VALUES (24, 10000208, 'A', 2020);
INSERT INTO `tb_staff_merits` VALUES (25, 10000309, 'C', 2022);
INSERT INTO `tb_staff_merits` VALUES (26, 10000309, 'B', 2021);
INSERT INTO `tb_staff_merits` VALUES (27, 10000309, 'A', 2020);
INSERT INTO `tb_staff_merits` VALUES (28, 10000309, 'A', 2019);
INSERT INTO `tb_staff_merits` VALUES (29, 10000110, 'A', 2022);
INSERT INTO `tb_staff_merits` VALUES (30, 10000110, 'A', 2021);
INSERT INTO `tb_staff_merits` VALUES (31, 10000110, 'A', 2020);
INSERT INTO `tb_staff_merits` VALUES (32, 10000110, 'A', 2019);
INSERT INTO `tb_staff_merits` VALUES (33, 10000110, 'A', 2022);
INSERT INTO `tb_staff_merits` VALUES (34, 10000111, 'A', 2021);
INSERT INTO `tb_staff_merits` VALUES (35, 10000111, 'A', 2020);
INSERT INTO `tb_staff_merits` VALUES (36, 10000111, 'A', 2019);
INSERT INTO `tb_staff_merits` VALUES (37, 10000112, 'A', 2022);
INSERT INTO `tb_staff_merits` VALUES (38, 10000112, 'A', 2021);
INSERT INTO `tb_staff_merits` VALUES (39, 10000112, 'A', 2020);
INSERT INTO `tb_staff_merits` VALUES (40, 10000112, 'A', 2019);

-- ----------------------------
-- Function structure for getStaffInformation
-- ----------------------------
DROP FUNCTION IF EXISTS `getStaffInformation`;
delimiter ;;
CREATE FUNCTION `getStaffInformation`(departmentID int(6))
 RETURNS varchar(100) CHARSET utf8mb4
  READS SQL DATA 
begin
declare result varchar(100) default null;
set result=(select group_concat(distinct staff_id)	
from tb_staff_department,tb_staff,tb_staff_merits 
where sd_department_id=departmentID and sm_merits_grade='A' and 2<=(year(now())-year(staff_createtime)) and sd_staff_id=staff_id and staff_id=sm_staff_id);
return result;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for insertNewStaff
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertNewStaff`;
delimiter ;;
CREATE PROCEDURE `insertNewStaff`(in staffId int,in staffName varchar(10),in staffPhone varchar(11),in staffPassword varchar(50),
in staffEmail varchar(20),in staffPost varchar(10),in staffCreatetime date,in departmentName int,
out result1 int(1),out result2 int(1))
begin
-- declare result1 int default 0;
-- declare result2 int default 0;
insert into tb_staff(staff_id,staff_name,staff_post,staff_phone,staff_email,staff_createtime,staff_password) values(staffId,staffName,staffPost,staffPhone,staffEmail,staffCreatetime,staffPassword);
select ROW_COUNT() into result1;
insert into tb_staff_department(sd_department_id,sd_staff_id)values(departmentName,staffId);
select ROW_COUNT() into result2;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
