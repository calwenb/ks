/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 21/11/2021 23:19:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `category` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pnum` int NULL DEFAULT NULL,
  `imgurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '彪马板鞋', 12, '12', 12, '#', '2');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `linkman` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `phonenumber` int NOT NULL COMMENT '联系电话',
  `amount` double(8, 2) NOT NULL COMMENT '总金额',
  `time` timestamp NOT NULL COMMENT '下单时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '无' COMMENT '备注',
  `status` int NOT NULL DEFAULT 1 COMMENT '订单状态 0:无效订单,1:已经下单,2:已完成的订单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (5, 'long', '文海龙', '南宁', 1010, 20.00, '2021-11-06 11:20:35', '无', 1);
INSERT INTO `order` VALUES (6, 'long', '文海龙', '南宁', 1010, 20.00, '2021-11-06 11:20:35', '无', 2);
INSERT INTO `order` VALUES (7, 'long', '文海龙', '南宁', 1010, 20.00, '2021-11-06 11:20:35', '无', 1);
INSERT INTO `order` VALUES (8, 'long', '文海龙', '南宁', 1010, 20.00, '1970-01-02 18:12:03', '无', 1);
INSERT INTO `order` VALUES (10, 'long', '文海龙', '南宁', 1010, 20.00, '1970-01-02 18:12:03', '无', 1);
INSERT INTO `order` VALUES (11, 'long', '文海龙', '南宁', 1010, 20.00, '1970-01-02 18:12:03', '无', 1);
INSERT INTO `order` VALUES (12, 'long', '文海龙', '南宁', 1010, 20.00, '1970-01-02 18:12:03', '无', 1);
INSERT INTO `order` VALUES (13, 'long', '文海龙', '南宁', 1010, 20.00, '1970-01-02 18:12:03', '无', 1);
INSERT INTO `order` VALUES (14, 'long', '文海龙', '南宁', 1010, 20.00, '1970-01-02 18:12:03', '无', 1);
INSERT INTO `order` VALUES (15, 'long', '文海龙', '南宁', 1010, 20.00, '1970-01-02 18:12:03', '无', 1);
INSERT INTO `order` VALUES (33, 'long', '文海龙', '南宁', 1010, 20.00, '2021-11-11 14:47:56', '无', 1);
INSERT INTO `order` VALUES (34, 'long', '文海龙', '南宁', 1010, 20.00, '2021-11-11 14:48:13', '无', 1);
INSERT INTO `order` VALUES (35, 'long', '文海龙', '南宁', 1010, 20.00, '2021-11-11 14:48:21', '无', 1);
INSERT INTO `order` VALUES (36, 'long', '文海龙', '南宁', 1010, 20.00, '2021-11-11 14:48:38', '无', 1);
INSERT INTO `order` VALUES (37, 'long', '文海龙', '南宁', 1010, 20.00, '2021-07-11 14:48:43', '无', 1);
INSERT INTO `order` VALUES (38, 'long', '文海龙', '南宁', 1010, 20.00, '2021-11-18 14:48:49', '无', 1);
INSERT INTO `order` VALUES (39, 'long', '文海龙', '南宁', 1010, 20.00, '2021-11-13 14:49:17', '无', 1);
INSERT INTO `order` VALUES (40, 'long', '文海龙', '南宁', 1010, 20.00, '2021-05-11 14:49:47', '无', 1);
INSERT INTO `order` VALUES (41, 'long', '文海龙', '南宁', 1010, 20.00, '2021-06-11 14:49:53', '无', 1);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `order_id` int NULL DEFAULT NULL COMMENT '订单表的id',
  `goods_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的名称',
  `goods_size` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的尺寸',
  `buy_num` int NOT NULL COMMENT '购买的数量',
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (1, '彪马', '40', 2);
INSERT INTO `order_item` VALUES (1, '彪马', '40', 2);
INSERT INTO `order_item` VALUES (3, '彪马', '40', 2);
INSERT INTO `order_item` VALUES (1, '彪马', '40', 2);
INSERT INTO `order_item` VALUES (4, '彪马', '40', 2);
INSERT INTO `order_item` VALUES (4, '彪马', '40', 2);
INSERT INTO `order_item` VALUES (5, '彪马', '40', 2);
INSERT INTO `order_item` VALUES (6, '彪马', '40', 2);
INSERT INTO `order_item` VALUES (7, '彪马', '40', 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `login_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `pass_word` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `user_type` int NOT NULL COMMENT '用户类型 0:超级管理员,1:管理员,2:普通用户',
  `phone_number` int NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'wen', 'wen', '123', 0, 10086, '2280400645@qq.com');
INSERT INTO `user` VALUES (3, 'whl', 'long', '123', 2, 10010, '2280400645@qq.com');
INSERT INTO `user` VALUES (4, '453242', '432423', '4324', 2, 4324, '2280400645@qq.com');
INSERT INTO `user` VALUES (5, '4324324', '432432', '321312', 2, 3213213, '3440882657@qq.com');
INSERT INTO `user` VALUES (7, '3213123123', '3123123', '31231', 2, 321313, '2280400645@qq.com');
INSERT INTO `user` VALUES (9, '12312321321', '312313', '43242342', 2, 34234324, '2280400645@qq.com');
INSERT INTO `user` VALUES (15, '文海龙', 'whlwhlwhl', '999', 2, 2313, '2280400645@qq.com');
INSERT INTO `user` VALUES (17, '文海龙', 'whlwhlwhlwhl', '123', 2, 123, '2280400645@qq.com');
INSERT INTO `user` VALUES (23, '蒋', 'jiangj', '123', 1, 3213, '213200645@qq.com');
INSERT INTO `user` VALUES (25, '32131', 'jjj', '123', 1, 123, '12300645@qq.com');
INSERT INTO `user` VALUES (26, '123', 'qwe', '123', 1, 123, '2280400645@qq.com');
INSERT INTO `user` VALUES (30, 'whl', '111', 'long', 1, 10010, '2280400645@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
