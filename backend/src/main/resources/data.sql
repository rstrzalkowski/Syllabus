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


INSERT INTO public.level (created_at, updated_at, version, archived, level) VALUES ('2023-01-30 21:06:09.929000', '2023-01-30 21:06:09.929000', 0, false, 1);
INSERT INTO public.level (created_at, updated_at, version, archived, level) VALUES ('2023-01-30 21:06:13.104000', '2023-01-30 21:06:13.104000', 0, false, 2);
INSERT INTO public.level (created_at, updated_at, version, archived, level) VALUES ('2023-01-30 21:06:14.974000', '2023-01-30 21:06:14.974000', 0, false, 3);
INSERT INTO public.level (created_at, updated_at, version, archived, level) VALUES ('2023-01-30 21:06:16.389000', '2023-01-30 21:06:16.389000', 0, false, 4);
INSERT INTO public.level (created_at, updated_at, version, archived, level) VALUES ('2023-01-30 21:06:17.888000', '2023-01-30 21:06:17.888000', 0, false, 5);
INSERT INTO public.level (created_at, updated_at, version, archived, level) VALUES ('2023-01-30 21:06:22.225000', '2023-01-30 21:06:22.225000', 0, false, 6);
INSERT INTO public.level (created_at, updated_at, version, archived, level) VALUES ('2023-01-30 21:06:24.641000', '2023-01-30 21:06:24.641000', 0, false, 7);
INSERT INTO public.level (created_at, updated_at, version, archived, level) VALUES ('2023-01-30 21:06:26.530000', '2023-01-30 21:06:26.530000', 0, false, 8);


INSERT INTO public.subject (created_at, updated_at, version, abbreviation, archived, name) VALUES ('2023-01-30 21:09:30.282000', '2023-01-30 21:09:30.282000', 0, 'MATH', false, 'Mathematics');
INSERT INTO public.subject (created_at, updated_at, version, abbreviation, archived, name) VALUES ('2023-01-30 21:09:48.860000', '2023-01-30 21:09:48.860000', 0, 'EN', false, 'English');
INSERT INTO public.subject (created_at, updated_at, version, abbreviation, archived, name) VALUES ('2023-01-30 21:10:15.045000', '2023-01-30 21:10:15.045000', 0, 'HIS', false, 'History');
INSERT INTO public.subject (created_at, updated_at, version, abbreviation, archived, name) VALUES ('2023-01-30 21:10:24.809000', '2023-01-30 21:10:24.809000', 0, 'BIO', false, 'Biology');
INSERT INTO public.subject (created_at, updated_at, version, abbreviation, archived, name) VALUES ('2023-01-30 21:10:50.751000', '2023-01-30 21:10:50.751000', 0, 'PHY', false, 'Physics');
INSERT INTO public.subject (created_at, updated_at, version, abbreviation, archived, name) VALUES ('2023-01-30 21:11:25.809000', '2023-01-30 21:11:25.809000', 0, 'PE', false, 'Physical education');


INSERT INTO public.school_class (created_at, updated_at, version, archived, level_id, supervising_teacher_id) VALUES ('2023-01-30 21:17:50.795000', '2023-01-30 21:17:50.795000', 0, false, 1, 2);
INSERT INTO public.school_class (created_at, updated_at, version, archived, level_id, supervising_teacher_id) VALUES ('2023-01-30 21:17:57.326000', '2023-01-30 21:17:57.326000', 0, false, 2, 4);
INSERT INTO public.school_class (created_at, updated_at, version, archived, level_id, supervising_teacher_id) VALUES ('2023-01-30 21:18:03.808000', '2023-01-30 21:18:03.808000', 0, false, 4, 2);


INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, child_id, school_class_id) VALUES ('2023-01-30 21:21:12.968000', '2023-01-30 21:21:12.968000', 0, false, 'Wojtek', 'Szczesny', false, '$2a$10$qkuccCpR/eZwGcwiZ163POYxM9SP.RQqIUpmiMJvakZrqHxq0aGXe', '56', 'firstStudent', null, 1);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, child_id, school_class_id) VALUES ('2023-01-30 21:22:49.476000', '2023-01-30 21:22:49.476000', 0, false, 'Rafal', 'Strzalkowski', false, '$2a$10$emsNMQBv4P6M/AlffJJWJu/ER61MWgeu0Fi4QbngPeeYJforLZ7r6', '536', 'secondStudent', null, 1);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, child_id, school_class_id) VALUES ('2023-01-30 21:23:11.929000', '2023-01-30 21:23:11.929000', 0, false, 'Wioletta', 'Graczyk', false, '$2a$10$jWuYQQyJehk2Rz4iBDhanOBNR6Ty7c94QugkJ0BdxVeylR9u5LkFq', '5326', 'thirdStudent', null, 1);

INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, child_id, school_class_id) VALUES ('2023-01-30 21:21:12.968000', '2023-01-30 21:21:12.968000', 0, false, 'Aleksandra', 'Tomczyk', false, '$2a$10$qkuccCpR/eZwGcwiZ163POYxM9SP.RQqIUpmiMJvakZrqHxq0aGXe', '5424', 'fourthStudent', null, 2);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, child_id, school_class_id) VALUES ('2023-01-30 21:22:49.476000', '2023-01-30 21:22:49.476000', 0, false, 'Michał', 'Sochacki', false, '$2a$10$emsNMQBv4P6M/AlffJJWJu/ER61MWgeu0Fi4QbngPeeYJforLZ7r6', '5354236', 'fifthStudent', null, 2);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, child_id, school_class_id) VALUES ('2023-01-30 21:23:11.929000', '2023-01-30 21:23:11.929000', 0, false, 'Mateusz', 'Cegielski', false, '$2a$10$jWuYQQyJehk2Rz4iBDhanOBNR6Ty7c94QugkJ0BdxVeylR9u5LkFq', '5354126', 'sixthStudent', null, 2);

INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, child_id, school_class_id) VALUES ('2023-01-30 21:21:12.968000', '2023-01-30 21:21:12.968000', 0, false, 'Robert', 'Szczesny', false, '$2a$10$qkuccCpR/eZwGcwiZ163POYxM9SP.RQqIUpmiMJvakZrqHxq0aGXe', '1356', 'seventhStudent', null, 3);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, child_id, school_class_id) VALUES ('2023-01-30 21:22:49.476000', '2023-01-30 21:22:49.476000', 0, false, 'Krzysztof', 'Rubski', false, '$2a$10$emsNMQBv4P6M/AlffJJWJu/ER61MWgeu0Fi4QbngPeeYJforLZ7r6', '24536', 'eighthStudent', null, 3);
INSERT INTO public.app_user (created_at, updated_at, version, archived, first_name, last_name, locked, password, personal_id, username, child_id, school_class_id) VALUES ('2023-01-30 21:23:11.929000', '2023-01-30 21:23:11.929000', 0, false, 'Julia', 'Honda', false, '$2a$10$jWuYQQyJehk2Rz4iBDhanOBNR6Ty7c94QugkJ0BdxVeylR9u5LkFq', '5325536', 'ninthStudent', null, 3);


INSERT INTO public.app_user_role (user_id, role) VALUES (7, 'STUDENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (8, 'STUDENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (9, 'STUDENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (10, 'STUDENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (11, 'STUDENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (12, 'STUDENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (13, 'STUDENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (14, 'STUDENT');
INSERT INTO public.app_user_role (user_id, role) VALUES (15, 'STUDENT');


INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:08.863000', '2023-01-30 21:34:08.863000', 0, false, 2023, 1, 1, 2);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:21.354000', '2023-01-30 21:34:21.354000', 0, false, 2023, 1, 2, 4);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:25.265000', '2023-01-30 21:34:25.265000', 0, false, 2023, 1, 3, 2);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:28.502000', '2023-01-30 21:34:28.502000', 0, false, 2023, 1, 4, 2);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:35.460000', '2023-01-30 21:34:35.460000', 0, false, 2023, 2, 1, 4);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:37.414000', '2023-01-30 21:34:37.414000', 0, false, 2023, 2, 2, 4);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:41.747000', '2023-01-30 21:34:41.747000', 0, false, 2023, 2, 5, 2);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:49.097000', '2023-01-30 21:34:49.097000', 0, false, 2023, 3, 6, 2);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:55.043000', '2023-01-30 21:34:55.043000', 0, false, 2023, 3, 2, 2);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:34:58.539000', '2023-01-30 21:34:58.539000', 0, false, 2023, 3, 3, 4);
INSERT INTO public.realisation (created_at, updated_at, version, archived, year, school_class_id, subject_id, teacher_id) VALUES ('2023-01-30 21:35:02.417000', '2023-01-30 21:35:02.417000', 0, false, 2023, 3, 4, 2);


INSERT INTO public.activity (created_at, updated_at, version, archived, description, edited, name, weight, realisation_id, teacher_id) VALUES ('2023-01-30 21:40:06.550000', '2023-01-30 21:40:06.550000', 0, false, 'Small test of integrals', false, 'Small test', 1, 1, 2);
INSERT INTO public.activity (created_at, updated_at, version, archived, description, edited, name, weight, realisation_id, teacher_id) VALUES ('2023-01-30 21:41:13.121000', '2023-01-30 21:41:13.121000', 0, false, 'Test of conditionals', false, 'Test conditionals', 3, 2, 4);
INSERT INTO public.activity (created_at, updated_at, version, archived, description, edited, name, weight, realisation_id, teacher_id) VALUES ('2023-01-30 21:42:11.415000', '2023-01-30 21:42:11.415000', 0, false, 'Test of geometry', false, 'Test geometry', 4, 5, 4);
INSERT INTO public.activity (created_at, updated_at, version, archived, description, edited, name, weight, realisation_id, teacher_id) VALUES ('2023-01-30 21:43:08.106000', '2023-01-30 21:43:08.106000', 0, false, 'Test of tennis', false, 'Test tennis', 2, 8, 2);


INSERT INTO public.post (created_at, updated_at, version, archived, content, edited, realisation_id, teacher_id) VALUES ('2023-01-30 21:48:28.454000', '2023-01-30 21:48:28.454000', 0, false, 'Hello students! Prepare for incoming test of integrals! Weight of the test is only 1', false, 1, 2);
INSERT INTO public.post (created_at, updated_at, version, archived, content, edited, realisation_id, teacher_id) VALUES ('2023-01-30 21:49:07.170000', '2023-01-30 21:49:07.170000', 0, false, 'Prepare for conditionals test. Weight is 3!', false, 2, 4);
INSERT INTO public.post (created_at, updated_at, version, archived, content, edited, realisation_id, teacher_id) VALUES ('2023-01-30 21:49:10.065000', '2023-01-30 21:49:10.065000', 0, false, 'There are no incoming tests, but keep on studying WW1', false, 3, 2);


INSERT INTO public.grade (created_at, updated_at, version, archived, edited, value, activity_id, student_id, teacher_id) VALUES ('2023-01-30 21:55:13.423000', '2023-01-30 21:55:13.423000', 0, false, false, 5, 1, 1, 2);
INSERT INTO public.grade (created_at, updated_at, version, archived, edited, value, activity_id, student_id, teacher_id) VALUES ('2023-01-30 21:57:21.342000', '2023-01-30 21:57:21.342000', 0, false, false, 3, 1, 2, 2);
INSERT INTO public.grade (created_at, updated_at, version, archived, edited, value, activity_id, student_id, teacher_id) VALUES ('2023-01-30 21:57:26.678000', '2023-01-30 21:57:26.678000', 0, false, false, 5, 1, 3, 2);
