CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

INSERT INTO users (id, first_name, last_name) VALUES
('1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c', 'John', 'Doe'),
('2d3e4f5a-6b7c-8d9e-0a1b-2c3d4e5f6a7b', 'Jane', 'Smith'),
('3e4f5a6b-7c8d-9e0a-1b2c-3d4e5f6a7b8c', 'Alice', 'Johnson'),
('4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9d', 'Bob', 'Brown');