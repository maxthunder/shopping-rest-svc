create schema shopping collate utf8_general_ci;

create table customer
(
	customer_id int auto_increment
		primary key,
	customer_name varchar(255) not null,
	constraint customer_customer_name_uindex
		unique (customer_name)
);

create table hibernate_sequence
(
	next_val bigint null
);

create table shirt_ref (
	shirt_ref_id int auto_increment
		primary key,
	shirt_name varchar(255) not null,
	size varchar(15) null,
	style varchar(50) null,
	constraint shirt_ref_shirt_ref_name_uindex
		unique (shirt_name)
);

create table shirt_inventory (
	shirt_inventory_id int auto_increment
		primary key,
	shirt_ref_id int not null,
	quanity int default 0 not null,
	constraint shirt_inventory_shirt_ref_id_uindex
		unique (shirt_ref_id),
	constraint shirt_inventory_shirt_ref_shirt_ref_id_fk
		foreign key (shirt_ref_id) references shirt_ref (shirt_ref_id)
);

create table shirt_order (
	shirt_order_id int auto_increment
		primary key,
	customer_id int null,
	shirt_ref_id int not null,
	constraint shirt_order_customer_customer_id_fk
		foreign key (customer_id) references customer (customer_id),
	constraint shirt_order_shirt_ref_shirt_ref_id_fk
		foreign key (shirt_ref_id) references shirt_ref (shirt_ref_id)
);

create table status_ref (
	status_ref_id int auto_increment
		primary key,
	status varchar(25) not null,
	description varchar(50) null,
	constraint status_ref_status_uindex
		unique (status)
);

create table phone_cart_order(
	phone_cart_order_id int auto_increment primary key,
	address varchar(75) not null,
	customer_id int not null,
	constraint phone_cart_order_customer_customer_id_fk
		foreign key (customer_id) references customer (customer_id)
);

create table phone_ref(
	phone_ref_id int auto_increment primary key,
	name varchar(25) not null,
	price float(8,2) not null,
	description varchar(50) null
);

create table phone_item_map_ref(
	phone_item_map_ref_id int auto_increment primary key,
	phone_cart_order_id int not null,
	phone_ref_id int not null,
	constraint phone_item_map_ref_phone_ref_phone_ref_id_fk
		foreign key (phone_ref_id) references phone_ref (phone_ref_id),
	constraint phone_item_map_ref_phone_cart_order_card_order_id_fk
		foreign key (phone_cart_order_id) references phone_cart_order (phone_cart_order_id)
);
