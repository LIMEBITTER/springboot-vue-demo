/*
 Navicat Premium Data Transfer

 Source Server         : spring-test
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : springboot-vue2

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 01/04/2022 11:30:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for health
-- ----------------------------
DROP TABLE IF EXISTS `health`;
CREATE TABLE `health`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `normal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `temperature` decimal(65, 0) NULL DEFAULT NULL,
  `vaccines` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of health
-- ----------------------------
INSERT INTO `health` VALUES (1, '正常', 37, '是');
INSERT INTO `health` VALUES (2, '正常', 37, '是');
INSERT INTO `health` VALUES (3, '隔离', 38, '是');
INSERT INTO `health` VALUES (4, '正常', 37, '是');
INSERT INTO `health` VALUES (5, '正常', 37, '是');
INSERT INTO `health` VALUES (6, '正常', 37, '是');
INSERT INTO `health` VALUES (7, '隔离', 39, '是');

-- ----------------------------
-- Table structure for resident
-- ----------------------------
DROP TABLE IF EXISTS `resident`;
CREATE TABLE `resident`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sex` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `local_people` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `v_id` int(11) NOT NULL,
  `health_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resident
-- ----------------------------
INSERT INTO `resident` VALUES (1, '张三', '男', 19, '华侨花园11幢4号', '13811111111', '是', 1, 1);
INSERT INTO `resident` VALUES (2, '李四', '女', 18, '华侨花园10幢1号', '13811111112', '是', 2, 2);
INSERT INTO `resident` VALUES (3, '王五', '男', 17, '华侨花园1幢7号', '13811111113', '是', 3, 3);
INSERT INTO `resident` VALUES (4, '赵六', '男', 24, '华侨花园1幢8号', '13811111114', '是', 4, 4);
INSERT INTO `resident` VALUES (5, '晓迪', '女', 22, '翻斗花园1幢10号', '13811111115', '否', 0, 5);
INSERT INTO `resident` VALUES (6, '杨兆来', '女', 24, '翻斗花园4幢8号', '13911111111', '是', 0, 6);
INSERT INTO `resident` VALUES (7, '范晓芬', '女', 23, '翻斗花园9幢3号', '13911111112', '否', 0, 7);
INSERT INTO `resident` VALUES (8, '杨波', '男', 22, '翻斗花园1幢8号', '13822222222', '否', 0, 8);
INSERT INTO `resident` VALUES (9, '白皙云', '女', 38, '大幅度1幢8号', '13811111111', '是', 0, 9);
INSERT INTO `resident` VALUES (10, '田所浩二', '男', 24, '下北沢3幢6号', '13811111111', '否', 0, 10);

-- ----------------------------
-- Table structure for resident_travel
-- ----------------------------
DROP TABLE IF EXISTS `resident_travel`;
CREATE TABLE `resident_travel`  (
  `rid` int(11) NOT NULL,
  `traval_id` int(11) NOT NULL,
  PRIMARY KEY (`rid`, `traval_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resident_travel
-- ----------------------------
INSERT INTO `resident_travel` VALUES (1, 1);
INSERT INTO `resident_travel` VALUES (2, 2);
INSERT INTO `resident_travel` VALUES (3, 3);

-- ----------------------------
-- Table structure for travel
-- ----------------------------
DROP TABLE IF EXISTS `travel`;
CREATE TABLE `travel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `travel_tool` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `totalman` int(255) NULL DEFAULT NULL,
  `r_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel
-- ----------------------------
INSERT INTO `travel` VALUES (1, '自行车', '市民公园', 1, NULL);
INSERT INTO `travel` VALUES (2, '自行车', '市民公园', 2, NULL);
INSERT INTO `travel` VALUES (3, '自行车', '万达广场', 1, NULL);
INSERT INTO `travel` VALUES (4, '自行车', '市民公园', 3, NULL);
INSERT INTO `travel` VALUES (5, '私家车', '万达广场', 3, NULL);
INSERT INTO `travel` VALUES (6, '自行车', '便民市场', 1, NULL);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `account` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '账户余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '管理员', 24, '男', '华侨花园9幢4号-1', 'http://localhost:9090/files/888f2d39d0724816a738a716d56ad58a', 100000.00);
INSERT INTO `user` VALUES (2, 'zhangsan', 'zhangsan', '管理员1', 19, '女', '翻斗花园3幢3号', NULL, 1000.00);
INSERT INTO `user` VALUES (3, 'lisi', 'lisi123', '管理员2', 25, '男', '碧桂园5幢9号', NULL, 5000.00);
INSERT INTO `user` VALUES (88, '的的', NULL, '的的', 0, NULL, '的的的', NULL, 0.00);

-- ----------------------------
-- Table structure for volunteer
-- ----------------------------
DROP TABLE IF EXISTS `volunteer`;
CREATE TABLE `volunteer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `work_time` decimal(10, 0) NULL DEFAULT NULL,
  `work_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of volunteer
-- ----------------------------
INSERT INTO `volunteer` VALUES (1, 15, '华侨花园西门');
INSERT INTO `volunteer` VALUES (2, 12, '华侨花园东门');

SET FOREIGN_KEY_CHECKS = 1;
