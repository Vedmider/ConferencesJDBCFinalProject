create database conferences CHARACTER SET utf8 COLLATE utf8_bin;
use conferences;
create table user_role (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
						role_name varchar(255) NOT NULL);
create table rights (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
					right_title varchar(255) NOT NULL);
create table role_rights_relation (role_id int NOT NULL,
									right_id int NOT NULL,
                                    CONSTRAINT unique_role_right UNIQUE (role_id, right_id));

create table users (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
					login varchar(255) NOT NULL,
                    user_password varchar(255) NOT NULL,
                    first_name varchar(255) NOT NULL,
                    last_name varchar(255) NOT NULL,
                    email varchar(255) NOT NULL,
                    user_role int NOT NULL,
                    CONSTRAINT user_role_fk FOREIGN KEY (user_role) REFERENCES user_role(id));
create table speaker (user_id int NOT NULL UNIQUE,
						rating int,
                        bonuses int,
                        CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users(id));
create table conferences (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
							theme varchar(255) NOT NULL,
                            date_time_planned datetime NOT NULL,
                            date_time_happened datetime,
                            address varchar(255));
create table reports (id int AUTO_INCREMENT PRIMARY KEY,
						title varchar(255) NOT NULL,
                        time_start time NOT NULL,
                        speaker_id int,
                        conference_id int NOT NULL,
                        registered int,
                        attended int,
                        CONSTRAINT conference_id_fk FOREIGN KEY (conference_id) REFERENCES conferences(id),
                        CONSTRAINT speaker_id_fk FOREIGN KEY (speaker_id) REFERENCES users(id));

insert  into user_role (role_name) values ('ADMIN'), ('ANONYMOUS'), ('MODERATOR'), ('SPEAKER'), ('USER');
insert into rights (right_title) values ('EDIT USER'), ('DELETE USER'), ('CREATE REPORT'), ('EDIT REPORT'), ('CREATE CONFERENCE'), ('EDIT CONFERENCE'), ('DELETE CONFERENCE');
ALTER TABLE  role_rights_relation ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES user_role(id), ADD CONSTRAINT rights_fk FOREIGN KEY (right_id) REFERENCES rights(id);
insert into role_rights_relation values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (3, 3), (3, 4), (4, 4);
insert into users (login, user_password, first_name, last_name, email, user_role)
    values ('user', '12345', 'Mart', 'Poom', 'Poom@gmail.com', 5),
       ('referee', '12345', 'Howard', 'Webb', 'HowardWeb@gmail.com', 3),
       ('AcountAdmin', '12345', 'Harold', 'Chapman', 'Harold@gmail.com', 1),
       ('speaker', '12345', 'Hovard', 'Lovecraft', 'lovecraft@gmail.com', 4);
insert into speaker VALUE ((select id from users where login like 'speaker'), 50, 5677);
insert into conferences(theme, date_time_planned, date_time_happened, address)
    values('devoxx ukraine 2019', '2019-10-01 10:00', '2019-10-01 10:00', 'Kyiv, Mezhyhirska St, 82, Event space M82');
insert into reports (title, time_start, speaker_id, conference_id, registered)
    VALUE ('Methodology & Culture', '10:10:00', (select id from users where login like 'speaker'), (select id from conferences where theme rlike '(?i)\\s*devoxx\\s+ukraine\\s+2019\\s*'), 50 );
