CREATE TABLE `teamtable`.`client` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `client` VARCHAR(45) NOT NULL,
  `api_key` VARCHAR(245) NOT NULL,
  `secret_key` VARCHAR(145) NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `api_key_UNIQUE` (`api_key` ASC),
  INDEX `secret_key_INDEX` (`secret_key` ASC));


ALTER TABLE `teamtable`.`user`
ADD UNIQUE INDEX `username_UNIQUE` (`username` ASC);
