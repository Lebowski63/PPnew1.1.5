package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.Driver;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/bd";
    private static final String USERNAME = "Timur";
    private static final String PASSWORD = "0000";

  /*  public static Connection getConnection() {
        Connection connection = null;
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к БД");
        }
        return connection;
    }*/

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties setting = new Properties();
                setting.put(Environment.DRIVER, DRIVER);
                setting.put(Environment.URL, URL);
                setting.put(Environment.USER, USERNAME);
                setting.put(Environment.PASS, PASSWORD);
                configuration.setProperties(setting);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                System.out.println("Проблема с созданием сессии");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}