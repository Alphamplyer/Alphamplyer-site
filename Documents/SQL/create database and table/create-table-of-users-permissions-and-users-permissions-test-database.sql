create table roles
(
	id serial not null,
	index serial not null,
	name varchar(255) not null
);

create unique index roles_id_uindex
	on roles (id);

alter table roles
	add constraint roles_pk
		primary key (id);

