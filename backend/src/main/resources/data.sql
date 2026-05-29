-- 1. INSERT THE ROOMS (Hier mit Unterstrichen, wie Spring Boot es intern übersetzt)
INSERT INTO rooms (title, description, capacity, price_per_night, image_path) VALUES
                                                                                  ('Einzelzimmer "Comfort"', 'Geräumiges, schönes Zimmer mit großem Fenster. Badezimmer mit Dusche und WC.', 1, 89.0, 'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85'),
                                                                                  ('Doppelzimmer mit Balkon', 'Modernes Doppelzimmer mit Balkon, wunderschöner Aussicht und großem Badezimmer.', 2, 129.0, 'https://images.unsplash.com/photo-1566665797739-1674de7a421a'),
                                                                                  ('Deluxe Suite', 'Exklusive Suite mit Wohnbereich, Whirlpool und traumhaftem Panoramablick.', 4, 249.0, 'https://images.unsplash.com/photo-1522708323590-d24dbb6b0267'),
                                                                                  ('Business Zimmer', 'Perfekt für Geschäftsreisen mit großem Schreibtisch, schnellem WLAN und ruhiger Atmosphäre.', 1, 109.0, 'https://images.unsplash.com/photo-1590490360182-c33d57733427'),
                                                                                  ('Familienzimmer', 'Großes Zimmer für Familien mit zusätzlichem Schlafbereich und viel Platz für Kinder.', 5, 189.0, 'https://images.unsplash.com/photo-1505693416388-ac5ce068fe85'),
                                                                                  ('Penthouse Suite', 'Luxus Penthouse mit privater Terrasse, exklusivem Service und atemberaubender Aussicht.', 2, 399.0, 'https://images.unsplash.com/photo-1542314831-068cd1dbfeeb');

-- 2. INSERT THE EXTRAS (FEATURES)
INSERT INTO extras (name, icon) VALUES
                                    ('1 Person', 'bi-person'),
                                    ('2 Personen', 'bi-people'),
                                    ('Doppelbett', 'bi-house-door'),
                                    ('Kingsize Bett', 'bi-house-door'),
                                    ('Dusche', 'bi-droplet'),
                                    ('TV', 'bi-tv'),
                                    ('Smart TV', 'bi-tv'),
                                    ('WLAN', 'bi-wifi'),
                                    ('Safe', 'bi-safe'),
                                    ('Frühstück', 'bi-cup-hot'),
                                    ('Whirlpool', 'bi-droplet-half'),
                                    ('Minibar', 'bi-cup-straw');

--- 3. LINK EXTRAS TO ROOMS (Spring Boot erzwingt hier intern room_id und extra_id)
INSERT INTO rooms_extras (room_id, extra_id) VALUES
                                                 (1, 1),
                                                 (1, 3),
                                                 (1, 5),
                                                 (1, 6),
                                                 (1, 8),
                                                 (1, 9);

INSERT INTO rooms_extras (room_id, extra_id) VALUES
                                                 (2, 2),
                                                 (2, 4),
                                                 (2, 10),
                                                 (2, 7),
                                                 (2, 8),
                                                 (2, 9);

INSERT INTO rooms_extras (room_id, extra_id) VALUES
                                                 (3, 11),
                                                 (3, 12),
                                                 (3, 8),
                                                 (3, 7);