package edu.javacourse.city.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DirectConnectionBuilder implements IConnectionBuilder {
    @Override
    public Connection getConnection() throws SQLException {
               return DriverManager.getConnection("jdbc:postgresql://localhost/city_register",
                "postgres", "Gloezict1");
    }
}
