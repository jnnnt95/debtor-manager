package model.IO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    private static final String suffix = "jdbc:sqlite:";
    public static Connection getConnection() {
        try {
            String url = suffix + "Data\\debtor_manager_data.db";
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException("Unable to connect to DB: " + e.getMessage());
        }
    }
}
