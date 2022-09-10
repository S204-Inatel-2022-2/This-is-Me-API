package br.inatel.thisismeapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
@ActiveProfiles("dev")
class TimeApplicationTests {

    @Autowired
    TimeApplication timeApplication;

    @Test
    void contextLoads() {
    }

    @Test
    public void main() {
        timeApplication.main(new String[]{});
    }

}
