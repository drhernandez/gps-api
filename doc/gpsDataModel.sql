drop table TRACKINGS;
drop table VEHICLES;
drop table SPEED_ALERTS;
drop table DEVICES;
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

ALTER TABLE public.users ALTER COLUMN id TYPE int8 USING id::int8;


create table DEVICES(
	id bigint primary key not null,
	deleted_at timestamp,
	last_updated timestamp,
	model varchar(45) not null,
	software_version varchar(45)
);


create table VEHICLES(
	id serial primary key not null,
	deleted_at timestamp,
	last_updated timestamp,
	user_id serial references USERS(id) on delete restrict not null,
	device_id bigint references DEVICES(id) on delete restrict not null,
	type varchar(45),
	plate varchar(45) not null,
	model varchar(45)	
);

ALTER TABLE public.vehicles ALTER COLUMN id TYPE int8 USING id::int8;
ALTER TABLE public.vehicles ALTER COLUMN user_id TYPE int8 USING user_id::int8;


create table TRACKINGS(
	id serial primary key not null,
	device_id bigint references DEVICES(id) on delete restrict not null,
	lat real not null,
	lng real  not null,
	speed real,
	sat integer not null,
	hdop integer not null,
	time timestamp not null
);

ALTER TABLE public.trackings ALTER COLUMN id TYPE int8 USING id::int8;


create table SPEED_ALERTS(
	id serial primary key not null, 
	active boolean not null,
	speed real,
	device_id bigint references DEVICES(id) on delete cascade unique
);

ALTER TABLE public.speed_alerts ALTER COLUMN id TYPE int8 USING id::int8;
