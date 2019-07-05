package com.mkyong.jdbc.preparestatement;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;

public class BatchUpdate {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/test", "root", "password");
             PreparedStatement psDDL = conn.prepareStatement(SQL_CREATE);
             PreparedStatement psInsert = conn.prepareStatement(SQL_INSERT);
             PreparedStatement psUpdate = conn.prepareStatement(SQL_UPDATE)) {

            // optional, for transaction
            // commit all or rollback all, if any errors
            conn.setAutoCommit(false); // default true

            psDDL.execute();

            // Run list of insert commands
            psInsert.setString(1, "mkyong");
            psInsert.setBigDecimal(2, new BigDecimal(10));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.addBatch();

            psInsert.setString(1, "kungfu");
            psInsert.setBigDecimal(2, new BigDecimal(20));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.addBatch();

            psInsert.setString(1, "james");
            psInsert.setBigDecimal(2, new BigDecimal(30));
            psInsert.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psInsert.addBatch();

            int[] rows = psInsert.executeBatch();

            System.out.println(Arrays.toString(rows));

            // Run list of update commands
            psUpdate.setBigDecimal(1, new BigDecimal(999.99));
            psUpdate.setString(2, "mkyong");
            psUpdate.addBatch();

            psUpdate.setBigDecimal(1, new BigDecimal(888.88));
            psUpdate.setString(2, "james");
            psUpdate.addBatch();

            int[] rows2 = psUpdate.executeBatch();

            System.out.println(Arrays.toString(rows2));

            conn.commit();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static final String SQL_INSERT = "INSERT INTO EMPLOYEE (NAME, SALARY, CREATED_DATE) VALUES (?,?,?)";

    private static final String SQL_UPDATE = "UPDATE EMPLOYEE SET SALARY=? WHERE NAME=?";

    private static final String SQL_CREATE = "CREATE TABLE EMPLOYEE"
            + "("
            + " ID INT NOT NULL AUTO_INCREMENT,"
            + " NAME VARCHAR(100) NOT NULL,"
            + " SALARY DECIMAL(15, 2) NOT NULL,"
            + " CREATED_DATE DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,"
            + " PRIMARY KEY (ID)"
            + ")";

}
