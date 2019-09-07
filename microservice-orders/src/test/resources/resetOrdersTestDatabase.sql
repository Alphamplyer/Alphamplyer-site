TRUNCATE order_lines RESTART IDENTITY CASCADE;
TRUNCATE orders RESTART IDENTITY CASCADE;

INSERT INTO public.orders (user_id, creator_id, payment_id, status, created_at, updated_at) VALUES (1, 1, 1, 'PENDING', '2019-09-07 17:52:35.820987', '2019-09-07 17:52:35.820987');
INSERT INTO public.orders (user_id, creator_id, payment_id, status, created_at, updated_at) VALUES (1, 1, 2, 'PENDING', '2019-09-07 17:52:35.820987', '2019-09-07 17:52:35.820987');
INSERT INTO public.orders (user_id, creator_id, payment_id, status, created_at, updated_at) VALUES (2, 2, 3, 'PENDING', '2019-09-07 17:52:35.820987', '2019-09-07 17:52:35.820987');
INSERT INTO public.orders (user_id, creator_id, payment_id, status, created_at, updated_at) VALUES (3, 3, 4, 'PENDING', '2019-09-07 17:52:35.820987', '2019-09-07 17:52:35.820987');

INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (1, 1, 2, 4, 1, 'IN_PREPARATION', 'NONE');
INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (1, 2, 1, 5, 1, 'IN_PREPARATION', 'NONE');
INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (1, 3, 1, 2.5, 1, 'IN_PREPARATION', 'NONE');
INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (2, 1, 3, 4, 1, 'IN_PREPARATION', 'NONE');
INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (2, 2, 2, 5, 1, 'IN_PREPARATION', 'NONE');
INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (3, 2, 1, 5, 1, 'IN_PREPARATION', 'NONE');
INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (3, 3, 1, 2.5, 1, 'IN_PREPARATION', 'NONE');
INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (3, 4, 2, 7.99, 1, 'IN_PREPARATION', 'NONE');
INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (4, 1, 1, 4, 1, 'IN_PREPARATION', 'NONE');
INSERT INTO public.order_lines (order_id, product_id, quantity, price, reduction_amount, status, renewal_rate) VALUES (4, 3, 1, 2.5, 1, 'IN_PREPARATION', 'NONE');