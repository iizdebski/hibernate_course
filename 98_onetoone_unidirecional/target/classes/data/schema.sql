#DROP DATABASE IF EXISTS TEST3;
CREATE DATABASE IF NOT EXISTS TEST3 CHARACTER SET utf8 COLLATE utf8_general_ci;
USE TEST3;
DROP TABLE IF EXISTS ADDRESS;
CREATE TABLE ADDRESS(

                            ADDRESS_ID INTEGER NOT NULL
                            AUTO_INCREMENT PRIMARY KEY,
                            STREET_NAME VARCHAR(50),
                            CITY_NAME VARCHAR(50),
                            STATE_NAME VARCHAR(255),
                            PIN_CODE INTEGER,
                            CONSTRAINT ADDRESS_ID_UNIQUE
                            UNIQUE (ADDRESS_ID)
)
  ENGINE = innoDB
  DEFAULT CHARACTER SET = utf8
;

DROP TABLE IF EXISTS EMPLOYEE;
CREATE TABLE EMPLOYEE(
                            EMPLOYEE_ID INTEGER NOT NULL
                            AUTO_INCREMENT PRIMARY KEY,
                            ADDRESS_ID INTEGER,
                            EMPLOYEE_NAME VARCHAR(200) NOT NULL,
                            EMAIL VARCHAR(255),
                            DATE_OF_JOIN TIMESTAMP,
                            SALARY DOUBLE PRECISION,
                            CONSTRAINT EMPLOYEE_ID_UNIQUE
                            UNIQUE (EMPLOYEE_ID),
                            FOREIGN KEY(ADDRESS_ID) REFERENCES ADDRESS(ADDRESS_ID)
)
  ENGINE = innoDB
  DEFAULT CHARACTER SET = utf8
;
INSERT INTO ADDRESS(ADDRESS_ID, STREET_NAME, CITY_NAME, STATE_NAME, PIN_CODE) VALUES (1, 'Danyla Halytskogo str.','Ternopil', 'Ukraine', 46000);
INSERT INTO EMPLOYEE(ADDRESS_ID, EMPLOYEE_NAME, EMAIL, DATE_OF_JOIN, SALARY) VALUES (1, 'Peter Petrenko', '123@gmail.com', CURRENT_TIMESTAMP, 5000);