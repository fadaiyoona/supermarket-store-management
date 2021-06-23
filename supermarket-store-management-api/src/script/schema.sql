CREATE TABLE `t_commodity` (
  `code` varchar(50) NOT NULL COMMENT '商品编号',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `price` decimal(10,2) NOT NULL COMMENT '商品单价',
  `supplier_code` varchar(50) NOT NULL COMMENT '供应商编号',
  `introduction` varchar(255) DEFAULT NULL COMMENT '简介',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

CREATE TABLE `t_purchase` (
  `code` varchar(50) NOT NULL COMMENT '采购清单号',
  `detail_code` varchar(50) NOT NULL COMMENT '采购明细号',
  `staff_code` varchar(50) NOT NULL COMMENT '员工编号',
  `commodity_code` varchar(50) NOT NULL COMMENT '商品编号',
  `num` int(11) NOT NULL COMMENT '采购数量',
  `total_price` decimal(10,2) NOT NULL COMMENT '采购总价',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间(采购时间)',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采购表';

CREATE TABLE `t_staff` (
  `code` varchar(50) NOT NULL COMMENT '编号',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `level` varchar(255) NOT NULL COMMENT '级别',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工表';

CREATE TABLE `t_supplier` (
  `code` varchar(50) NOT NULL COMMENT '供应商编号',
  `name` varchar(255) NOT NULL COMMENT '供应商名称',
  `short_name` varchar(255) NOT NULL COMMENT '供应商简称',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `tellphone` varchar(255) DEFAULT NULL COMMENT '公司电话',
  `email` varchar(255) DEFAULT NULL COMMENT '邮件',
  `contacts` varchar(255) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(255) DEFAULT NULL COMMENT '联系人电话',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商表';

