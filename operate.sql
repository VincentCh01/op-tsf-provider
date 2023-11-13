-- `user`.account definition
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
                           `id` bigint(20) NOT NULL,
                           `user_id` varchar(255) NOT NULL COMMENT '用户id',
                           `email` varchar(255) NOT NULL COMMENT '邮箱',
                           `mobile` varchar(255) NOT NULL COMMENT '手机号',
                           `password` varchar(255) NOT NULL COMMENT '密码',
                           `nickname` varchar(255) NOT NULL COMMENT '昵称',
                           `role_id` varchar(255) NOT NULL COMMENT '角色id',
                           `department` varchar(255) NOT NULL COMMENT '部门',
                           `others` varchar(255) NOT NULL DEFAULT 'def',
                           `state` tinyint(4) NOT NULL DEFAULT '1',
                           `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           `created_by` bigint(20) NOT NULL,
                           `updated_by` bigint(20) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账号表';


-- `user`.menu definition
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
                        `id` bigint(20) NOT NULL,
                        `type` varchar(255) NOT NULL COMMENT '菜单类型',
                        `level` tinyint(4) NOT NULL COMMENT '层级',
                        `order` tinyint(4) NOT NULL COMMENT '排序',
                        `parent_id` varchar(255) NOT NULL COMMENT '父id',
                        `name` varchar(255) NOT NULL COMMENT '菜单名',
                        `desc` varchar(255) NOT NULL COMMENT '描述',
                        `url` varchar(255) NOT NULL COMMENT '请求路径',
                        `others` varchar(255) NOT NULL DEFAULT 'def',
                        `state` tinyint(4) NOT NULL DEFAULT '1',
                        `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `created_by` bigint(20) NOT NULL,
                        `updated_by` bigint(20) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';


-- `user`.resource definition
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
                            `id` bigint(20) NOT NULL,
                            `owner` varchar(255) NOT NULL COMMENT '所有者',
                            `type` varchar(255) NOT NULL COMMENT '资源类型',
                            `level` tinyint(4) NOT NULL COMMENT '层级',
                            `order` tinyint(4) NOT NULL COMMENT '排序',
                            `parent_id` varchar(255) NOT NULL COMMENT '父id',
                            `name` varchar(255) NOT NULL COMMENT '资源名',
                            `desc` varchar(255) NOT NULL COMMENT '描述',
                            `extend` varchar(255) NOT NULL COMMENT '扩展信息：如菜单资源的 url/icon 等.以 json 格式存储',
                            `url` varchar(255) NOT NULL COMMENT '请求路径',
                            `others` varchar(255) NOT NULL DEFAULT 'def',
                            `state` tinyint(4) NOT NULL DEFAULT '1',
                            `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `created_by` bigint(20) NOT NULL,
                            `updated_by` bigint(20) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';


-- `user`.`role` definition
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint(20) NOT NULL,
                        `owner` varchar(255) NOT NULL COMMENT '所有者',
                        `name` varchar(255) NOT NULL COMMENT '角色名',
                        `type` varchar(255) NOT NULL COMMENT '角色类型',
                        `weight` tinyint(4) NOT NULL COMMENT '角色权重  权重大的角色可以控制权重小的角色.数值越小权重越大',
                        `desc` varchar(255) NOT NULL COMMENT '描述',
                        `others` varchar(255) NOT NULL DEFAULT 'def',
                        `state` tinyint(4) NOT NULL DEFAULT '1',
                        `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `created_by` bigint(20) NOT NULL,
                        `updated_by` bigint(20) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';


-- `user`.role_menu definition
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
                             `id` bigint(20) NOT NULL,
                             `role_id` varchar(255) NOT NULL COMMENT '角色id',
                             `menu_id` varchar(255) NOT NULL COMMENT '菜单id',
                             `others` varchar(255) NOT NULL DEFAULT 'def',
                             `state` tinyint(4) NOT NULL DEFAULT '1',
                             `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             `created_by` bigint(20) NOT NULL,
                             `updated_by` bigint(20) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关系表';


-- `user`.role_res definition
DROP TABLE IF EXISTS `role_res`;
CREATE TABLE `role_res` (
                            `id` bigint(20) NOT NULL,
                            `role_id` varchar(255) NOT NULL COMMENT '角色id',
                            `res_id` varchar(255) NOT NULL COMMENT '资源id',
                            `others` varchar(255) NOT NULL DEFAULT 'def',
                            `state` tinyint(4) NOT NULL DEFAULT '1',
                            `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            `created_by` bigint(20) NOT NULL,
                            `updated_by` bigint(20) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关系表';


-- `user`.`user` definition
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL,
                        `nick_name` varchar(255) NOT NULL COMMENT '昵称',
                        `real_name` varchar(255) NOT NULL COMMENT '真实姓名',
                        `avatar` varchar(255) NOT NULL COMMENT '头像',
                        `mobile` varchar(255) NOT NULL COMMENT '手机号',
                        `email` varchar(255) NOT NULL COMMENT '邮箱',
                        `gender` tinyint(4) NOT NULL COMMENT '性别',
                        `others` varchar(255) NOT NULL DEFAULT 'def',
                        `state` tinyint(4) NOT NULL DEFAULT '1',
                        `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        `created_by` bigint(20) NOT NULL,
                        `updated_by` bigint(20) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';