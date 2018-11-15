-- ----------------------------
-- Table structure for cms_employee 雇员表
-- ----------------------------
DROP TABLE IF EXISTS `cms_employee`;
CREATE TABLE `cms_employee` (
  `employee_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_code` varchar(30) NOT NULL COMMENT '雇员编号',
  `name` varchar(50) NOT NULL COMMENT '雇员名称',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `dept` varchar(50) DEFAULT NULL COMMENT '部门',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB COMMENT='雇员表';

-- ----------------------------
-- Table structure for cms_rewarehouse_main 仓库退货单主表
-- ----------------------------
DROP TABLE IF EXISTS `cms_rewarehouse_main`;
CREATE TABLE `cms_rewarehouse_main` (
  `main_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL COMMENT '退货单日期',
  `bill_code` varchar(50) DEFAULT NULL COMMENT '退货单编号',
  `units_id` int(11) DEFAULT NULL COMMENT '公司单位id',
  `handle` varchar(30) DEFAULT NULL COMMENT '经手人',
  `summary` varchar(100) DEFAULT NULL COMMENT '总结，备注',
  `full_gathering` decimal(18,2) DEFAULT NULL COMMENT '应得到金额',
  `gathering` decimal(18,2) DEFAULT NULL COMMENT '实际得到金额',
  `paper_bill_code` varchar(50) DEFAULT NULL COMMENT '纸质退货单编号',
  `memo` varchar(200) DEFAULT NULL COMMENT '备忘',
  `bill_flag` int(2) COMMENT '状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`main_id`)
) ENGINE=InnoDB COMMENT='仓库退货单主表';

-- ----------------------------
-- Table structure for cms_rewarehouse_detail 仓库退货单详情表
-- ----------------------------
DROP TABLE IF EXISTS `cms_rewarehouse_detail`;
CREATE TABLE `cms_rewarehouse_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL COMMENT '退货单日期',
  `bill_code` varchar(50) DEFAULT NULL COMMENT '退货单编号',
  `trade_code` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `full_name` varchar(200) DEFAULT NULL COMMENT '完整名称',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `standard` varchar(50) DEFAULT NULL COMMENT '标准',
  `produce` varchar(50) DEFAULT NULL COMMENT '商品',
  `unit` decimal(18,2) DEFAULT NULL COMMENT '数量单位',
  `qty` decimal(18,2) DEFAULT NULL COMMENT '数量',
  `price` decimal(18,2) DEFAULT NULL COMMENT '单价',
  `tsum` decimal(18,2) COMMENT '总数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB COMMENT='仓库退货单详情表';

-- ----------------------------
-- Table structure for cms_resell_main 销售退货单主表
-- ----------------------------
DROP TABLE IF EXISTS `cms_resell_main`;
CREATE TABLE `cms_resell_main` (
  `main_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL COMMENT '退货单日期',
  `bill_code` varchar(50) DEFAULT NULL COMMENT '退货单编号',
  `units_id` int(11) DEFAULT NULL COMMENT '公司单位id',
  `handle` varchar(30) DEFAULT NULL COMMENT '经手人',
  `summary` varchar(100) DEFAULT NULL COMMENT '总结，备注',
  `full_payment` decimal(18,2) DEFAULT NULL COMMENT '应付出金额',
  `payment` decimal(18,2) DEFAULT NULL COMMENT '实际付出金额',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`main_id`)
) ENGINE=InnoDB COMMENT='销售退货单主表';

-- ----------------------------
-- Table structure for cms_resell_detail 销售退货单详情表
-- ----------------------------
DROP TABLE IF EXISTS `cms_resell_detail`;
CREATE TABLE `cms_resell_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL COMMENT '退货单日期',
  `bill_code` varchar(50) DEFAULT NULL COMMENT '退货单编号',
  `trade_code` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `full_name` varchar(200) DEFAULT NULL COMMENT '完整名称',
  `unit` varchar(30) DEFAULT NULL COMMENT '数量单位',
  `qty` decimal(18,2) DEFAULT NULL COMMENT '数量',
  `price` decimal(18,2) DEFAULT NULL COMMENT '单价',
  `tsum` decimal(18,2) COMMENT '总数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB COMMENT='销售退货单详情表';

-- ----------------------------
-- Table structure for cms_sell_main 销售出货单主表
-- ----------------------------
DROP TABLE IF EXISTS `cms_sell_main`;
CREATE TABLE `cms_sell_main` (
  `main_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL COMMENT '出货单日期',
  `bill_code` varchar(50) DEFAULT NULL COMMENT '出货单编号',
  `units_id` int(11) DEFAULT NULL COMMENT '公司单位id',
  `handle` varchar(30) DEFAULT NULL COMMENT '经手人',
  `summary` varchar(100) DEFAULT NULL COMMENT '总结，备注',
  `full_gathering` decimal(18,2) DEFAULT NULL COMMENT '应收入金额',
  `gathering` decimal(18,2) DEFAULT NULL COMMENT '实际收入金额',
  `bill_qty` decimal(18,2) DEFAULT NULL COMMENT '订单数量',
  `paper_bill_code` varchar(50) DEFAULT NULL COMMENT '纸质订单编号',
  `memo` varchar(200) DEFAULT NULL COMMENT '备忘',
  `bill_flag` int(2) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`main_id`)
) ENGINE=InnoDB COMMENT='销售出货单主表';

-- ----------------------------
-- Table structure for cms_sell_detail 销售出货单详情表
-- ----------------------------
DROP TABLE IF EXISTS `cms_sell_detail`;
CREATE TABLE `cms_sell_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL COMMENT '出货单日期',
  `bill_code` varchar(50) DEFAULT NULL COMMENT '出货单编号',
  `trade_code` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `full_name` varchar(200) DEFAULT NULL COMMENT '完整名称',
  `unit` varchar(30) DEFAULT NULL COMMENT '数量单位',
  `qty` decimal(18,2) DEFAULT NULL COMMENT '数量',
  `price` decimal(18,2) DEFAULT NULL COMMENT '单价',
  `tsum` decimal(18,2) COMMENT '总数',
  `memo` varchar(200) DEFAULT NULL COMMENT '备忘',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `in_price` decimal(18,2) DEFAULT NULL COMMENT '入库价格',
  `pack_unit` varchar(50) DEFAULT NULL COMMENT '包装单位',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB COMMENT='销售出货单详情表';

-- ----------------------------
-- Table structure for cms_account 账单表
-- ----------------------------
DROP TABLE IF EXISTS `cms_account`;
CREATE TABLE `cms_account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL COMMENT '账单日期',
  `bill_code` varchar(50) DEFAULT NULL COMMENT '账单编号',
  `add_gathering` double DEFAULT NULL COMMENT '增加的收入',
  `fact_add_fee` double DEFAULT NULL COMMENT '实际增加的费用',
  `reduce_gathering` double DEFAULT NULL COMMENT '减少的收入',
  `fact_fee` double DEFAULT NULL COMMENT '实际费用',
  `total_balance` double DEFAULT NULL COMMENT '总账目',
  `units_id` int(11) DEFAULT NULL COMMENT '公司单位id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB COMMENT='账单表';

-- ----------------------------
-- Table structure for cms_company 公司表
-- ----------------------------
DROP TABLE IF EXISTS `cms_company`;
CREATE TABLE `cms_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话',
  `qq` varchar(30) DEFAULT NULL COMMENT 'QQ',
  `wechat` varchar(50) DEFAULT NULL COMMENT '微信',
  `tax_num` varchar(50) DEFAULT NULL COMMENT '税号',
  `bank_num` varchar(50) DEFAULT NULL COMMENT '银行账号',
  `bank_name` varchar(200) DEFAULT NULL COMMENT '银行名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB COMMENT='公司表';

-- ----------------------------
-- Table structure for cms_brand 品牌表
-- ----------------------------
DROP TABLE IF EXISTS `cms_brand`;
CREATE TABLE `cms_brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(200) NOT NULL COMMENT '品牌名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB COMMENT='品牌表';

-- ----------------------------
-- Table structure for cms_warehouse_main 仓库进货单主表
-- ----------------------------
DROP TABLE IF EXISTS `cms_warehouse_main`;
CREATE TABLE `cms_warehouse_main` (
  `main_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL COMMENT '进货单日期',
  `bill_code` varchar(50) DEFAULT NULL COMMENT '进货单编号',
  `units_id` int(11) DEFAULT NULL COMMENT '公司单位id',
  `handle` varchar(30) DEFAULT NULL COMMENT '经手人',
  `summary` varchar(100) DEFAULT NULL COMMENT '总结，备注',
  `full_payment` decimal(18,2) DEFAULT NULL COMMENT '应付出金额',
  `payment` decimal(18,2) DEFAULT NULL COMMENT '实际付出金额',
  `bill_qty` decimal(18,2) DEFAULT NULL COMMENT '数量',
  `paper_bill_code` varchar(50) DEFAULT NULL COMMENT '纸质退货单编号',
  `memo` varchar(200) DEFAULT NULL COMMENT '备忘',
  `bill_flag` int(2) COMMENT '状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`main_id`)
) ENGINE=InnoDB COMMENT='仓库进货单主表';

-- ----------------------------
-- Table structure for cms_warehouse_detail 仓库进货单详情表
-- ----------------------------
DROP TABLE IF EXISTS `cms_warehouse_detail`;
CREATE TABLE `cms_warehouse_detail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_date` datetime DEFAULT NULL COMMENT '进货单日期',
  `bill_code` varchar(50) DEFAULT NULL COMMENT '进货单编号',
  `trade_code` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `full_name` varchar(200) DEFAULT NULL COMMENT '完整名称',
  `unit` varchar(30) DEFAULT NULL COMMENT '数量单位',
  `qty` decimal(18,2) DEFAULT NULL COMMENT '数量',
  `price` decimal(18,2) DEFAULT NULL COMMENT '单价',
  `tsum` decimal(18,2) COMMENT '总价',
  `memo` varchar(200) COMMENT '备忘',
  `in_price` decimal(18,2) COMMENT '进货价',
  `pack_unit` varchar(30) COMMENT '包装单位',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`detail_id`)
) ENGINE=InnoDB COMMENT='仓库进货单详情表';

-- ----------------------------
-- Table structure for cms_units 客户单位表
-- ----------------------------
DROP TABLE IF EXISTS `cms_units`;
CREATE TABLE `cms_units` (
  `units_id` int(11) NOT NULL AUTO_INCREMENT,
  `units_code` varchar(50) NOT NULL COMMENT '单位编码',
  `name` varchar(200) NOT NULL COMMENT '单位名称',
  `tax_num` varchar(50) DEFAULT NULL COMMENT '税号',
  `tel_num` varchar(50) DEFAULT NULL COMMENT '电话号码',
  `link_man` varchar(30) DEFAULT NULL COMMENT '联系人',
  `address` varchar(200) DEFAULT NULL COMMENT '联系地址',
  `bank_name` varchar(200) DEFAULT NULL COMMENT '银行名称',
  `bank_num` varchar(50) DEFAULT NULL COMMENT '银行账号',
  `gathering` decimal(18,2) DEFAULT NULL COMMENT '收入金额',
  `payment` decimal(18,2) COMMENT '支出金额',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`units_id`)
) ENGINE=InnoDB COMMENT='客户单位表';

-- ----------------------------
-- Table structure for cms_trades_type 交易商品类型表
-- ----------------------------
DROP TABLE IF EXISTS `cms_trades_type`;
CREATE TABLE `cms_trades_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(200) NOT NULL COMMENT '商品类型名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB COMMENT='交易商品类型表';

-- ----------------------------
-- Table structure for cms_stock 库存表
-- ----------------------------
DROP TABLE IF EXISTS `cms_stock`;
CREATE TABLE `cms_stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `trade_code` varchar(50) NOT NULL COMMENT '商品编号',
  `full_name` varchar(200) NOT NULL COMMENT '完整名称',
  `type` varchar(50) DEFAULT NULL COMMENT '类型',
  `standard` varchar(200) DEFAULT NULL COMMENT '标准',
  `unit` varchar(30) DEFAULT NULL COMMENT '数量单位',
  `produce` varchar(50) DEFAULT NULL COMMENT '商品',
  `qty` decimal(18,2) DEFAULT NULL COMMENT '数量',
  `price` decimal(18,2) DEFAULT NULL COMMENT '入库单价',
  `average_price` decimal(18,2) DEFAULT NULL COMMENT '平均单价',
  `sale_price` decimal(18,2) DEFAULT NULL COMMENT '销售单价',
  `stock_check` decimal(18,2) DEFAULT NULL COMMENT '检查库存量',
  `upper_limit` decimal(18,2) DEFAULT NULL COMMENT '库存上限',
  `lower_limit` decimal(18,2) DEFAULT NULL COMMENT '库存下限',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌id',
  `trades_type_id` int(11) DEFAULT NULL COMMENT '交易商品类型id',
  `pack_qty` decimal(18,2) DEFAULT NULL COMMENT '包装数量',
  `pack_unit` varchar(30) DEFAULT NULL COMMENT '包装单位',
  `memo` varchar(200) DEFAULT NULL COMMENT '备忘',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB COMMENT='库存表';

-- ----------------------------
-- Table structure for cms_sequence 序列表
-- ----------------------------
DROP TABLE IF EXISTS `cms_sequence`;
CREATE TABLE `cms_sequence` (
  `sequence_id` int(11) NOT NULL AUTO_INCREMENT,
  `sequence_code` varchar(50) NOT NULL COMMENT '序列号',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`sequence_id`)
) ENGINE=InnoDB COMMENT='交易序列表';

-- ----------------------------
-- Table structure for cms_sell_profit 销售利润表
-- ----------------------------
DROP TABLE IF EXISTS `cms_sell_profit`;
CREATE TABLE `cms_sell_profit` (
  `profit_id` int(11) NOT NULL AUTO_INCREMENT,
  `bill_code` varchar(50) NOT NULL COMMENT '订单编号',
  `trade_code` varchar(50) NOT NULL COMMENT '商品编号',
  `name` varchar(200) NOT NULL COMMENT '名称',
  `in_price` decimal(18,2) DEFAULT NULL COMMENT '买入价格',
  `out_price` decimal(18,2) DEFAULT NULL COMMENT '卖出价格',
  `qty` decimal(18,2) DEFAULT NULL COMMENT '数量',
  `profit` decimal(18,2) DEFAULT NULL COMMENT '利润',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_user_id` int(11) COMMENT '创建者ID',
  `update_user_id` int(11) COMMENT '修改者ID',
  PRIMARY KEY (`profit_id`)
) ENGINE=InnoDB COMMENT='交易序列表';

-- --------------------------
-- init data
-- --------------------------
INSERT INTO `cms_employee` (`employee_code`,`name`,`sex`,`dept`,`phone`,`remark`,`create_user_id`,`update_user_id`) VALUES
('E1001','王艺','男','销售部','18927567097', NULL,1,1),
('E1002','成彪','男','销售部','18927567097', NULL,1,1);

INSERT INTO `cms_company` (`name`,`address`,`phone`,`qq`,`wechat`,`tax_num`,`bank_num`,`bank_name`,`create_user_id`,`update_user_id`) VALUES
('广州思谱热科仪器有限公司', '广州市天河区东站路1号东站综合楼二楼A区330B房', '13450399531/020-95270013', '563553209', 'lcbiao34', '440106088155428', '3602072809200177385', '工商银行广州燕子岗支行', 1, 1);

INSERT INTO `cms_brand` (`brand_id`,`brand_name`,`create_user_id`,`update_user_id`) VALUES
(1, 'Crystalgen上海', 1, 1),
(2, 'Crystalgen浙江', 1, 1),
(3, 'Kirgen', 1, 1),
(4, 'Axygen', 1, 1);

INSERT INTO cms_units(`units_id`,`units_code`,`name`,`tax_num`,`tel_num`,`link_man`,`address`,`bank_name`,`bank_num`,`gathering`,`payment`,`create_user_id`,`update_user_id`) VALUES
(1, 'U1001','上海科进医疗器材有限公司','3101 1569 0122 32X','021-58955233/1395197','姚一飞','上海市浦东张江高科技园区东区瑞庆路528号19号楼一层','中国银行上海市紫薇路支行','4520 5921 8076', 0, 0, 1, 1),
(2, 'U1002','苏州科创生物技术有限公司','3205 0055 1215 403','0512-89578819','郑小姐','江苏省苏州市东环南路999号中博科技园B幢2265室','工商银行苏州分行道前支行','1102 1110 0900 9069 278', 0, 0, 1, 1),
(3, 'U1003','中山大学附属第六医院','','','陈秀婷','广州市天河区员村二横路26号','','', 0, 0, 1, 1),
(4, 'U1004','广州市海进生物科技有限公司','440106076514980','15800016684','金龙','广州是天河区棠东毓南卢-7号冠达商务中心B211','工商银行广州第三支行','3602028909200805022', 0, 0, 1, 1);

INSERT INTO `cms_trades_type` (`type_id`,`type_name`,`create_user_id`,`update_user_id`) VALUES
(1, '吸头', 1, 1),
(2, 'PCR管', 1, 1),
(3, '培养板', 1, 1),
(4, '普通离心管', 1, 1),
(5, '微量离心管', 1, 1),
(6, '超速离心管', 1, 1),
(7, '冻存盒', 1, 1),
(8, '加样槽', 1, 1),
(9, '安全锁', 1, 1),
(10, 'PCR板', 1, 1),
(11, '吸头盒', 1, 1),
(12, '培养瓶', 1, 1),
(13, '培养皿', 1, 1),
(14, '移液管', 1, 1),
(15, '移液枪', 1, 1),
(16, '孵育盒', 1, 1),
(17, '果蝇管', 1, 1),
(18, '试剂', 1, 1),
(19, '手套', 1, 1);

INSERT INTO `cms_stock` (`trade_code`,`full_name`,`type`,`standard`,`unit`,`produce`,`qty`,`price`,`average_price`,`sale_price`,`stock_check`,`upper_limit`,`lower_limit`,`brand_id`,`trades_type_id`,`pack_qty`,`pack_unit`,`create_user_id`,`update_user_id`) VALUES
('T1391015', '1.70ml 微量离心管,无DNA酶/RNA酶无热源, 灭菌', '23-2052', '500只/盒,10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 2, 4, 10, '箱', 1, 1),
('T1391017', '1.70ml 微量离心管,无DNA酶/RNA酶无热源, 深咖啡色，避光，灭菌', '23-2052A', '500只/盒,10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 2, 5, 10, '箱', 1, 1),
('T1391016', '1.70ml 微量离心管,无DNA酶/RNA酶无热源, 棕色，灭菌', '23-2052R', '50只/架,8架/组，10组/箱', '架', '', 0, 0, 0, 0, 0, 0, 0, 2, 5, 80, '箱', 1, 1),
('T1391018', '1.70ml 微量离心管,无DNA酶/RNA酶无热源, 紫色，灭菌', '23-2052V', '500只/盒,10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 2, 5, 10, '箱', 1, 1),
('T1391019', '1.70ml 微量离心管,无DNA酶/RNA酶无热源, 黄色，灭菌', '23-2052Y', '500只/盒,10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 2, 5, 10, '箱', 1, 1),
('T1391002', '14ml 细胞培养管/离心管, PP(聚丙烯), 无DNA酶/RNA酶无热源,灭菌', '23-2059', '25只/包, 20包', '包', '', 2.00, 22.00, 22.00, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T0001001', '14ml 细胞培养管/离心管, PS, 无DNA酶/RNA酶无热源,灭菌', '23-2059S', '50只/架, 10架', '架', '', 1.00, 3.00, 3.00, 0, 0, 0, 0, 2, 4, 10, '箱', 1, 1),
('T1391006', '50ml 离心管,PP(聚丙烯), 锥底, 印刷标记区, 无DNA酶/RNA酶无热源', '23-2262', '25只/架,20架/箱', '架', '', 1.00, 31.00, 31.00, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T1391005', '50ml 离心管,PP(聚丙烯), 锥底, 印刷标记区, 无DNA酶/RNA酶无热源, 灭菌', '23-2263', '25只/包,20包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T1391004', '15ml 离心管,PP(聚丙烯), 锥底, 印刷标记区, 无DNA酶/RNA酶无热源, 灭菌', '23-2265', '50只/架,10架/箱', '架', '', -2.00, 0, 0, 0, 0, 0, 0, 2, 4, 10, '箱', 1, 1),
('T1391003', '15ml 离心管,PP(聚丙烯), 锥底,印刷标记区,无DNA酶/RNA酶无热源, 灭菌', '23-2266', '25只/包, 20包/箱', '包', '', 4.00, 5.50, 5.50, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T1391008', '15ml 离心管,PS(聚苯乙烯), 锥底, 印刷标记区, 无DNA酶/RNA酶无热源', '23-2268', '50只/架,10架/箱', '箱', '', 0, 0, 0, 0, 0, 0, 0, 2, 4, 10, '箱', 1, 1),
('T1391009', '50ml 离心管,PS(聚苯乙烯), 锥底, 印刷标记区, 无DNA酶/RNA酶无热源', '23-2271', '25只/架,20架/箱', '架', '', 15.00, 12.00, 12.00, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T1391010', '15ml 离心管,PP(聚丙烯), 5色盖, 印刷标记区, 无DNA酶/RNA酶无热源, 灭菌', '23-2273', '25只/包,20包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T1391011', '15ml 离心管,PP(聚丙烯), 5色盖, 印刷标记区, 无DNA酶/RNA酶无热源, 灭菌', '23-2274', '50只/架,10架/箱', '架', '', 0, 0, 0, 0, 0, 0, 0, 2, 4, 10, '箱', 1, 1),
('T1391012', '50ml 离心管,PP(聚丙烯), 5色盖, 印刷标记区, 无DNA酶/RNA酶无热源, 灭菌', '23-2275', '25只/包,20包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T1391013', '50ml 离心管,PP(聚丙烯), 5色盖, 印刷标记区, 无DNA酶/RNA酶无热源, 灭菌', '23-2276', '25只/架,20架/箱', '架', '', 8.00, 4.50, 4.50, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T1391014', '10ml 离心管,PP(聚柄烯), 自立, 印刷标记区, 无DNA酶/RNA酶无热源, 灭菌', '23-3260', '25只/包,20包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T1391007', '50ml 离心管,PP(聚丙烯), 自立, 印刷标记区, 无DNA酶/RNA酶无热源, 灭菌', '23-3262', '25只/包,20包/箱', '箱', '', 0, 0, 0, 0, 0, 0, 0, 2, 4, 20, '箱', 1, 1),
('T1401054', '0.1-10ul吸嘴盒，封塑包装', 'KG1000', '10个/组', '组', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, '组', 1, 1),
('T1391024', '0.1-10ul吸嘴，无色，带刻度，袋装', 'KG1011', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1391032', '0.1-10ul吸嘴，无色，带刻度,盒装灭菌', 'KG1031', '96支/盒，10盒/组，5组/箱', '盒', '', 40.00, 9.00, 9.00, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401050', '0.1-10ul吸嘴，无色，带刻度，叠装灭菌', 'KG1051', '96支/层，5层/叠/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401055', '0.5-10ul吸嘴盒，封塑包装', 'KG1100', '10个/组', '组', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, '组', 1, 1),
('T1391026', '0.5-10ul吸嘴，无色，带刻度，袋装', 'KG1111', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1391029', '0.1-10ul吸嘴，无色，盒装', 'KG1121', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1391033', '0.5-10ul吸嘴，无色，带刻度,盒装灭菌', 'KG1131', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401051', '0.5-10ul吸嘴，无色，带刻度，叠装灭菌', 'KG1151', '96支/层，5层/叠/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401056', '1-200ul吸嘴盒，封塑包装', 'KG1200', '10个/组', '组', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, '组', 1, 1),
('T1391027', '1-200ul吸嘴，黄色，带刻度，袋装', 'KG1212', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1391030', '1-200ul吸嘴，黄色，带刻度，盒装', 'KG1222', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1391034', '1-200ul吸嘴，黄色，带刻度，盒装灭菌', 'KG1232', '96支/盒，10盒/组，5组/箱', '盒', '', 50.00, 10.10, 10.10, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401052', '1-200ul吸嘴，黄色，带刻度，叠装灭菌', 'KG1252', '96支/层，5层/叠/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401057', '100-1000ul吸嘴盒，封塑包装', 'KG1300', '10个/组', '组', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, '组', 1, 1),
('T1391028', '100-1000ul吸嘴，蓝色，带刻度，袋装', 'KG1313', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1391031', '100-1000ul吸嘴，蓝色，带刻度，盒装', 'KG1323', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401035', '100-1000ul吸嘴，蓝色，带刻度，盒装灭菌', 'KG1333', '96支/盒，10盒/组，5组/箱', '盒', '', 50.00, 11.20, 11.20, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401053', '100-1000ul吸嘴，蓝色，带刻度，叠装灭菌', 'KG1353', '96支/层，5层/叠/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401088', '1ml血清移液管', 'KG1411', '50支/包，20包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 14, 20, '箱', 1, 1),
('T1401089', '2ml血清移液管', 'KG1421', '50支/包，20包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 14, 20, '箱', 1, 1),
('T1401090', '5ml血清移液管', 'KG1431', '50支/包，4包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 14, 4, '箱', 1, 1),
('T1401091', '10ml血清移液管', 'KG1441', '50支/包，4包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 14, 4, '箱', 1, 1),
('T1401092', '25ml血清移液管', 'KG1451', '25支/包，8包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 14, 8, '箱', 1, 1),
('T1401093', '50ml血清移液管', 'KG1461', '25支/包，4包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 14, 4, '箱', 1, 1),
('T1401058', '0.65ml微量离心管，无色，超清晰，带刻度', 'KG2111', '500个/包，2包/盒，10盒/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 20, '组', 1, 1),
('T1401062', '1.5ml微量离心管，橙色，超清晰，带刻度', 'KG2210', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401059', '1.5ml微量离心管，无色，超清晰，带刻度', 'KG2211', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401067', '1.5ml微量离心管，琥珀色，超清晰，带刻度', 'KG221A', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401068', '1.5ml微量离心管，杂色，超清晰，带刻度', 'KG221AS', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401066', '1.5ml微量离心管，蓝色，超清晰，带刻度', 'KG221B', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401064', '1.5ml微量离心管，绿色，超清晰，带刻度', 'KG221G', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401063', '1.5ml微量离心管，粉色，超清晰，带刻度', 'KG221P', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401065', '1.5ml微量离心管，紫色，超清晰，带刻度', 'KG221V', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401061', '1.5ml微量离心管，黄色，超清晰，带刻度', 'KG221Y', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401086', '0.2ml 八联排管盖，凸盖，无色', 'KG2411', '125排/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 2, 10, '箱', 1, 1),
('T1401087', '0.2ml 八联排管盖，平盖，透明，无色', 'KG2431', '125排/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 2, 10, '箱', 1, 1),
('T1401085', '0.2ml八联排管', 'KG2501', '125排/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 2, 10, '箱', 1, 1),
('T1401084', '0.2ml八联排管，无色 凸盖', 'KG2511', '125排/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 2, 10, '箱', 1, 1),
('T1401083', '0.2ml 八联排管，平盖透明', 'KG2531', '125排/ 盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 2, 10, '箱', 1, 1),
('T1391020', '15ml锥形离心管，8000g，PP，灭菌', 'KG2611', '25个/包，20包/箱', '包', '', 20.00, 22.00, 22, 0, 0, 0, 0, 3, 4, 20, '箱', 1, 1),
('T1391021', '15ml锥形离心管，8000g，PP，灭菌，带架', 'KG2621', '50个/架，10架/箱', '架', '', 0, 0, 0, 0, 0, 0, 0, 3, 4, 10, '箱', 1, 1),
('T1401094', '1.8ml/81格，纸质，联盖盒', 'KG2701', '100个/箱', '个', '', 0, 0, 0, 0, 0, 0, 0, 3, 7, 100, '箱', 1, 1),
('T1401095', '1.5ml/100格，纸质', 'KG2702', '100个/箱', '个', '', 0, 0, 0, 0, 0, 0, 0, 3, 7, 100, '箱', 1, 1),
('T1401096', '2.0ml/100格，PC质', 'KG2703', '100个/箱', '个', '', 0, 0, 0, 0, 0, 0, 0, 3, 7, 100, '箱', 1, 1),
('T1391022', '50ml锥型离心管，9500g，PP，灭菌', 'KG2811', '25个/包，20包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 4, 20, '箱', 1, 1),
('T1391023', '50ml锥形离心管，9500g，PP，灭菌，带架', 'KG2821', '25个/架，20架/箱', '架', '', 0, 0, 0, 0, 0, 0, 0, 3, 4, 20, '箱', 1, 1),
('T1401060', '2.0ml微量离心管，无色，超清晰，带刻度', 'KG2911', '500个/包/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 5, 10, '箱', 1, 1),
('T1401075', '无粉乳胶，表面防滑处理，不粘胶，舒适牢固，特小号', 'KG3100', '100个/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401076', '无粉乳胶，表面防滑处理，不粘胶，舒适牢固，Small', 'KG3110', '100个/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401077', '无粉乳胶，表面防滑处理，不粘胶，舒适牢固，Middle', 'KG3120', '100个/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401078', '无粉乳胶，表面防滑处理，不粘胶，舒适牢固，Large', 'KG3130', '100个/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401079', '无粉乳胶，表面防滑处理，不粘胶，XS，独立包装', 'KG3200', '1副/包，30包/盒，20盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 20, '箱', 1, 1),
('T1401080', '无粉乳胶，表面防滑处理，不粘胶，Small，独立包装', 'KG3210', '1副/包，30包/盒，20盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 20, '箱', 1, 1),
('T1401081', '无粉乳胶，表面防滑处理，不粘胶，Middle，独立包装', 'KG3220', '1副/包，30包/盒，20盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 20, '箱', 1, 1),
('T1401082', '无粉乳胶，表面防滑处理，不粘胶，Large，独立包装', 'KG3230', '1副/包，30包/盒，20盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 20, '箱', 1, 1),
('T1391025', '50ml自立离心管，9500g，灭菌', 'KG3282', '20个/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 4, 10, '箱', 1, 1),
('T1401069', '无粉蓝色丁腈手套，小号', 'KG3311', '100个/盒，10盒/箱', '盒', '', 45.00, 5.00, 5.00, 0, 10, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401072', '无粉白色丁腈手套，小号', 'KG3312', '100个/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401070', '无粉蓝色丁腈手套，中号', 'KG3321', '100个/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401073', '无粉白色丁腈手套，中号', 'KG3322', '100个/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401071', '无粉蓝色丁腈手套，大号', 'KG3331', '100个/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401074', '无粉白色丁腈手套，大号', 'KG3332', '100个/盒，10盒/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 19, 10, '箱', 1, 1),
('T1401036', '0.1-10ul滤芯吸嘴，无色，袋装', 'KG5011', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401040', '0.1-10ul滤芯吸嘴，无色，盒装灭菌', 'KG5031', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401037', '0.5-10ul滤芯吸嘴，无色，袋装', 'KG5111', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401041', '0.5-10ul滤芯吸嘴，无色，盒装灭菌', 'KG5131', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401038', '1-200ul滤芯吸嘴，黄色，袋装', 'KG5212', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401042', '1-200ul滤芯吸嘴，黄色，盒装灭菌', 'KG5232', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401039', '100-1000ul滤芯吸嘴，蓝色，袋装', 'KG5313', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401043', '100-1000ul滤芯吸嘴，蓝色，盒装灭菌', 'KG5333', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401044', '1-100ul滤芯吸嘴，黄色，袋装', 'KG5411', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401045', '1-100ul滤芯吸嘴，黄色，盒装灭菌', 'KG5431', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401046', '1-20ul滤芯吸嘴，黄色，袋装', 'KG5512', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401047', '1-20ul滤芯吸嘴，黄色，盒装灭菌', 'KG5532', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1401048', '1-50ul滤芯吸嘴，黄色，袋装', 'KG5611', '1000支/包，10包/箱', '包', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 10, '箱', 1, 1),
('T1401049', '1-50ul滤芯吸嘴，黄色，盒装灭菌', 'KG5631', '96支/盒，10盒/组，5组/箱', '盒', '', 0, 0, 0, 0, 0, 0, 0, 3, 1, 50, '箱', 1, 1),
('T1500002', '0.5～10ul盒装灭菌吸头', 'T-300-R-S', '96个/盒，10盒/彩盒,5彩盒/箱', '盒', '', 2.00, 15.20, 15.20, 0, 0, 0, 0, 4, 1, 50, '箱', 1, 1),
('T1500003', '200ul盒装灭菌黄吸头', 'T-200-Y-R-S', '96个/盒，10盒/彩盒，5彩盒/箱', '盒', '', 2.00, 15.20, 15.20, 0, 0, 0, 0, 4, 1, 50, '箱', 1, 1);

INSERT INTO `cms_sequence` (`sequence_code`, `name`) VALUES
('1000065', '入库'),
('1000033', '出库'),
('1500013', '商品'),
('1000043', '采购退货'),
('10000046', '销售退货');

COMMIT;
