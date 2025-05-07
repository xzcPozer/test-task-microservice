INSERT INTO users_subscriptions (user_id, sub_id)
SELECT
    (random() * 14 + 1)::int AS user_id,
        (random() * 4 + 1)::int AS sub_id
FROM generate_series(1,30)
ON CONFLICT DO NOTHING;