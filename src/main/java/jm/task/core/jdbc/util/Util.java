package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;


public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/users";
    private static final String user = "root";
    private static final String password = "root1";

    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;

    private Util() {
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

            try {
                conn.close();
            } catch (SQLException ignored) {
            }
            try {
                stmt.close();
            } catch (SQLException ignored) {
            }
            try {
                rs.close();
            } catch (SQLException ignored) {

            }
        }
    }


    public static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
        return conn;
    }


    private static volatile SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {


                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, url);
                settings.put(Environment.USER, user);
                settings.put(Environment.PASS, password);

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  sessionFactory;
    }
}


