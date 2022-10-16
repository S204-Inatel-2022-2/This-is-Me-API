package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.wrapper.CreateUserContext;
import br.inatel.thisismeapi.controllers.wrapper.ResetPasswordContext;
import br.inatel.thisismeapi.controllers.wrapper.VerifyResetContext;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.entities.dtos.CharacterBasicInfosDTO;
import br.inatel.thisismeapi.services.UserService;
import br.inatel.thisismeapi.services.exceptions.TokenInvalidException;
import br.inatel.thisismeapi.services.impl.MailServiceImpl;
import br.inatel.thisismeapi.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    MailServiceImpl mailService;


    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewAccount(@RequestBody CreateUserContext createUserContext, HttpServletResponse response) throws IOException {

        LOGGER.info("m=createNewAccount, email={}", createUserContext.getUserDtoInput().getEmail());
        User user = new User(
                createUserContext.getUserDtoInput().getEmail(), createUserContext.getUserDtoInput().getPassword());

        user.verifyPassword(createUserContext.getVerifyPassword());

        Character character = new Character(createUserContext.getCharacterName());
        if (createUserContext.getSex() != null)
            character.setSex(createUserContext.getSex());

        userService.createNewAccount(user, character);
        LOGGER.info("m=createNewAccount, status=CREATED");
    }

    @PostMapping("/reset/forgot-password")
    public ResponseEntity<String> forgotPasswordSendEmail(@RequestParam("email") String email) {

        LOGGER.info("m=forgotPassword, email={}", email);
        mailService.sendEmailForgotPassword(email);

        return ResponseEntity.ok().body("Código enviado para seu email: " + email);
    }

    @PostMapping("/reset/verify-code-reset")
    public void verifyCodeReset(@RequestBody VerifyResetContext verifyResetContext, HttpServletResponse response) {

        LOGGER.info("m=verifyCodeReset, email={}", verifyResetContext.getEmail());
        String jwt = mailService.verifyNumberPassword(
                verifyResetContext.getEmail(), verifyResetContext.getNumber());

        Cookie cookie = new Cookie("token_reset", jwt);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @PostMapping("/reset/reset-password")
    public void resetPassword(@RequestBody ResetPasswordContext resetPasswordContext, HttpServletResponse response, HttpServletRequest request) {

        LOGGER.info("m=resetPassword");

        UserUtils.verifyPassword(resetPasswordContext.getPassword(), resetPasswordContext.getPasswordVerify());

        Cookie tokenReset = WebUtils.getCookie(request, "token_reset");

        if (tokenReset == null) {
            throw new TokenInvalidException("Não encontrado Token valido");
        }

        userService.resetPassword(resetPasswordContext.getPassword(), tokenReset.getValue());
    }


    @GetMapping("/helloUser")
    public String helloUser() {
        LOGGER.info("m=helloUser");
        return "Hello User";
    }

}
