package com.mkyong.jdbc.callablestatement;

import java.sql.*;

/*
    CREATE OR REPLACE FUNCTION getUsers(mycurs OUT refcursor) RETURNS refcursor
    AS $$
	BEGIN
		OPEN mycurs FOR select * from pg_user;
	END;
	$$
	LANGUAGE plpgsql;
 */
public class FunctionReturnRefCursor {

    public static void main(String[] args) {

        String createFunction = "CREATE OR REPLACE FUNCTION getUsers(mycurs OUT refcursor) "
                + " RETURNS refcursor "
                + " AS $$ "
                + " BEGIN "
                + "     OPEN mycurs FOR select * from pg_user; "
                + " END; "
                + " $$ "
                + " LANGUAGE plpgsql";

        String runFunction = "{? = call getUsers()}";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/test", "postgres", "password");
             Statement statement = conn.createStatement();
             CallableStatement cs = conn.prepareCall(runFunction);
        ) {

            // We must be inside a transaction for cursors to work.
            conn.setAutoCommit(false);

            // create function
            statement.execute(createFunction);

            // register output
            cs.registerOutParameter(1, Types.REF_CURSOR);

            // run function
            cs.execute();

            // get refcursor and convert it to ResultSet
            ResultSet resultSet = (ResultSet) cs.getObject(1);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("usename"));
                System.out.println(resultSet.getString("passwd"));
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
