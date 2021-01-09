-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        8.0.22 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 url_monitoring 的数据库结构
CREATE DATABASE IF NOT EXISTS `url_monitoring` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `url_monitoring`;

-- 导出  表 url_monitoring.url_table 结构
CREATE TABLE IF NOT EXISTS `url_table` (
  `id` int NOT NULL AUTO_INCREMENT,
  `url_address` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `url_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `monitoring_period` int NOT NULL,
  `response_time` int NOT NULL,
  `response_code` int NOT NULL,
  `response_substring` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `response_charset` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `connect_timeout` int NOT NULL,
  `read_timeout` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 正在导出表  url_monitoring.url_table 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `url_table` DISABLE KEYS */;
INSERT INTO `url_table` (`id`, `url_address`, `status`, `url_name`, `monitoring_period`, `response_time`, `response_code`, `response_substring`, `response_charset`, `connect_timeout`, `read_timeout`) VALUES
	(1, 'http://localhost:8080/WebMonitoringTool/', 'Unknown', '监控系统', 10000, 600, 200, '简单', 'UTF-8', 1, 400000),
	(2, 'http://www.yini.org/', 'Unknown', '秘密花园', 10000, 600, 200, '秘密花园', 'GBK', 1, 400000);
/*!40000 ALTER TABLE `url_table` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
