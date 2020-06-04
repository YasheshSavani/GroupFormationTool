package com.csci5308.groupme.instructor.service;

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
