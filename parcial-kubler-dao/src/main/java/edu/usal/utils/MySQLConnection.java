package edu.usal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection {

    private static MySQLConnection instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    private MySQLConnection() throws SQLException {
        try {
            Properties properties = new Properties();
            try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new RuntimeException("Unable to find config.properties");
                }
                properties.load(input);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            this.url = properties.getProperty("db.url");
            this.username = properties.getProperty("db.username");
            this.password = properties.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Connection to database failed.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static MySQLConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new MySQLConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new MySQLConnection();
        }

        return instance;
    }
}
