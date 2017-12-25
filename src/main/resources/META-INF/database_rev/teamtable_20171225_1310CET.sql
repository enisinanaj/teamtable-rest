#
# SQL Export
# Created by Querious (201017)
# Created: 25 December 2017 at 13:10:51 CET
# Encoding: Unicode (UTF-8)
#


SET @PREVIOUS_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `session`;


CREATE TABLE `session` (
  `session_id` int(11) NOT NULL AUTO_INCREMENT,
  `session_key` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `host` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `date_in` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_out` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


ALTER TABLE `teamtable`.`session` ADD COLUMN `user` INT NOT NULL DEFAULT 0 AFTER `date_out`;


SET FOREIGN_KEY_CHECKS = @PREVIOUS_FOREIGN_KEY_CHECKS;


