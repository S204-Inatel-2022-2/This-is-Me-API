package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.wrapper.CreateUserContext;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewAccount(@RequestBody CreateUserContext createUserContext) {
        User user = new User(
                createUserContext.getUserDtoInput().getEmail(), createUserContext.getUserDtoInput().getPassword());

        user.verifyPassword(createUserContext.getVerifyPassword());

        userService.createNewAccount(user);
    }
/*
    @GetMapping
    public @ResponseBody User login(@RequestBody UserDtoInput userDtoInput){
        User user = new User();
        User userLogged = userService.login(user);



        String jwt = JWT.create()
                .withClaim("idUserLogged", user.getId())
                .sign(Algorithm.HMAC256("${KEY}"));
        Cookie cookie = new Cookie("token", jwt);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 30); // 30 segundos
        response.addCookie(cookie);

        return userLogged;
    }

 */
}
