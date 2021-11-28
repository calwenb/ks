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

 Date: 28/11/2021 23:38:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`
(
    `cart_id`    int                                                          NOT NULL AUTO_INCREMENT COMMENT '购物车id',
    `goods_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品的名称',
    `price`      double                                                       NOT NULL COMMENT '商品的价格',
    `buy_num`    int                                                          NOT NULL COMMENT '购买的数量',
    `stock`      int                                                          NOT NULL COMMENT '库存',
    `p_id`       int                                                          NOT NULL COMMENT '产品名称',
    `u_id`       int                                                          NOT NULL COMMENT '用户id',
    PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 66
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart`
VALUES (125, 'g2000t恤1', 29.9, 1, 995, 17, 3);
INSERT INTO `cart`
VALUES (128, 'g2000t恤2', 29.9, 1, 996, 18, 3);
INSERT INTO `cart`
VALUES (129, 'g2000t恤3', 39.9, 3, 0, 19, 3);
INSERT INTO `cart`
VALUES (130, 'g2000t恤4', 39, 1, 996, 20, 3);
INSERT INTO `cart`
VALUES (131, 'g2000t恤5', 39.9, 1, 996, 21, 3);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`
(
    `id`          int                                                     NOT NULL AUTO_INCREMENT COMMENT '商品id',
    `name`        varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '商品名称',
    `price`       double                                                  NULL DEFAULT NULL COMMENT '商品价格',
    `category`    varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci  NULL DEFAULT NULL COMMENT '商品种类',
    `pnum`        int                                                     NULL DEFAULT NULL COMMENT '商品数量',
    `imgurl`      varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
    `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品备注',
    `size`        varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品尺码',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 48
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '商品表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods`
VALUES (15, '马克华菲卫衣2', 59.9, '卫衣', 998, 'images/goods/wy5.jpg', '非常不错的卫衣，适合情侣穿', 'l');
INSERT INTO `goods`
VALUES (16, 'g2000t恤', 29, 't恤', 995, 'images/goods/tx1.jpg', '非常不错的t恤', 'l');
INSERT INTO `goods`
VALUES (17, 'g2000t恤1', 29.9, 't恤', 995, 'images/goods/tx2.jpg', '很不错的t恤', 'l');
INSERT INTO `goods`
VALUES (18, 'g2000t恤2', 29.9, 't恤', 996, 'images/goods/tx3.jpg', '很不错的t恤,穿起来很有气质', 'l');
INSERT INTO `goods`
VALUES (19, 'g2000t恤3', 39.9, 't恤', 0, 'images/goods/tx4.jpg', '非常不错的t恤', 'l');
INSERT INTO `goods`
VALUES (20, 'g2000t恤4', 39, 't恤', 996, 'images/goods/tx5.jpg', '很适合夏天穿', 'l');
INSERT INTO `goods`
VALUES (21, 'g2000t恤5', 39.9, 't恤', 996, 'images/goods/tx6.jpg', '很适合冬天穿', 'l');
INSERT INTO `goods`
VALUES (22, '马克卫衣', 59, '卫衣', 999, 'images/goods/wy1.jpg', '非常不错的卫衣，很保暖', 'l');
INSERT INTO `goods`
VALUES (23, '华菲卫衣', 59.9, '卫衣', 999, 'images/goods/wy2.jpg', '穿起来很酷', 'l');
INSERT INTO `goods`
VALUES (24, '马克华菲卫衣1', 59.9, '卫衣', 999, 'images/goods/wy3.jpg', '很适合冬天穿', 'l');
INSERT INTO `goods`
VALUES (25, '马克卫衣1', 59, '卫衣', 999, 'images/goods/wy4.jpg', '非常不错的卫衣，适合情侣穿', 'l');
INSERT INTO `goods`
VALUES (26, '华菲卫衣1', 69.9, '卫衣', 999, 'images/goods/wy6.jpg', '非常不错的卫衣，适合情侣穿', 'l');
INSERT INTO `goods`
VALUES (27, '商务衬衫', 49, '衬衫', 998, 'images/goods/cs1.jpg', '非常不错的衬衫', 'l');
INSERT INTO `goods`
VALUES (28, '商务衬衫1', 49.9, '衬衫', 998, 'images/goods/cs2.jpg', '穿起来很有气质', 'l');
INSERT INTO `goods`
VALUES (29, '商务衬衫2', 59.9, '衬衫', 999, 'images/goods/cs3.jpg', '很不错的衬衫，与西装很搭', 'l');
INSERT INTO `goods`
VALUES (30, '商务衬衫3', 59, '衬衫', 999, 'images/goods/cs4.jpg', '非常不错的衬衫，与西装天配', 'l');
INSERT INTO `goods`
VALUES (31, '商务衬衫4', 59, '衬衫', 998, 'images/goods/cs5.jpg', '非常不错的衬衫', 'l');
INSERT INTO `goods`
VALUES (32, '商务衬衫5', 59, '衬衫', 999, 'images/goods/cs6.jpg', '非常不错的衬衫，显瘦', 'l');
INSERT INTO `goods`
VALUES (33, '休闲裤', 29, '裤子', 997, 'images/goods/kz1.jpg', '非常不错的裤子', 'l');
INSERT INTO `goods`
VALUES (34, '休闲裤1', 59, '裤子', 999, 'images/goods/kz2.jpg', '不错的裤子', 'l');
INSERT INTO `goods`
VALUES (35, '休闲裤2', 59, '裤子', 999, 'images/goods/kz3.jpg', '非常不错的长裤', 'l');
INSERT INTO `goods`
VALUES (36, '休闲裤3', 59.9, '裤子', 999, 'images/goods/kz4.jpg', '很不错的裤子', 'l');
INSERT INTO `goods`
VALUES (39, '休闲裤4', 59, '裤子', 999, 'images/goods/kz5.jpg', '很不错的裤子', 'l');
INSERT INTO `goods`
VALUES (40, '休闲裤5', 59, '裤子', 999, 'images/goods/kz6.jpg', '穿起来显高的裤子', 'l');
INSERT INTO `goods`
VALUES (41, '保暖外套', 79.9, '外套', 999, 'images/goods/wt1.jpg', '非常不错的外套', 'l');
INSERT INTO `goods`
VALUES (42, '保暖外套1', 69.9, '外套', 998, 'images/goods/wt2.jpg', '很适合冬天穿', 'l');
INSERT INTO `goods`
VALUES (43, '保暖外套2', 59, '外套', 999, 'images/goods/wt3.jpg', '真的很保暖', 'l');
INSERT INTO `goods`
VALUES (44, '保暖外套3', 69, '外套', 999, 'images/goods/wt4.jpg', '很适合冬天穿，足够保暖', 'l');
INSERT INTO `goods`
VALUES (45, '保暖外套4', 69.9, '外套', 999, 'images/goods/wt5.jpg', '寒冬必备的外穿衣服', 'l');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
    `id`          int                                                     NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `login_name`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '登录账号',
    `linkman`     varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '联系人',
    `address`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
    `phonenumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '联系电话',
    `amount`      double(8, 2)                                            NOT NULL COMMENT '总金额',
    `time`        timestamp                                               NOT NULL COMMENT '下单时间',
    `remark`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL     DEFAULT '无' COMMENT '备注',
    `status`      int                                                     NOT NULL DEFAULT 1 COMMENT '订单状态 0:无效订单,1:已经下单,2:已完成的订单',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 42
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '订单表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order`
VALUES (68, 'wen', '文海龙', '1', '1234', 87.90, '2021-11-28 18:04:56', '无', 1);
INSERT INTO `order`
VALUES (69, 'wen', '文海龙', '1', '1234', 87.90, '2021-11-28 18:05:19', '无', 1);
INSERT INTO `order`
VALUES (72, 'long', '文海龙', '1', '1234', 219.40, '2021-11-28 18:11:51', '无', 2);
INSERT INTO `order`
VALUES (73, 'long', '文海龙', '1', '1234', 58.90, '2021-11-28 18:14:01', '无', 1);
INSERT INTO `order`
VALUES (76, 'long', '文海龙', '1', '1234', 97.90, '2021-11-28 20:16:50', '无', 1);
INSERT INTO `order`
VALUES (81, 'long', '文海龙', '1', '1234', 58.90, '2021-11-28 20:24:34', '无', 1);
INSERT INTO `order`
VALUES (82, 'long', '文海龙', '广西南宁市兴宁区燕子岭路燕子岭住宅小区', '17607889421', 98.90, '2021-11-28 20:27:28', '无', 1);
INSERT INTO `order`
VALUES (83, 'long', '文海龙', '广西南宁市兴宁区燕子岭路燕子岭住宅小区', '17607889421', 128.80, '2021-11-28 20:28:19', '无', 1);
INSERT INTO `order`
VALUES (84, 'long', '文海龙', '广西南宁市兴宁区燕子岭路燕子岭住宅小区', '17607889421', 58.90, '2021-11-28 20:37:43', '无', 1);
INSERT INTO `order`
VALUES (87, 'long', '文海龙', '广西南宁市兴宁区燕子岭路燕子岭住宅小区', '17607889421', 197.70, '2021-11-28 20:39:03', '无', 1);
INSERT INTO `order`
VALUES (88, 'long', '文海龙', '广西南宁市兴宁区燕子岭路燕子岭住宅小区', '17607889421', 197.70, '2021-11-28 20:39:06', '无', 1);
INSERT INTO `order`
VALUES (89, 'long', '文海龙', '广西南宁市兴宁区燕子岭路燕子岭住宅小区', '17607889421', 207.60, '2021-11-28 20:40:02', '无', 1);
INSERT INTO `order`
VALUES (92, 'long', '文海龙', '广西南宁市兴宁区燕子岭路燕子岭住宅小区', '17607889421', 287.40, '2021-11-28 20:44:11', '无', 1);
INSERT INTO `order`
VALUES (94, 'ren', '文海龙', '广西南宁市兴宁区燕子岭路燕子岭住宅小区', '17607889421', 88.00, '2021-11-28 23:36:15', '无', 1);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`
(
    `order_id`   int                                                     NULL DEFAULT NULL COMMENT '订单表的id',
    `goods_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品的名称',
    `goods_size` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '商品的尺寸',
    `buy_num`    int                                                     NOT NULL COMMENT '购买的数量',
    INDEX `order_id` (`order_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '订单项表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item`
VALUES (68, 'g2000t恤', 'l', 2);
INSERT INTO `order_item`
VALUES (69, 'g2000t恤', 'l', 2);
INSERT INTO `order_item`
VALUES (72, 'g2000t恤3', 'l', 4);
INSERT INTO `order_item`
VALUES (72, 'g2000t恤2', 'l', 1);
INSERT INTO `order_item`
VALUES (72, 'g2000t恤1', 'l', 1);
INSERT INTO `order_item`
VALUES (73, 'g2000t恤', 'l', 1);
INSERT INTO `order_item`
VALUES (73, 'g2000t恤1', 'l', 1);
INSERT INTO `order_item`
VALUES (75, 'g2000t恤1', 'l', 1);
INSERT INTO `order_item`
VALUES (76, 'g2000t恤', 'l', 1);
INSERT INTO `order_item`
VALUES (76, 'g2000t恤1', 'l', 1);
INSERT INTO `order_item`
VALUES (76, 'g2000t恤4', 'l', 1);
INSERT INTO `order_item`
VALUES (81, 'g2000t恤', 'l', 1);
INSERT INTO `order_item`
VALUES (81, 'g2000t恤1', 'l', 1);
INSERT INTO `order_item`
VALUES (82, '商务衬衫', 'l', 1);
INSERT INTO `order_item`
VALUES (82, '商务衬衫1', 'l', 1);
INSERT INTO `order_item`
VALUES (83, '保暖外套1', 'l', 1);
INSERT INTO `order_item`
VALUES (83, 'g2000t恤2', 'l', 1);
INSERT INTO `order_item`
VALUES (83, 'g2000t恤', 'l', 1);
INSERT INTO `order_item`
VALUES (84, 'g2000t恤', 'l', 1);
INSERT INTO `order_item`
VALUES (84, 'g2000t恤1', 'l', 1);
INSERT INTO `order_item`
VALUES (87, 'g2000t恤5', 'l', 1);
INSERT INTO `order_item`
VALUES (87, 'g2000t恤4', 'l', 2);
INSERT INTO `order_item`
VALUES (87, 'g2000t恤3', 'l', 2);
INSERT INTO `order_item`
VALUES (89, 'g2000t恤', 'l', 1);
INSERT INTO `order_item`
VALUES (89, 'g2000t恤1', 'l', 1);
INSERT INTO `order_item`
VALUES (89, 'g2000t恤2', 'l', 1);
INSERT INTO `order_item`
VALUES (89, 'g2000t恤3', 'l', 1);
INSERT INTO `order_item`
VALUES (89, 'g2000t恤4', 'l', 1);
INSERT INTO `order_item`
VALUES (89, 'g2000t恤5', 'l', 1);
INSERT INTO `order_item`
VALUES (92, '休闲裤', 'l', 1);
INSERT INTO `order_item`
VALUES (92, 'g2000t恤5', 'l', 1);
INSERT INTO `order_item`
VALUES (92, 'g2000t恤4', 'l', 1);
INSERT INTO `order_item`
VALUES (92, '马克华菲卫衣2', 'l', 1);
INSERT INTO `order_item`
VALUES (92, 'g2000t恤1', 'l', 2);
INSERT INTO `order_item`
VALUES (92, 'g2000t恤2', 'l', 2);
INSERT INTO `order_item`
VALUES (94, '休闲裤', 'l', 1);
INSERT INTO `order_item`
VALUES (94, '商务衬衫4', 'l', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`           int                                                    NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `user_name`    varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
    `login_name`   varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
    `pass_word`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
    `user_type`    int                                                    NOT NULL COMMENT '用户类型 0:超级管理员,1:管理员,2:普通用户',
    `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
    `email`        varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `login_name` (`login_name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 34
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '用户信息表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'wen', 'wen', '123', 0, '10086', '2280400645@qq.com');
INSERT INTO `user`
VALUES (3, 'whl', 'long', '123', 2, '10010', '2280400645@qq.com');
INSERT INTO `user`
VALUES (15, '文海龙', 'whlwhlwhl', '999', 2, '2313', '2280400645@qq.com');
INSERT INTO `user`
VALUES (17, '文海龙', 'whlwhlwhlwhl', '123', 2, '123', '2280400645@qq.com');
INSERT INTO `user`
VALUES (23, '蒋', 'jiangj', '123', 1, '3213', '213200645@qq.com');
INSERT INTO `user`
VALUES (25, '32131', 'jjj', '123', 1, '123', '12300645@qq.com');
INSERT INTO `user`
VALUES (26, '123', 'qwe', '123', 1, '123', '2280400645@qq.com');
INSERT INTO `user`
VALUES (30, 'whl', '111', 'long', 1, '10010', '2280400645@qq.com');
INSERT INTO `user`
VALUES (32, 'pan', 'pan', '123', 1, '123', '2337318824@qq.com');
INSERT INTO `user`
VALUES (33, 'huang', 'huang', '123', 1, '123', '12300645@qq.com');
INSERT INTO `user`
VALUES (34, 'admin', 'admin', '123', 1, '3213', '2280400645@qq.com');
INSERT INTO `user`
VALUES (37, 'ren', 'ren', '123', 2, '17607889421', '2280400645@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
