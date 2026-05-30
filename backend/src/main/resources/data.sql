INSERT INTO rooms (title, description, capacity, price_per_night, image_path) VALUES
                                                                                        ('Deluxe Room', 'Nice deluxe room', 2, 120.0, '/images/deluxe.jpg'),
                                                                                        ('Family Room', 'Big family room', 4, 220.0, '/images/family.jpg'),
                                                                                        ('Standard Room', 'Cozy standard room with basic amenities', 2, 85.0, '/images/standard.jpg'),
                                                                                        ('Suite Room', 'Luxury suite with separate living area', 3, 300.0, '/images/suite.jpg'),
                                                                                        ('Single Room', 'Compact room perfect for solo travelers', 1, 70.0, '/images/single.jpg'),
                                                                                        ('Executive Room', 'Modern executive room with workspace', 2, 180.0, '/images/executive.jpg'),
                                                                                        ('Presidential Suite', 'Premium suite with top-class facilities', 5, 500.0, '/images/presidential.jpg');

INSERT INTO extras (name, icon) VALUES
                                        ('WiFi', 'bi-wifi'),
                                        ('Minibar', 'bi-cup-straw'),
                                        ('TV', 'bi-tv');

INSERT INTO room_extras (room_id, extra_id) VALUES
                                                (1, 1),
                                                (1, 3),
                                                (2, 1),
                                                (2, 2),
                                                (3, 1),
                                                (4, 1),
                                                (4, 2),
                                                (4, 3),
                                                (5, 1),
                                                (6, 1),
                                                (6, 2),
                                                (6, 3),
                                                (7, 1),
                                                (7, 2),
                                                (7, 3);