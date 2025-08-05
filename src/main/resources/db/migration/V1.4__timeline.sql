CREATE TABLE timeline (
    user_id  UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    tweet_id UUID NOT NULL REFERENCES tweets(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, tweet_id)
);

INSERT INTO timeline (user_id, tweet_id) VALUES
    ('1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c', '2d3e4f5a-6b7c-8d9e-0a1b-2c3d4e5f6a7b'),
    ('1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c', '3e4f5a6b-7c8d-9e0a-1b2c-3d4e5f6a7b8c'),
    ('1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c', '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9d'),
    ('1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c', '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9e'),
    ('1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c', '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9f'),
    ('1c9f8b2e-3d4e-4f5a-8b6c-7d8e9f0a1b2c', '4f5a6b7c-8d9e-0a1b-2c3d-4e5f6a7b8c9a');