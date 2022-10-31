package br.inatel.thisismeapi.units.services.impl;


import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.exceptions.ErrorOnCreateException;
import br.inatel.thisismeapi.exceptions.TokenInvalidException;
import br.inatel.thisismeapi.exceptions.UnregisteredUserException;
import br.inatel.thisismeapi.exceptions.mongo.UniqueViolationConstraintException;
import br.inatel.thisismeapi.repositories.UserRepository;
import br.inatel.thisismeapi.services.CharacterService;
import br.inatel.thisismeapi.services.MailService;
import br.inatel.thisismeapi.services.impl.MailServiceImpl;
import br.inatel.thisismeapi.services.impl.UserServiceImpl;
import br.inatel.thisismeapi.units.classestotest.EmailConstToTest;
import br.inatel.thisismeapi.units.classestotest.PasswordConstToTest;
import br.inatel.thisismeapi.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import javax.mail.internet.MimeMessage;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {MailServiceImpl.class, SimpleMailMessage.class})
class MailServiceTests {


    @Autowired
    private MailServiceImpl mailService;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private SimpleMailMessage simpleMailMessage;

//    @BeforeEach
//    public void setUp(){
//        this.simpleMailMessage = new SimpleMailMessage();
//    }

    // TODO tentar novamente Mockar o UserUtils
    @Test
    void testSendEmailWithMessageSuccess() {

        doNothing().when(simpleMailMessage).setTo(anyString());
        doNothing().when(simpleMailMessage).setSubject(anyString());
        doNothing().when(simpleMailMessage).setText(anyString());
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());

        mailService.sendEmailWithMessage("email","subject", "message");

        verify(javaMailSender).send((SimpleMailMessage) any());
    }
}
