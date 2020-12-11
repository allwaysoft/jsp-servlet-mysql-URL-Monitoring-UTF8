CREATE TABLE `url_table` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `url_address` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
                             `status` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
                             `url_name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
                             `monitoring_period` int(11) NOT NULL,
                             `response_time` int(11) NOT NULL,
                             `response_code` int(11) NOT NULL,
                             `response_substring` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                             `response_range_min` int(11) NOT NULL,
                             `response_range_max` int(11) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;