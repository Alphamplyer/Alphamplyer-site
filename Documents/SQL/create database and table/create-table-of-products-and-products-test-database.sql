
create table product_types
(
	id serial not null,
	parent_id int,
	code varchar(255) not null,
	name varchar(255) not null,
	description varchar(500)
);

create unique index product_types_id_uindex
	or product_types (id);
	
alter table product_types
	add constraint product_types_pk
		primary key (id);

alter table product_types
	add constraint product_types_product_types_id_fk
		foreign key (parent_id) references product_types
			on delete cascade;
			
			
			

create table products
(
	id serial not null,
	type_id int,
	image_id int not null,
	unit_by_user int default -1 not null,
	price decimal not null,
	code varchar(255) not null,
	name varchar(255) not null,
	description varchar(1000),
	status varchar(255) default 'AVAILABLE' not null,
	game_content boolean default false not null,
	renewal boolean default false not null,
	available_from timestamp default current_timestamp not null,
	available_to timestamp
);

create unique index products_id_uindex
	on products (id);
	
create unique index products_code_uindex
	on products (code);

alter table products
	add constraint products_pk
		primary key (id);
		
alter table products
	add constraint products_product_types_id_fk
		foreign key (type_id) references product_types
			on delete set null;




create table reductions
(
	id serial not null,
	amount decimal not null,
	activation_code varchar(255),
	begin_date_time timestamp default current_timestamp not null,
	end_date_time timestamp not null
);

create unique index reductions_id_uindex
	on reductions (id);

alter table reductions
	add constraint reductions_pk
		primary key (id);
		
		
		

create table product_reduction
(
	id serial not null,
	product_id int not null
		constraint product_reduction_products_id_fk
			references products
				on delete cascade,
	reduction_id int not null
		constraint product_reduction_reductions_id_fk
			references reductions
				on delete cascade
);

create unique index product_reduction_id_uindex
	on product_reduction (id);

alter table product_reduction
	add constraint product_reduction_pk
		primary key (id);
		
		
		

create table product_type_reduction
(
	id serial not null,
	type_id int not null
		constraint product_type_reduction_product_types_id_fk
			references product_types
				on delete cascade,
	reduction_id int not null
		constraint product_type_reduction_reductions_id_fk
			references reductions
				on delete cascade
);

create unique index product_type_reduction_id_uindex
	on product_type_reduction (id);

alter table product_type_reduction
	add constraint product_type_reduction_pk
		primary key (id);