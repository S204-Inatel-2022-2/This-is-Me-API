package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.models.Card;
import br.inatel.thisismeapi.services.impl.QuestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/quest")
public class QuestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestController.class);

    @Autowired
    private QuestServiceImpl questService;


    @GetMapping("/today-cards")
    public List<Card> getDayCards(Authentication authentication) {

        LOGGER.info("m=getDayCards, email={}", authentication.getName());
        List<Quest> quests = questService.getQuestToday(authentication.getName());

        return questService.getCardsTodayByQuestList(quests);
    }

    @GetMapping("/week-cards")
    public List<Card> getWeekCards(Authentication authentication) {

        LOGGER.info("m=getWeekCards, email={}", authentication.getName());
        List<Quest> quests = questService.getQuestWeek(authentication.getName());

        return questService.getCardsWeekByQuestList(quests);
    }

    @GetMapping("/next-week-cards")
    public List<Card> getNextWeekCards(Authentication authentication) {

        LOGGER.info("m=getNextWeekCards, email={}", authentication.getName());
        List<Quest> quests = questService.getQuestNextWeek(authentication.getName());

        return questService.getCardsWeekByQuestList(quests);
    }

    @GetMapping("/all")
    public List<Quest> getAllQuest(Authentication authentication) {

        LOGGER.info("m=getQuestOfTheDay, email={}", authentication.getName());

        return questService.getAllQuest(authentication.getName());
    }

    @PostMapping
    public Quest createNewQuest(@RequestBody Quest quest, Authentication authentication) {
        LOGGER.info("m=getQuestOfTheDay, email={}", authentication.getName());
        return questService.createNewQuest(quest, authentication.getName());
    }
}
