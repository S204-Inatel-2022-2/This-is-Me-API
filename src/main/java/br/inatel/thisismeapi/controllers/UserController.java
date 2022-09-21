package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.wrapper.CreateUserContext;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewAccount(@RequestBody CreateUserContext createUserContext, HttpServletResponse response) throws IOException {

        LOGGER.info("m=createNewAccount, email={}", createUserContext.getUserDtoInput().getEmail());
        User user = new User(
                createUserContext.getUserDtoInput().getEmail(), createUserContext.getUserDtoInput().getPassword());

        user.verifyPassword(createUserContext.getVerifyPassword());

        userService.createNewAccount(user, createUserContext.getCharacterName());
        LOGGER.info("m=createNewAccount, status=CREATED");
    }

    @GetMapping("/getCharacter")
    public ResponseEntity<Character> getCharacter(Authentication authentication) {

        LOGGER.info("m=getCharacter, email={}", authentication.getName());
        Character character = userService.findCharacterByEmail(authentication.getName());

        return ResponseEntity.ok().body(character);
    }

    @GetMapping("/helloUser")
    public String helloUser() {
        LOGGER.info("m=helloUser");
        return "Hello User";
    }

}
