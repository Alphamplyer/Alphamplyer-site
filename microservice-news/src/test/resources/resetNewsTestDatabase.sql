TRUNCATE TABLE news_authors RESTART IDENTITY CASCADE;
TRUNCATE TABLE news RESTART IDENTITY CASCADE;
truncate table news_categories RESTART IDENTITY CASCADE;

INSERT INTO public.news_categories (parent_id, creator_id, name, description, created_at, updated_at) VALUES (null, 1, 'Info', null, '2019-09-07 12:51:15.034370', '2019-09-07 12:51:15.034370');
INSERT INTO public.news_categories (parent_id, creator_id, name, description, created_at, updated_at) VALUES (null, 1, 'MAJ', null, '2019-09-07 12:51:15.034370', '2019-09-07 12:51:15.034370');
INSERT INTO public.news_categories (parent_id, creator_id, name, description, created_at, updated_at) VALUES (1, 1, 'Shop', null, '2019-09-07 12:51:31.176966', '2019-09-07 12:51:31.176966');
INSERT INTO public.news_categories (parent_id, creator_id, name, description, created_at, updated_at) VALUES (1, 1, 'Game', null, '2019-09-07 12:52:04.449181', '2019-09-07 12:52:04.449181');
INSERT INTO public.news_categories (parent_id, creator_id, name, description, created_at, updated_at) VALUES (2, 1, 'Game Update', null, '2019-09-07 12:52:17.447408', '2019-09-07 12:52:17.447408');
INSERT INTO public.news_categories (parent_id, creator_id, name, description, created_at, updated_at) VALUES (3, 1, 'New Product', null, '2019-09-07 12:52:59.976495', '2019-09-07 12:52:59.976495');

INSERT INTO public.news (category_id, main_image_id, title, content, description, publication_time, created_at, updated_at) VALUES (1, 1, 'Version 1', 'Prepare yourselves ! The first version of the site will arrive', null, '2019-09-07 12:54:52.092514', '2019-09-07 12:54:52.092514', '2019-09-07 12:54:52.092514');
INSERT INTO public.news (category_id, main_image_id, title, content, description, publication_time, created_at, updated_at) VALUES (4, 1, 'New game in production !', 'We started production of a new game! We will give you the details in the week, and we are impervious to share this event with you.', 'Get ready !', '2019-09-07 12:58:47.953871', '2019-09-07 12:58:47.953871', '2019-09-07 12:58:47.953871');
INSERT INTO public.news (category_id, main_image_id, title, content, description, publication_time, created_at, updated_at) VALUES (2, 1, 'Website Update', 'We have finally finished a first version of the login and registration system.', 'Become a member right now and support us !', '2019-09-07 13:01:03.767761', '2019-09-07 13:01:03.767761', '2019-09-07 13:01:03.767761');
INSERT INTO public.news (category_id, main_image_id, title, content, description, publication_time, created_at, updated_at) VALUES (3, 1, 'The first products', 'We have added products to our shop for display testing and show you the result. This will also allow testing on the traffic.', 'Take a look at new (false) products', '2019-09-07 13:04:45.033501', '2019-09-07 13:04:45.033501', '2019-09-07 13:04:45.033501');
INSERT INTO public.news (category_id, main_image_id, title, content, description, publication_time, created_at, updated_at) VALUES (5, 1, 'Game Dev Log #1', 'We started production this week, so it will be a survival game. But what else can he bring? You will discover it in the next Dev Log in video explained by our lead programmer.', 'As promised here are some words about our game.', '2019-09-07 13:07:58.100773', '2019-09-07 13:07:58.100773', '2019-09-07 13:07:58.100773');
INSERT INTO public.news (category_id, main_image_id, title, content, description, publication_time, created_at, updated_at) VALUES (6, 1, 'New Products', 'We have added products to our shop. Take a look to our shop ! ', 'Get ready !', '2019-09-07 13:09:39.814581', '2019-09-07 13:09:39.814581', '2019-09-07 13:09:39.814581');

INSERT INTO public.news_authors (news_id, author_id) VALUES (1, 1);
INSERT INTO public.news_authors (news_id, author_id) VALUES (2, 1);
INSERT INTO public.news_authors (news_id, author_id) VALUES (3, 1);
INSERT INTO public.news_authors (news_id, author_id) VALUES (4, 1);
INSERT INTO public.news_authors (news_id, author_id) VALUES (5, 1);
INSERT INTO public.news_authors (news_id, author_id) VALUES (6, 1);
INSERT INTO public.news_authors (news_id, author_id) VALUES (1, 2);
INSERT INTO public.news_authors (news_id, author_id) VALUES (1, 3);
INSERT INTO public.news_authors (news_id, author_id) VALUES (2, 2);
INSERT INTO public.news_authors (news_id, author_id) VALUES (3, 3);
INSERT INTO public.news_authors (news_id, author_id) VALUES (3, 4);
INSERT INTO public.news_authors (news_id, author_id) VALUES (4, 2);
INSERT INTO public.news_authors (news_id, author_id) VALUES (6, 4);
INSERT INTO public.news_authors (news_id, author_id) VALUES (6, 2);