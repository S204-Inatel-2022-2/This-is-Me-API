package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.wrapper.CreateUserContext;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.entities.entitiesDTO.UserDtoInput;
import br.inatel.thisismeapi.entities.entitiesDTO.UserDtoOutput;
import br.inatel.thisismeapi.services.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Value("${PRIVATE.KEY}")
    private String PRIVATE_KEY;

    @PostMapping("/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createNewAccount(@RequestBody CreateUserContext createUserContext) {

        User user = new User(
                createUserContext.getUserDtoInput().getEmail(), createUserContext.getUserDtoInput().getPassword());

        user.verifyPassword(createUserContext.getVerifyPassword());

        userService.createNewAccount(user, createUserContext.getCharacterName());
    }



    @PostMapping("/login")
    public ResponseEntity<UserDtoOutput> login(@RequestBody UserDtoInput userDtoInput, HttpServletResponse response, HttpServletRequest request){
        User user = new User(userDtoInput.getEmail(), userDtoInput.getPassword());
        String id = userService.login(user);

        UserDtoOutput userDtoOutput = new UserDtoOutput(id);

        String jwt;
        Cookie token = WebUtils.getCookie(request, "token");

        if (token == null){
            jwt = JWT.create()
                    .withClaim("id", id)
                    .sign(Algorithm.HMAC256(PRIVATE_KEY));
            Cookie cookie = new Cookie("token", jwt);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(60 * 60 * 24 * 30);

            response.addCookie(cookie);
        }else {
            try {
                jwt = token.getValue();
                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(PRIVATE_KEY))
                                            .build()
                                            .verify(jwt);

                String idLogged = decodedJWT.getClaim("id").toString();
                request.setAttribute("id", idLogged);
            }catch (JWTVerificationException e){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserDtoOutput());
            }
        }



        //return ResponseEntity.ok().header(jwt).body(userDtoOutput);
        return ResponseEntity.ok().body(userDtoOutput);
    }

}
