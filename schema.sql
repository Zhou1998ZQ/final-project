/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 10.6.7-MariaDB : Database - fsd09
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fsd09` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `fsd09`;

/*Table structure for table `jac_item` */

DROP TABLE IF EXISTS `jac_item`;

CREATE TABLE `jac_item` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `item_name` varchar(255) DEFAULT NULL,
                            `quantity` int(11) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_item` */

insert  into `jac_item`(`id`,`item_name`,`quantity`) values (1,'iPhone 15 Pro',1000),(2,'MacBook Pro',1000),(3,'MacBook Air',1000),(4,'iPad Pro',1000),(5,'Air Pods',1000),(6,'Apple Watch',1000);

/*Table structure for table `jac_order` */

DROP TABLE IF EXISTS `jac_order`;

CREATE TABLE `jac_order` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `create_time` datetime(6) DEFAULT NULL,
                             `order_state` enum('UNPAID','PAID') DEFAULT NULL,
                             `user_id` bigint(20) DEFAULT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FK8y7m5sokph3f2gws7r9t0pu` (`user_id`),
                             CONSTRAINT `FK8y7m5sokph3f2gws7r9t0pu` FOREIGN KEY (`user_id`) REFERENCES `jac_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_order` */

insert  into `jac_order`(`id`,`create_time`,`order_state`,`user_id`) values (14,'2023-11-18 19:27:13.000000','PAID',38),(15,'2023-11-30 21:17:42.000000','UNPAID',37),(16,'2023-11-30 21:22:03.000000','UNPAID',37);

/*Table structure for table `jac_order_item` */

DROP TABLE IF EXISTS `jac_order_item`;

CREATE TABLE `jac_order_item` (
                                  `order_id` bigint(20) NOT NULL,
                                  `item_id` bigint(20) NOT NULL,
                                  KEY `FKt6ivokvlq25uf721t7okdaxcj` (`item_id`),
                                  KEY `FKsx16bn3d5prog7y26bfpcbjri` (`order_id`),
                                  CONSTRAINT `FKsx16bn3d5prog7y26bfpcbjri` FOREIGN KEY (`order_id`) REFERENCES `jac_order` (`id`),
                                  CONSTRAINT `FKt6ivokvlq25uf721t7okdaxcj` FOREIGN KEY (`item_id`) REFERENCES `jac_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_order_item` */

insert  into `jac_order_item`(`order_id`,`item_id`) values (14,1),(14,5),(14,6),(15,1),(15,6),(16,2),(16,5);

/*Table structure for table `jac_role` */

DROP TABLE IF EXISTS `jac_role`;

CREATE TABLE `jac_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `role_name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_role` */

insert  into `jac_role`(`id`,`role_name`) values (1,'ROLE_ADMIN'),(2,'ROLE_CLIENT');

/*Table structure for table `jac_user` */

DROP TABLE IF EXISTS `jac_user`;

CREATE TABLE `jac_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `email` varchar(255) DEFAULT NULL,
                            `registration_time` datetime(6) DEFAULT NULL,
                            `user_name` varchar(255) DEFAULT NULL,
                            `password` varchar(255) DEFAULT NULL,
                            `enabled` bit(1) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_user` */

insert  into `jac_user`(`id`,`email`,`registration_time`,`user_name`,`password`,`enabled`) values (36,'332211@QQ.COM','2023-12-07 16:02:28.000000','332211','$2a$10$bpfbluGToybBWE5U0X2jM.2iiWt6WcmfLZoDs/O3QyG0g3c4QKrK6',''),(37,'999@QQ.COM','2023-12-07 16:08:15.000000','999','$2a$10$zkngsrg.OiJq9.dA9qs28uMpaga0qXt3W.FWtRO7SsW4HQuLuN2ZK',''),(38,'000@gg.com','2023-12-07 16:35:17.000000','000','$2a$10$XScFSFWWKCg3AiV2an5.oOS.9jzhJOST9SB5eqJTXPlF/I4x5cdg2',''),(39,'222@qq.com','2023-12-07 16:54:49.000000','222','$2a$10$6BG1ekTY6NUwMwAZxhPJlu/uvsUB9SJ6.BiJ.yZ7dUGq/MCe.nT3C','');

/*Table structure for table `jac_user_payment_info` */

DROP TABLE IF EXISTS `jac_user_payment_info`;

CREATE TABLE `jac_user_payment_info` (
                                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                         `credit_card_number` varchar(255) DEFAULT NULL,
                                         `cvv_code` varchar(255) DEFAULT NULL,
                                         `expiration_date` varchar(255) DEFAULT NULL,
                                         `user_id` bigint(20) DEFAULT NULL,
                                         PRIMARY KEY (`id`),
                                         UNIQUE KEY `UK_3ilw3krxsy58nq1dntwsa75sq` (`user_id`),
                                         CONSTRAINT `FKaxulqepx57mpfmlxalqfwmg2o` FOREIGN KEY (`user_id`) REFERENCES `jac_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_user_payment_info` */

insert  into `jac_user_payment_info`(`id`,`credit_card_number`,`cvv_code`,`expiration_date`,`user_id`) values (14,'2222-2222-1111-1111','222','12/23',37);

/*Table structure for table `jac_user_role` */

DROP TABLE IF EXISTS `jac_user_role`;

CREATE TABLE `jac_user_role` (
                                 `user_id` bigint(20) NOT NULL,
                                 `role_id` bigint(20) NOT NULL,
                                 KEY `FKksmmh1x6jyj0xj8id74erjnsr` (`role_id`),
                                 KEY `FKhlc0vpjdho3mtr30iqal8pc0w` (`user_id`),
                                 CONSTRAINT `FKhlc0vpjdho3mtr30iqal8pc0w` FOREIGN KEY (`user_id`) REFERENCES `jac_user` (`id`),
                                 CONSTRAINT `FKksmmh1x6jyj0xj8id74erjnsr` FOREIGN KEY (`role_id`) REFERENCES `jac_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_user_role` */

insert  into `jac_user_role`(`user_id`,`role_id`) values (37,1),(38,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
