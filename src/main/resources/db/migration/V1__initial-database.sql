-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema massoterapiadb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema massoterapiadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `massoterapiadb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `massoterapiadb` ;

-- -----------------------------------------------------
-- Table `massoterapiadb`.`tb_unidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `massoterapiadb`.`tb_unidade` ;

CREATE TABLE IF NOT EXISTS `massoterapiadb`.`tb_unidade` (
  `id_unidade` BINARY(16) NOT NULL,
  `ds_unidade` VARCHAR(100) NOT NULL,
  `nu_posicoes` INT NOT NULL,
  PRIMARY KEY (`id_unidade`))
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `massoterapiadb`.`tb_funcionario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `massoterapiadb`.`tb_funcionario` ;

CREATE TABLE IF NOT EXISTS `massoterapiadb`.`tb_funcionario` (
  `id_funcionario` BINARY(16) NOT NULL,
  `co_funcionario` VARCHAR(50) NOT NULL,
  `no_funcionario` VARCHAR(150) NOT NULL,
  `st_ativo` TINYINT NOT NULL DEFAULT 1,
  `foto` LONGTEXT NULL,
  `dt_nascimento` DATE NOT NULL,
  `tp_funcionario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_funcionario`),
  UNIQUE INDEX `co_funcionario_UNIQUE` (`co_funcionario` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `massoterapiadb`.`tb_posicao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `massoterapiadb`.`tb_posicao` ;

CREATE TABLE IF NOT EXISTS `massoterapiadb`.`tb_posicao` (
  `id_posicao` BINARY(16) NOT NULL,
  `nu_posicao` INT NOT NULL,
  `id_unidade` BINARY(16) NOT NULL,
  `id_funcionario` BINARY(16) NULL,
  PRIMARY KEY (`id_posicao`),
  INDEX `fk_tb_posicao_tb_unidade1_idx` (`id_unidade` ASC) VISIBLE,
  INDEX `fk_tb_posicao_tb_funcionario1_idx` (`id_funcionario` ASC) VISIBLE)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `massoterapiadb`.`tb_sessao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `massoterapiadb`.`tb_sessao` ;

CREATE TABLE IF NOT EXISTS `massoterapiadb`.`tb_sessao` (
  `id_sessao` BINARY(16) NOT NULL,
  `dt_sessao` DATETIME NOT NULL,
  `id_massoterapeuta` BINARY(16) NOT NULL,
  `id_unidade` BINARY(16) NOT NULL,
  `id_funcionario` BINARY(16) NOT NULL,
  PRIMARY KEY (`id_sessao`),
  INDEX `fk_tb_sessao_tb_unidade1_idx` (`id_unidade` ASC) VISIBLE,
  INDEX `fk_tb_sessao_tb_funcionario1_idx` (`id_funcionario` ASC) VISIBLE)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
