create table news_categories
(
	id serial not null,
	parent_id int,
	creator_id int not null,
	name varchar(255) not null,
	description varchar(500),
	created_at timestamp default current_timestamp not null,
	updated_at timestamp default current_timestamp not null
);

create unique index news_categories_id_uindex
	on news_categories (id);

alter table news_categories
	add constraint news_categories_pk
		primary key (id);
		
alter table news_categories
	add constraint news_categories_news_categories_id_fk
		foreign key (parent_id) references news_categories
			on delete cascade;
		

create table news
(
	id bigserial not null,
	category_id int not null,
	main_image_id int not null,
	title varchar(500) not null,
	content text not null,
	description varchar(1000),
	publication_time timestamp default current_timestamp not null,
	created_at timestamp default current_timestamp not null,
	updated_at timestamp default current_timestamp not null
);

create unique index news_id_uindex
	on news (id);

alter table news
	add constraint news_pk
		primary key (id);

alter table news
	add constraint news_news_categories_id_fk
		foreign key (category_id) references news_categories
			on delete cascade;

		
create table news_authors
(
	id bigserial not null,
	news_id bigint not null,
	author_id int not null
);

create unique index news_authors_id_uindex
	on news_authors (id);

alter table news_authors
	add constraint news_authors_pk
		primary key (id);
		
alter table news_authors
	add constraint news_authors_news_id_fk
		foreign key (news_id) references news
			on delete cascade;