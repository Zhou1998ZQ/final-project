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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_order` */

insert  into `jac_order`(`id`,`create_time`,`order_state`,`user_id`) values (2,'2023-11-22 19:25:11.000000','PAID',8),(3,'2023-11-29 19:25:27.000000','UNPAID',8),(4,'2023-11-23 19:25:41.000000','UNPAID',1),(5,'2023-11-16 19:25:52.000000','PAID',2),(6,'2023-11-26 19:26:01.000000','UNPAID',1),(7,'2023-11-21 19:26:11.000000','UNPAID',3),(8,'2023-11-22 19:26:23.000000','PAID',4),(9,'2023-11-25 19:26:31.000000','UNPAID',4),(10,'2023-11-17 19:26:38.000000','UNPAID',5),(11,'2023-11-29 19:26:46.000000','PAID',6),(12,'2023-11-30 19:26:54.000000','PAID',7),(13,'2023-11-26 19:27:03.000000','UNPAID',9),(14,'2023-11-18 19:27:13.000000','PAID',10);

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

insert  into `jac_order_item`(`order_id`,`item_id`) values (2,1),(2,1),(3,6),(4,2),(2,1),(5,2),(5,3),(6,1),(6,4),(7,1),(7,5),(8,1),(9,1),(10,2),(11,3),(11,4),(12,1),(13,1),(14,1),(14,5),(14,6);

/*Table structure for table `jac_user` */

DROP TABLE IF EXISTS `jac_user`;

CREATE TABLE `jac_user` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `email` varchar(255) DEFAULT NULL,
                            `registration_time` datetime(6) DEFAULT NULL,
                            `user_name` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_user` */

insert  into `jac_user`(`id`,`email`,`registration_time`,`user_name`) values (1,'alice.smith@email.com','2023-11-22 21:00:00.000000','Alice Smith'),(2,'bob.jones@email.com','2023-11-25 21:15:00.000000','Bob Jones'),(3,'charlie.davis@email.com','2023-11-03 21:30:00.000000','Charlie Davis'),(4,'diana.miller@email.com','2023-11-09 21:45:00.000000','Diana Miller'),(5,'eric.wilson@email.com','2023-11-03 22:00:00.000000','Eric Wilson'),(6,'fiona.anderson@email.com','2023-11-04 22:15:00.000000','Fiona Anderson'),(7,'george.wright@email.com','2023-11-13 22:30:00.000000','George Wright'),(8,'helen.thompson@email.com','2023-11-01 22:45:00.000000','Helen Thompson'),(9,'jac1234@gmail.com','2023-11-20 19:13:11.000000','John Abbott'),(10,'ian.hall@email.com','2023-11-04 23:00:00.000000','Ian Hall'),(11,'jenny.baker@email.com','2023-11-29 23:15:00.000000','Jenny Baker');

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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `jac_user_payment_info` */

insert  into `jac_user_payment_info`(`id`,`credit_card_number`,`cvv_code`,`expiration_date`,`user_id`) values (5,'2222-1111-2222-1118','432','12/24',9),(6,'1234-5678-9012-3456','123','12/25',1),(7,'2345-6789-0123-4567','456','01/24',2),(8,'3456-7890-1234-5678','789','09/23',3),(9,'4567-8901-2345-6789','012','03/26',4),(10,'5678-9012-3456-7890','345','05/25',5),(11,'6789-0123-4567-8901','678','07/22',6),(12,'7890-1234-5678-9012','901','11/23',7),(13,'8901-2345-6789-0123','234','02/25',8);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
