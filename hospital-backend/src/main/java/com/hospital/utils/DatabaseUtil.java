package com.hospital.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String DB_URL = "jdbc:mariadb://localhost:3307/hospital_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "u59tAt80Aa"; // or your XAMPP password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
