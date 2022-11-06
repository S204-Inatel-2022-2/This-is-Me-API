package br.inatel.thisismeapi.units.services.impl;

import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.entities.SubQuest;
import br.inatel.thisismeapi.enums.DayOfWeekCustom;
import br.inatel.thisismeapi.enums.QuestStatus;
import br.inatel.thisismeapi.exceptions.ErrorOnCreateException;
import br.inatel.thisismeapi.models.Day;
import br.inatel.thisismeapi.repositories.SubQuestRepository;
import br.inatel.thisismeapi.services.impl.SubQuestsServiceImpl;
import br.inatel.thisismeapi.units.classesToTest.EmailConstToTest;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = {SubQuestsServiceImpl.class})
public class SubQuestsServiceTest {

    @Autowired
    private SubQuestsServiceImpl subQuestsService;

    @MockBean
    private SubQuestRepository subQuestRepository;

    @Test
    void testCreateSubQuestByQuestSuccess() {

        LocalDateTime today = LocalDateTime.of(LocalDate.of(2022, 11, 6), LocalTime.of(12, 0));

        Quest quest = this.getInstanceOfQuestWithoutWeek();
        quest.setStartDate(LocalDate.of(2022, 11, 6));
        quest.setEndDate(LocalDate.of(2022, 11, 12));
        quest.setWeek(this.getOneOfEachDayOfTheWeekStartAtTenAndFinishingAtElevenOfMorning());

        try (MockedStatic<LocalDateTime> localDateMockedStatic = Mockito.mockStatic(LocalDateTime.class)) {
            localDateMockedStatic.when(LocalDateTime::now).thenReturn(today);
            localDateMockedStatic.when(() -> LocalDateTime.of(any(LocalDate.class), any(LocalTime.class))).thenCallRealMethod();
            List<SubQuest> subQuestsList = new ArrayList<>();
            subQuestsList.add(new SubQuest());
            subQuestsList.add(new SubQuest());
            Mockito.when(subQuestRepository.saveAll(any())).thenReturn(subQuestsList);
            List<SubQuest> result = this.subQuestsService.createSubQuestByQuest(quest, EmailConstToTest.EMAIL_DEFAULT);

            assertEquals(2, result.size());
        }
    }

    @Test
    void testCreateSubQuestByQuestThrowExceptionWhenWeekFromQuestIsEmpty() {

        LocalDateTime today = LocalDateTime.of(LocalDate.of(2022, 11, 6), LocalTime.of(12, 0));

        Quest quest = this.getInstanceOfQuestWithoutWeek();
        quest.setStartDate(LocalDate.of(2022, 11, 6));
        quest.setEndDate(LocalDate.of(2022, 11, 12));

        ErrorOnCreateException exception = assertThrows(ErrorOnCreateException.class, () -> {
            this.subQuestsService.createSubQuestByQuest(quest, EmailConstToTest.EMAIL_DEFAULT);
        });

        assertEquals("Não pode criar uma tarefa com a semana vazia, por favor, selecione pelo menos um dia da semana!", exception.getMessage());
    }

    @Test
    void testCreateSubQuestByQuestThrowExceptionWhenNoSubQuestCreated() {

        LocalDateTime today = LocalDateTime.of(LocalDate.of(2023, 11, 6), LocalTime.of(12, 0));

        Quest quest = this.getInstanceOfQuestWithoutWeek();
        quest.setStartDate(LocalDate.of(2022, 11, 6));
        quest.setEndDate(LocalDate.of(2022, 11, 12));
        quest.setWeek(this.getOneOfEachDayOfTheWeekStartAtTenAndFinishingAtElevenOfMorning());

        try (MockedStatic<LocalDateTime> localDateMockedStatic = Mockito.mockStatic(LocalDateTime.class)) {
            localDateMockedStatic.when(LocalDateTime::now).thenReturn(today);
            localDateMockedStatic.when(() -> LocalDateTime.of(any(LocalDate.class), any(LocalTime.class))).thenCallRealMethod();
            List<SubQuest> subQuestsList = new ArrayList<>();
            subQuestsList.add(new SubQuest());
            subQuestsList.add(new SubQuest());
            Mockito.when(subQuestRepository.saveAll(any())).thenReturn(subQuestsList);
            ErrorOnCreateException exception = assertThrows(ErrorOnCreateException.class, () -> {
                this.subQuestsService.createSubQuestByQuest(quest, EmailConstToTest.EMAIL_DEFAULT);
            });

            assertEquals("Nenhuma tarefa criada com o periodo e os dias da semana selecionados!", exception.getMessage());
        }
    }

    @Test
    void testFindAllSubQuestsTodaySuccess() {

        LocalDate today = LocalDate.of(2022, 11, 6);


        try (MockedStatic<LocalDate> localDateMockedStatic = Mockito.mockStatic(LocalDate.class)) {

            localDateMockedStatic.when(LocalDate::now).thenReturn(today);
            List<SubQuest> subQuestsList = new ArrayList<>();
            subQuestsList.add(new SubQuest());
            subQuestsList.add(new SubQuest());
            Mockito.when(subQuestRepository.findAllSubQuestsByRangeDate(any(), any(), any())).thenReturn(subQuestsList);
            List<SubQuest> result = this.subQuestsService.findAllSubQuestsToday(EmailConstToTest.EMAIL_DEFAULT);

            assertEquals(2, result.size());
        }
    }

    private Quest getInstanceOfQuestWithoutWeek() {

        Quest quest = new Quest();
        quest.setName("Quest");
        quest.setStartDate(LocalDate.now());
        quest.setStartDate(LocalDate.now().plusDays(6));
        quest.setEmail(EmailConstToTest.EMAIL_DEFAULT);
        quest.setHexColor("#000000");
        quest.setDesc("desc");
        quest.setStatus(QuestStatus.IN_PROGRESS);

        return quest;
    }

    private List<Day> getOneOfEachDayOfTheWeekStartAtTenAndFinishingAtElevenOfMorning() {

        List<Day> week = new ArrayList<>();

        for (int i = 1; i <= 7; i++) {
            Day day = new Day();
            day.setDayOfWeekCustom(DayOfWeekCustom.of(i));
            day.setStartTime("10:00");
            day.setEndTime("11:00");
            week.add(day);
        }

        Collections.shuffle(week);
        return week;
    }

    @Test
    void testFindAllSubQuestsWeeklySuccess() {

        LocalDate today = LocalDate.of(2022, 11, 6);

        try (MockedStatic<LocalDate> localDateMockedStatic = Mockito.mockStatic(LocalDate.class)) {

            localDateMockedStatic.when(LocalDate::now).thenReturn(today);
            List<SubQuest> subQuestsList = new ArrayList<>();
            subQuestsList.add(new SubQuest());
            subQuestsList.add(new SubQuest());
            Mockito.when(subQuestRepository.findAllSubQuestsByRangeDate(any(), any(), any())).thenReturn(subQuestsList);
            List<SubQuest> result = this.subQuestsService.findAllSubQuestsWeekly(EmailConstToTest.EMAIL_DEFAULT);

            assertEquals(2, result.size());
        }
    }
}
