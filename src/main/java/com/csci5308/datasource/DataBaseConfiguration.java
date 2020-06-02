package com.csci5308.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataBaseConfiguration {

    Connection openConnection() throws SQLException;

    void closeConnection() throws SQLException;
}
