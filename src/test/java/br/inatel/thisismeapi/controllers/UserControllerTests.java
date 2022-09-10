package br.inatel.thisismeapi.controllers;


import br.inatel.thisismeapi.consts.EmailConst;
import br.inatel.thisismeapi.consts.PasswordConst;
import br.inatel.thisismeapi.controllers.exceptions.ConstraintViolationException;
import br.inatel.thisismeapi.controllers.wrapper.CreateUserContext;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.entities.entitiesDTO.UserDtoInput;
import br.inatel.thisismeapi.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Objects;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@WebMvcTest(value = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class UserControllerTests {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @BeforeEach
    public void setup() {
        standaloneSetup(this.userController, this.userService);
    }


    @Test
    public void testCreateNewAccountSuccess() {
        UserDtoInput userDtoInput = new UserDtoInput(EmailConst.EMAIL_MAX_LENGHT_255, "12345");
        String verifyPassword = "12345";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);
        User user = new User(userDtoInput.getEmail(), userDtoInput.getPassword());

        when(userService.createNewAccount(any(User.class))).thenReturn(user);

        given()
                .accept()
                .contentType("application/json")
                .body(createUserContext.toStringJson())
            .when()
                .post("/user")
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }


    @Test
    public void testCreateNewAccountWithEmailInvalid() throws Exception {
        UserDtoInput userDtoInput = new UserDtoInput("test@", "12345");
        String verifyPassword = "12345";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertEquals("Email inválido!", Objects.requireNonNull(result.getResolvedException()).getMessage());
    }

    @Test
    public void testCreateNewAccountWithEmailNull() throws Exception {
        UserDtoInput userDtoInput = new UserDtoInput(null, "12345");
        String verifyPassword = "12345";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertEquals("Email não pode ser nulo!", Objects.requireNonNull(result.getResolvedException()).getMessage());
    }

    @Test
    public void testCreateNewAccountWithEmailWithSpacesOnly() throws Exception {
        UserDtoInput userDtoInput = new UserDtoInput("    ", "12345");
        String verifyPassword = "12345";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertEquals("Email não pode ser deixado em branco!", Objects.requireNonNull(result.getResolvedException()).getMessage());
    }

    @Test
    public void testCreateNewAccountWithEmailWithMoreMaxCharacter() throws Exception {
        UserDtoInput userDtoInput = new UserDtoInput(EmailConst.EMAIL_WITH_MORE_MAX_LENGHT_256, "12345");
        String verifyPassword = "12345";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertEquals("Email não pode ser ter mais de 255 digitos!", Objects.requireNonNull(result.getResolvedException()).getMessage());
    }

    @Test
    public void testCreateNewAccountWithVerifyPasswordDifferent() throws Exception {
        UserDtoInput userDtoInput = new UserDtoInput("test@email.com", "12345");
        String verifyPassword = "123";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertEquals("As Senhas não coincidem!", Objects.requireNonNull(result.getResolvedException()).getMessage());
    }

    @Test
    public void testCreateNewAccountWithPasswordWithLessThanMinNumberOfCharacters() throws Exception {
        UserDtoInput userDtoInput = new UserDtoInput("test@email.com", PasswordConst.PASSWORD_WITH_LESS_MIN_LENGHT_4);
        String verifyPassword = PasswordConst.PASSWORD_WITH_LESS_MIN_LENGHT_4;
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);

        when(userService.createNewAccount(any(User.class))).thenThrow(ConstraintViolationException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testCreateNewAccountWithPasswordWithMoreThanMaxNumberOfCharacters() throws Exception {
        UserDtoInput userDtoInput = new UserDtoInput("test@email.com", PasswordConst.PASSWORD_WITH_MORE_MAX_LENGHT_31);
        String verifyPassword = PasswordConst.PASSWORD_WITH_MORE_MAX_LENGHT_31;
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);

        when(userService.createNewAccount(any(User.class))).thenThrow(ConstraintViolationException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testCreateNewAccountWithPasswordNull() throws Exception {
        UserDtoInput userDtoInput = new UserDtoInput("test@email.com", null);
        String verifyPassword = "12345";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertEquals("Senha não pode ser nula!", Objects.requireNonNull(result.getResolvedException()).getMessage());
    }

    @Test
    public void testCreateNewAccountWithPasswordWithSpacesOnly() throws Exception {
        UserDtoInput userDtoInput = new UserDtoInput("test@email.com", "    ");
        String verifyPassword = "12345";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUserDtoInput(userDtoInput);
        createUserContext.setVerifyPassword(verifyPassword);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("application/json")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

        assertEquals("Senha não pode ser deixada em branco!", Objects.requireNonNull(result.getResolvedException()).getMessage());
    }
}
