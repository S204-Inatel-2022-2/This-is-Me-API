package br.inatel.thisismeapi.cucumber.steps;

import br.inatel.thisismeapi.cucumber.config.RestAssuredExtension;
import org.junit.Before;

public class TestInitialize {

    @Before
    public void setup() {
        RestAssuredExtension restAssuredExtension = new RestAssuredExtension();
    }
}
