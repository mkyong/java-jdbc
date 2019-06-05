-- create test database
--CREATE DATABASE test
--    WITH
--    OWNER = postgres
--    ENCODING = 'UTF8';

-- create employee table
CREATE TABLE EMPLOYEE
(
    ID serial,
    NAME varchar(100) NOT NULL,
    SALARY numeric(15, 2) NOT NULL,
    CREATED_DATE timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
    PRIMARY KEY (ID)
);