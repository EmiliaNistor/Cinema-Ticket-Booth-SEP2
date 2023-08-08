-- Administrator and user account
SET schema 'sep2reexam_database';
INSERT INTO users (username, password, administrator) VALUES ("admin", "admin", true);
INSERT INTO users (username, password) VALUES ("user", "user");

-- Screens
INSERT INTO screen (name) VALUES ("IMAX");
INSERT INTO screen (name) VALUES ("2D");

-- Menus
INSERT INTO menu (food, price) VALUES ("Big Mac", 10);
INSERT INTO menu (food, price) VALUES ("Coca-Cola", 1.5);

-- Seats
-- Screen1
INSERT INTO seat (row, number, screen_id) VALUES ("A", 1, 1);
INSERT INTO seat (row, number, screen_id) VALUES ("A", 2, 1);
INSERT INTO seat (row, number, screen_id) VALUES ("B", 1, 1);
INSERT INTO seat (row, number, screen_id) VALUES ("B", 2, 1);
--Screen2
INSERT INTO seat (row, number, screen_id) VALUES ("A", 1, 2);
INSERT INTO seat (row, number, screen_id) VALUES ("A", 2, 2);

-- Movies
INSERT INTO movie (name, start_time, end_time, date, length, genre, screen_id)
VALUES ("Oppenheimer", '02:03:04', '11:03:04', '03/03/2023', 180, "Comedy", 1);
INSERT INTO movie (name, start_time, end_time, date, length, genre, screen_id)
VALUES ("Oppenheimer", '05:03:04', '11:03:04', '03/03/2023', 180, "Comedy", 2);

INSERT INTO movie (name, start_time, end_time, date, length, genre, screen_id)
VALUES ("Barbie", '05:03:04', '12:03:04', '03/03/2023', 120, "Drama", 1);
INSERT INTO movie (name, start_time, end_time, date, length, genre, screen_id)
VALUES ("Barbie", '05:04:04', '15:03:04', '03/03/2023', 120, "Drama", 2);