package com.mkyong.jdbc.statement.row;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RowDelete {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test", "root", "password");
             Statement statement = conn.createStatement()) {

            int row = statement.executeUpdate(deleteByName("mkyong"));

            // rows affected
            System.out.println(row);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String deleteByName(String name) {

        return "DELETE FROM EMPLOYEE WHERE NAME='" + name + "'";

    }
}