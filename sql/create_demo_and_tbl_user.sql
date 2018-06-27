CREATE SCHEMA `demo` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `demo`.`tbl_user` (
  `uuid` VARCHAR(36) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`uuid`),
  UNIQUE INDEX `uuid_UNIQUE` (`uuid` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
