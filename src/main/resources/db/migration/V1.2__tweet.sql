CREATE TABLE tweets (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    content TEXT NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
 );

INSERT INTO tweets (id, user_id, content) VALUES
(
 '1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c',
 '1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c',
 'Hello, world!'
),
(
 '3e4f5a6b-7c8d-9e0a-1b2c-3d4e5f6a7b8c',
 '3e4f5a6b-7c8d-9e0a-1b2c-3d4e5f6a7b8c',
 'At a startup, you don’t wear many hats. You are the hat.'
),
(
 '2d3e4f5a-6b7c-8d9e-0a1b-2c3d4e5f6a7b',
 '2d3e4f5a-6b7c-8d9e-0a1b-2c3d4e5f6a7b',
 'Coffee: because adulting is hard and naps are frowned upon'
),
(
 '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9d',
 '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9d',
 'You never realize how long a minute is until you’re holding a plank.'
),
(
    '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9e',
    '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9e',
    'The Analytical Engine weaves algebraic patterns just as the Jacquard loom weaves flowers and leaves.'
),
(
    '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9f',
    '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9f',
    'Those who can imagine anything, can create the impossible.'
),
(
    '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9a',
    '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9a',
    'The most dangerous phrase in the language is, “We’ve always done it this way.'
);