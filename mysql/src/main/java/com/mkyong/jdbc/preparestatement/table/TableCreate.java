package com.mkyong.jdbc.preparestatement.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCreate {

    private static final String SQL_CREATE = "CREATE TABLE EMPLOYEE"
            + "("
            + " ID INT NOT NULL AUTO_INCREMENT,"
            + " NAME VARCHAR(100) NOT NULL,"
            + " SALARY DECIMAL(15, 2) NOT NULL,"
            + " CREATED_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
            + " PRIMARY KEY (ID)"
            + ")";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test", "root", "password");
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_CREATE)) {

            preparedStatement.execute();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
