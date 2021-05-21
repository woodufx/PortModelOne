
CREATE TABLE cargoType
(
cargo_id SERIAL PRIMARY KEY,
cargo_name VARCHAR(50)
);

CREATE TABLE crane
(
crane_id SERIAL PRIMARY KEY,
crane_speed FLOAT,
crane_active BOOLEAN,
cargo_id   INTEGER,
CONSTRAINT crane_cargo_id FOREIGN KEY (cargo_id) REFERENCES cargoType (cargo_id) ON DELETE CASCADE

);

CREATE TABLE ship
(
ship_id SERIAL PRIMARY KEY,
ship_name VARCHAR(50),
cargo_id   INTEGER,
ship_weight FLOAT  ,
CONSTRAINT ship_cargo_id FOREIGN KEY (cargo_id) REFERENCES cargoType (cargo_id) ON DELETE CASCADE
);
CREATE TABLE schedule
(
schedule_id SERIAL PRIMARY KEY,
schedule_arrival_date VARCHAR(50),
schedule_real_arrival_date VARCHAR(50),
ship_id   INTEGER,
crane_id   INTEGER,
schedule_weight FLOAT  ,
schedule_start_of_unloading VARCHAR(50),
schedule_end_of_unloading VARCHAR(50),
CONSTRAINT schedule_ship_id FOREIGN KEY (ship_id) REFERENCES ship (ship_id) ON DELETE CASCADE,
CONSTRAINT schedule_crane_id FOREIGN KEY (crane_id) REFERENCES crane (crane_id) ON DELETE CASCADE

);
