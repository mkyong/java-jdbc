-- Oracle

-- create employee table
CREATE TABLE EMPLOYEE
(
    ID number GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    NAME varchar2(100) NOT NULL,
    SALARY number(15, 2) NOT NULL,
    CREATED_DATE timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT employee_pk PRIMARY KEY (ID)
);