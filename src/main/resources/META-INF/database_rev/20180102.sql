ALTER TABLE `teamtable`.`activity`
CHANGE COLUMN `description`
`description` VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL  COMMENT '' AFTER `activity_type`;

ALTER TABLE `teamtable`.`event`
CHANGE COLUMN `description`
`description` VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL  COMMENT '' AFTER `event_date`;

ALTER TABLE `teamtable`.`legal_practice`
CHANGE COLUMN `description`
`description` VARCHAR(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL  COMMENT '' AFTER `creator`;

