package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.repositories.UserRepository;
import br.inatel.thisismeapi.services.MailService;
import br.inatel.thisismeapi.services.exceptions.TokenExpiredException;
import br.inatel.thisismeapi.services.exceptions.TokenInvalidException;
import br.inatel.thisismeapi.services.exceptions.UnregisteredUserException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @Value("${private.key.mail}")
    private String PRIVATE_KEY_MAIL;

    @Override
    public void sendEmailForgotPassword(String email){

        Optional<User> userOp = userRepository.findByEmail(email);

        if (userOp.isEmpty())
            throw new UnregisteredUserException("Usúario com email " + email + " não encontrado!");


        Random random = new Random();
        int number = random.nextInt(899_999) + 100_000;

        // TODO: Verificar se já não há um token não expirado e valido

        String jwt = JWT.create()
                .withClaim("email", userOp.get().getEmail())
                .withClaim("number", number)
                .withExpiresAt(Date.from(Instant.now().plusSeconds(86400)))
                .sign(Algorithm.HMAC256(PRIVATE_KEY_MAIL));

        LOGGER.info("m=sendEmailForgotPassword, status=token gerado");

        User user = userOp.get();
        user.setTokenResetPassword(jwt);

        userRepository.save(user);

        var msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("[TiMe] Troca de Senha");
        msg.setText("Solicitação de troca de senha \n\n" +
                "Token de Verificação: " + number + "" +
                "\n\nO token irá expirar em 24 horas.\n" +
                "caso não tenha solicitado a troca de senha, ignore este email." +
                "\n\n\n" +
                "Time Not Working - App This is Me");

        javaMailSender.send(msg);
    }


    @Override
    public String verifyNumberPassword(String email, String number){
        Optional<User> userOp = userRepository.findByEmail(email);

        if (userOp.isEmpty())
            throw new UnregisteredUserException("Usúario com email " + email + " não encontrado!");

        User user = userOp.get();

        String jwt = user.getTokenResetPassword();

        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(PRIVATE_KEY_MAIL))
                    .build()
                    .verify(jwt);

            Integer tokenNumber = decodedJWT.getClaim("number").asInt();

            if(!number.equals(tokenNumber.toString())){
                LOGGER.info("m=verifyNumberPassword,code={}, codeToken={}, msg=codigo incorreto", number, tokenNumber);
                throw new TokenInvalidException("Código de verificação incorreto!");
            }

            LOGGER.info("m=verifyNumberPassword, status=validated token, number={}", tokenNumber);
            return jwt;
        } catch (JWTVerificationException e) {
            LOGGER.info("m=verifyNumberPassword, errorMsg={}", e.getMessage());
            throw new TokenInvalidException(e.getMessage());
        }
    }
}
