TRUNCATE  roles RESTART IDENTITY CASCADE;

INSERT INTO public.roles (index, name) VALUES (-1, 'Super Administrator');
INSERT INTO public.roles (index, name) VALUES (0, 'Member');
INSERT INTO public.roles (index, name) VALUES (1, 'Subscriber');
INSERT INTO public.roles (index, name) VALUES (2, 'Administrator');
INSERT INTO public.roles (index, name) VALUES (3, 'Moderator');