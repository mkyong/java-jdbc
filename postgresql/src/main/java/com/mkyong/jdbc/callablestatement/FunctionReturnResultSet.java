package com.mkyong.jdbc.callablestatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    CREATE OR REPLACE FUNCTION getRoles() RETURNS SETOF pg_authid
    AS 'select * from pg_roles' LANGUAGE sql;

    select * from getRoles();
 */
public class FunctionReturnResultSet {

    public static void main(String[] args) {

        List<String> users = new ArrayList<>();

        String createFunction = "CREATE OR REPLACE FUNCTION getRoles() RETURNS SETOF pg_roles "
                + " AS 'select * from pg_roles' LANGUAGE sql;";

        String runFunction = "select * from getRoles();";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/test", "postgres", "password");
             Statement statement = conn.createStatement()) {

            // create a function returns as SETOF
            statement.execute(createFunction);

            // run it
            ResultSet resultSet = statement.executeQuery(runFunction);
            while (resultSet.next()) {
                users.add(resultSet.getString("rolname"));
            }

            System.out.println("Database roles...");
            users.forEach(x -> System.out.println(x));

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
