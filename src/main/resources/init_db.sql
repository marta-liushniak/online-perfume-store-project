CREATE SCHEMA IF NOT EXISTS `perfume_store` DEFAULT CHARACTER SET utf8;
USE `perfume_store`;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sellers`;
CREATE TABLE `sellers`  (
                            `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                            `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                            `is_deleted` bit(1) NOT NULL DEFAULT b'0',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `brands`;
CREATE TABLE `brands`  (
                                  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `brand_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `is_deleted` bit(1) NOT NULL DEFAULT b'0',
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `fragrances`;
CREATE TABLE `fragrances`  (
                         `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `fragrance_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                         `brand_id` bigint(0) UNSIGNED NOT NULL,
                         `is_deleted` bit(1) NOT NULL DEFAULT b'0',
                         PRIMARY KEY (`id`) USING BTREE,
                         INDEX `FK_brand_id`(`brand_id`) USING BTREE,
                         CONSTRAINT `FK_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `fragrances_sellers`;
CREATE TABLE `fragrances_sellers`  (
                                 `fragrance_id` bigint(0) UNSIGNED NOT NULL,
                                 `seller_id` bigint(0) UNSIGNED NOT NULL,
                                 PRIMARY KEY (`fragrance_id`, `seller_id`) USING BTREE,
                                 INDEX `seller_id`(`seller_id`) USING BTREE,
                                 INDEX `fragrance_id`(`fragrance_id`) USING BTREE,
                                 CONSTRAINT `fragrance_id` FOREIGN KEY (`fragrance_id`) REFERENCES `fragrances` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
                                 CONSTRAINT `seller_id` FOREIGN KEY (`seller_id`) REFERENCES `sellers` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
