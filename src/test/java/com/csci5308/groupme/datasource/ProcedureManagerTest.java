package com.csci5308.groupme.datasource;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProcedureManagerTest {

    ProcedureManager procedureManager;

    @Test
    void getStoredProcedureStatementTest() {
        procedureManager = new ProcedureManager();
        assertNotNull(procedureManager.getStoredProcedureStatement("INSERT_STUDENT(?, ?, ?, ?, ?, ?)"));
        assertNotNull(procedureManager.getStoredProcedureStatement("GET_STUDENT(?)"));
    }
}