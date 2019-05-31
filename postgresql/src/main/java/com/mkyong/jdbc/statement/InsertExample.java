package com.mkyong.jdbc.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertExample {

    private static final String SQL_CREATE = "CREATE TABLE EMPLOYEE"
            + "("
            + " ID serial,"
            + " NAME varchar(100) NOT NULL,"
            + " SALARY numeric(15, 2) NOT NULL,"
            + " CREATED_DATE date NOT NULL DEFAULT CURRENT_DATE,"
            + " PRIMARY KEY (ID)"
            + ")";

    private static final String SQL_DROP = "DROP TABLE IF EXISTS EMPLOYEE";

    public static void main(String[] args) {

        try {

            // auto close connection
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/test", "postgres", "password");
                 Statement statement = conn.createStatement()) {

                // if DDL failed, it will raise an SQLException
                statement.executeUpdate(SQL_CREATE);
            }

        }catch (SQLException e){
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
