package br.inatel.thisismeapi.security;

import br.inatel.thisismeapi.entities.Roles;
import br.inatel.thisismeapi.entities.dtos.UserDtoInput;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUsernamePasswordAuthenticationFilter.class);

    @Value("${private.key}")
    private String PRIVATE_KEY;

    private final AuthenticationManager authManager;


    public JWTUsernamePasswordAuthenticationFilter(AuthenticationManager authManager) {
        super();
        this.authManager = authManager;
        this.setFilterProcessesUrl("/user/login");

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LOGGER.info("m=attemptAuthentication, status=trying authenticate");
        try {
            UserDtoInput authRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UserDtoInput.class);

            Authentication auth = new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                    authRequest.getPassword());
            LOGGER.info("m=attemptAuthentication, email={}", authRequest.getEmail());
            return authManager.authenticate(auth);
        } catch (Exception exp) {
            LOGGER.error("m=attemptAuthentication, status=fail auth, error={}", exp.getMessage());
            throw new RuntimeException(exp);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        UserPrincipal user = (UserPrincipal) authResult.getPrincipal();
        String email = user.getUsername();
        LOGGER.info("m=successfulAuthentication, username={}", user.getUsername());

        String[] grant_auth = new String[authResult.getAuthorities().size()];
        Object[] grants = authResult.getAuthorities().toArray();

        for (int i = 0; i < authResult.getAuthorities().size(); i++) {
            Roles roles = (Roles) grants[i];
            grant_auth[i] = roles.getRoleName().toString();
        }


        String jwt = JWT.create()
                .withClaim("email", email)
                .withArrayClaim("auths", grant_auth)
                .sign(Algorithm.HMAC256(PRIVATE_KEY));
        Cookie cookie = new Cookie("token", jwt);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60 * 60 * 24 * 30);

        response.addCookie(cookie);

        response.sendRedirect("/user/getCharacter");

        LOGGER.info("m=successfulAuthentication, status=TokenJWTCreated");
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
