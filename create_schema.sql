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



INSERT INTO `camel`.`tenant` (`tenant_guid`, `name`, `phone_number`, `email`, `outbound_end_point_type`, `outbound_url`) VALUES ('test_guid', 'Jabs Solutions', '324234', 'rahat.jaan@gmail.com', '1', 'http://localhost:8081/pos/order');

INSERT INTO `camel`.`tenant` (`tenant_guid`, `name`, `phone_number`, `email`, `outbound_end_point_type`, `outbound_url`) VALUES ('test_guid2', 'Elixir Technologies', '23323', 'rahat.jaan@gmail.com', '1', 'http://localhost:8081/pos/order');

