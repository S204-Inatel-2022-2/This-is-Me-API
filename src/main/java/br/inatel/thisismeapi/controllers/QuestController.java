package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.entities.dtos.QuestInputDTO;
import br.inatel.thisismeapi.services.impl.QuestServiceImpl;
import br.inatel.thisismeapi.services.impl.UserServiceImpl;
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
    private UserServiceImpl userService;

    @Autowired
    private QuestServiceImpl questService;

    @GetMapping("/today")
    public List<Quest> getQuestOfTheDay(Authentication authentication){

        LOGGER.info("m=getQuestOfTheDay, email={}", authentication.getName());

        // TODO criar e chamar QuestService para pegar as tarefas do dia

        return questService.getQuestToday(authentication.getName());
    }

    @GetMapping("/all")
    public List<Quest> getAllQuest(Authentication authentication){

        LOGGER.info("m=getQuestOfTheDay, email={}", authentication.getName());

        // TODO criar e chamar QuestService para pegar as tarefas do dia

        return questService.getAllQuest(authentication.getName());
    }

    @PostMapping
    public Quest createNewQuest(@RequestBody QuestInputDTO questInputDTO, Authentication authentication){
        LOGGER.info("m=getQuestOfTheDay, email={}", authentication.getName());
        return questService.createNewQuest(questInputDTO.getQuest(), authentication.getName());
    }
}
