delete from TRACKINGS;
delete from DEVICES;
delete from VEHICLES;
delete from USERS;

insert into VEHICLES values (1);
insert into VEHICLES values (2);

insert into USERS values (-1, 'jose_buendia', 'JoseBuendia1', 'José Arcadio', 'Buendia', '2037374105',
							'5ta Avenida de Macondo 1', '2034-203401', 'jose_buendia@gmail.com');
insert into USERS values (-2, 'ursula_iguaran', 'UrsulaIguaran2', 'Úrsula', 'Iguarán', '2387875106',
							'5ta Avenida de Macondo 1', '2034-203402', 'ursula_iguaran@gmail.com');

insert into DEVICES values (00001, -1, 1, 'ARDUINO GENUINO/UNO', '1.0');
insert into DEVICES values (00002, -2, 2, 'ARDUINO GENUINO/UNO', '1.0');

--insert into TRAKINGS (device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:09.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values  (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:10.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:11.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:12.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:13.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:14.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:15.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:16.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:17.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:18.000-03:00');
--insert into TRAKINGS(device_id, lat, long, sat, hdop, time) values (00001, -31.4109, -64.1897, 4 , 246, '10-09-2018 20:51:19.000-03:00');
