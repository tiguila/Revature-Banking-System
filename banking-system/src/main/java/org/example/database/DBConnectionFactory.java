package org.example.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {

    private static final String URL = System.getenv("DB_URL");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    static {
        try{
            String driver = System.getenv().getOrDefault("DB_DRIVER", "org.postgresql.Driver");
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            throw new ExceptionInInitializerError("PostgreSQL Driver not found: " + e.getMessage());
        }
    }

    private DBConnectionFactory() {}

    public static Connection getConnection() throws SQLException {
        if (URL == null || USER == null || PASSWORD == null) {
            throw new IllegalStateException("DB_URL, DB_USER, and DB_PASSWORD env var must be set");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }




}
