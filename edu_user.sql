/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : edu_user

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 13/06/2023 13:47:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '系统功能', '', '', 0, 'el-icon-setting', NULL);
INSERT INTO `permission` VALUES (2, '采购管理', '', '', 1, 'el-icon-shopping-cart-1', 1);
INSERT INTO `permission` VALUES (3, '销售管理', '', '', 1, 'el-icon-bank-card', 2);
INSERT INTO `permission` VALUES (4, '生产管理', '', '', 1, 'el-icon-magic-stick', 3);
INSERT INTO `permission` VALUES (5, '仓库管理', '', '', 1, 'el-icon-box', 4);
INSERT INTO `permission` VALUES (6, '质量管理', '', '', 1, 'el-icon-user', 5);
INSERT INTO `permission` VALUES (7, '基础数据', '', '', 1, 'el-icon-position', 6);
INSERT INTO `permission` VALUES (10, '采购审核', NULL, '', 2, NULL, 2);
INSERT INTO `permission` VALUES (12, '采购退货', NULL, '', 2, NULL, 4);
INSERT INTO `permission` VALUES (14, '销售出库', NULL, '/out', 3, NULL, 2);
INSERT INTO `permission` VALUES (15, '销售退货', NULL, '/back', 3, NULL, 3);
INSERT INTO `permission` VALUES (16, '领料', NULL, NULL, 4, NULL, 1);
INSERT INTO `permission` VALUES (17, '退料', NULL, NULL, 4, NULL, 2);
INSERT INTO `permission` VALUES (18, '生产入库', NULL, NULL, 4, NULL, 3);
INSERT INTO `permission` VALUES (19, '待生产订单', NULL, NULL, 4, NULL, 4);
INSERT INTO `permission` VALUES (20, '盘点', NULL, NULL, 5, NULL, 1);
INSERT INTO `permission` VALUES (21, '库存查询', NULL, NULL, 5, NULL, 2);
INSERT INTO `permission` VALUES (22, '其它入库', NULL, NULL, 5, NULL, 3);
INSERT INTO `permission` VALUES (23, '其它出库', NULL, NULL, 5, NULL, 4);
INSERT INTO `permission` VALUES (24, '库存移库', NULL, NULL, 5, NULL, 5);
INSERT INTO `permission` VALUES (25, '出入库明细查询', NULL, NULL, 5, NULL, 6);
INSERT INTO `permission` VALUES (26, '采购入库检查', NULL, NULL, 6, NULL, 1);
INSERT INTO `permission` VALUES (29, '商品信息', NULL, NULL, 7, NULL, 1);
INSERT INTO `permission` VALUES (30, '商品类型信息', NULL, NULL, 7, NULL, 2);
INSERT INTO `permission` VALUES (31, '客户信息', NULL, NULL, 7, NULL, 3);
INSERT INTO `permission` VALUES (32, '供应商信息', NULL, NULL, 7, NULL, 4);
INSERT INTO `permission` VALUES (33, '仓库信息', NULL, NULL, 7, NULL, 5);
INSERT INTO `permission` VALUES (34, '账户信息', NULL, NULL, 7, NULL, 6);
INSERT INTO `permission` VALUES (35, '用户管理', NULL, '/user', 7, NULL, 1);
INSERT INTO `permission` VALUES (36, '角色管理', NULL, '/role', 7, NULL, 2);
INSERT INTO `permission` VALUES (37, '权限管理', NULL, '/permission', 7, NULL, 3);
INSERT INTO `permission` VALUES (38, '机构管理', NULL, NULL, 7, NULL, 4);
INSERT INTO `permission` VALUES (39, '字典管理', NULL, NULL, 7, NULL, 5);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `site_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '管理员', 'qwe', 1);
INSERT INTO `role` VALUES (2, '销售主管', 'qqq', 1);
INSERT INTO `role` VALUES (3, '仓管主管', NULL, 1);
INSERT INTO `role` VALUES (4, '采购主管', 'zz', 1);
INSERT INTO `role` VALUES (5, '质检', NULL, 1);
INSERT INTO `role` VALUES (6, '销售', NULL, 1);
INSERT INTO `role` VALUES (7, '采购', NULL, 1);
INSERT INTO `role` VALUES (37, '零时工', '背锅侠2', 1);
INSERT INTO `role` VALUES (38, '仓管', '123', 1);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `permission_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 227 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (54, 3, 5);
INSERT INTO `role_permission` VALUES (55, 3, 20);
INSERT INTO `role_permission` VALUES (56, 3, 22);
INSERT INTO `role_permission` VALUES (57, 3, 21);
INSERT INTO `role_permission` VALUES (58, 3, 23);
INSERT INTO `role_permission` VALUES (59, 3, 24);
INSERT INTO `role_permission` VALUES (60, 3, 25);
INSERT INTO `role_permission` VALUES (61, 4, 2);
INSERT INTO `role_permission` VALUES (62, 4, 11);
INSERT INTO `role_permission` VALUES (63, 4, 9);
INSERT INTO `role_permission` VALUES (64, 4, 10);
INSERT INTO `role_permission` VALUES (65, 4, 12);
INSERT INTO `role_permission` VALUES (66, 5, 26);
INSERT INTO `role_permission` VALUES (67, 5, 28);
INSERT INTO `role_permission` VALUES (68, 5, 27);
INSERT INTO `role_permission` VALUES (69, 5, 6);
INSERT INTO `role_permission` VALUES (79, 8, 20);
INSERT INTO `role_permission` VALUES (80, 8, 21);
INSERT INTO `role_permission` VALUES (81, 8, 5);
INSERT INTO `role_permission` VALUES (82, 8, 22);
INSERT INTO `role_permission` VALUES (83, 8, 23);
INSERT INTO `role_permission` VALUES (84, 8, 24);
INSERT INTO `role_permission` VALUES (85, 8, 25);
INSERT INTO `role_permission` VALUES (86, 37, 29);
INSERT INTO `role_permission` VALUES (87, 37, 30);
INSERT INTO `role_permission` VALUES (88, 37, 31);
INSERT INTO `role_permission` VALUES (89, 37, 32);
INSERT INTO `role_permission` VALUES (90, 37, 33);
INSERT INTO `role_permission` VALUES (91, 37, 34);
INSERT INTO `role_permission` VALUES (92, 37, 7);
INSERT INTO `role_permission` VALUES (109, 6, 3);
INSERT INTO `role_permission` VALUES (110, 6, 13);
INSERT INTO `role_permission` VALUES (111, 6, 14);
INSERT INTO `role_permission` VALUES (112, 6, 15);
INSERT INTO `role_permission` VALUES (146, 1, 5);
INSERT INTO `role_permission` VALUES (147, 1, 20);
INSERT INTO `role_permission` VALUES (148, 1, 21);
INSERT INTO `role_permission` VALUES (149, 1, 22);
INSERT INTO `role_permission` VALUES (150, 1, 23);
INSERT INTO `role_permission` VALUES (151, 1, 24);
INSERT INTO `role_permission` VALUES (152, 1, 25);
INSERT INTO `role_permission` VALUES (153, 1, 2);
INSERT INTO `role_permission` VALUES (154, 1, 9);
INSERT INTO `role_permission` VALUES (155, 1, 10);
INSERT INTO `role_permission` VALUES (156, 1, 11);
INSERT INTO `role_permission` VALUES (157, 1, 12);
INSERT INTO `role_permission` VALUES (158, 1, 6);
INSERT INTO `role_permission` VALUES (159, 1, 26);
INSERT INTO `role_permission` VALUES (160, 1, 27);
INSERT INTO `role_permission` VALUES (161, 1, 28);
INSERT INTO `role_permission` VALUES (162, 1, 7);
INSERT INTO `role_permission` VALUES (163, 1, 29);
INSERT INTO `role_permission` VALUES (164, 1, 30);
INSERT INTO `role_permission` VALUES (165, 1, 31);
INSERT INTO `role_permission` VALUES (166, 1, 32);
INSERT INTO `role_permission` VALUES (167, 1, 33);
INSERT INTO `role_permission` VALUES (168, 1, 34);
INSERT INTO `role_permission` VALUES (169, 1, 35);
INSERT INTO `role_permission` VALUES (170, 1, 36);
INSERT INTO `role_permission` VALUES (171, 1, 37);
INSERT INTO `role_permission` VALUES (172, 1, 38);
INSERT INTO `role_permission` VALUES (173, 1, 39);
INSERT INTO `role_permission` VALUES (174, 1, 40);
INSERT INTO `role_permission` VALUES (175, 1, 3);
INSERT INTO `role_permission` VALUES (176, 1, 13);
INSERT INTO `role_permission` VALUES (177, 1, 14);
INSERT INTO `role_permission` VALUES (178, 1, 15);
INSERT INTO `role_permission` VALUES (179, 1, 4);
INSERT INTO `role_permission` VALUES (180, 1, 16);
INSERT INTO `role_permission` VALUES (181, 1, 17);
INSERT INTO `role_permission` VALUES (182, 1, 18);
INSERT INTO `role_permission` VALUES (183, 1, 19);
INSERT INTO `role_permission` VALUES (188, 7, 9);
INSERT INTO `role_permission` VALUES (203, 0, 3);
INSERT INTO `role_permission` VALUES (204, 0, 14);
INSERT INTO `role_permission` VALUES (205, 0, 15);
INSERT INTO `role_permission` VALUES (206, 0, 4);
INSERT INTO `role_permission` VALUES (207, 0, 16);
INSERT INTO `role_permission` VALUES (208, 0, 17);
INSERT INTO `role_permission` VALUES (209, 0, 18);
INSERT INTO `role_permission` VALUES (210, 0, 19);
INSERT INTO `role_permission` VALUES (219, 2, 3);
INSERT INTO `role_permission` VALUES (220, 2, 14);
INSERT INTO `role_permission` VALUES (221, 2, 15);
INSERT INTO `role_permission` VALUES (222, 2, 4);
INSERT INTO `role_permission` VALUES (223, 2, 16);
INSERT INTO `role_permission` VALUES (224, 2, 17);
INSERT INTO `role_permission` VALUES (225, 2, 18);
INSERT INTO `role_permission` VALUES (226, 2, 19);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `realname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `telephone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$MieFvgTED163OIzgY3.sVO/lkCYDx02r705dtS.98Ff25zHpqh4Hy', '百里半', '15667778888', NULL, NULL);
INSERT INTO `user` VALUES (2, 'user0', '$2a$10$MieFvgTED163OIzgY3.sVO/lkCYDx02r705dtS.98Ff25zHpqh4Hy', '李四', '18812346666', 'http://192.168.1.114:8848/nacos/img/logo-2000-390.svg', 0);
INSERT INTO `user` VALUES (3, 'user1', '$2a$10$MieFvgTED163OIzgY3.sVO/lkCYDx02r705dtS.98Ff25zHpqh4Hy', '王五', '15888886666', 'http://ossfortest123.oss-cn-hangzhou.aliyuncs.com/images/61e7b02c89264f3da59e55d989f2b9d0.jpg?Expires=1684552674&OSSAccessKeyId=LTAI5tFkN91uejDHbQYxvdiR&Signature=Pi%2BBfFA7NoORw93jgyDroPiU6w0%3D', 0);
INSERT INTO `user` VALUES (4, 'user2', '$2a$10$MieFvgTED163OIzgY3.sVO/lkCYDx02r705dtS.98Ff25zHpqh4Hy', '张三', '13912348888', NULL, 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (49, 4, 3);
INSERT INTO `user_role` VALUES (50, 5, 3);
INSERT INTO `user_role` VALUES (51, 6, 3);
INSERT INTO `user_role` VALUES (52, 37, 3);
INSERT INTO `user_role` VALUES (55, 2, 2);
INSERT INTO `user_role` VALUES (56, 3, 2);
INSERT INTO `user_role` VALUES (57, 4, 2);
INSERT INTO `user_role` VALUES (58, 5, 2);
INSERT INTO `user_role` VALUES (59, 5, 4);
INSERT INTO `user_role` VALUES (60, 6, 4);
INSERT INTO `user_role` VALUES (61, 7, 4);
INSERT INTO `user_role` VALUES (62, 1, 1);
INSERT INTO `user_role` VALUES (63, 2, 1);
INSERT INTO `user_role` VALUES (64, 3, 1);
INSERT INTO `user_role` VALUES (65, 4, 1);
INSERT INTO `user_role` VALUES (66, 5, 1);
INSERT INTO `user_role` VALUES (67, 6, 1);
INSERT INTO `user_role` VALUES (68, 7, 1);
INSERT INTO `user_role` VALUES (69, 37, 1);
INSERT INTO `user_role` VALUES (70, 38, 1);

SET FOREIGN_KEY_CHECKS = 1;
