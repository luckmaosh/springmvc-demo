create table car_seat(
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`gmt_create` datetime NOT NULL COMMENT '创建时间',
`gmt_modified` datetime NOT NULL COMMENT '修改时间',
`seat_no` varchar(64)NOT NULL COMMENT '车位编号',
`location` varchar(64)DEFAULT NULL COMMENT '标志',
`start_time` datetime DEFAULT NULL COMMENT '开始时间',
`end_time` datetime DEFAULT NULL COMMENT '结束时间',
`renter` varchar(128)DEFAULT NULL COMMENT '承租人',
`owner` varchar(64)DEFAULT NULL COMMENT '所有人',
`owner_id` int(11)NOT NULL COMMENT '所有者ID',
`status` int(11)NOT NULL COMMENT '0空闲，1已出租',
`price` int(11)DEFAULT NULL COMMENT '价格',
PRIMARY KEY(`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '车位'
;


create table user(
`id` bigint(20)unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`gmt_create` datetime NOT NULL COMMENT '创建时间',
`gmt_modified` datetime NOT NULL COMMENT '修改时间',
`owner` varchar(64)DEFAULT NULL COMMENT '所有人',
`status` int(11)NOT NULL COMMENT '0正常,-1删除',
`mobile` varchar(128)DEFAULT NULL COMMENT '手机',
`room_no` varchar(128)DEFAULT NULL COMMENT '房间号',
`seat_no` varchar(128)DEFAULT NULL COMMENT '车位号',
`location` varchar(128)DEFAULT NULL COMMENT '小区',
`city` varchar(128)DEFAULT NULL COMMENT 'city',
PRIMARY KEY(`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = '注册用户';


create table rent_record(
`id` bigint(20)unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
`gmt_create` datetime NOT NULL COMMENT '创建时间',
`gmt_modified` datetime NOT NULL COMMENT '修改时间',
`owner` varchar(64)DEFAULT NULL COMMENT '所有人',
`renter` varchar(64)DEFAULT NULL COMMENT '承租人',
`status` int(11)NOT NULL COMMENT '0空闲，1已出租',
`mobile` varchar(128)DEFAULT NULL COMMENT '手机',
`renter_mobile` varchar(128)DEFAULT NULL COMMENT '承租人手机',
`price` int(11)DEFAULT NULL COMMENT '价格',
PRIMARY KEY(`id`)
)ENGINE = InnoDB AUTO_INCREMENT = 62 DEFAULT CHARSET = utf8 COMMENT = '出租记录';






