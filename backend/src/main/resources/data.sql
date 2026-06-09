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

INSERT INTO guests (first_name, last_name, email) VALUES
                                                      ('Alice', 'Nguyen', 'alice.nguyen@example.com'),
                                                      ('Marco', 'Rossi', 'marco.rossi@example.com'),
                                                      ('Sofia', 'Berger', 'sofia.berger@example.com'),
                                                      ('James', 'Okoro', 'james.okoro@example.com'),
                                                      ('Lena', 'Hartmann', 'lena.hartmann@example.com'),
                                                      ('Yuki', 'Tanaka', 'yuki.tanaka@example.com');

INSERT INTO bookings (room_id, guest_id, check_in_date, check_out_date, total_price, breakfast_included) VALUES
                                                                                                             (1, 1, '2026-06-12', '2026-06-15', 360.00, false),
                                                                                                             (3, 2, '2026-06-14', '2026-06-16', 170.00, true),
                                                                                                             (4, 3, '2026-07-01', '2026-07-08', 2100.00, false),
                                                                                                             (2, 4, '2026-07-03', '2026-07-06', 660.00, true),
                                                                                                             (5, 5, '2026-08-20', '2026-08-25', 350.00, false),
                                                                                                             (6, 6, '2026-09-10', '2026-09-12', 360.00, true),
                                                                                                             (7, 1, '2026-12-23', '2026-12-27', 2000.00, false),
                                                                                                             (1, 2, '2026-09-15', '2026-09-18', 360.00, true);