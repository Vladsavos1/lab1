create table if not exists sports
(
    sportsman varchar(20) not null,
    day int not null,
    result double not null,
    unique (sportsman, day)
);

truncate table sports;

INSERT INTO labs_db.sports (sportsman, day, result) VALUES ('sportsman1', 1, 5.2);

INSERT INTO labs_db.sports (sportsman, day, result) VALUES ('sportsman1', 2, 2.6);

INSERT INTO labs_db.sports (sportsman, day, result) VALUES ('sportsman1', 3, 1.7);

INSERT INTO labs_db.sports (sportsman, day, result) VALUES ('sportsman2', 1, 3.7);

INSERT INTO labs_db.sports (sportsman, day, result) VALUES ('sportsman2', 2, 4.1);

INSERT INTO labs_db.sports (sportsman, day, result) VALUES ('sportsman2', 3, 1.3);

INSERT INTO labs_db.sports (sportsman, day, result) VALUES ('sportsman3', 1, 2.1);

INSERT INTO labs_db.sports (sportsman, day, result) VALUES ('sportsman3', 2, 5.3);

INSERT INTO labs_db.sports (sportsman, day, result) VALUES ('sportsman3', 3, 3.3);