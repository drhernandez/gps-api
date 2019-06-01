drop table TRACKINGS;
drop table DEVICES;
drop table VEHICLES;
drop table USERS;

create table USERS(
	id serial primary key not null,
	deleted_at timestamp,
	last_updated timestamp,
	user_name varchar(45) not null,
	password varchar(45) not null,
	name varchar(45) not null,
	last_name varchar(45) not null,
	dni varchar(45) not null,
	address varchar(45) not null,
	phone varchar(45) not null,
	email varchar(45) not null
);

create table VEHICLES(
	id serial primary key not null,
	deleted_at timestamp,
	last_updated timestamp,
	user_id serial references USERS(id) on delete restrict not null,
	type varchar(45),
	plate varchar(45) not null,
	model varchar(45)	
);

create table DEVICES(
	id bigint primary key not null,
	deleted_at timestamp,
	last_updated timestamp,
	vehicle_id bigint unique references VEHICLES(id) on delete restrict not null,
	model varchar(45) not null,
	software_version varchar(45)
);

create table TRACKINGS(
	id serial primary key not null,
	device_id bigint references DEVICES(id) on delete restrict not null,
	lat real not null,
	long real  not null,
	sat integer not null,
	hdop integer not null,
	time timestamp not null
);