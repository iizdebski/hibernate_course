DROP DATABASE IF EXISTS ONETOONEBIDIRECTIONALWEBSYSTIQUE;
CREATE DATABASE IF NOT EXISTS ONETOONEBIDIRECTIONALWEBSYSTIQUE CHARACTER SET utf8 COLLATE utf8_general_ci;

USE ONETOONEBIDIRECTIONALWEBSYSTIQUE;

DROP TABLE IF EXISTS UNIVERSITY;

create table UNIVERSITY (
university_id BIGINT NOT NULL AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
country  VARCHAR(30) NOT NULL,
PRIMARY KEY (university_id)
);

create table STUDENT (
   student_id BIGINT NOT NULL AUTO_INCREMENT,
   university_id BIGINT NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   section    VARCHAR(30) NOT NULL,
   PRIMARY KEY (student_id),
   CONSTRAINT student_university FOREIGN KEY (university_id) REFERENCES UNIVERSITY (university_id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO university (university_id, name, country) VALUES (1, 'TNPU', 'Ukraine');

INSERT INTO student (university_id, first_name, last_name, section) VALUES (1, 'Peter', 'Petrenko', 'Maths');
INSERT INTO student (university_id, first_name, last_name, section) VALUES (1, 'Maxim', 'Maxymenko', 'Physics');
INSERT INTO student (university_id, first_name, last_name, section) VALUES (1, 'Roman', 'Romanenko', 'Chemistry');