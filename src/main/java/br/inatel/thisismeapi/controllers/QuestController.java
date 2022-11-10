package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.dtos.responses.QuestResponseDTO;
import br.inatel.thisismeapi.entities.Quest;
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

    @PostMapping
    public Quest createNewQuest(@RequestBody Quest quest, Authentication authentication) {

        LOGGER.info("m=createNewQuest, email={}", authentication.getName());
        return questService.createNewQuest(quest, authentication.getName());
    }

    @GetMapping
    public QuestResponseDTO getQuestById(@RequestParam String id, Authentication authentication) {

        LOGGER.info("m=getQuestById, email={}", authentication.getName());
        return new QuestResponseDTO(questService.getQuestById(id, authentication.getName()));
    }

    @Deprecated
    @GetMapping("/today-cards")
    public List<Quest> getDayCards(Authentication authentication) {

        LOGGER.info("m=getDayCards, email={}", authentication.getName());
        List<Quest> quests = questService.getQuestToday(authentication.getName());

        return quests;
    }
}
