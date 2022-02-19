package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static final String DRIVE_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/labs_db";
    private static final String PASSWORD = "password";
    private static final String USERNAME = "root";

    private DataSource() {

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVE_CLASS_NAME);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
