SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS lab3;
USE lab3;
DROP TABLE IF EXISTS desktops;
DROP TABLE IF EXISTS desktops_has_software;
DROP TABLE IF EXISTS monitors;
DROP TABLE IF EXISTS lamonitors;
DROP TABLE IF EXISTS office;
DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS printers;
DROP TABLE IF EXISTS printers_has_workspace;
DROP TABLE IF EXISTS routers;
DROP TABLE IF EXISTS software;
DROP TABLE IF EXISTS workers;
DROP TABLE IF EXISTS workers_has_offices;
DROP TABLE IF EXISTS workspace;
DROP TABLE IF EXISTS workspace_has_desktops;
DROP TABLE IF EXISTS workspace_has_monitors;


CREATE table desktops (
  id INT(11) NOT NULL AUTO_INCREMENT,
  motherboard_id INT(11) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  videoadapter VARCHAR(45) NULL DEFAULT NULL,
  RAM INT(11) NULL DEFAULT NULL,
  storage VARCHAR(45) NULL DEFAULT NULL,
  price INT(11) NULL DEFAULT NULL,
  type VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (id));

CREATE TABLE monitors (
  id INT(11) NOT NULL AUTO_INCREMENT,
  resolution VARCHAR(45) NULL DEFAULT NULL,
  `srgb_coverage` INT(11) NULL DEFAULT NULL,
  `matrix_type` VARCHAR(45) NULL DEFAULT NULL,
  price VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (id));

CREATE TABLE office (
  id INT(11) NOT NULL AUTO_INCREMENT,
  address VARCHAR(45) NULL DEFAULT NULL,
  name VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (id),
  INDEX office (id, `name` ASC) VISIBLE);

CREATE TABLE routers (
  ip VARCHAR(45) NOT NULL,
  office_id INT(11) NOT NULL,
  PRIMARY KEY (ip, office_id),
  INDEX fk_routers_office1_idx (office_id ASC) VISIBLE,
  CONSTRAINT fk_routers_office1
    FOREIGN KEY (office_id)
    REFERENCES office (id));

CREATE TABLE workers (
  id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NULL DEFAULT NULL,
  surname VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (id));

CREATE TABLE workspace (
  id INT(11) NOT NULL AUTO_INCREMENT,
  workers_id INT(11) NOT NULL,
  routers_ip VARCHAR(45) NOT NULL,
  routers_office_id INT(11) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_workspace_workers1_idx (id, workers_id ASC) VISIBLE,
  INDEX fk_workspace_routers1_idx (routers_ip ASC, routers_office_id ASC) VISIBLE,
  CONSTRAINT fk_workspace_routers1
    FOREIGN KEY (routers_ip , routers_office_id)
    REFERENCES routers (ip , office_id),
  CONSTRAINT fk_workspace_workers1
    FOREIGN KEY (workers_id)
    REFERENCES workers (id));

CREATE TABLE phones (
  number INT(11) NOT NULL,
  price INT(11) NULL DEFAULT NULL,
  workspace_id INT(11) NOT NULL,
  PRIMARY KEY (number),
  INDEX fk_phones_workspace1_idx (workspace_id ASC) VISIBLE,
  CONSTRAINT fk_phones_workspace1
    FOREIGN KEY (workspace_id)
    REFERENCES workspace (id));

CREATE TABLE printers (
  id INT(11) NOT NULL AUTO_INCREMENT,
  type VARCHAR(45) NULL DEFAULT NULL,
  brand VARCHAR(45) NULL,
  model VARCHAR(45) NULL,
  PRIMARY KEY (`id`));


CREATE TABLE IF NOT EXISTS software (
  id INT(11) NOT NULL,
  price VARCHAR(10) NULL DEFAULT NULL,
  name VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (id), 
  INDEX software (id, `name` ASC) VISIBLE);

CREATE TABLE IF NOT EXISTS workers_has_offices (
  workers_id INT(11) NOT NULL,
  offices_id INT(11) NOT NULL,
  PRIMARY KEY (workers_id, offices_id),
  INDEX fk_workers_has_offices_offices1_idx (offices_id ASC) VISIBLE,
  INDEX fk_workers_has_offices_workers_idx (workers_id ASC) VISIBLE,
  CONSTRAINT fk_workers_has_offices_offices1
    FOREIGN KEY (offices_id)
    REFERENCES office (id),
  CONSTRAINT fk_workers_has_offices_workers
    FOREIGN KEY (workers_id)
    REFERENCES workers (id));


CREATE TABLE IF NOT EXISTS workspace_has_desktops (
  workspace_id INT(11) NOT NULL,
  desktops_id INT(11) NOT NULL,
  PRIMARY KEY (workspace_id, desktops_id),
  INDEX fk_workspace_has_desktops_desktops1_idx (desktops_id ASC) VISIBLE,
  INDEX fk_workspace_has_desktops_workspace1_idx (workspace_id ASC) VISIBLE,
  CONSTRAINT fk_workspace_has_desktops_workspace1
    FOREIGN KEY (workspace_id)
    REFERENCES workspace (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_workspace_has_desktops_desktops1
    FOREIGN KEY (desktops_id)
    REFERENCES desktops (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS desktops_has_software (
  desktops_id INT(11) NOT NULL,
  software_id INT(11) NOT NULL,
  PRIMARY KEY (desktops_id, software_id),
  INDEX fk_desktops_has_software_software1_idx (software_id ASC) VISIBLE,
  INDEX fk_desktops_has_software_desktops1_idx (desktops_id ASC) VISIBLE,
  CONSTRAINT fk_desktops_has_software_desktops1
    FOREIGN KEY (desktops_id)
    REFERENCES  desktops (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_desktops_has_software_software1
    FOREIGN KEY (software_id)
    REFERENCES software (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS workspace_has_monitors (
  workspace_id INT(11) NOT NULL,
  monitors_id INT(11) NOT NULL,
  PRIMARY KEY (workspace_id, monitors_id),
  INDEX fk_workspace_has_monitors_monitors1_idx (monitors_id ASC) VISIBLE,
  INDEX fk_workspace_has_monitors_workspace1_idx (workspace_id ASC) VISIBLE,
  CONSTRAINT fk_workspace_has_monitors_workspace1
    FOREIGN KEY (workspace_id)
    REFERENCES workspace (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_workspace_has_monitors_monitors1
    FOREIGN KEY (monitors_id)
    REFERENCES monitors (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS printers_has_workspace (
  printers_id INT(11) NOT NULL,
  workspace_id INT(11) NOT NULL,
  PRIMARY KEY (printers_id, workspace_id),
  INDEX fk_printers_has_workspace_workspace1_idx (workspace_id ASC) VISIBLE,
  INDEX fk_printers_has_workspace_printers1_idx (printers_id ASC) VISIBLE,
  CONSTRAINT fk_printers_has_workspace_printers1
    FOREIGN KEY (printers_id)
    REFERENCES printers (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_printers_has_workspace_workspace1
    FOREIGN KEY (workspace_id)
    REFERENCES workspace (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (1,1,'DELL','RTX 2080',64,'Samsung SSD',2000,'Desktop');
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (2,2,'ASUS','GTX 1050',8,'Intel SSD',500,'Desktop');
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (3,3,'HP','Radeon vega 64',1,'HDD',200,'Laptop');
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (4,4,'ASUS','GeForce 610',1,'HDD',100,'Laptop');
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (5,5,'DELL','RTX Titan',128,'Samsung SSD',3000,'Desktop');
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (6,6,'APPLE','Quadro rtx 8000',128,'Hynix SSD',5000,'Monoblock');
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (7,7,'LG','Intel hd graphics',4,'HDD',200,'Monoblock');
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (8,8,'ASUS','none',256,'HDD',10000,'Mini desktop');
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (9,9,'DELL','Nvidia Ion',64,'Hynix SATA SSD',500,'Mini desktop');
INSERT INTO desktops (id,motherboard_id,name,videoadapter,RAM,storage,price,type) VALUES (10,10,'LENOVO','GTX 1060 Max-Q',64,'HDD',1500,'Laptop');


INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (4,2);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (2,3);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (1,4);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (1,5);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (2,6);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (3,7);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (6,7);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (3,9);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (5,9);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (6,10);
INSERT INTO desktops_has_software (desktops_id,software_id) VALUES (7,10);


INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (1,'1080p',70,'ips','100');
INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (2,'720p',50,'tn','50');
INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (3,'720p',50,'ahva','60');
INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (4,'4k',100,'oled','300');
INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (5,'8k',150,'oled','700');
INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (6,'1440p',120,'ips','500');
INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (7,'1080p',120,'tn','300');
INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (8,'720p',45,'ahva','70');
INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (9,'1080p',60,'oled','200');
INSERT INTO monitors (id,resolution,`srgb_coverage`,`matrix_type`,price) VALUES (10,'4k',100,'oled','500');


INSERT INTO office (id,address,`name`) VALUES (1,'Lviv, Chukarina street, 20','Main'); 
INSERT INTO office (id,address,`name`) VALUES (2,'Lviv, Rynok square, 4','LMR');
INSERT INTO office (id,address,`name`) VALUES  (3,'Lviv, Antonycha street, 5','Secondary');
INSERT INTO office (id,address,`name`) VALUES  (4,'Lviv, Bandery street, 20','NULP');
INSERT INTO office (id,address,`name`) VALUES  (5,'Lviv, Vesnyana street, 4','technical');
INSERT INTO office (id,address,`name`) VALUES  (6,'Lviv, Antonovycha treet, 30','Service');
INSERT INTO office (id,address,`name`) VALUES  (7,'Lviv, Zelena street, 500','Dyplomatic');
INSERT INTO office (id,address,`name`) VALUES  (8,'Kyiv, Hreshchatyk street, 5','Kyivs');
INSERT INTO office (id,address,`name`) VALUES  (9,'Lviv, Shevchenka prospect, 300','Literature');
INSERT INTO office (id,address,`name`) VALUES  (10,'Lviv, Horodotska street, 200','Railway');


INSERT INTO phones (number,price,workspace_id) VALUES (234432,30,8);
INSERT INTO phones (number,price,workspace_id) VALUES (432342,90,9);
INSERT INTO phones (number,price,workspace_id) VALUES (3424324,30,4);
INSERT INTO phones (number,price,workspace_id) VALUES (4234324,35,6);
INSERT INTO phones (number,price,workspace_id) VALUES (6273393,110,10);
INSERT INTO phones (number,price,workspace_id) VALUES (9863653,10,2);
INSERT INTO phones (number,price,workspace_id) VALUES (22121288,20,1);
INSERT INTO phones (number,price,workspace_id) VALUES (24334423,40,5);
INSERT INTO phones (number,price,workspace_id) VALUES (67437843,50,3);
INSERT INTO phones (number,price,workspace_id) VALUES (243342423,65,7);


INSERT INTO printers (id,`type`,brand,model) VALUES (1,'laser','Samsung','ghdsf09');
INSERT INTO printers (id,`type`,brand,model) VALUES (2,'stream','Toshiba','jap202');
INSERT INTO printers (id,`type`,brand,model) VALUES (3,'laser','Gurren','Lagann');
INSERT INTO printers (id,`type`,brand,model) VALUES (4,'stream','NoName','hdsh');
INSERT INTO printers (id,`type`,brand,model) VALUES (5,'ancient','Mitsubishi','Lancer');
INSERT INTO printers (id,`type`,brand,model) VALUES (6,'ancient','DM','fdsuy');
INSERT INTO printers (id,`type`,brand,model) VALUES (7,'stream','mechanic','ldsgjahg');
INSERT INTO printers (id,`type`,brand,model) VALUES (8,'laser','LG','udayuhiad');
INSERT INTO printers (id,`type`,brand,model) VALUES (9,'3D','Apple','iPrinter');
INSERT INTO printers (id,`type`,brand,model) VALUES (10,'3D','Asus','ZenPrinter');


INSERT INTO printers_has_workspace (printers_id,workspace_id) VALUES (6,1);
INSERT INTO printers_has_workspace (printers_id,workspace_id) VALUES (4,2);
INSERT INTO printers_has_workspace (printers_id,workspace_id) VALUES (3,3);
INSERT INTO printers_has_workspace (printers_id,workspace_id) VALUES (2,4);
INSERT INTO printers_has_workspace (printers_id,workspace_id) VALUES (1,5);
INSERT INTO printers_has_workspace (printers_id,workspace_id) VALUES (5,6);


INSERT INTO routers (ip,office_id) VALUES ('192.168.1.1',1);
INSERT INTO routers (ip,office_id)  VALUES ('178.257.376.936',2);
INSERT INTO routers (ip,office_id)  VALUES ('235.36.97.395',3);
INSERT INTO routers (ip,office_id)  VALUES ('167.0.23.556',4);
INSERT INTO routers (ip,office_id)  VALUES ('473.92.09.876',5);
INSERT INTO routers (ip,office_id)  VALUES ('266.23.12.457',6);
INSERT INTO routers (ip,office_id)  VALUES ('123.4.5.6',7);
INSERT INTO routers (ip,office_id)  VALUES ('6.5.4.3',8);
INSERT INTO routers (ip,office_id)  VALUES ('1.6.7.8',9);
INSERT INTO routers (ip,office_id)  VALUES ('756.926.56.358',10);


INSERT INTO software (id,price,`name`) VALUES (1,'200','intellij idea');
INSERT INTO software (id,price,`name`) VALUES (2,'50','windows 10');
INSERT INTO software (id,price,`name`) VALUES (3,'30','adobe photoshop');
INSERT INTO software (id,price,`name`) VALUES (4,'free','pycharm');
INSERT INTO software (id,price,`name`) VALUES (5,'100','Microsoft office');
INSERT INTO software (id,price,`name`) VALUES (6,'free','Telegram');
INSERT INTO software (id,price,`name`) VALUES (7,'free','Avidemux');


INSERT INTO workers (id,`name`,surname) VALUES (1,'Oleh','Ivaniuk');
INSERT INTO workers (id,`name`,surname) VALUES (2,'Diana','Vynnyk');
INSERT INTO workers (id,`name`,surname) VALUES (3,'Marysia','Mostova');
INSERT INTO workers (id,`name`,surname) VALUES (4,'Maxym','Marina');
INSERT INTO workers (id,`name`,surname) VALUES (5,'Vasyl\'','Havryluk');
INSERT INTO workers (id,`name`,surname) VALUES (6,'Danylo','Chalyj');
INSERT INTO workers (id,`name`,surname) VALUES (7,'Yulia','Yasso');
INSERT INTO workers (id,`name`,surname) VALUES (8,'Roman','Senyshyn');
INSERT INTO workers (id,`name`,surname) VALUES (9,'Oleh','Kuzyk');
INSERT INTO workers (id,`name`,surname) VALUES (10,'Pavlo','Sivakov');


INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (1,1);
INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (10,2);
INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (5,3);
INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (3,4);
INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (4,5);
INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (2,6);
INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (6,7);
INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (9,8);
INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (8,9);
INSERT INTO workers_has_offices (workers_id,offices_id) VALUES (7,10);


INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (1,1,'192.168.1.1',1);
INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (2,2,'178.257.376.936',2);
INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (3,3,'235.36.97.395',3);
INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (4,4,'167.0.23.556',4);
INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (5,5,'473.92.09.876',5);
INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (6,6,'266.23.12.457',6);
INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (7,7,'123.4.5.6',7);
INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (8,8,'6.5.4.3',8);
INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (9,9,'1.6.7.8',9);
INSERT INTO workspace (id,workers_id,routers_ip,routers_office_id) VALUES (10,10,'756.926.56.358',10);


INSERT INTO workspace_has_desktops (workspace_id,desktops_id) VALUES (1,1);
INSERT INTO workspace_has_desktops (workspace_id,desktops_id) VALUES (2,1);
INSERT INTO workspace_has_desktops (workspace_id,desktops_id) VALUES (3,2);
INSERT INTO workspace_has_desktops (workspace_id,desktops_id) VALUES (4,3);
INSERT INTO workspace_has_desktops (workspace_id,desktops_id) VALUES (5,4);
INSERT INTO workspace_has_desktops (workspace_id,desktops_id) VALUES (6,5);
INSERT INTO workspace_has_desktops (workspace_id,desktops_id) VALUES (7,6);
INSERT INTO workspace_has_desktops (workspace_id,desktops_id) VALUES (7,7);


INSERT INTO workspace_has_monitors (workspace_id,monitors_id) VALUES (1,1);
INSERT INTO workspace_has_monitors (workspace_id,monitors_id) VALUES (2,1);
INSERT INTO workspace_has_monitors (workspace_id,monitors_id) VALUES (3,1);
INSERT INTO workspace_has_monitors (workspace_id,monitors_id) VALUES (9,2);
INSERT INTO workspace_has_monitors (workspace_id,monitors_id) VALUES (8,3);
INSERT INTO workspace_has_monitors (workspace_id,monitors_id) VALUES (7,4);
INSERT INTO workspace_has_monitors (workspace_id,monitors_id) VALUES (6,5);
INSERT INTO workspace_has_monitors (workspace_id,monitors_id) VALUES (5,6);
INSERT INTO workspace_has_monitors (workspace_id,monitors_id) VALUES (4,7);

