package com.mkyong.jdbc.misc;

import java.sql.*;

public class PrintAllTables {

    public static void main(String[] argv) {

        System.out.println("PostgreSQL JDBC Connection Testing ~");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find the PostgreSQL JDBC Driver!");
            e.printStackTrace();
            return;
        }

        // default database: postgres
        // JDK 7, auto close connection with try-with-resources
        try (Connection connection =
                     DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres",
                             "postgres", "password")) {


            DatabaseMetaData metaData = connection.getMetaData();

            try (ResultSet rs = metaData.getTables(null, null, "%", null)) {

                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();

                while (rs.next()) {

                    System.out.println("\n----------");
                    System.out.println(rs.getString("TABLE_NAME"));
                    System.out.println("----------");

                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rsMeta.getColumnName(i);
                        System.out.format("%s:%s\n", columnName, rs.getString(i));
                    }

                }
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong!");
            e.printStackTrace();
            return;
        }

    }

}