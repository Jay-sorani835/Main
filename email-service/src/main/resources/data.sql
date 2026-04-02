INSERT INTO users (email, password, role) VALUES ('user@example.com', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HCGzExGexX.8Ww4x46uO6', 'ROLE_USER');

-- Default SMTP and IMAP configs. The user should replace these in H2 console or the application will throw connection errors.
INSERT INTO smtp_configuration (host, port, username, password, protocol, active) VALUES ('smtp.example.com', 587, 'dummy_user', 'dummy_pass', 'smtp', true);
INSERT INTO imap_configuration (host, port, username, password, protocol, active) VALUES ('imap.example.com', 993, 'dummy_user', 'dummy_pass', 'imaps', true);

-- Default filter config for scheduler
INSERT INTO scheduled_filter_config (target_email, target_subject, active) VALUES ('alert@example.com', 'Daily Report', true);
