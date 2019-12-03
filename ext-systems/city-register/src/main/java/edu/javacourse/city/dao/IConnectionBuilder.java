package edu.javacourse.city.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionBuilder {
    Connection getConnection() throws SQLException;
}
