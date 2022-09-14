package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.wrapper.CreateUserContext;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.services.impl.AdminServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    AdminServiceImpl adminService;

    @Value("${PRIVATE.KEY}")
    private String PRIVATE_KEY;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewAccountAdmin(@RequestBody CreateUserContext createUserContext) {

        User user = new User(
                createUserContext.getUserDtoInput().getEmail(), createUserContext.getUserDtoInput().getPassword());

        user.verifyPassword(createUserContext.getVerifyPassword());

        adminService.createNewAccount(user, createUserContext.getCharacterName());
    }


    @GetMapping("/helloAdmin")
    public String helloAdmin() {
        return "Hello Admin";
    }
}
