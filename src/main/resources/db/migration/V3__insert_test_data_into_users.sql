-- insert random users
INSERT INTO users (first_name, last_name, sur_name, date_of_birth, email)
SELECT
        'FirstName' || generate_series(1,15),
        'LastName' || generate_series(1,15),
        'SurName' || generate_series(1,15),
        current_date - interval '1 day' * (random() * 10000 + 18 * 365),
        'user' || generate_series(1,15) || '@example.com'
FROM generate_series(1,15)
ON CONFLICT (email) DO NOTHING;