CREATE TABLE IF NOT EXISTS `broker_message` (
  `message_id` varchar(128) COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息 id',
  `message` varchar(4000) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息内容',
  `try_count` tinyint DEFAULT NULL COMMENT '重试次数',
  `status` varchar(10) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '消息状态(0: 发送中; 1: 发送成功; 2: 发送失败)',
  `next_retry` datetime DEFAULT NULL COMMENT '下次尝试时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;