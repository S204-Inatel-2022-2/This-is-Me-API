package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.exceptions.PasswordVerifyIsNotEqualException;
import br.inatel.thisismeapi.controllers.wrapper.CreateUserContext;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.services.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewAccount(@RequestBody CreateUserContext createUserContext){
        User user = createUserContext.getUser();
        String verifyPassword = createUserContext.getVerifyPassword();

        if(!user.getPassword().equals(verifyPassword))
            throw new PasswordVerifyIsNotEqualException("As Senhas não coincidem!");

        userService.createNewAccount(user.getEmail(), user.getPassword());
    }
}
