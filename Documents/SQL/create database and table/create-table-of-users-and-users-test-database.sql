create table users
(
	id serial not null,
	role_id int default 0 not null,
	username varchar(50) not null,
	email varchar(254) not null,
	password varchar(255) not null,
	salt varchar(128) not null,
	birth_date date not null,
	term_accepted boolean default false not null,
	email_validated boolean default false not null,
	created_at timestamp default current_timestamp not null,
	updated_at timestamp default current_timestamp not null
);

create unique index users_email_uindex
	on users (email);

create unique index users_id_uindex
	on users (id);

create unique index users_username_uindex
	on users (username);

alter table users
	add constraint users_pk
		primary key (id);




create table verification_tokens
(
	id serial not null,
	token varchar(255) not null,
	user_id int not null
		constraint verification_tokens_users_id_fk
			references users
				on delete cascade,
	expiry_date_time timestamp not null
);

create unique index verification_tokens_id_uindex
	on verification_tokens (id);

alter table verification_tokens
	add constraint verification_tokens_pk
		primary key (id);