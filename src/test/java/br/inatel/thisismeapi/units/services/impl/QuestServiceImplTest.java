package br.inatel.thisismeapi.units.services.impl;

import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.exceptions.QuestValidationsException;
import br.inatel.thisismeapi.repositories.QuestRepository;
import br.inatel.thisismeapi.services.CharacterService;
import br.inatel.thisismeapi.services.SubQuestsService;
import br.inatel.thisismeapi.services.impl.QuestServiceImpl;
import br.inatel.thisismeapi.units.classesToTest.EmailConstToTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {QuestServiceImpl.class})
class QuestServiceImplTest {

    @Autowired
    private QuestServiceImpl questService;

    @MockBean
    private QuestRepository questRepository;

    @MockBean
    private CharacterService characterService;

    @MockBean
    private SubQuestsService subQuestsService;

    @Test
    void testCreateNewQuestSuccess() {

        // given
        String email = EmailConstToTest.EMAIL_DEFAULT;
        Quest quest = new Quest();
        quest.setName("Quest");
        quest.setStartDate(LocalDate.now());
        quest.setEndDate(LocalDate.now().plusDays(1));

        Character character = new Character();
        character.setEmail(email);

        // when
        when(characterService.findCharacterByEmail(email)).thenReturn(character);
        when(questRepository.save(quest)).thenReturn(quest);
        when(subQuestsService.createSubQuestByQuest(quest, email)).thenReturn(new ArrayList<>());
        when(questRepository.save(quest)).thenReturn(quest);
        when(characterService.updateCharacter(character)).thenReturn(character);

        Quest result = questService.createNewQuest(quest, email);

        // then
        assertEquals(email, result.getEmail());
        assertEquals("Quest", result.getName());
        assertEquals(0, result.getTotal());
    }

    @Test
    void testCreateNewQuestThrowExceptionWhenStartDateLessThanEndDate() {

        Quest quest = new Quest();
        quest.setStartDate(LocalDate.now());
        quest.setEndDate(LocalDate.now().minusDays(1));


        QuestValidationsException exception = assertThrows(QuestValidationsException.class, () -> {
            questService.createNewQuest(quest, EmailConstToTest.EMAIL_DEFAULT);
        });

        assertEquals("Data de inicio precisa ser maior que a data final!", exception.getMessage());
    }

    @Test
    void testCreateNewQuestThrowExceptionWhenStartDateLessThanCurrentDate() {

        Quest quest = new Quest();
        quest.setStartDate(LocalDate.now().minusDays(10));


        QuestValidationsException exception = assertThrows(QuestValidationsException.class, () -> {
            questService.createNewQuest(quest, EmailConstToTest.EMAIL_DEFAULT);
        });

        assertEquals("Data de inicio precisa ser maior ou igual a data do dia atual!", exception.getMessage());
    }

    @Test
    void testGetQuestTodaySuccess() {

        List<Quest> questListExpected = new ArrayList<>();
        questListExpected.add(new Quest());
        questListExpected.add(new Quest());

        when(questRepository.findAllQuestsByDate(any(), any())).thenReturn(questListExpected);

        List<Quest> questListActual = questService.getQuestToday(EmailConstToTest.EMAIL_DEFAULT);

        assertEquals(questListExpected, questListActual);
    }
}
