package br.inatel.thisismeapi.controllers;


import br.inatel.thisismeapi.controllers.exceptions.PasswordVerifyIsNotEqualException;
import br.inatel.thisismeapi.controllers.wrapper.CreateUserContext;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.services.impl.UserServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import javax.validation.ConstraintViolationException;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @BeforeEach
    public void  setup(){
        standaloneSetup(this.userController, this.userService);
    }

    @Test
    public void testCreateNewAccountSuccess(){
        User user = new User("test@email.com", "12345");
        String verifyPassword = "12345";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUser(user);
        createUserContext.setVerifyPassword(verifyPassword);


        when(userService.createNewAccount(user.getEmail(), user.getPassword(), verifyPassword)).thenReturn(user);

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
    public void testCreateNewAccountWithEmailInvalid() throws Exception{
        User user = new User("test@ema", "12345");
        String verifyPassword = "12345";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUser(user);
        createUserContext.setVerifyPassword(verifyPassword);

        when(userService.createNewAccount(user.getEmail(), user.getPassword(), verifyPassword)).thenThrow(ConstraintViolationException.class);


        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

    }

    @Test
    public void testCreateNewAccountWithPasswordVerifyDifferent() throws Exception{
        User user = new User("test@ema.com", "12345");
        String verifyPassword = "123456";
        CreateUserContext createUserContext = new CreateUserContext();
        createUserContext.setUser(user);
        createUserContext.setVerifyPassword(verifyPassword);

        when(userService.createNewAccount(user.getEmail(), user.getPassword(), verifyPassword)).thenThrow(PasswordVerifyIsNotEqualException.class);


        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .accept("")
                        .contentType("application/json")
                        .content(createUserContext.toStringJson())
                )
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());

    }
}
