package com.mkyong.jdbc.callablestatement;

import java.math.BigDecimal;
import java.sql.*;

/*
    CREATE OR REPLACE PROCEDURE get_employee_by_id(
	   p_id IN EMPLOYEE.ID%TYPE,
       o_name OUT EMPLOYEE.NAME%TYPE,
	   o_salary OUT EMPLOYEE.SALARY%TYPE,
	   o_date OUT EMPLOYEE.CREATED_DATE%TYPE)
    AS
    BEGIN

    SELECT NAME , SALARY, CREATED_DATE INTO o_name, o_salary, o_date from EMPLOYEE WHERE ID = p_id;

    END;
 */
public class StoreProcedureOutParameter {

    public static void main(String[] args) {

        String createSP = "CREATE OR REPLACE PROCEDURE get_employee_by_id( "
                + " p_id IN EMPLOYEE.ID%TYPE, "
                + " o_name OUT EMPLOYEE.NAME%TYPE, "
                + " o_salary OUT EMPLOYEE.SALARY%TYPE, "
                + " o_date OUT EMPLOYEE.CREATED_DATE%TYPE) "
                + " AS "
                + " BEGIN "
                + "     SELECT NAME, SALARY, CREATED_DATE INTO o_name, o_salary, o_date from EMPLOYEE WHERE ID = p_id; "
                + " END;";

        String runSP = "{ call get_employee_by_id(?,?,?,?) }";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:orcl", "system", "Password123");
             Statement statement = conn.createStatement();
             CallableStatement callableStatement = conn.prepareCall(runSP)) {

            // create or replace stored procedure
            statement.execute(createSP);

            callableStatement.setInt(1, 3);

            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            callableStatement.registerOutParameter(3, Types.DECIMAL);
            callableStatement.registerOutParameter(4, java.sql.Types.DATE);

            // run it
            callableStatement.executeUpdate();

            // java.sql.SQLException: operation not allowed: Ordinal binding and Named binding cannot be combined!
            /*String name = callableStatement.getString("NAME");
            BigDecimal salary = callableStatement.getBigDecimal("SALARY");
            Timestamp createdDate = callableStatement.getTimestamp("CREATED_DATE");*/

            String name = callableStatement.getString(2);
            BigDecimal salary = callableStatement.getBigDecimal(3);
            Timestamp createdDate = callableStatement.getTimestamp(4);

            System.out.println("name: " + name);
            System.out.println("salary: " + salary);
            System.out.println("createdDate: " + createdDate);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
