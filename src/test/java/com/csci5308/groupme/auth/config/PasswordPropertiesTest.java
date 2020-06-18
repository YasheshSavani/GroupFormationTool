package com.csci5308.groupme.auth.config;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.SystemConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class PasswordPropertiesTest {

    private final Logger logger = (Logger) LoggerFactory.getLogger(PasswordPropertiesTest.class);

    @Test
    public void loadPasswordPropertiesTest() {
        PasswordProperties passwordProperties = SystemConfig.instance().getPasswordProperties();
        logger.info("Invalid chars: {}", passwordProperties.getInvalidChars());
    }

}
