CREATE TABLE "app_user" (
    user_id INT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    address VARCHAR(100),
    fk_city_id INT,
    fess BOOLEAN
);

CREATE TABLE "city" (
    city_id INT,
    city_name VARCHAR(50)
);

INSERT INTO "app_user" VALUES (1, 'PINCO', 'PALLINO', 'VIA DEI PAZZI', 1, TRUE);
INSERT INTO "app_user" VALUES (2, 'JOHN', 'DOE', NULL, 4, TRUE);
INSERT INTO "app_user" VALUES (3, 'PAOLINO', 'PAPERINO', 'VIA DEI PAPERI', 2, FALSE);
INSERT INTO "app_user" VALUES (4, 'FRED', 'FLINSTONE', 'VIA ROSSI', 3, FALSE);

INSERT INTO "city" VALUES (1, 'MILANO');
INSERT INTO "city" VALUES (2, 'ROMA');
INSERT INTO "city" VALUES (3, 'NAPOLI');
INSERT INTO "city" VALUES (4, 'NEW YORK');