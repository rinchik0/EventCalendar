INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqL3sPQ3lQkjWq9p2l.PdtZ5rV.CG', 'ADMIN')
ON CONFLICT (username) DO NOTHING;