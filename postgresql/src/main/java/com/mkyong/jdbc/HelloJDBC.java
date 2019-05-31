package com.mkyong.jdbc;

import java.sql.*;

public class HelloJDBC {

    public static void main(String[] args) {

        System.out.println("PostgreSQL JDBC Connection Testing ~");

        try {

            // https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html#package.description
            // auto java.sql.Driver discovery -- no longer need to load a java.sql.Driver class via Class.forName

            // loads JDBC driver
            Class.forName("org.postgresql.Driver");

            // auto close connection
            try (Connection conn =
                         DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test",
                                 "postgres", "password")) {


                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("Select * from \"ORDER\"");

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
                    System.out.println("\n----------");
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        System.out.format("%s:%s\n", columnName, resultSet.getString(i));
                    }

                }

            }


        } catch (Exception e) {
            System.err.println("Something went wrong!");
            e.printStackTrace();
            return;
        }

    }


}
