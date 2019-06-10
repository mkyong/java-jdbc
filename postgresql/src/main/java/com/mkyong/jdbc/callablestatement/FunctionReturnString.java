package com.mkyong.jdbc.callablestatement;

import java.sql.*;

/*
    CREATE OR REPLACE FUNCTION hello(p1 TEXT) RETURNS TEXT
    AS $$
    BEGIN
        RETURN 'hello ' || p1;
    END;
    $$
    LANGUAGE plpgsql;

    select hello('mkyong');
 */
public class FunctionReturnString {

    public static void main(String[] args) {

        String createFunction = "CREATE OR REPLACE FUNCTION hello(p1 TEXT) RETURNS TEXT "
                + " AS $$ "
                + " BEGIN "
                + " RETURN 'hello ' || p1; "
                + " END; "
                + " $$ "
                + " LANGUAGE plpgsql";

        String runFunction = "{ ? = call hello( ? ) }";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/test", "postgres", "password");
             Statement statement = conn.createStatement();
             CallableStatement callableStatement = conn.prepareCall(runFunction)) {

            // create or replace stored function
            statement.execute(createFunction);

            //----------------------------------

            // output
            callableStatement.registerOutParameter(1, Types.VARCHAR);

            // input
            callableStatement.setString(2, "mkyong");

            // Run hello() function
            callableStatement.executeUpdate();

            // Get result
            String result = callableStatement.getString(1);

            System.out.println(result);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
