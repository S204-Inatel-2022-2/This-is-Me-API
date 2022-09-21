package br.inatel.thisismeapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TimeApplicationTests.class)
@ActiveProfiles("dev")
class TimeApplicationTests {

    @Test
    void contextLoads() {
    }

}
