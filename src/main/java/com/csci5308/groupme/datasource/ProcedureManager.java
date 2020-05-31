package com.csci5308.groupme.datasource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ProcedureManager {

    DataBaseConnection databaseConnection;

    public ProcedureManager() {
        databaseConnection = new DataBaseConnectionImpl();
    }

    public CallableStatement getStoredProcedureStatement(String procedure) {
        try{
            Connection connection = databaseConnection.getConnection();
            return connection.prepareCall("{call " + procedure + "}");
        } catch (Exception e){
            return null;
        }
    }

}
