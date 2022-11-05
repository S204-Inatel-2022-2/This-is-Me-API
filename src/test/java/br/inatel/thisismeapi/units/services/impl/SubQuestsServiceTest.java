package br.inatel.thisismeapi.units.services.impl;

import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.entities.SubQuest;
import br.inatel.thisismeapi.enums.DayOfWeekCustom;
import br.inatel.thisismeapi.enums.QuestStatus;
import br.inatel.thisismeapi.models.Day;
import br.inatel.thisismeapi.services.SubQuestsService;
import br.inatel.thisismeapi.units.classesToTest.EmailConstToTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {SubQuestsService.class})
public class SubQuestsServiceTest {

    @Test
    void testCreateSubQuestByQuestSuccess(){

//        List<Day> week = new ArrayList<>();
//        Day day = new Day();
//        day.setDayOfWeekCustom(DayOfWeekCustom.FRIDAY);
//        day.setStartTime("10:00");
//        day.setEndTime("11:00");
//        week.add(day);
//        Quest quest = new Quest();

    }

    private Quest getInstanceOfQuest() {

        Quest quest = new Quest();
        quest.setName("Quest");
        quest.setStartDate(LocalDate.now());
        quest.setStartDate(LocalDate.now().plusDays(1));
        quest.setEmail(EmailConstToTest.EMAIL_DEFAULT);
        quest.setHexColor("#000000");
        quest.setDesc("desc");
        quest.setStatus(QuestStatus.IN_PROGRESS);

        return quest;
    }
}
