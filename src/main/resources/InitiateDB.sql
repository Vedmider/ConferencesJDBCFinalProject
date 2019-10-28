create database conferences CHARACTER SET utf8 COLLATE utf8_bin;
use conferences;
create table user_role (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
						role_name varchar(255) NOT NULL);
create table rights (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
					right_title varchar(255) NOT NULL);
create table role_rigths_relation (role_id int NOT NULL,
									right_id int NOT NULL,
                                    CONSTRAINT unique_role_right UNIQUE (role_id, right_id));

create table users (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
					login varchar(255) NOT NULL,
                    user_password varchar(255) NOT NULL,
                    first_name varchar(255) NOT NULL,
                    last_name varchar(255) NOT NULL,
                    email varchar(255) NOT NULL,
                    user_role int NOT NULL,
                    rights long NOT NULL,
                    CONSTRAINT user_role_fk FOREIGN KEY (user_role) REFERENCES user_role(id));
create table speaker (user_id int NOT NULL UNIQUE,
						rating int,
                        bonuses int,
                        CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES users(id));
create table conferences (id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
							theme varchar(255) NOT NULL,
                            date_time_planned datetime NOT NULL,
                            date_time_happened datetime NOT NULL,
                            address varchar(255));
create table reports (id int AUTO_INCREMENT PRIMARY KEY,
						title varchar(255) NOT NULL,
                        time_start time NOT NULL,
                        duration int,
                        speaker_id int,
                        conference_id int NOT NULL,
                        registered int,
                        attended int,
                        CONSTRAINT conference_id_fk FOREIGN KEY (conference_id) REFERENCES conferences(id),
                        CONSTRAINT speaker_id_fk FOREIGN KEY (speaker_id) REFERENCES users(id));

insert  into user_role (role_name) values ('ADMIN'), ('ANONYMOUS'), ('MODERATOR'), ('SPEAKER'), ('USER');
insert into rights (right_title) values ('EDIT USER'), ('DELETE USER'), ('CREATE REPORT'), ('EDIT REPORT'), ('CREATE CONFERENCE'), ('EDIT CONFERENCE'), ('DELETE CONFERENCE');
