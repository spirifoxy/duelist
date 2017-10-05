# duelist

DB:

CREATE TABLE `users` (

	`id` INT(11) NOT NULL AUTO_INCREMENT,
  
	`username` VARCHAR(20) NULL DEFAULT NULL,
  
	`password` TEXT NULL,
  
	`damage` INT(11) NOT NULL DEFAULT '0',
  
	`hp` INT(11) NOT NULL DEFAULT '0',
  
	`rating` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  
	PRIMARY KEY (`id`)
)
