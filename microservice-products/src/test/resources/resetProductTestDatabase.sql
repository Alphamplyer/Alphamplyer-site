TRUNCATE product_reduction RESTART IDENTITY CASCADE;
TRUNCATE product_type_reduction RESTART IDENTITY CASCADE;
TRUNCATE products RESTART IDENTITY CASCADE;
TRUNCATE reductions RESTART IDENTITY CASCADE;
TRUNCATE product_types RESTART IDENTITY CASCADE;


INSERT INTO public.product_types (parent_id, code, name, description) VALUES (null, 'TEST', 'Test', 'Test Products');
INSERT INTO public.product_types (parent_id, code, name, description) VALUES (null, 'GAME', 'Game', 'Game Products');
INSERT INTO public.product_types (parent_id, code, name, description) VALUES (1, 'GAME-TEST', 'Game Test', 'Game Test Products');
INSERT INTO public.product_types (parent_id, code, name, description) VALUES (2, 'GAME-SUB', 'Subscription', 'Game Subscription ');

INSERT INTO public.reductions (amount, activation_code, begin_date_time, end_date_time) VALUES (0.5, 'DEUXPOURUN', '2019-09-05 12:00:00.000000', '2019-09-15 12:00:00.000000');
INSERT INTO public.reductions (amount, activation_code, begin_date_time, end_date_time) VALUES (0.8, 'UNPEUMOINS', '2019-09-05 12:00:00.000000', '2019-09-15 12:00:00.000000');
INSERT INTO public.reductions (amount, activation_code, begin_date_time, end_date_time) VALUES (0.3, 'CLAIREMENTMOINS', '2019-09-05 12:00:00.000000', '2019-09-15 12:00:00.000000');
INSERT INTO public.reductions (amount, activation_code, begin_date_time, end_date_time) VALUES (0.7, 'CESTMOINSQUEPLUS', '2019-09-05 12:00:00.000000', '2019-09-15 12:00:00.000000');

INSERT INTO public.products (type_id, image_id, unit_by_user, price, code, name, description, status, game_content, renewal, available_from, available_to) VALUES (1, 1, -1, 9.99, 'TEST-00001', 'Test Product 1', '', 'AVAILABLE', false, false, '2019-09-07 22:53:34.292490', null);
INSERT INTO public.products (type_id, image_id, unit_by_user, price, code, name, description, status, game_content, renewal, available_from, available_to) VALUES (1, 1, -1, 39.5, 'TEST-00002', 'Test Product 2', '', 'AVAILABLE', false, false, '2019-09-07 22:54:40.807873', null);
INSERT INTO public.products (type_id, image_id, unit_by_user, price, code, name, description, status, game_content, renewal, available_from, available_to) VALUES (2, 1, -1, 25, 'GAME-00001', 'Game Product 1', '', 'AVAILABLE', false, false, '2019-09-07 22:56:32.681648', null);
INSERT INTO public.products (type_id, image_id, unit_by_user, price, code, name, description, status, game_content, renewal, available_from, available_to) VALUES (2, 1, -1, 7.5, 'GAME-00002', 'Game Product 2', '', 'AVAILABLE', false, false, '2019-09-07 23:06:39.981795', null);
INSERT INTO public.products (type_id, image_id, unit_by_user, price, code, name, description, status, game_content, renewal, available_from, available_to) VALUES (3, 1, -1, 10, 'GAME-TEST-00001', 'Game Test Product 1', '', 'AVAILABLE', false, false, '2019-09-07 23:06:39.981795', null);
INSERT INTO public.products (type_id, image_id, unit_by_user, price, code, name, description, status, game_content, renewal, available_from, available_to) VALUES (3, 1, -1, 12.5, 'GAME-TEST-00002', 'Game Test Product 2', '', 'AVAILABLE', false, false, '2019-09-07 23:06:39.981795', null);
INSERT INTO public.products (type_id, image_id, unit_by_user, price, code, name, description, status, game_content, renewal, available_from, available_to) VALUES (4, 1, -1, 15, 'GAME-SUB-00001', 'Game Subscription Product 1', '', 'AVAILABLE', false, true, '2019-09-07 23:06:39.981795', null);
INSERT INTO public.products (type_id, image_id, unit_by_user, price, code, name, description, status, game_content, renewal, available_from, available_to) VALUES (4, 1, -1, 5, 'GAME-SUB-00002', 'Game Subscription Product 2', '', 'AVAILABLE', false, true, '2019-09-07 23:06:39.981795', null);

INSERT INTO public.product_type_reduction (type_id, reduction_id) VALUES (1, 1);
INSERT INTO public.product_type_reduction (type_id, reduction_id) VALUES (2, 2);
INSERT INTO public.product_type_reduction (type_id, reduction_id) VALUES (2, 4);
INSERT INTO public.product_type_reduction (type_id, reduction_id) VALUES (3, 3);

INSERT INTO public.product_reduction (product_id, reduction_id) VALUES (1, 1);
INSERT INTO public.product_reduction (product_id, reduction_id) VALUES (1, 2);
INSERT INTO public.product_reduction (product_id, reduction_id) VALUES (2, 3);
INSERT INTO public.product_reduction (product_id, reduction_id) VALUES (3, 4);
INSERT INTO public.product_reduction (product_id, reduction_id) VALUES (3, 2);
INSERT INTO public.product_reduction (product_id, reduction_id) VALUES (4, 3);