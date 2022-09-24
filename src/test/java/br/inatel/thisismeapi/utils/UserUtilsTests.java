package br.inatel.thisismeapi.utils;

import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = UserUtils.class)
@ActiveProfiles("dev")
public class UserUtilsTests {


    @Test
    public void testVerifyPasswordSuccess(){
        String pass1 = "123456";
        String pass2 = "123456";

        assertTrue(UserUtils.verifyPassword(pass1, pass2));
    }

    @Test
    public void testVerifyPasswordWhenIsNotEquals(){
        String pass1 = "123456";
        String pass2 = "654321";

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> {
           UserUtils.verifyPassword(pass1, pass2);
        });

        assertEquals("As Senhas não coincidem!", exception.getMessage());
    }
}
