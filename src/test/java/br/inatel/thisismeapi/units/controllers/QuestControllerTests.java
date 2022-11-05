package br.inatel.thisismeapi.units.controllers;


import br.inatel.thisismeapi.controllers.QuestController;
import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.services.impl.QuestServiceImpl;
import br.inatel.thisismeapi.units.classesToTest.EmailConstToTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {QuestController.class})
class QuestControllerTests {

    @Autowired
    private QuestController questController;

    @MockBean
    private QuestServiceImpl questService;

    @Mock
    private Authentication authentication;

    @Test
    void testCreateNewQuestSuccess() {

        Quest expectedQuest = new Quest();
        expectedQuest.setEmail(EmailConstToTest.EMAIL_DEFAULT);
        expectedQuest.setName("QuestTest");

        when(authentication.getName()).thenReturn(EmailConstToTest.EMAIL_DEFAULT);
        Mockito.when(questService.createNewQuest(any(), anyString())).thenReturn(expectedQuest);

        Quest actual = questController.createNewQuest(expectedQuest, authentication);

        assertEquals(expectedQuest, actual);
    }

    @Test
    void testGetDayCardsSuccessWithTwoQuest() {

        Quest expectedQuest = new Quest();
        expectedQuest.setEmail(EmailConstToTest.EMAIL_DEFAULT);
        expectedQuest.setName("QuestTest");

        List<Quest> list = new ArrayList<>();
        list.add(expectedQuest);
        list.add(expectedQuest);

        when(authentication.getName()).thenReturn(EmailConstToTest.EMAIL_DEFAULT);
        Mockito.when(questService.getQuestToday(anyString())).thenReturn(list);

        List<Quest> questListActual = questController.getDayCards(authentication);

        assertEquals(list, questListActual);
    }
}
