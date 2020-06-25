delete from TRACKINGS;
delete from VEHICLES;
delete from DEVICES;
delete from SPEED_ALERTS_HISTORY;
delete from MOVEMENT_ALERTS_HISTORY;
delete from SPEED_ALERTS;
delete from MOVEMENT_ALERTS;
delete from BRAND_LINES;
delete from BRANDS;

					
insert into DEVICES (physical_id, deleted_at, last_updated, model, software_version, status) 
	values (10001, null, null, 'ARDUINO GENUINO/UNO', '1.0', 'ACTIVE');
insert into DEVICES (physical_id, deleted_at, last_updated, model, software_version, status) 
	values (00002, null, null, 'ARDUINO GENUINO/UNO', '1.0', 'ACTIVE');

insert into VEHICLES values (10, 'ACTIVE', null, null, 1, 00001, 'AA 383 TI', 'Ford', 'Fiesta');
insert into VEHICLES values (11, 'ACTIVE', null, null, 2, 00002,  'AD 257 TO', 'Toyota', 'Corolla');

insert into SPEED_ALERTS (active, speed, device_id, created_at, updated_at, activated_at, last_fired) 
	values (false, 60, 00001, NOW(), null, NOW(), null);
insert into SPEED_ALERTS (active, speed, device_id, created_at, updated_at, activated_at, last_fired) 
	values (false, 60, 00002, NOW(), null, NOW(), null);

insert into MOVEMENT_ALERTS (active, lat, lng, device_id, created_at, updated_at, activated_at, last_fired) 
	values (false, -31.422068, -64.186497, 00001, NOW(), null, NOW(), null);
insert into MOVEMENT_ALERTS (active, lat, lng, device_id, created_at, updated_at, activated_at, last_fired) 
	values (false, -31.422068, -64.186497, 00002, NOW(), null, NOW(), null);

insert into SPEED_ALERTS_HISTORY values (NOW(), 1, 80);

insert into MOVEMENT_ALERTS_HISTORY values (NOW(), 1, -31.422068, -64.186497);

insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.422108, -64.186429, 10.0, 4 , 246, '10-09-2018 20:51:09.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.422218, -64.185785, 10.0, 4 , 246, '10-09-2018 20:51:19.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.422731, -64.184842, 10.0, 4 , 246, '10-09-2018 20:51:29.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.422841, -64.183941, 10.0, 4 , 246, '10-09-2018 20:51:39.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.423097, -64.183512, 10.0, 4 , 246, '10-09-2018 20:51:49.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.423015, -64.183206, 10.0, 4 , 246, '10-09-2018 20:51:59.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.423108, -64.183062, 10.0, 4 , 246, '10-09-2018 20:52:09.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.423136, -64.182994, 10.0, 4 , 246, '10-09-2018 20:52:19.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.423159, -64.182929, 10.0, 4 , 246, '10-09-2018 20:52:29.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.423182, -64.182865, 10.0, 4 , 246, '10-09-2018 20:52:39.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.423269, -64.182629, 10.0, 4 , 246, '10-09-2018 20:52:49.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.422953, -64.182511, 10.0, 4 , 246, '10-09-2018 20:52:59.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.422761, -64.182436, 10.0, 4 , 246, '10-09-2018 20:53:09.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.422338, -64.182310, 10.0, 4 , 246, '10-09-2018 20:53:19.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421972, -64.182160, 10.0, 4 , 246, '10-09-2018 20:53:29.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421729, -64.182367, 10.0, 4 , 246, '10-09-2018 20:53:39.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421665, -64.182646, 10.0, 4 , 246, '10-09-2018 20:53:49.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421546, -64.183096, 10.0, 4 , 246, '10-09-2018 20:53:59.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421482, -64.183482, 10.0, 4 , 246, '10-09-2018 20:54:09.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421381, -64.183772, 10.0, 4 , 246, '10-09-2018 20:54:19.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421271, -64.184083, 10.0, 4 , 246, '10-09-2018 20:54:29.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421198, -64.184394, 10.0, 4 , 246, '10-09-2018 20:54:39.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421088, -64.184866, 10.0, 4 , 246, '10-09-2018 20:54:49.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.420997, -64.185188, 10.0, 4 , 246, '10-09-2018 20:54:59.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.420914, -64.185617, 10.0, 4 , 246, '10-09-2018 20:55:09.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.420859, -64.185874, 10.0, 4 , 246, '10-09-2018 20:55:19.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.420951, -64.186110, 10.0, 4 , 246, '10-09-2018 20:55:29.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421180, -64.186207, 10.0, 4 , 246, '10-09-2018 20:55:39.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421381, -64.186250, 10.0, 4 , 246, '10-09-2018 20:55:49.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421519, -64.186357, 10.0, 4 , 246, '10-09-2018 20:55:59.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421665, -64.186422, 10.0, 4 , 246, '10-09-2018 20:56:09.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421793, -64.186422, 10.0, 4 , 246, '10-09-2018 20:56:19.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.421921, -64.186508, 10.0, 4 , 246, '10-09-2018 20:56:29.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00001, -31.422068, -64.186497, 10.0, 4 , 246, '10-09-2018 20:56:39.000-03:00');
       
INSERT INTO BRANDS (id, name) VALUES 
 (1,'VOLVO'),
 (2,'AGRALE'),
 (3,'ALFA ROMEO'),
 (4,'AUDI'),
 (5,'BMW'),
 (6,'CHERY'),
 (7,'CHEVROLET'),
 (8,'CHRYSLER'),
 (9,'CITROEN'),
 (10,'DACIA'),
 (11,'DAEWO'),
 (12,'DAIHATSU'),
 (13,'DODGE'),
 (14,'FERRARI'),
 (15,'FIAT'),
 (16,'FORD'),
 (17,'GALLOPER'),
 (18,'HEIBAO'),
 (19,'HONDA'),
 (20,'HYUNDAI'),
 (21,'ISUZU'),
 (22,'JAGUAR'),
 (23,'JEEP'),
 (24,'KIA'),
 (25,'LADA'),
 (26,'LAND ROVER'),
 (27,'LEXUS'),
 (28,'MASERATI'),
 (29,'MAZDA'),
 (30,'MERCEDES BENZ'),
 (31,'MG'),
 (32,'MINI'),
 (33,'MITSUBISHI'),
 (34,'NISSAN'),
 (35,'PEUGEOT'),
 (36,'PORSCHE'),
 (37,'RAM'),
 (38,'RENAULT'),
 (39,'ROVER'),
 (40,'SAAB'),
 (41,'SEAT'),
 (42,'SMART'),
 (43,'SSANGYONG'),
 (44,'SUBARU'),
 (45,'SUZUKI'),
 (46,'TATA'),
 (47,'TOYOTA'),
 (48,'VOLKSWAGEN');

INSERT INTO BRAND_LINES (id, name, brand_id) VALUES 
 (1,'XC90',1),
 (2,'MARRUA',2),
 (3,'147',3),
 (4,'156',3),
 (5,'159',3),
 (6,'166',3),
 (7,'BRERA',3),
 (8,'GIULIETTA',3),
 (9,'GT',3),
 (10,'GTV',3),
 (11,'MITO',3),
 (12,'SPIDER',3),
 (13,'A1',4),
 (14,'A3',4),
 (15,'A4',4),
 (16,'A5',4),
 (17,'A6',4),
 (18,'A7',4),
 (19,'A8',4),
 (20,'ALLROAD',4),
 (21,'Q3',4),
 (22,'Q5',4),
 (23,'Q7',4),
 (24,'R8',4),
 (25,'TT',4),
 (26,'SERIE1',5),
 (27,'SERIE3',5),
 (28,'SERIE5',5),
 (29,'SERIE6',5),
 (30,'SERIE7',5),
 (31,'X1',5),
 (32,'X3',5),
 (33,'X5',5),
 (34,'X6',5),
 (35,'Z3',5),
 (36,'Z4',5),
 (37,'FACE',6),
 (38,'FULWIN',6),
 (39,'QQ',6),
 (40,'SKIN',6),
 (41,'TIGGO',6),
 (42,'AGILE',7),
 (43,'ASTRA',7),
 (44,'ASTRA II',7),
 (45,'AVALANCHE',7),
 (46,'AVEO',7),
 (47,'BLAZER',7),
 (48,'CAMARO',7),
 (49,'CAPTIVA',7),
 (50,'CELTA',7),
 (51,'CLASSIC',7),
 (52,'COBALT',7),
 (53,'CORSA',7),
 (54,'CORSA CLASSIC',7),
 (55,'CORSA II',7),
 (56,'CORVETTE',7),
 (57,'CRUZE',7),
 (58,'MERIVA',7),
 (59,'MONTANA',7),
 (60,'ONIX',7),
 (61,'PRISMA',7),
 (62,'VECTRA',7),
 (63,'S-10',7),
 (64,'SILVERADO',7),
 (65,'SONIC',7),
 (66,'SPARK',7),
 (67,'SPIN',7),
 (68,'TRACKER',7),
 (69,'TRAILBLAZER',7),
 (70,'ZAFIRA',7),
 (71,'300',8),
 (72,'CARAVAN',8),
 (73,'TOWN & COUNTRY',8),
 (74,'GRAND CARAVAN',8),
 (75,'CROSSFIRE',8),
 (76,'NEON',8),
 (77,'PT CRUISER',8),
 (78,'SEBRIG',8),
 (79,'BERLINGO',9),
 (80,'C3',9),
 (81,'C3 AIRCROSS',9),
 (82,'C3 PICASSO',9),
 (83,'C4 AIRCROSS',9),
 (84,'C4 LOUNGE',9),
 (85,'C4 PICASSO',9),
 (86,'C4 GRAND PICASSO',9),
 (87,'C5',9),
 (88,'C6',9),
 (89,'DS3',9),
 (90,'DS4',9),
 (91,'C15',9),
 (92,'JUMPER',9),
 (93,'SAXO',9),
 (94,'XSARA',9),
 (95,'XSARA PICASSO',9),
 (96,'PICK-UP',10),
 (97,'LANOS',11),
 (98,'LEGANZA',11),
 (99,'NUBIRA',11),
 (100,'MATIZ',11),
 (101,'TACUMA',11),
 (102,'DAMAS',11),
 (103,'LABO',11),
 (104,'MOVE',12),
 (105,'ROCKY',12),
 (106,'SIRION',12),
 (107,'TERIOS',12),
 (108,'MIRA',12),
 (109,'JOURNEY',13),
 (110,'RAM',13),
 (111,'360',14),
 (112,'430',14),
 (113,'456',14),
 (114,'575',14),
 (115,'599',14),
 (116,'612',14),
 (117,'CALIFORNIA',14),
 (118,'SUPERAMERICA',14),
 (119,'500',15),
 (120,'BRAVA',15),
 (121,'BRAVO',15),
 (122,'DOBLO',15),
 (123,'DUCATO',15),
 (124,'FIORINO',15),
 (125,'FIORINO QUBO',15),
 (126,'IDEA',15),
 (127,'LINEA',15),
 (128,'MAREA',15),
 (129,'PALIO',15),
 (130,'PALIO ADVENTURE',15),
 (131,'PUNTO',15),
 (132,'QUBO',15),
 (133,'SIENA',15),
 (134,'GRAND SIENA',15),
 (135,'STILO',15),
 (136,'STRADA',15),
 (137,'UNO',15),
 (138,'UNO EVO',15),
 (139,'COURIER',16),
 (140,'ECOSPORT',16),
 (141,'ECOSPORT KD',16),
 (142,'ESCAPE',16),
 (143,'F100',16),
 (144,'FIESTA KD',16),
 (145,'FIESTA',16),
 (146,'FOCUS',16),
 (147,'FOCUS III',16),
 (148,'KA',16),
 (149,'KUGA',16),
 (150,'MONDEO',16),
 (151,'RANGER',16),
 (152,'S-MAX',16),
 (153,'TRANSIT',16),
 (154,'EXCEED',17),
 (155,'HB',18),
 (156,'ACCORD',19),
 (157,'CITY',19),
 (158,'CIVIC',19),
 (159,'CRV',19),
 (160,'FIT',19),
 (161,'HRV',19),
 (162,'LEGEND',19),
 (163,'PILOT',19),
 (164,'STREAM',19),
 (165,'ACCENT',20),
 (166,'ATOS PRIME',20),
 (167,'COUPE',20),
 (168,'ELANTRA',20),
 (169,'I 10',20),
 (170,'I 30',20),
 (171,'MATRIX',20),
 (172,'SANTA FE',20),
 (173,'SONATA',20),
 (174,'TERRACAN',20),
 (175,'TRAJET',20),
 (176,'TUCSON',20),
 (177,'VELOSTER',20),
 (178,'VERACRUZ',20),
 (179,'AMIGO',21),
 (180,'PICK-UP CABIAN SIMPLE',21),
 (181,'PICK-UP SPACE CAB',21),
 (182,'PICK-UP CABINA DOBLE',21),
 (183,'TROOPER',21),
 (184,'X-TYPE',22),
 (185,'XF',22),
 (186,'F-TYPE',22),
 (187,'S-TYPE',22),
 (188,'XJ',22),
 (189,'XK',22),
 (190,'CHEROKEE',23),
 (191,'COMPASS',23),
 (192,'GRAND CHEROKEE',23),
 (193,'PATRIOT',23),
 (194,'WRANGLER',23),
 (195,'CARENS',24),
 (196,'CARNIVAL',24),
 (197,'CERATO',24),
 (198,'MAGENTIS',24),
 (199,'MOHAVE',24),
 (200,'OPIRUS',24),
 (201,'PICANTO',24),
 (202,'RIO',24),
 (203,'RONDO',24),
 (204,'SPORTAGE',24),
 (205,'GRAND SPORTAGE',24),
 (206,'SORENTO',24),
 (207,'SOUL',24),
 (208,'PREGIO',24),
 (209,'AFALINA',25),
 (210,'SAMARA',25),
 (211,'DEFENDER',26),
 (212,'DISCOVERY',26),
 (213,'FREELANDER',26),
 (214,'RANGE ROVER',26),
 (215,'LS',27),
 (216,'GS',27),
 (217,'IS',27),
 (218,'QUATTROPORTE',28),
 (219,'COUPE',28),
 (220,'GRAND TURISMO',28),
 (221,'SPYDER',28),
 (222,'323',29),
 (223,'626',29),
 (224,'MPV',29),
 (225,'B 2500',29),
 (226,'B 2900',29),
 (227,'AMG',30),
 (228,'CLASE A',30),
 (229,'CLASE B',30),
 (230,'CLASE C',30),
 (231,'CLASE CL',30),
 (232,'CLASE CLA',30),
 (233,'CLASE CLC',30),
 (234,'CLASE CLK',30),
 (235,'CLASE CLS',30),
 (236,'CLASE E',30),
 (237,'CLASE G',30),
 (238,'CLASE GL',30),
 (239,'CLASE ML',30),
 (240,'CLASE S',30),
 (241,'CLASE SL',30),
 (242,'CLASE SLK',30),
 (243,'VIANO',30),
 (244,'MGF',31),
 (245,'COOPER',32),
 (246,'CANTER',33),
 (247,'L-200',33),
 (248,'LANCER',33),
 (249,'MONTERO',33),
 (250,'NATIVA',33),
 (251,'OUTLANDER',33),
 (252,'350',34),
 (253,'370Z',34),
 (254,'PATHFINDER',34),
 (255,'FRONTIER',34),
 (256,'MARCH',34),
 (257,'MURANO',34),
 (258,'NP300',34),
 (259,'PICK-UP',34),
 (260,'SENTRA',34),
 (261,'TEANA',34),
 (262,'TERRANO II',34),
 (263,'TIIDA',34),
 (264,'VERSA',34),
 (265,'X-TERRA',34),
 (266,'X-TRAIL',34),
 (267,'106',35),
 (268,'206',35),
 (269,'207',35),
 (270,'208',35),
 (271,'306',35),
 (272,'307',35),
 (273,'308',35),
 (274,'3008',35),
 (275,'405',35),
 (276,'406',35),
 (277,'407',35),
 (278,'408',35),
 (279,'4008',35),
 (280,'508',35),
 (281,'5008',35),
 (282,'607',35),
 (283,'806',35),
 (284,'807',35),
 (285,'RCZ',35),
 (286,'EXPERT',35),
 (287,'HOGGAR',35),
 (288,'PARTNER',35),
 (289,'BOXER',35),
 (290,'911',36),
 (291,'BOXSTER',36),
 (292,'CAYENNE',36),
 (293,'CAYMAN',36),
 (294,'PANAMERA',36),
 (295,'1500',37),
 (296,'2500',37),
 (297,'CLIO MIO',38),
 (298,'CLIO L/NUEVA',38),
 (299,'CLIO 2',38),
 (300,'DUSTER',38),
 (301,'FLUENCE',38),
 (302,'KANGOO',38),
 (303,'KANGOO FURGON',38),
 (304,'KOLEOS',38),
 (305,'LAGUNA',38),
 (306,'LATITUDE',38),
 (307,'LOGAN',38),
 (308,'MEGANE',38),
 (309,'MEGANE II',38),
 (310,'MEGANE III',38),
 (311,'SANDERO',38),
 (312,'SANDERO STEPWAY',38),
 (313,'SCENIC',38),
 (314,'SYMBOL',38),
 (315,'TWINGO',38),
 (316,'TRAFIC',38),
 (317,'MASTER',38),
 (318,'LINEA 25',39),
 (319,'LINEA 45',39),
 (320,'LINEA 75',39),
 (321,'LINEA 9.3',39),
 (322,'LINEA 9.5',39),
 (323,'ALHAMBRA',40),
 (324,'ALTEA',40),
 (325,'CORDOBA',40),
 (326,'IBIZA',40),
 (327,'INCA FURGON',40),
 (328,'LEON',40),
 (329,'TOLEDO',40),
 (330,'FORTWO',41),
 (331,'ACTYON',42),
 (332,'KORANDO',42),
 (333,'KYRON',42),
 (334,'ISTANA',42),
 (335,'MUSSO',42),
 (336,'REXTON',42),
 (337,'STAVIC',42),
 (338,'IMPREZA',43),
 (339,'LEGACY',43),
 (340,'OUTBACK',43),
 (341,'TRIBECA',43),
 (342,'XV',43),
 (343,'FORESTER',43),
 (344,'FUN',44),
 (345,'GRAND VITARA',44),
 (346,'SWIFT',44),
 (347,'JIMNY',44),
 (348,'207 TELCOLINE',45),
 (349,'SUMO',46),
 (350,'86',47),
 (351,'AVENSIS',47),
 (352,'CAMRY',47),
 (353,'COROLLA',47),
 (354,'CORONA',47),
 (355,'ETIOS',47),
 (356,'ETIOS CROSS',47),
 (357,'HILUX',47),
 (358,'LAND CRUISER',47),
 (359,'PRIUS',47),
 (360,'RAV 4',47),
 (361,'AMAROK',48),
 (362,'BORA',48),
 (363,'CADDY',48),
 (364,'CROSSFOX',48),
 (365,'FOX',48),
 (366,'GOL',48),
 (367,'GOL TREND',48),
 (368,'GOLF',48),
 (369,'MULTIVAN',48),
 (370,'THE BEETLE',48),
 (371,'NEW BEETLE',48),
 (372,'PASSAT',48),
 (373,'CC',48),
 (374,'POLO',48),
 (375,'SANTANA',48),
 (376,'SAVEIRO',48),
 (377,'SCIROCCO',48),
 (378,'SHARAN',48),
 (379,'SURAN',48),
 (380,'TIGUAN',48),
 (381,'TOUAREG',48),
 (382,'TRANSPORTER',48),
 (383,'UP',48),
 (384,'VENTO',48),
 (385,'VOYAGE',48),
 (386,'C30',1),
 (387,'C70',1),
 (388,'S40',1),
 (389,'S60',1),
 (390,'S80',1),
 (391,'V40',1),
 (392,'V50',1),
 (393,'V60',1),
 (394,'V70',1),
 (395,'XC60',1),
 (396,'XC70',1);



