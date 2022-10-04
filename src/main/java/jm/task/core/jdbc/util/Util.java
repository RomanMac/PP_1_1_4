package jm.task.core.jdbc.util;

import java.sql.*;



public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/users";
    private static final String user = "root";
    private static final String password = "root1";


    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;

    public static void main(String args[]) {
        String query = "select count(*) from users";

        try {

            conn = DriverManager.getConnection(url, user, password);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Соединение ОК. Данных : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {

            try { conn.close(); } catch(SQLException ignored) { }
            try { stmt.close(); } catch(SQLException ignored) { }
            try { rs.close();
            } catch(SQLException ignored) {

            }
        }
    }

    public static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

}

