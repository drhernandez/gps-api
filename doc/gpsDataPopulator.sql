delete from TRACKINGS;
delete from DEVICES;
delete from VEHICLES;
delete from USERS;

insert into USERS values (10, null, null, 'jose_buendia', 'JoseBuendia1', 'José Arcadio', 'Buendia', '2037374105',
							'5ta Avenida de Macondo 1', '2034-203401', 'jose_buendia@gmail.com');
insert into USERS values (11, null, null, 'ursula_iguaran', 'UrsulaIguaran2', 'Úrsula', 'Iguarán', '2387875106',
							'5ta Avenida de Macondo 1', '2034-203402', 'ursula_iguaran@gmail.com');
					
insert into DEVICES values (00001, null, null, 'ARDUINO GENUINO/UNO', '1.0');
insert into DEVICES values (00002, null, null, 'ARDUINO GENUINO/UNO', '1.0');

insert into VEHICLES values (10, null, null, 10, 00001, 'Ford Fiesta', 'AA 383 TI', '2018');
insert into VEHICLES values (11, null, null, 11, 00002, 'Toyota Corolla', 'AD 257 TO', '2019');

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


----------------------------------------new device---------------------------------------------       
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:09.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:10.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:11.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:12.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:13.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:14.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:15.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:16.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:17.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:18.000-03:00');
insert into TRACKINGS (device_id, lat, lng, speed, sat, hdop, time)
        values (00003, -31.4109, -64.1897, 4 , 10.0, 246, '10-09-2018 20:51:19.000-03:00');