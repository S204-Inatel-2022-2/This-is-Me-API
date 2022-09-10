package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.consts.PasswordConst;
import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, SecurityAutoConfiguration.class})
public class UserServiceTests {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;

    @Test
    public void testCreateNewAccountSuccess() {
        // given
        String email = "test@email.com";
        String password = PasswordConst.PASSWORD_MIN_LENGHT_5;
        User user = new User(email, password);

        // when
        when(encoder.encode(password)).thenReturn(password);
        when(userRepository.save(any(User.class))).thenReturn(user);
        User expected = userService.createNewAccount(user);

        // then
        verify(userRepository, times(1)).save(user);
        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getPassword(), user.getPassword());
        assertEquals(expected, user);
        assertTrue(expected.equals(user));
    }

    @Test
    public void testCreateNewAccountWithPasswordWithTheMaxNumberOfCharacters() {
        String email = "test@email.com";
        String password = PasswordConst.PASSWORD_WITH_MORE_MAX_LENGHT_31;
        User user = new User(email, "0123456789012345678901234567890");

        ConstraintViolationException thrown = assertThrows(ConstraintViolationException.class, () -> {
            userService.createNewAccount(user);
        });

        // then
        assertEquals("Senha deve conter no minimo 5 e no maximo 30 digitos!", thrown.getMessage());
    }

    @Test
    public void testCreateNewAccountWithPasswordWithLessThanMinNumberOfCharacters() {
        // given
        String email = "test@email.com";
        String password = PasswordConst.PASSWORD_WITH_LESS_MIN_LENGHT_4;
        User user = new User(email, password);

        // when
        ConstraintViolationException thrown = assertThrows(ConstraintViolationException.class, () -> {
            userService.createNewAccount(user);
        });

        // then
        assertEquals("Senha deve conter no minimo 5 e no maximo 30 digitos!", thrown.getMessage());
    }
}
