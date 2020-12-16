DROP TABLE IF EXISTS `storage_tbl`;
CREATE TABLE `storage_tbl` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`commodity_code` varchar(255)DEFAULT NULL,
`count` int(11)DEFAULT 0,
PRIMARY KEY(`id`),
UNIQUE KEY(`commodity_code`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `order_tbl`;
CREATE TABLE `order_tbl` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` varchar(255)DEFAULT NULL,
`commodity_code` varchar(255)DEFAULT NULL,
`count` int(11)DEFAULT 0,
`money` int(11)DEFAULT 0,
PRIMARY KEY(`id`)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE IF NOT EXISTS `undo_log`
(
`branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
`xid` VARCHAR(100)NOT NULL COMMENT 'global transaction id',
`context` VARCHAR(128)NOT NULL COMMENT 'undo_log context,such as serialization',
`rollback_info` LONGBLOB NOT NULL COMMENT 'rollback info',
`log_status` INT(11)NOT NULL COMMENT '0:normal status,1:defense status',
`log_created` DATETIME(6)NOT NULL COMMENT 'create datetime',
`log_modified` DATETIME(6)NOT NULL COMMENT 'modify datetime',
UNIQUE KEY `ux_undo_log`(`xid`, `branch_id`)
)ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8 COMMENT = 'AT transaction mode undo table';