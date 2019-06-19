package com.mkyong.jdbc.statement;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class BatchUpdate {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl", "system", "Password123");
             Statement statement = conn.createStatement()) {

            // optional, for transaction
            // commit all or rollback all, if any errors
            conn.setAutoCommit(false);

            // add list of SQL commands and run as a batch

            // drop table
            statement.addBatch(SQL_DROP);

            // create table
            statement.addBatch(SQL_CREATE);

            // insert
            statement.addBatch(generateInsert("mkyong", new BigDecimal(1000)));

            // insert
            statement.addBatch(generateInsert("jane", new BigDecimal(2000)));

            // update
            statement.addBatch(updateSalaryByName("mkyong", new BigDecimal(888)));

            int[] rows = statement.executeBatch();

            System.out.println(Arrays.toString(rows)); // [0, 0, 1, 1, 1]

            // commit everything
            conn.commit();

            // statement.executeLargeBatch();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static final String SQL_CREATE = "CREATE TABLE EMPLOYEE"
            + "("
            + " ID number GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),"
            + " NAME varchar2(100) NOT NULL,"
            + " SALARY number(15, 2) NOT NULL,"
            + " CREATED_DATE DATE DEFAULT SYSDATE NOT NULL,"
            + " CONSTRAINT employee_pk PRIMARY KEY (ID)"
            + ")";


    private static final String SQL_DROP = "DROP TABLE EMPLOYEE";

    private static String generateInsert(String name, BigDecimal salary) {

        return "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) " +
                "VALUES ('" + name + "','" + salary + "',TO_DATE('" + formatter.format(LocalDateTime.now()) + "','dd/MM/yyyy hh24:mi:ss'))";

    }

    private static String updateSalaryByName(String name, BigDecimal salary) {

        return "UPDATE EMPLOYEE SET SALARY='" + salary + "' WHERE NAME='" + name + "'";
    }

}