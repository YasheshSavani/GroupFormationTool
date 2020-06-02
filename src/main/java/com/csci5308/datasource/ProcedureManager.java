package com.csci5308.datasource;

import java.sql.CallableStatement;
import java.sql.Connection;

public class ProcedureManager {

    DataBaseConfiguration databaseConfiguration;
    Connection connection;

    public ProcedureManager() {
        databaseConfiguration = new DataBaseConfigurationImpl();
    }

    public CallableStatement getStoredProcedureStatement(String procedure) {
        try {
            connection = databaseConfiguration.openConnection();
            return connection.prepareCall("{call " + procedure + "}");
        } catch (Exception e) {
            return null;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
