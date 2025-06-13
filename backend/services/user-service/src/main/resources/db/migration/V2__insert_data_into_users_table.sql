INSERT INTO users (id, full_name, email, password, role, created_at, updated_at)
VALUES (
    'a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11',
    'Vaycheslav Radiuk',
    'cuerno@nextpizza.com',
    '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W',
    'USER',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO users (id, full_name, email, password, role, created_at, updated_at)
VALUES (
    'b1eebc99-9c0b-4ef8-bb6d-6bb9bd380a22',
    'Maksim Lastovsky',
    'mlastovsky@nextpizza.com',
    '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W',
    'ADMIN',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO users (id, full_name, email, role, provider, provider_id, created_at, updated_at)
VALUES (
    'c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33',
    'Google User',
    'google.user@example.com',
    'USER',
    'google',
    'google-oauth2-123456789',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);