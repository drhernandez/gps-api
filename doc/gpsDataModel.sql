drop table TRACKINGS;
drop table VEHICLES;
drop table SPEED_ALERTS_HISTORY;
drop table MOVEMENT_ALERTS_HISTORY;
drop table SPEED_ALERTS;
drop table MOVEMENT_ALERTS;
drop table DEVICES;
drop table ACCESS_TOKENS;
drop table USERS;

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
	device_id bigint references DEVICES(id) on delete cascade unique
);
ALTER TABLE public.speed_alerts ALTER COLUMN id TYPE int8 USING id::int8;


create table MOVEMENT_ALERTS(
	id serial primary key not null, 
	active boolean not null,
	lat real,
	lng real,
	device_id bigint references DEVICES(id) on delete cascade unique
);
ALTER TABLE public.movement_alerts ALTER COLUMN id TYPE int8 USING id::int8;


create table SPEED_ALERTS_HISTORY(
	time timestamp primary key not null, 
	alert_id bigint references SPEED_ALERTS(id) on delete cascade,
	speed real
);


create table MOVEMENT_ALERTS_HISTORY(
	time timestamp primary key not null, 
	alert_id bigint references SPEED_ALERTS(id) on delete cascade,
	lat real,
	lng real
);
