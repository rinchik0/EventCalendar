DELETE FROM tasks;
DELETE FROM events;
DELETE FROM users;
ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE events_id_seq RESTART WITH 1;
ALTER SEQUENCE tasks_id_seq RESTART WITH 1;
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$vKvXkGLX85SeZLZ7U5E6guFqsU6Y1IYUQFyOEeGWgyBqr/2ZQ9K4O', 'ADMIN'),
    ('admin2', '$2a$10$wgMl00GB/AHpMSVU05Y0YuuRvxSGB1cYkPzdx6pGVzrgtGkoHc0hm', 'ADMIN'),
    ('user2', '$2a$10$jTfh6qu4s6XPqxITiweLs.VeyyOEQkjSY9AlcuMdhz0v.yneKJM9i', 'USER'),
    ('user', '$2a$10$evDf/hdA5Lmgs3n9qfzKZu8FAQvMmxp4nFx7INWRavYrCSNdbJqWi', 'USER');
INSERT INTO events (title, description, start_time, end_time, organizer_id)
VALUES ('Создание приложения', 'Выдано задание по университетской летней практике. Разработка приложения',
        '2025-07-10 18:00:00', '2025-07-19 20:00:00', 1),
    ('Предпоказ приложений', 'Предварительный отчет по проделанной работе.', '2025-07-15 18:15:00', '2025-07-15 20:00:00', 1),
    ('Финальный показ', 'Демонстрация программы и предоставления отчета.', '2025-07-19 13:00:00', '2025-07-19 15:00:00', 2);
INSERT INTO tasks (status, title, assignee_id, event_id)
VALUES ('NEW', 'Написать программу', 3, 1),
    ('NEW', 'Сделать отчет', 4, 1),
    ('NEW', 'Протестировать программу', 1, 1),
    ('IN_PROGRESS', 'Показать программу', 3, 2),
    ('DONE', 'Продемонстрировать готовое приложение', 3, 3),
    ('DONE', 'Продемонстрировать отчет', 4, 3);