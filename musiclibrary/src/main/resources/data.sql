INSERT INTO album (name, artist, release_date, number_of_songs, genre, description, image, created, updated)
VALUES
    ('Album 1', 'Artist 1', '2023-01-01', 12, 'POP', 'Description for Album 1', NULL, '2024-04-04', '2024-04-04'),
    ('Album 2', 'Artist 2', '2022-05-15', 10, 'ROCK', 'Description for Album 2', NULL, '2024-04-04', '2024-04-04'),
    ('Album 3', 'Artist 3', '2024-02-20', 15, 'HIP_HOP', 'Description for Album 3', NULL, '2024-04-04', '2024-04-04'),
    ('Greatest Hits', 'Queen', '1981-10-26', 17, 'ROCK', 'Collection of Queen''s greatest hits.', NULL, '2024-04-04', '2024-04-04'),
    ('Back in Black', 'AC/DC', '1980-07-25', 10, 'ROCK', 'AC/DC''s best-selling album.', NULL, '2024-04-04', '2024-04-04');

INSERT INTO album_ratings (album_id, ratings)
VALUES
    (6, 3),
    (7, 1),
    (7, 1),
    (9, 4),
    (9, 5),
    (9, 5),
    (9, 5),
    (10, 5),
    (10, 5),
    (10, 5),
    (10, 4),
    (10, 4);