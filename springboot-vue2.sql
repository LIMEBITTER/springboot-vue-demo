/*
 Navicat Premium Data Transfer

 Source Server         : JavaEE
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : springboot-vue2

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 19/05/2022 09:08:03
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
  `health_status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of health
-- ----------------------------
INSERT INTO `health` VALUES (17, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (18, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (19, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (20, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (21, '正常', 36, '是', '2');
INSERT INTO `health` VALUES (22, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (23, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (24, '正常', 37, '是', '1');
INSERT INTO `health` VALUES (25, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (26, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (27, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (28, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (29, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (30, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (31, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (32, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (33, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (34, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (35, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (36, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (37, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (38, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (39, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (40, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (41, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (42, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (43, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (44, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (45, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (46, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (47, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (48, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (49, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (50, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (51, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (52, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (53, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (54, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (55, '正常', 37, '否', '2');
INSERT INTO `health` VALUES (56, '隔离', 38, '否', '2');
INSERT INTO `health` VALUES (57, '正常', 37, '是', '2');
INSERT INTO `health` VALUES (58, '隔离', 38, '是', '2');
INSERT INTO `health` VALUES (59, '隔离', 39, '是', '2');
INSERT INTO `health` VALUES (60, '隔离', 40, '否', '2');

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
  `v_id` int(11) NULL DEFAULT NULL,
  `health_id` int(11) NULL DEFAULT NULL,
  `resident_status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resident
-- ----------------------------
INSERT INTO `resident` VALUES (32, '张三', '女', 23, '华轿花园1栋1号', '19911111111', '是', 8, 17, 2);
INSERT INTO `resident` VALUES (33, '李四', '女', 18, '碧桂园3栋3号', '17711111111', '否', 9, 18, 2);
INSERT INTO `resident` VALUES (34, '王五', '男', 34, '华轿花园1栋1号', '18811111111', '是', 10, 19, 2);
INSERT INTO `resident` VALUES (36, '赵六', '女', 23, '华轿花园4栋3号', '19911111112', '是', 11, 20, 2);
INSERT INTO `resident` VALUES (37, '张晓晓', '男', 17, '碧桂园2栋1号', '17711111112', '否', 12, 21, 2);
INSERT INTO `resident` VALUES (38, '李子明', '男', 34, '双凤小区6栋203室', '18811111111', '否', 13, 22, 2);
INSERT INTO `resident` VALUES (39, '李子晴', '女', 24, '碧桂园2栋', '19911111111', '是', 14, 23, 2);
INSERT INTO `resident` VALUES (40, '小明一', '男', 22, '碧桂园2号', '17711111111', '否', 15, 24, 2);
INSERT INTO `resident` VALUES (41, '小明二', '男', 34, '华轿花园1栋4号', '17711111113', '是', 16, 25, 2);
INSERT INTO `resident` VALUES (42, '小明三', '女', 25, '华轿花园1栋5号', '17711111114', '是', 17, 26, 2);
INSERT INTO `resident` VALUES (43, '小明四', '女', 56, '华轿花园1栋6号', '17711111116', '是', 18, 27, 2);
INSERT INTO `resident` VALUES (44, '小明五', '男', 67, '华轿花园1栋17号', '17711111117', '是', 19, 28, 2);
INSERT INTO `resident` VALUES (45, '小明六', '男', 37, '华轿花园1栋13号', '17711111118', '是', 20, 29, 2);
INSERT INTO `resident` VALUES (46, '小明八', '男', 28, '华轿花园1栋18号', '17711111119', '是', 21, 30, 2);
INSERT INTO `resident` VALUES (47, '小明久', '男', 24, '华轿花园1栋19号', '17711111110', '是', 22, 31, 2);
INSERT INTO `resident` VALUES (48, '小明时', '男', 22, '华轿花园2栋1号', '17711111143', '是', 23, 32, 2);
INSERT INTO `resident` VALUES (49, '张四', '男', 46, '华轿花园2栋2号', '17711111178', '是', 24, 33, 2);
INSERT INTO `resident` VALUES (50, '张武', '女', 44, '华轿花园2栋34号', '17711111123', '是', 25, 34, 2);
INSERT INTO `resident` VALUES (51, '张柳', '女', 42, '华轿花园3栋1号', '18811111137', '是', 26, 35, 2);
INSERT INTO `resident` VALUES (52, '张琪', '女', 41, '华轿花园7栋12号', '18811111111', '是', 27, 36, 2);
INSERT INTO `resident` VALUES (53, '张坝', '女', 48, '双凤小区6栋222室', '18811111111', '否', 28, 37, 2);
INSERT INTO `resident` VALUES (54, '掌灸', '男', 49, '双凤小区6栋674室', '18811111111', '否', 29, 38, 2);
INSERT INTO `resident` VALUES (55, '李梓萌', '男', 57, '碧桂园5栋1号', '18811111111', '否', 30, 39, 2);
INSERT INTO `resident` VALUES (56, '李子怡', '男', 55, '双凤小区6栋388室', '18811111111', '否', 31, 40, 2);
INSERT INTO `resident` VALUES (57, '李天一', '女', 53, '双凤小区7栋232室', '17711111111', '否', 32, 41, 2);
INSERT INTO `resident` VALUES (58, '李天而', '女', 56, '碧桂园10栋1号', '17711111111', '否', 33, 42, 2);
INSERT INTO `resident` VALUES (59, '李恬儿', '女', 59, '双凤小区6栋258室', '17711111111', '否', 34, 43, 2);
INSERT INTO `resident` VALUES (60, '李天梭', '男', 44, '双凤小区1栋888室', '17711111111', '否', 35, 44, 2);
INSERT INTO `resident` VALUES (61, '赵柳', '男', 34, '碧桂园2栋45号', '17711111111', '否', 36, 45, 2);
INSERT INTO `resident` VALUES (62, '赵琦', '男', 33, '天元街433弄', '18811111111', '否', 37, 46, 2);
INSERT INTO `resident` VALUES (63, '赵琪', '男', 32, '天元街765弄', '18811111111', '否', 38, 47, 2);
INSERT INTO `resident` VALUES (64, '赵霸', '男', 31, '碧桂园8栋19号', '17711111111', '否', 39, 48, 2);
INSERT INTO `resident` VALUES (65, '赵久', '男', 37, '坡子口33号', '18811111111', '否', 40, 49, 2);
INSERT INTO `resident` VALUES (66, '赵氏', '女', 36, '天元街12弄', '17711111111', '否', 41, 50, 2);
INSERT INTO `resident` VALUES (67, '周天一', '女', 37, '碧桂园9栋11号', '18811111111', '否', 42, 51, 2);
INSERT INTO `resident` VALUES (68, '周天尔', '男', 39, '天元街8弄', '17711111111', '否', 43, 52, 2);
INSERT INTO `resident` VALUES (69, '周天山', '女', 33, '碧桂园10栋1号', '18811111111', '否', 44, 53, 2);
INSERT INTO `resident` VALUES (70, '周天使', '女', 31, '天元街764弄', '18811111111', '否', 45, 54, 2);
INSERT INTO `resident` VALUES (71, '周天武', '女', 40, '碧桂园1栋23号', '17711111111', '否', NULL, 55, 2);
INSERT INTO `resident` VALUES (72, '周天刘', '男', 45, '坡子口67号', '17711111111', '否', NULL, 56, 2);
INSERT INTO `resident` VALUES (73, '周天琪', '女', 56, '坡子口72号', '17711111111', '否', 46, 57, 2);
INSERT INTO `resident` VALUES (74, '武威', '女', 68, '碧桂园5栋11号', '18811111111', '否', 47, 58, 2);
INSERT INTO `resident` VALUES (75, '吴唯一', '男', 77, '碧桂园8栋34号', '17711111111', '否', NULL, 59, 2);
INSERT INTO `resident` VALUES (76, '吴伟尔', '女', 23, '碧桂园10栋22号', '17711111112', '否', NULL, 60, 2);

-- ----------------------------
-- Table structure for resident_travel
-- ----------------------------
DROP TABLE IF EXISTS `resident_travel`;
CREATE TABLE `resident_travel`  (
  `rid` int(11) NOT NULL,
  `traval_id` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resident_travel
-- ----------------------------
INSERT INTO `resident_travel` VALUES (32, 20);
INSERT INTO `resident_travel` VALUES (33, 21);
INSERT INTO `resident_travel` VALUES (34, 22);
INSERT INTO `resident_travel` VALUES (35, 23);
INSERT INTO `resident_travel` VALUES (36, 24);
INSERT INTO `resident_travel` VALUES (37, 25);
INSERT INTO `resident_travel` VALUES (38, 26);
INSERT INTO `resident_travel` VALUES (39, 27);
INSERT INTO `resident_travel` VALUES (40, 28);
INSERT INTO `resident_travel` VALUES (41, 29);
INSERT INTO `resident_travel` VALUES (42, 30);
INSERT INTO `resident_travel` VALUES (43, 31);
INSERT INTO `resident_travel` VALUES (44, 32);
INSERT INTO `resident_travel` VALUES (45, 33);
INSERT INTO `resident_travel` VALUES (46, 34);
INSERT INTO `resident_travel` VALUES (47, 35);
INSERT INTO `resident_travel` VALUES (48, 36);
INSERT INTO `resident_travel` VALUES (49, 37);
INSERT INTO `resident_travel` VALUES (50, 38);
INSERT INTO `resident_travel` VALUES (51, 39);
INSERT INTO `resident_travel` VALUES (52, 40);
INSERT INTO `resident_travel` VALUES (53, 41);
INSERT INTO `resident_travel` VALUES (54, 42);
INSERT INTO `resident_travel` VALUES (55, 43);
INSERT INTO `resident_travel` VALUES (56, 44);
INSERT INTO `resident_travel` VALUES (57, 45);
INSERT INTO `resident_travel` VALUES (58, 46);
INSERT INTO `resident_travel` VALUES (59, 47);
INSERT INTO `resident_travel` VALUES (60, 48);
INSERT INTO `resident_travel` VALUES (61, 49);
INSERT INTO `resident_travel` VALUES (62, 50);
INSERT INTO `resident_travel` VALUES (64, 52);
INSERT INTO `resident_travel` VALUES (64, 52);
INSERT INTO `resident_travel` VALUES (65, 53);
INSERT INTO `resident_travel` VALUES (66, 54);
INSERT INTO `resident_travel` VALUES (67, 55);
INSERT INTO `resident_travel` VALUES (68, 56);
INSERT INTO `resident_travel` VALUES (69, 57);
INSERT INTO `resident_travel` VALUES (70, 58);
INSERT INTO `resident_travel` VALUES (71, 59);
INSERT INTO `resident_travel` VALUES (72, 60);
INSERT INTO `resident_travel` VALUES (73, 61);
INSERT INTO `resident_travel` VALUES (74, 62);
INSERT INTO `resident_travel` VALUES (75, 63);
INSERT INTO `resident_travel` VALUES (76, 64);

-- ----------------------------
-- Table structure for travel
-- ----------------------------
DROP TABLE IF EXISTS `travel`;
CREATE TABLE `travel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `travelTool` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `totalman` int(255) NULL DEFAULT NULL,
  `travelStatus` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of travel
-- ----------------------------
INSERT INTO `travel` VALUES (20, '自行车', '菜市场', 1, 1);
INSERT INTO `travel` VALUES (21, '摩托车', '人民广场', 2, 1);
INSERT INTO `travel` VALUES (22, '自行车', '菜市场', 1, 1);
INSERT INTO `travel` VALUES (23, '电动车', '万达广场', 2, 1);
INSERT INTO `travel` VALUES (24, '汽车', '少年宫', 3, 1);
INSERT INTO `travel` VALUES (25, '三轮车', '菜市场', 4, 1);
INSERT INTO `travel` VALUES (26, '步行', '少年宫', 1, 1);
INSERT INTO `travel` VALUES (27, '自行车', '菜市场', 1, 1);
INSERT INTO `travel` VALUES (28, '自行车', '菜市场', 1, 2);
INSERT INTO `travel` VALUES (29, '自行车', '菜市场', 4, 1);
INSERT INTO `travel` VALUES (30, '自行车', '万达广场', 1, 1);
INSERT INTO `travel` VALUES (31, '步行', '菜市场', 1, 1);
INSERT INTO `travel` VALUES (32, '自行车', '菜市场', 1, 1);
INSERT INTO `travel` VALUES (33, '自行车', '菜市场', 1, 1);
INSERT INTO `travel` VALUES (34, '步行', '万达广场', 1, 1);
INSERT INTO `travel` VALUES (35, '步行', '菜市场', 2, 1);
INSERT INTO `travel` VALUES (36, '步行', '菜市场', 6, 1);
INSERT INTO `travel` VALUES (37, '步行', '菜市场', 4, 1);
INSERT INTO `travel` VALUES (38, '步行', '菜市场', 3, 1);
INSERT INTO `travel` VALUES (39, '步行', '人民广场', 1, 1);
INSERT INTO `travel` VALUES (40, '摩托车', '人民广场', 2, 1);
INSERT INTO `travel` VALUES (41, '摩托车', '人民广场', 1, 1);
INSERT INTO `travel` VALUES (42, '摩托车', '人民广场', 1, 1);
INSERT INTO `travel` VALUES (43, '摩托车', '少年宫', 2, 1);
INSERT INTO `travel` VALUES (44, '摩托车', '少年宫', 1, 1);
INSERT INTO `travel` VALUES (45, '摩托车', '少年宫', 1, 1);
INSERT INTO `travel` VALUES (46, '摩托车', '人民广场', 1, 1);
INSERT INTO `travel` VALUES (47, '步行', '少年宫', 1, 1);
INSERT INTO `travel` VALUES (48, '步行', '少年宫', 1, 1);
INSERT INTO `travel` VALUES (49, '步行', '人民医院', 7, 1);
INSERT INTO `travel` VALUES (50, '步行', '人民广场', 1, 1);
INSERT INTO `travel` VALUES (51, '步行', '人民广场', 1, 1);
INSERT INTO `travel` VALUES (52, '步行', '万达广场', 1, 1);
INSERT INTO `travel` VALUES (53, '电动车', '人民广场', 3, 1);
INSERT INTO `travel` VALUES (54, '电动车', '人民广场', 3, 1);
INSERT INTO `travel` VALUES (55, '电动车', '万达广场', 3, 1);
INSERT INTO `travel` VALUES (56, '电动车', '少年宫', 2, 1);
INSERT INTO `travel` VALUES (57, '电动车', '万达广场', 5, 1);
INSERT INTO `travel` VALUES (58, '三轮车', '万达广场', 2, 1);
INSERT INTO `travel` VALUES (59, '三轮车', '少年宫', 3, 1);
INSERT INTO `travel` VALUES (60, '三轮车', '万达广场', 3, 1);
INSERT INTO `travel` VALUES (61, '汽车', '少年宫', 2, 1);
INSERT INTO `travel` VALUES (62, '汽车', '万达广场', 1, 1);
INSERT INTO `travel` VALUES (63, '电动车', '万达广场', 2, 1);
INSERT INTO `travel` VALUES (64, '电动车', '少年宫', 3, 1);
INSERT INTO `travel` VALUES (65, '电动车', '万达广场', 4, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `is_admin` int(1) NULL DEFAULT NULL COMMENT '是否是管理员',
  `rid` int(11) NULL DEFAULT NULL COMMENT '居民号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 1, 0);
INSERT INTO `user` VALUES (99, 'zhangsan', '123', 0, 32);
INSERT INTO `user` VALUES (100, 'lisi', '123', 0, 33);
INSERT INTO `user` VALUES (101, 'wangwu', '123', 0, 34);
INSERT INTO `user` VALUES (102, 'zhaoliu', '123', 0, 36);
INSERT INTO `user` VALUES (103, 'xiaoming', '123', 0, 38);
INSERT INTO `user` VALUES (104, 'xiaozhang', '123', 0, 37);
INSERT INTO `user` VALUES (105, 'xiaoqiang', '123', 0, 39);
INSERT INTO `user` VALUES (106, 'xxx', '123', 0, 40);
INSERT INTO `user` VALUES (114, 'test1', '123', 0, 41);
INSERT INTO `user` VALUES (115, 'test2', '123', 0, 42);
INSERT INTO `user` VALUES (116, 'test3', '123', 0, 43);
INSERT INTO `user` VALUES (117, 'test4', '123', 0, 44);
INSERT INTO `user` VALUES (118, 'test5', '123', 0, 45);
INSERT INTO `user` VALUES (119, 'test6', '123', 0, 46);
INSERT INTO `user` VALUES (120, 'test7', '123', 0, 47);
INSERT INTO `user` VALUES (121, 'test8', '12', 0, 48);
INSERT INTO `user` VALUES (122, 'test9', '12', 0, 49);
INSERT INTO `user` VALUES (123, 'test10', '123', 0, 50);
INSERT INTO `user` VALUES (124, 'test11', '123', 0, 51);
INSERT INTO `user` VALUES (125, 'test12', '12', 0, 52);
INSERT INTO `user` VALUES (126, 'test13', '123', 0, 53);
INSERT INTO `user` VALUES (127, 'test14', '12', 0, 54);
INSERT INTO `user` VALUES (128, 'test15', '132', 0, 55);
INSERT INTO `user` VALUES (129, 'test16', '123', 0, 56);
INSERT INTO `user` VALUES (130, 'test17', '123', 0, 57);
INSERT INTO `user` VALUES (131, 'test18', '123', 0, 58);
INSERT INTO `user` VALUES (132, 'test19', '123', 0, 59);
INSERT INTO `user` VALUES (133, 'test20', NULL, NULL, NULL);
INSERT INTO `user` VALUES (134, 'test20', '12', 0, 60);
INSERT INTO `user` VALUES (135, 'test21', '123', 0, 61);
INSERT INTO `user` VALUES (136, 'test22', '123', 0, 62);
INSERT INTO `user` VALUES (137, 'test23', '123', 0, 63);
INSERT INTO `user` VALUES (138, 'test24', '123', 0, 64);
INSERT INTO `user` VALUES (139, 'test25', '123', 0, 65);
INSERT INTO `user` VALUES (140, 'test26', '123', 0, 66);
INSERT INTO `user` VALUES (141, 'test27', '123', 0, 67);
INSERT INTO `user` VALUES (142, 'test28', '123', 0, 68);
INSERT INTO `user` VALUES (143, 'test29', '123', 0, 69);
INSERT INTO `user` VALUES (144, 'test30', '123', 0, 70);
INSERT INTO `user` VALUES (145, 'test40', '123', 0, 71);
INSERT INTO `user` VALUES (146, 'test41', '123', 0, 72);
INSERT INTO `user` VALUES (147, 'test42', '123', 0, 73);
INSERT INTO `user` VALUES (148, 'test43', '123', 0, 74);
INSERT INTO `user` VALUES (149, 'test44', '123', 0, 75);
INSERT INTO `user` VALUES (150, 'test45', NULL, NULL, NULL);
INSERT INTO `user` VALUES (151, 'test45', '213', 0, 76);

-- ----------------------------
-- Table structure for volunteer
-- ----------------------------
DROP TABLE IF EXISTS `volunteer`;
CREATE TABLE `volunteer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `workTime` decimal(10, 0) NULL DEFAULT NULL,
  `workAddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `volunteerStatus` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of volunteer
-- ----------------------------
INSERT INTO `volunteer` VALUES (8, 3, '人民西路233号', 1);
INSERT INTO `volunteer` VALUES (9, 1, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (10, 3, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (11, 3, '三元西路211号', 1);
INSERT INTO `volunteer` VALUES (12, 4, '人民西路233号', 1);
INSERT INTO `volunteer` VALUES (13, 5, '人民西路233号', 1);
INSERT INTO `volunteer` VALUES (14, 3, '人民西路233号', 1);
INSERT INTO `volunteer` VALUES (15, 2, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (16, 2, '人民西路233号', 1);
INSERT INTO `volunteer` VALUES (17, 2, '人民西路233号', 1);
INSERT INTO `volunteer` VALUES (18, 2, '人民西路233号', 1);
INSERT INTO `volunteer` VALUES (19, 2, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (20, 2, '人民西路233号', 1);
INSERT INTO `volunteer` VALUES (21, 2, '人民西路233号', 1);
INSERT INTO `volunteer` VALUES (22, 2, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (23, 1, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (24, 1, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (25, 1, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (26, 1, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (27, 2, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (28, 2, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (29, 2, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (30, 4, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (31, 4, '三元西路211号', 1);
INSERT INTO `volunteer` VALUES (32, 5, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (33, 6, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (34, 3, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (35, 3, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (36, 3, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (37, 3, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (38, 3, '三元西路211号', 1);
INSERT INTO `volunteer` VALUES (39, 4, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (40, 5, '郑和西路233号', 1);
INSERT INTO `volunteer` VALUES (41, 2, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (42, 2, '三元西路211号', 1);
INSERT INTO `volunteer` VALUES (43, 1, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (44, 1, '三元西路211号', 1);
INSERT INTO `volunteer` VALUES (45, 1, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (46, 1, '人民北路111号', 1);
INSERT INTO `volunteer` VALUES (47, 1, '三元西路211号', 1);

SET FOREIGN_KEY_CHECKS = 1;
