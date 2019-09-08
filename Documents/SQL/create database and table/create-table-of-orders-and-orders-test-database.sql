create table orders
(
	id serial not null,
	user_id int not null,
	creator_id int not null,
	payment_id int not null,
	status varchar(255) default 'PENDING' not null,
	created_at timestamp default current_timestamp not null,
	updated_at timestamp default current_timestamp not null
);

create unique index orders_id_uindex
	on orders (id);

alter table orders
	add constraint orders_pk
		primary key (id);


create table order_lines
(
	id serial not null,
	order_id int not null,
	product_id int not null,
	quantity int not null,
	price decimal not null,
	reduction_amount decimal default 1.0 not null,
	status varchar(255) default 'IN_PREPARATION' not null,
	renewal_rate varchar(255) default 'NONE' not null
);

create unique index order_lines_id_uindex
	on order_lines (id);

alter table order_lines
	add constraint order_lines_pk
		primary key (id);

alter table order_lines
	add constraint order_lines_orders_id_fk
		foreign key (order_id) references orders
			on delete cascade;