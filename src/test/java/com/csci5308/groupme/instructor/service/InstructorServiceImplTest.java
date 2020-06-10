package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.instructor.dao.InstructorDAOImpl;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class InstructorServiceImplTest {

    @Mock
    InstructorDAOImpl instructorDAO;

    @InjectMocks
    InstructorServiceImpl instructorService;

    private Reader reader;

    @Test
    public void readAllTest() {
        try {
            reader = new BufferedReader(new FileReader("src/test/resources/validrecords.csv"));
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                assertEquals(4, record.length);
            }

            reader = new BufferedReader(new FileReader("src/test/resources/invalidrecords.csv"));
            csvReader = new CSVReader(reader);
            records = csvReader.readAll();
            for (String[] record : records) {
                assertNotEquals(4, record.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
