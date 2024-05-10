-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: seckill
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `goods_id` int NOT NULL,
  `amount` int NOT NULL DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `sales` int NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `description` text,
  `image` varchar(45) DEFAULT '',
  `purchase_limit` int(10) unsigned zerofill NOT NULL DEFAULT '0000000010',
  `stock` int NOT NULL DEFAULT '100',
  PRIMARY KEY (`id`),
  KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES (111,'Wireless Bluetooth Headphones',79.99,152,'2024-05-06 18:49:13','High-quality wireless headphones with noise-cancellation and 30-hour battery life.','111.webp',0000000010,75),(112,'4K Ultra HD Smart TV',599.99,45,'2024-05-06 18:49:13','A 55-inch smart TV with 4K Ultra HD resolution and built-in streaming apps.','112.webp',0000000010,8258),(113,'Laptop Stand',29.99,300,'2024-05-06 18:49:13','Adjustable laptop stand suitable for most devices, made from aluminum alloy.','113.webp',0000000010,5045203),(114,'Fitness Tracker',39.99,250,'2024-05-06 18:49:13','Waterproof fitness tracker with heart-rate monitor and sleep tracking.','114.webp',0000000010,88),(115,'Electric Toothbrush',49.99,200,'2024-05-06 18:49:13','Rechargeable electric toothbrush with multiple brushing modes and a long-lasting battery.','115.webp',0000000010,5238),(116,'Gaming Mouse',19.99,500,'2024-05-06 18:49:13','Ergonomic gaming mouse with customizable RGB lighting and programmable buttons.','116.webp',0000000010,99),(117,'Wireless Speaker',89.99,121,'2024-05-06 18:49:13','Portable wireless speaker with deep bass, water resistance, and Bluetooth connectivity.','117.webp',0000000010,269),(118,'Kitchen Knife Set',99.99,80,'2024-05-06 18:49:13','Professional kitchen knife set made from high-carbon stainless steel, including sharpening tools.','118.webp',0000000010,776),(119,'Smartphone',699.99,60,'2024-05-06 18:49:13','Latest model smartphone with high-speed processor, dual cameras, and long battery life.','119.webp',0000000010,0),(120,'Air Purifier',129.99,90,'2024-05-06 18:49:13','HEPA air purifier with smart controls and energy-efficient operation.','120.webp',0000000010,76786),(121,'Smart Light Bulb',14.99,300,'2024-05-06 18:49:43','Dimmable and color-changing smart LED bulb compatible with popular smart home systems.','121.webp',0000000010,100),(122,'Wireless Charging Pad',24.99,400,'2024-05-06 18:49:43','Fast wireless charging pad for smartphones and smartwatches, with LED indicator.','122.webp',0000000010,7813),(123,'Portable Hard Drive',79.99,150,'2024-05-06 18:49:43','1TB portable hard drive with USB 3.0 connectivity and backup software.','123.webp',0000000010,100),(124,'Noise-Cancelling Earbuds',129.99,200,'2024-05-06 18:49:43','True wireless noise-cancelling earbuds with water resistance and charging case.','124.webp',0000000010,100),(125,'Robot Vacuum Cleaner',249.99,70,'2024-05-06 18:49:43','Smart robot vacuum cleaner with powerful suction and multi-floor mapping capabilities.','125.webp',0000000010,4504),(126,'Electric Kettle',39.99,180,'2024-05-06 18:49:43','Electric kettle with rapid boiling, temperature control, and stainless steel body.','126.webp',0000000010,45045045),(127,'Digital Camera',499.99,50,'2024-05-06 18:49:43','24-megapixel digital camera with interchangeable lenses, Wi-Fi, and 4K video recording.','127.webp',0000000010,450),(128,'Smart Thermostat',199.99,110,'2024-05-06 18:49:43','Programmable smart thermostat with learning capabilities and remote control via app.','128.webp',0000000010,100),(129,'Video Doorbell',149.99,90,'2024-05-06 18:49:43','Wireless video doorbell with motion detection, night vision, and 2-way audio.','129.webp',0000000010,581),(130,'Portable Projector',299.99,60,'2024-05-06 18:49:43','Mini portable projector with HD resolution, HDMI input, and built-in speakers.','130.webp',0000000010,717),(131,'Bluetooth Tracker',9.99,350,'2024-05-06 18:49:43','Bluetooth tracker for locating keys, wallets, or other personal items via smartphone.','131.webp',0000000010,100),(132,'Smartwatch',199.99,140,'2024-05-06 18:49:43','Smartwatch with fitness tracking, notifications, and built-in GPS.','132.webp',0000000010,100),(133,'Electric Scooter',399.99,45,'2024-05-06 18:49:43','Folding electric scooter with 15-mile range, LED lights, and dual braking system.','133.webp',0000000010,8270),(134,'Induction Cooktop',69.99,100,'2024-05-06 18:49:43','Portable induction cooktop with precise temperature control and auto-off feature.','134.webp',0000000010,50450),(135,'Wireless Keyboard',49.99,250,'2024-05-06 18:49:43','Ergonomic wireless keyboard with quiet keys, adjustable tilt, and long battery life.','135.webp',0000000010,100),(136,'Electric Bike',899.99,25,'2024-05-06 18:49:43','Electric bike with pedal assist, removable battery, and multiple speed settings.','136.webp',0000000010,12012),(137,'3D Printer',449.99,40,'2024-05-06 18:49:43','3D printer with high precision, dual-extruder system, and large build volume.','137.webp',0000000010,100),(138,'Coffee Maker',79.99,160,'2024-05-06 18:49:43','Drip coffee maker with programmable timer, adjustable brew strength, and keep-warm function.','138.webp',0000000010,42),(139,'VR Headset',299.99,65,'2024-05-06 18:49:51','Virtual reality headset with high-resolution display and motion tracking.','139.webp',0000000010,75042),(140,'Wireless Door Lock',129.99,150,'2024-05-06 18:49:51','Keyless wireless door lock with smartphone access, keypad, and remote control.','140.webp',0000000010,752),(141,'Smart Baby Monitor',99.99,80,'2024-05-06 18:49:51','Wi-Fi-enabled baby monitor with camera, night vision, and temperature sensor.','141.webp',0000000010,752065),(142,'Gaming Keyboard',79.99,230,'2024-05-06 18:49:51','Mechanical gaming keyboard with RGB backlighting and customizable macro keys.','142.webp',0000000010,100),(143,'Smart Scale',49.99,190,'2024-05-06 18:49:51','Bluetooth smart scale that tracks weight, body fat, and muscle mass with a companion app.','143.webp',0000000010,100),(144,'Portable Power Bank',39.99,300,'2024-05-06 18:49:51','10,000mAh portable power bank with fast charging and multiple output ports.','144.webp',0000000010,1000),(145,'Smart Pet Feeder',129.99,90,'2024-05-06 18:49:51','Wi-Fi-enabled smart pet feeder with automatic scheduling, portion control, and HD camera.','145.webp',0000000010,450),(146,'4K Action Camera',199.99,70,'2024-05-06 18:49:51','Waterproof 4K action camera with image stabilization and slow-motion recording.','146.webp',0000000010,752),(147,'Cordless Drill',59.99,150,'2024-05-06 18:49:51','Cordless drill with multiple speed settings, rechargeable battery, and LED light.','147.webp',0000000010,100),(148,'Electric Griddle',49.99,180,'2024-05-06 18:49:51','Non-stick electric griddle with adjustable temperature and grease trap.','148.webp',0000000010,700),(149,'Dehumidifier',159.99,85,'2024-05-06 18:49:51','Energy-efficient dehumidifier with adjustable humidity levels and auto shut-off.','149.webp',0000000010,120),(150,'Standing Desk',299.99,45,'2024-05-06 18:49:51','Electric adjustable-height standing desk with memory presets and cable management.','150.webp',0000000010,120),(151,'Portable Air Conditioner',349.99,50,'2024-05-06 18:49:51','Portable air conditioner with remote control, timer, and dehumidifying mode.','151.webp',0000000010,450),(152,'Fitness Dumbbell Set',69.99,130,'2024-05-06 18:49:51','Adjustable dumbbell set with multiple weight plates and a secure locking system.','152.webp',0000000010,786),(153,'Electric Blanket',39.99,250,'2024-05-06 18:49:51','Electric blanket with multiple heat settings, auto shut-off, and soft microplush fabric.','153.webp',0000000010,100),(154,'Bluetooth Headset',59.99,210,'2024-05-06 18:49:51','On-ear Bluetooth headset with noise-cancelling microphone and 24-hour battery life.','154.webp',0000000010,732402),(155,'E-Reader',129.99,90,'2024-05-06 18:49:51','6-inch e-reader with adjustable backlight, Wi-Fi, and built-in dictionary.','155.webp',0000000010,4504),(156,'Air Fryer',99.99,160,'2024-05-06 18:49:51','Compact air fryer with adjustable temperature control and non-stick basket.','156.webp',0000000010,7375),(157,'Bluetooth Speaker',69.99,270,'2024-05-06 18:49:51','Waterproof Bluetooth speaker with deep bass, stereo sound, and built-in microphone.','157.webp',0000000010,78275),(158,'Smart Window Blinds',199.99,40,'2024-05-06 18:49:51','Smart window blinds with voice control, remote app access, and scheduling.','158.webp',0000000010,8625);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;

--
-- Table structure for table `order_goods`
--

DROP TABLE IF EXISTS `order_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `goods_id` int NOT NULL,
  `amount` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_goods`
--

/*!40000 ALTER TABLE `order_goods` DISABLE KEYS */;
INSERT INTO `order_goods` VALUES (37,52,116,1,19.99),(38,52,111,4,79.99),(39,53,117,1,89.99),(40,54,111,1,79.99),(41,55,122,1,24.99),(42,56,122,1,24.99),(43,57,115,1,49.99),(44,58,111,10,79.99),(45,58,119,5,699.99),(46,58,141,10,99.99),(47,58,118,1,99.99),(48,58,117,1,89.99),(49,59,118,1,99.99),(50,60,122,3,24.99),(51,61,114,1,39.99),(52,62,114,1,39.99),(53,63,113,1,29.99),(54,64,118,3,99.99),(55,65,118,1,99.99),(56,66,114,10,39.99),(57,66,111,1,79.99),(58,67,112,1,599.99),(59,68,111,1,79.99),(60,69,111,1,79.99),(61,70,111,1,79.99),(62,71,115,1,49.99),(63,72,112,1,599.99),(64,73,111,1,79.99),(65,74,111,1,79.99),(66,75,111,1,79.99),(67,76,111,1,79.99),(68,77,111,1,79.99),(69,78,117,1,89.99);
/*!40000 ALTER TABLE `order_goods` ENABLE KEYS */;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `address` varchar(45) NOT NULL,
  `payment_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint NOT NULL DEFAULT '0',
  `payment_method` varchar(45) DEFAULT NULL COMMENT '1: credit card; 2: PayPal;',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (52,1,338.00,'Brooklyn','2024-05-07 21:55:46','2024-05-07 21:55:38',1,'credit_card'),(53,1,89.00,'Brooklyn','2024-05-07 22:07:16','2024-05-07 22:07:14',1,'credit_card'),(54,1,79.00,'Brooklyn','2024-05-07 22:08:20','2024-05-07 22:08:18',1,'credit_card'),(55,1,24.99,'Brooklyn','2024-05-08 14:20:57','2024-05-08 14:08:31',1,'paypal'),(56,1,24.99,'Brooklyn','2024-05-08 14:14:56','2024-05-08 14:14:44',1,'apple_pay'),(57,1,49.99,'aaa','2024-05-08 14:22:29','2024-05-08 14:22:11',1,'credit_card'),(58,1,5489.73,'Brooklyn',NULL,'2024-05-08 16:41:28',0,NULL),(59,1,99.99,'Brooklyn',NULL,'2024-05-08 16:44:59',0,NULL),(60,1,74.97,'Brooklyn',NULL,'2024-05-08 16:45:55',0,NULL),(61,1,39.99,'Brooklyn',NULL,'2024-05-08 16:55:57',0,NULL),(62,1,39.99,'Brooklyn',NULL,'2024-05-08 16:57:11',0,NULL),(63,1,29.99,'Brooklyn',NULL,'2024-05-08 17:39:30',0,NULL),(64,1,299.97,'Brooklyn',NULL,'2024-05-08 17:40:41',0,NULL),(65,1,99.99,'Brooklyn','2024-05-08 18:50:48','2024-05-08 17:47:39',1,'credit_card'),(66,1,479.89,'Brooklyn','2024-05-08 19:44:47','2024-05-08 19:43:10',1,'credit_card'),(67,1,599.99,'Brooklyn','2024-05-08 19:56:05','2024-05-08 19:56:03',1,'credit_card'),(68,1,79.99,'Brooklyn',NULL,'2024-05-08 19:58:06',0,NULL),(69,1,79.99,'Brooklyn',NULL,'2024-05-08 19:58:34',0,NULL),(70,1,79.99,'Brooklyn','2024-05-08 20:00:22','2024-05-08 20:00:20',1,'credit_card'),(71,1,49.99,'Brooklyn','2024-05-08 20:01:49','2024-05-08 20:01:46',1,'credit_card'),(72,1,599.99,'aaaaaaaaaaaa','2024-05-08 20:02:06','2024-05-08 20:01:56',1,'apple_pay'),(73,1,79.99,'bk','2024-05-08 20:25:30','2024-05-08 20:25:19',1,'credit_card'),(74,1,79.99,'bk','2024-05-08 20:26:23','2024-05-08 20:26:17',1,'credit_card'),(75,1,79.99,'bk','2024-05-08 20:28:59','2024-05-08 20:28:51',1,'credit_card'),(76,1,79.99,'bk','2024-05-08 20:52:32','2024-05-08 20:52:30',1,'credit_card'),(77,1,79.99,'bk','2024-05-08 20:52:50','2024-05-08 20:52:48',1,'credit_card'),(78,1,89.99,'bk','2024-05-08 23:13:41','2024-05-08 23:13:34',1,'credit_card');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

--
-- Table structure for table `seckill_goods`
--

DROP TABLE IF EXISTS `seckill_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seckill_goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `goods_id` int NOT NULL,
  `seckill_price` decimal(10,2) NOT NULL,
  `seckill_stock` int NOT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_commodity_id` (`goods_id`),
  KEY `idx_start_time` (`start_time`),
  KEY `idx_end_time` (`end_time`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seckill_goods`
--

/*!40000 ALTER TABLE `seckill_goods` DISABLE KEYS */;
INSERT INTO `seckill_goods` VALUES (1,11,55.00,80,'2023-03-20 04:00:00','2023-03-20 14:00:00',0,'2024-03-08 00:14:39'),(2,12,60.00,67,'2022-03-21 18:00:00','2030-03-21 19:00:00',0,'2024-03-08 00:14:39'),(3,13,65.00,90,'2030-03-22 16:00:00','2030-03-22 17:00:00',0,'2024-03-08 00:14:39'),(4,14,70.00,70,'2024-03-23 22:00:00','2024-03-23 23:00:00',0,'2024-03-08 00:14:39'),(5,15,75.00,80,'2024-03-24 14:00:00','2024-03-24 15:00:00',0,'2024-03-08 00:14:39'),(6,16,80.00,60,'2024-03-25 20:00:00','2024-03-25 21:00:00',0,'2024-03-08 00:14:39'),(7,17,85.00,90,'2024-03-26 15:00:00','2024-03-26 16:00:00',0,'2024-03-08 00:14:39'),(8,18,90.00,100,'2024-03-27 19:00:00','2024-03-27 20:00:00',0,'2024-03-08 00:14:39'),(9,19,95.00,70,'2024-03-28 14:00:00','2024-03-28 15:00:00',0,'2024-03-08 00:14:39'),(10,20,100.00,80,'2024-03-29 17:00:00','2024-03-29 18:00:00',0,'2024-03-08 00:14:39');
/*!40000 ALTER TABLE `seckill_goods` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'tm3929@nyu.edu','123456','helloc','2024-03-21 19:08:44','bk'),(2,'user1@example.com','password123','user1','2024-03-21 19:16:42','123 Main St'),(3,'user2@example.com','pass456','user2','2024-03-21 19:16:42','456 Elm St'),(4,'test@test.com','testpassword','testuser','2024-03-21 19:16:42','789 Oak St'),(5,'user3@example.com','pass123','user3','2024-03-21 19:16:42','101 Maple Ave'),(6,'user4@example.com','pass789','user4','2024-03-21 19:16:42','202 Pine St'),(7,'user5@example.com','qwerty','user5','2024-03-21 19:16:42','303 Cedar St'),(8,'user6@example.com','abc123','user6','2024-03-21 19:16:42','404 Birch St'),(9,'user7@example.com','password456','user7','2024-03-21 19:16:42','505 Walnut St'),(10,'user8@example.com','passpass','user8','2024-03-21 19:16:42','606 Chestnut St'),(11,'user9@example.com','123456','user9','2024-03-21 19:16:42','707 Oak St'),(12,'user10@example.com','987654','user10','2024-03-21 19:16:42','808 Maple St'),(13,'user11@example.com','passpass','user11','2024-03-21 19:16:42','909 Pine St'),(14,'user12@example.com','password','user12','2024-03-21 19:16:42','1010 Cedar St'),(15,'user13@example.com','password','user13','2024-03-21 19:16:42','1111 Elm St'),(16,'user14@example.com','password','user14','2024-03-21 19:16:42','1212 Birch St'),(17,'user15@example.com','password','user15','2024-03-21 19:16:42','1313 Walnut St'),(18,'user16@example.com','password','user16','2024-03-21 19:16:42','1414 Chestnut St'),(19,'user17@example.com','password','user17','2024-03-21 19:16:42','1515 Oak St'),(20,'user18@example.com','password','user18','2024-03-21 19:16:42','1616 Maple St'),(21,'user19@example.com','password','user19','2024-03-21 19:16:42','1717 Pine St'),(22,'user20@example.com','password','user20','2024-03-21 19:16:42','1818 Cedar St');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Dumping routines for database 'seckill'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-08 19:28:52
