CREATE  TABLE `camel`.`tenant` (

  `tenant_guid` VARCHAR(36) NOT NULL ,

  `name` VARCHAR(45) NULL ,

  `phone_number` VARCHAR(45) NULL ,

  `email` VARCHAR(45) NULL ,

  `outbound_end_point_type` INT NULL ,

  `outbound_url` VARCHAR(45) NULL ,

  `outbound_user` VARCHAR(45) NULL ,

  `outbound_password` VARCHAR(45) NULL ,

  PRIMARY KEY (`tenant_guid`) );

