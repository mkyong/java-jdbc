-- create test database
CREATE DATABASE test
    WITH
    OWNER = postgres
    ENCODING = 'UTF8';

-- create order table
CREATE TABLE "ORDER"
(
    "ID" serial,
    "ORDER_NO" varchar(50) NOT NULL,
    "ORDER_NAME" varchar(50),
    "TOTAL_AMT" numeric(15, 6) NOT NULL,
    "CREATED_DATE" date NOT NULL DEFAULT CURRENT_DATE,
    PRIMARY KEY ("ID")
)

-- insert data
INSERT INTO "ORDER"("ORDER_NO", "ORDER_NAME", "TOTAL_AMT") VALUES ('A1001', 'Special Order for Apple', 999.39);
INSERT INTO "ORDER"("ORDER_NO", "ORDER_NAME", "TOTAL_AMT", "CREATED_DATE") VALUES ('A1002', 'This for Orange', 19.39489678, '2019-05-10');
INSERT INTO "ORDER"("ORDER_NO", "ORDER_NAME", "TOTAL_AMT", "CREATED_DATE") VALUES ('A1003', 'Banana', 49999.50, '2019-05-12');

