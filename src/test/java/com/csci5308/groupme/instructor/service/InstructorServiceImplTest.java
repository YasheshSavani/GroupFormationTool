package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.instructor.dao.InstructorDAOImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class InstructorServiceImplTest {

    @Mock
    InstructorDAOImpl instructorDAO;

    @InjectMocks
    InstructorServiceImpl instructorService;

    @Test
    void findByTAEmailId() throws Exception {
        String userNameTest = "savani";
        String roleNameTest = "ROLE_TA";

        when(instructorDAO.findByTAEmailId(userNameTest, roleNameTest)).thenReturn("TA Assigned");

        String assignmentConfirmation = instructorService.findByTAEmailId(userNameTest, roleNameTest);
        assertEquals("TA Assigned", assignmentConfirmation);
    }



    private Reader reader;

    @Test
    public void readAllTest() {
        try {
            reader = new BufferedReader(new FileReader("src/test/resources/validrecords.csv"));
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> records = csvReader.readAll();
            for(String[] record: records){
                assertEquals(4, record.length);
            }

            reader = new BufferedReader(new FileReader("src/test/resources/invalidrecords.csv"));
            csvReader = new CSVReader(reader);
            records = csvReader.readAll();
            for(String[] record: records){
                assertNotEquals(4, record.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
