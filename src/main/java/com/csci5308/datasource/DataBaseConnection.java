package com.csci5308.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataBaseConnection {

    Connection getConnection() throws SQLException;

    void closeConnection() throws SQLException;
}
