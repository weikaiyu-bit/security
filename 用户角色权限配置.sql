# Host: 127.0.0.1  (Version: 5.5.15)
# Date: 2020-06-14 14:50:40
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES gb2312 */;

#
# Structure for table "t_permission"
#

CREATE TABLE `t_permission` (
  `id` int(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "t_permission"
#

INSERT INTO `t_permission` VALUES (1,'p1','宽带业务','p1','/r/r1'),(2,'p2','机顶盒业务','p2','/r/r2');

#
# Structure for table "t_role"
#

CREATE TABLE `t_role` (
  `id` int(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "t_role"
#

INSERT INTO `t_role` VALUES (1,'经理','经理牛'),(2,'程序员','程序员牛');

#
# Structure for table "role_permission"
#

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_t_role` (`role_id`),
  KEY `fk_user_role_role_permission` (`permission_id`),
  CONSTRAINT `fk_user_role_t_role` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `fk_user_role_role_permission` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "role_permission"
#

INSERT INTO `role_permission` VALUES (1,2,1),(2,1,2),(3,2,2);

#
# Structure for table "t_user"
#

CREATE TABLE `t_user` (
  `id` int(20) NOT NULL COMMENT '主键',
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "t_user"
#

INSERT INTO `t_user` VALUES (1,'张三','$2a$10$8Y4OAqnDoBpKUhbpFLwwI.SLDuQA/Q1fRLdSHCpxet8Di23T0G7du','张三'),(2,'李四','$2a$10$8Y4OAqnDoBpKUhbpFLwwI.SLDuQA/Q1fRLdSHCpxet8Di23T0G7du','李四');

#
# Structure for table "user"
#

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `r_role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'admin','$2a$10$8Y4OAqnDoBpKUhbpFLwwI.SLDuQA/Q1fRLdSHCpxet8Di23T0G7du','admin'),(2,'user','$2a$10$8Y4OAqnDoBpKUhbpFLwwI.SLDuQA/Q1fRLdSHCpxet8Di23T0G7du','normal');

#
# Structure for table "user_role"
#

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role_t_role_1` (`role_id`),
  KEY `fk_user_role_t_user_1` (`user_id`),
  CONSTRAINT `fk_user_role_t_role_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_t_user_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for table "user_role"
#

INSERT INTO `user_role` VALUES (1,1,1),(2,2,1),(3,1,2);
