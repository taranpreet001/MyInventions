package com.example.assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/covid19Details";
    private static final String USER = "root";
    private static final String PASS = "taran@1211";
    public Connection connect() {
        try {
        return DriverManager.getConnection(URL, USER, PASS);
        }
        catch (SQLException e) {
        throw new RuntimeException("Error connecting to the database", e); }
    } }