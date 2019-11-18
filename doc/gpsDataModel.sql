drop table if exists TRACKINGS;
drop table if exists VEHICLES;
drop table if exists SPEED_ALERTS_HISTORY;
drop table if exists MOVEMENT_ALERTS_HISTORY;
drop table if exists SPEED_ALERTS;
drop table if exists MOVEMENT_ALERTS;
drop table if exists DEVICES;
drop table if exists ACCESS_TOKENS;
drop table if exists RECOVERY_TOKENS;
drop table if exists USERS;
drop table if exists BRANDS;
drop table if exists BRAND_LINES;


create table USERS(
	id serial primary key not null,
	deleted_at timestamp,
	last_updated timestamp,
	email varchar unique not null,
	password varchar not null,
	name varchar not null,
	last_name varchar not null,
	dni varchar not null,
	address varchar not null,
	phone varchar not null
);
ALTER TABLE public.users ALTER COLUMN id TYPE int8 USING id::int8;


create table ACCESS_TOKENS(
	user_id bigint references USERS(id) primary key not null,
	token varchar
);

create table RECOVERY_TOKENS(
	user_id bigint references USERS(id) primary key not null,
	token varchar not null unique,
	expiration_date timestamp not null
);

create table DEVICES(
	id bigint primary key not null,
	deleted_at timestamp,
	last_updated timestamp,
	model varchar not null,
	software_version varchar
);


create table VEHICLES(
	id serial primary key not null,
	deleted_at timestamp,
	last_updated timestamp,
	user_id serial references USERS(id) on delete restrict not null,
	device_id bigint references DEVICES(id) on delete restrict not null,
	type varchar,
	plate varchar not null,
	model varchar	
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
	device_id bigint references DEVICES(id) on delete cascade unique,
	created_at timestamp not null,
	updated_at timestamp,
	activated_at timestamp
);
ALTER TABLE public.speed_alerts ALTER COLUMN id TYPE int8 USING id::int8;


create table MOVEMENT_ALERTS(
	id serial primary key not null, 
	active boolean not null,
	lat real,
	lng real,
	device_id bigint references DEVICES(id) on delete cascade unique,
	created_at timestamp not null,
	updated_at timestamp,
	activated_at timestamp
);
ALTER TABLE public.movement_alerts ALTER COLUMN id TYPE int8 USING id::int8;


create table SPEED_ALERTS_HISTORY(
	time timestamp primary key not null, 
	alert_id bigint references SPEED_ALERTS(id) on delete cascade,
	speed real
);


create table MOVEMENT_ALERTS_HISTORY(
	time timestamp primary key not null, 
	alert_id bigint references MOVEMENT_ALERTS(id) on delete cascade,
	lat real,
	lng real
);


create table BRANDS (
  id serial primary key not null,
  name varchar default null
);
ALTER TABLE public.BRANDS ALTER COLUMN id TYPE int8 USING id::int8;


CREATE TABLE BRAND_LINES (
  id serial primary key not null,
  name varchar NOT NULL,
  brand_id bigint references BRANDS(id) on delete cascade
);
ALTER TABLE public.BRAND_LINES ALTER COLUMN id TYPE int8 USING id::int8;


