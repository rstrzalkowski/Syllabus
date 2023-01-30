INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, school_class_id) VALUES ('2023-01-30 16:26:39.580000', '2023-01-30 16:26:39.581000', 0, false, 'Rafal', 'Strzalkowski', false, '$2a$10$SqtH2pSy7S0/Qyq6EC.oK.rII19t9TcRS.oJW2vjDeyI.H2AoouJ.', '1', 'student', null);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, school_class_id) VALUES ('2023-01-30 16:27:09.795000', '2023-01-30 16:27:09.795000', 0, false, 'Bogumiła', 'Saganiak', false, '$2a$10$EXs63QvasFklVxgy2P3.Zu7mZNZoRneS95d6zNTUnziYrczMgGFPm', '2', 'teacher', null);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, school_class_id) VALUES ('2023-01-30 16:27:47.577000', '2023-01-30 16:27:47.577000', 0, false, 'Katarzyna', 'Jakubiak', false, '$2a$10$HPhLYhupMvNMSd189B/EMudYxDXLAr57X2eiCmVuG0CLfkv8ErZDy', '24', 'office', null);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, school_class_id) VALUES ('2023-01-30 16:28:10.452000', '2023-01-30 16:28:10.452000', 0, false, 'Andrzej', 'Andrzejowski', false, '$2a$10$ovVYXGN389ENeAeNiSq0p.PhtZtmRxY2KB9X0jyOjjCUoy6PJYcY.', '11443', 'director', null);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, school_class_id) VALUES ('2023-01-30 16:28:34.633000', '2023-01-30 16:28:34.633000', 0, false, 'Zbigniew', 'Strzałkowski', false, '$2a$10$amlwu75Z8g6TxHo6DDqZSuwNC1TLfu1nHdjaiu8jaw0VuvGMEWylm', '244', 'parent', null);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, school_class_id) VALUES ('2023-01-30 16:28:34.633000', '2023-01-30 16:28:34.633000', 0, false, 'Admin', 'Admin', false, '$2a$10$7N1o4nfnGBFoQcCqwVBgT.IlkTvqIJ7SFUkOGxuzx3.Q5CD8AAKci', '2442', 'admin', null);

INSERT INTO public.app_user_role (user_id, role) VALUES (1, 'STUDENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (2, 'TEACHER');
INSERT INTO public.app_user_role (user_id, role) VALUES (3, 'OFFICE');
INSERT INTO public.app_user_role (user_id, role) VALUES (4, 'DIRECTOR');
INSERT INTO public.app_user_role (user_id, role) VALUES (4, 'OFFICE');
INSERT INTO public.app_user_role (user_id, role) VALUES (4, 'TEACHER');
INSERT INTO public.app_user_role (user_id, role) VALUES (5, 'PARENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (6, 'ADMIN');

