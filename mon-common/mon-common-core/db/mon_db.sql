-- 请先设置mysql的my.cnf配置文件支持utf8mb4字符
create database mon_db default character set utf8mb4 collate utf8mb4_general_ci;

-- ----------------------------
-- Table structure for sys_menu 菜单表
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(2) DEFAULT NULL COMMENT '类型,0：模块 1：目录 2：菜单  3：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  `layer` varchar(50) COMMENT '标记该节点所在树的层级,从0开始',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB COMMENT='菜单管理';
-- ----------------------------
-- Table structure for sys_dept 部门表
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `dept_id` int(4) NOT NULL AUTO_INCREMENT,
  `parent_id` int(4) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  `layer` varchar(50) COMMENT '标记该节点所在树的层级,从0开始',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB  COMMENT='部门';
-- ----------------------------
-- Table structure for sys_role 角色表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(4) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `dept_id` int(4) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB COMMENT='角色';
-- ----------------------------
-- Table structure for sys_role_menu 角色菜单对应关系表
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(4) DEFAULT NULL COMMENT '角色ID',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user_id` int(11) COMMENT '操作人ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='角色与菜单对应关系';
-- ----------------------------
-- Table structure for sys_user 系统用户表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) DEFAULT NULL COMMENT '盐',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `dept_id` int(4) DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB COMMENT='系统用户';

-- ----------------------------
-- Table structure for sys_user_token 系统用户Token表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_token`;
CREATE TABLE `sys_user_token` (
  `user_id` int(11) NOT NULL,
  `token` varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB COMMENT='系统用户Token';
-- ----------------------------
-- Table structure for sys_user_role 用户与角色对应关系表
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(4) DEFAULT NULL COMMENT '角色ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB COMMENT='用户与角色对应关系';


-- ----------------------------
-- 初始化数据
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, 0, '权限中心', '/pcenter', NULL, 0, 'ios-lock', 0, NOW(), NOW(), 1, 1, 0);
INSERT INTO `sys_menu` VALUES (2, 0, '用户中心', '/ucenter', NULL, 0, 'ios-contacts', 1, NOW(), NOW(), 1, 1, 0);
INSERT INTO `sys_menu` VALUES (3, 0, '商品中心', '/wcenter', NULL, 0, 'ios-basket', 2, NOW(), NOW(), 1, 1, 0);
INSERT INTO `sys_menu` VALUES (4, 0, '订单中心', '/ocenter', NULL, 0, 'ios-paper', 3, NOW(), NOW(), 1, 1, 0);
INSERT INTO `sys_menu` VALUES (5, 0, '数据中心', '/dcenter', NULL, 0, 'ios-stats', 4, NOW(), NOW(), 1, 1, 0);
INSERT INTO `sys_menu` VALUES (6, 1, '菜单管理', '/pcenter/menu', NULL, 1, 'md-paper', 0, NOW(), NOW(), 1, 1, 1);
INSERT INTO `sys_menu` VALUES (7, 1, '角色管理', '/pcenter/role', NULL, 1, 'ios-shirt', 1, NOW(), NOW(), 1, 1, 1);
INSERT INTO `sys_menu` VALUES (8, 1, '部门管理', '/pcenter/dept', NULL, 1, 'ios-cube', 2, NOW(), NOW(), 1, 1, 1);
INSERT INTO `sys_menu` VALUES (9, 1, '系统用户管理', '/pcenter/sysuser', '', 1, 'ios-contact', 3, NOW(), NOW(), 1, 1, 1);
INSERT INTO `sys_menu` VALUES (10, 9, '系统用户', '/pcenter/sysuser/user', NULL, 2, 'md-person-add', 0, NOW(), NOW(), 1, 1, 2);
INSERT INTO `sys_menu` VALUES (11, 9, '权限设置', '/pcenter/sysuser/permit', NULL, 2, 'md-shuffle', 1, NOW(), NOW(), 1, 1, 2);
INSERT INTO `sys_menu` VALUES (12, 1, '代码生成', '/pcenter/code', NULL, 1, 'md-hammer', 4, NOW(), NOW(), 1, 1, 1);

INSERT INTO `sys_role` VALUES (1, '超级管理员', '拥有系统最高权限', NULL, NOW(), NOW(), 1, 1);
INSERT INTO `sys_user_role` VALUES (1, 1, 1, NOW(), NOW(), 1, 1);

INSERT INTO `sys_role_menu` VALUES (1, 1, 1, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (2, 1, 2, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (3, 1, 3, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (4, 1, 4, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (5, 1, 5, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (6, 1, 6, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (7, 1, 7, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (8, 1, 8, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (9, 1, 9, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (10, 1, 10, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (11, 1, 11, NOW(), 1);
INSERT INTO `sys_role_menu` VALUES (12, 1, 12, NOW(), 1);

INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_time`, `update_time`, `create_user_id`, `update_user_id`) VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e', 'lcbiao34@gmail.com', '13450399531', '1', NOW(), NOW(), 1, 1);
COMMIT;
