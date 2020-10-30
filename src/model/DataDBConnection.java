package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataDBConnection {
    private static Connection connection;
    private static final String path = "Data\\debtor_manager_data.db";
    
    private static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection == null) {
            connect();
        }
        return connection;
    }
    
}
