
alter table legal_practice add archived int(1) default 0;
alter table legal_practice add archived_by int;
alter table legal_practice add archived_on timestamp null default null;

alter table activity add archived int(1) default 0;
alter table activity add archived_by int;
alter table activity add archived_on timestamp null default null;

alter table event add archived int(1) default 0;
alter table event add archived_by int;
alter table event add archived_on timestamp null default null;

ALTER TABLE `teamtable`.`legal_practice`
ADD INDEX `FK_ARCHIVED_BY_idx` (`archived_by` ASC);
ALTER TABLE `teamtable`.`legal_practice`
ADD CONSTRAINT `FK_ARCHIVED_BY`
  FOREIGN KEY (`archived_by`)
  REFERENCES `teamtable`.`user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `teamtable`.`activity`
ADD INDEX `FK_ACT_ARCHIVED_BY_idx` (`archived_by` ASC);
ALTER TABLE `teamtable`.`activity`
ADD CONSTRAINT `FK_ACT_ARCHIVED_BY`
  FOREIGN KEY (`archived_by`)
  REFERENCES `teamtable`.`user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `teamtable`.`event`
ADD INDEX `FK_EVT_ARCHIVED_BY_idx` (`archived_by` ASC);
ALTER TABLE `teamtable`.`event`
ADD CONSTRAINT `FK_EVT_ARCHIVED_BY`
  FOREIGN KEY (`archived_by`)
  REFERENCES `teamtable`.`user` (`user_id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;