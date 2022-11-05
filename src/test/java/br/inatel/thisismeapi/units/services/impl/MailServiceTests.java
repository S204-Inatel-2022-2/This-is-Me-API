package br.inatel.thisismeapi.units.services.impl;


import br.inatel.thisismeapi.services.impl.MailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = {MailServiceImpl.class, SimpleMailMessage.class})
class MailServiceTests {


    @Autowired
    private MailServiceImpl mailService;

    @MockBean
    private JavaMailSender javaMailSender;

    @MockBean
    private SimpleMailMessage simpleMailMessage;

    // TODO tentar novamente Mockar o UserUtils
    @Test
    void testSendEmailWithMessageSuccess() {

        doNothing().when(simpleMailMessage).setTo(anyString());
        doNothing().when(simpleMailMessage).setSubject(anyString());
        doNothing().when(simpleMailMessage).setText(anyString());
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());

        mailService.sendEmailWithMessage("email", "subject", "message");

        verify(javaMailSender).send((SimpleMailMessage) any());
    }
}
