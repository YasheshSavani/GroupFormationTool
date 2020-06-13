package com.csci5308.groupme.instructor.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class OptionTest {

    private String optionTextTest = "Beginner";
    private Integer optionIdTest = 1;

    @Test
    public void getOptionTextTest() {
        Option option = new Option();
        option.setOptionText(optionTextTest);
        assertEquals(optionTextTest, option.getOptionText());
    }

    @Test
    public void setOptionTextTest() {
        Option option = new Option();
        option.setOptionText(optionTextTest);
        assertEquals(optionTextTest, option.getOptionText());
    }

    @Test
    public void getOptionIdTest() {
        Option option = new Option();
        option.setOptionId(optionIdTest);
        assertEquals(optionIdTest, option.getOptionId());
    }

    @Test
    public void setOptionIdTest() {
        Option option = new Option();
        option.setOptionId(optionIdTest);
        assertEquals(optionIdTest, option.getOptionId());
    }
}
