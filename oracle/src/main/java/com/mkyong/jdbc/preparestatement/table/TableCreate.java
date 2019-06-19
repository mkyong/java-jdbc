package com.mkyong.jdbc.preparestatement.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCreate {

    private static final String SQL_CREATE = "CREATE TABLE EMPLOYEE"
            + "("
            + " ID number GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),"
            + " NAME varchar2(100) NOT NULL,"
            + " SALARY number(15, 2) NOT NULL,"
            + " CREATED_DATE DATE DEFAULT SYSDATE NOT NULL,"
            + " CONSTRAINT employee_pk PRIMARY KEY (ID)"
            + ")";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl", "system", "Password123");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE)) {

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
