CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    handle VARCHAR(20) NOT NULL,
    avatar_url VARCHAR(255),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

INSERT INTO users (id, handle, avatar_url, first_name, last_name) VALUES
(
 '1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c',
 'john',
 '',
 'John',
 'Doe'
),
(
 '2d3e4f5a-6b7c-8d9e-0a1b-2c3d4e5f6a7b',
    'jane',
    'https://i.pravatar.cc/100?img=60',
 'Jane',
 'Smith'
),
(
 '3e4f5a6b-7c8d-9e0a-1b2c-3d4e5f6a7b8c',
    'alice',
    'https://i.pravatar.cc/100?img=61',
 'Alice',
 'Johnson'
),
(
 '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9d',
    'bob',
    '',
 'Bob',
 'Brown'
),
(
    '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9e',
    'ada',
    'https://avatars.githubusercontent.com/u/583231?v=5',
    'Ada',
    'Lovelace'
),
(
    '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9f',
    'aturing',
    'https://i.pravatar.cc/100?img=5''',
    'Alan',
    'Turing'
),
(
    '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9a',
    'ghopper',
    'https://i.pravatar.cc/100?img=15',
    'Grace',
    'Hopper'
);