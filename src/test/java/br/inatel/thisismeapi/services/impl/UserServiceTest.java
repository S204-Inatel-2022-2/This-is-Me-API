package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.controllers.exceptions.PasswordVerifyIsNotEqualException;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("dev")
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testCreateNewAccountSuccess() {
        // given
        String email = "test@email.com";
        String password = PasswordConst.PASSWORD_MIN_LENGHT;
        User user = new User(email, password);

        // when
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User expected = userService.createNewAccount(email, password, password);

        // then
        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getPassword(), user.getPassword());
        assertEquals(expected, user);
    }

    @Test
    public void testCreateNewAccountSuccessWithMaxCharacters() {
        String email = EmailConst.EMAIL_MAX_LENGHT;
        String password = "12345";
        User user = new User(email, password);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User expected = userService.createNewAccount(email, password, password);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getPassword(), user.getPassword());
        assertEquals(expected, user);
    }

    @Test
    public void testCreateNewAccountSuccessWithMinimalEmail() {
        String email = "t@t.t";
        String password = "12345";
        User user = new User(email, password);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User expected = userService.createNewAccount(email, password, password);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getPassword(), user.getPassword());
        assertEquals(expected, user);
    }

    @Test
    public void testCreateNewAccountWithEmailNull() {
        String email = null;
        String password = "12345";
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountWithEmailEmpty() {
        String email = "";
        String password = "12345";
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountWithInvalidEmail() {
        String email = "test";
        String password = "12345";
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountWithInvalidEmailWithoutDotCom() {
        String email = "test@email";
        String password = "12345";
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountWithInvalidEmailWithAtSingOnly() {
        String email = "test@";
        String password = "12345";
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountWithInvalidEmailWithoutFirstPart() {
        String email = "@email.com";
        String password = "12345";
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountWithInvalidEmailWithMoreMaxCharacters() {
        String email = EmailConst.EMAIL_WITH_MORE_MAX_LENGHT;
        String password = "12345";
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountWithPasswordNull() {
        String email = "test@email.com";
        String password = null;
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountWithPasswordEmpty() {
        String email = "test@email.com";
        String password = "";
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountSuccessPasswordWithTheMaxNumberOfCharacters() {
        String email = "test@email.com";
        String password = PasswordConst.PASSWORD_MAX_LENGHT;
        User user = new User(email, password);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        User expected = userService.createNewAccount(email, password, password);

        Mockito.verify(userRepository, Mockito.times(1)).save(user);
        assertEquals(expected.getEmail(), user.getEmail());
        assertEquals(expected.getPassword(), user.getPassword());
        assertEquals(expected, user);
    }

    @Test
    public void testCreateNewAccountPasswordWithMoreThanTheMaxNumberOfCharacters() {
        String email = "test@email.com";
        String password = PasswordConst.PASSWORD_WITH_MORE_MAX_LENGHT;
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void testCreateNewAccountPasswordWithLessThanTheMinNumberOfCharacters() {
        String email = "test@email.com";
        String password = PasswordConst.PASSWORD_WITH_LESS_MIN_LENGHT;
        User user = new User(email, password);

        Mockito.when(userRepository.save(user)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> {
            userService.createNewAccount(email, password, password);
        });
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }


    @Test
    public void testCreateNewAccountThrowExceptionWhenVerifyPasswordIsDifferent() {
        String email = "test@email.com";
        String password = "12345";
        String verifyPassword = "123456";
        User user = new User(email, password);

        PasswordVerifyIsNotEqualException thrown = assertThrows(PasswordVerifyIsNotEqualException.class, () -> {
            userService.createNewAccount(email, password, verifyPassword);
        });

        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(User.class));
        assertEquals("As Senhas não coincidem!", thrown.getMessage());
    }

    @Test
    public void testCreateNewAccountThrowExceptionWhenVerifyPasswordIsNull() {
        String email = "test@email.com";
        String password = "12345";
        String verifyPassword = null;
        User user = new User(email, password);

        PasswordVerifyIsNotEqualException thrown = assertThrows(PasswordVerifyIsNotEqualException.class, () -> {
            userService.createNewAccount(email, password, verifyPassword);
        });

        Mockito.verify(userRepository, Mockito.times(0)).save(Mockito.any(User.class));
        assertEquals("As Senhas não coincidem!", thrown.getMessage());
    }

}
