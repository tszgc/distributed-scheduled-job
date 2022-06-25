-- MySQL Script generated by MySQL Workbench
-- Sat Jun 25 07:51:31 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema dsj
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dsj
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dsj` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `dsj` ;

-- -----------------------------------------------------
-- Table `dsj`.`dsj_task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dsj`.`dsj_task` (
                                                `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'Id',
                                                `code` VARCHAR(50) NOT NULL COMMENT '任务编码',
                                                `name` VARCHAR(50) NOT NULL COMMENT '任务名称',
                                                `type` SMALLINT(1) NOT NULL COMMENT '任务类型：1-实时执行；2-cron执行',
                                                `cron` VARCHAR(50) NULL COMMENT '定时任务表达式',
                                                `url` VARCHAR(100) NOT NULL COMMENT '任务地址',
                                                `param` VARCHAR(100) NULL COMMENT '参数',
                                                `delay` INT NULL COMMENT '延时执行时间（ms）',
                                                `status` SMALLINT(1) NULL DEFAULT 1 COMMENT '状态：1-待执行；2-执行中；3-已执行；4-删除；5-执行失败',
                                                `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                PRIMARY KEY (`id`),
                                                UNIQUE INDEX `uk_code` (`code` ASC))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = DEFAULT
    COMMENT = '任务表';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
