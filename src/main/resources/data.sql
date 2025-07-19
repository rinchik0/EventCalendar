DELETE FROM tasks;
DELETE FROM events;
DELETE FROM users;
ALTER SEQUENCE users_id_seq RESTART WITH 1;
ALTER SEQUENCE events_id_seq RESTART WITH 1;
ALTER SEQUENCE tasks_id_seq RESTART WITH 1;
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$vKvXkGLX85SeZLZ7U5E6guFqsU6Y1IYUQFyOEeGWgyBqr/2ZQ9K4O', 'ADMIN');
INSERT INTO events (title, description, start_time, end_time, organizer_id)
VALUES ('Создание приложения', 'Выдано задание по университетской летней практике. Разработка приложения',
        '2025-07-10 18:00:00', '2025-07-19 20:00:00', 1),
    ('Предпоказ приложений', 'Предварительный отчет по проделанной работе.', '2025-07-15 18:15:00', '2025-07-15 20:00:00', 1),
    ('Финальный показ', 'Демонстрация программы и предоставления отчета.', '2025-07-19 13:00:00', '2025-07-19 15:00:00', 1);
INSERT INTO tasks (status, title, assignee_id, event_id)
VALUES ('NEW', 'Написать программу', 1, 1),
    ('NEW', 'Сделать отчет', 1, 1),
    ('NEW', 'Протестировать программу', 1, 1),
    ('IN_PROGRESS', 'Показать программу', 1, 2),
    ('DONE', 'Продемонстрировать готовое приложение', 1, 3),
    ('DONE', 'Продемонстрировать отчет', 1, 3);