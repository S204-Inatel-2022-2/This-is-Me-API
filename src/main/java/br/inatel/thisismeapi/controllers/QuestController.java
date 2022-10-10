package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.services.UserService;
import br.inatel.thisismeapi.services.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/quest")
public class QuestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestController.class);
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/today")
    public List<Quest> getQuestOfTheDay(Authentication authentication){

        LOGGER.info("m=getQuestOfTheDay, email={}", authentication.getName());
        Character character = userService.findCharacterByEmail(authentication.getName());

        // TODO criar e chamar QuestService para pegar as tarefas do dia
        return new ArrayList<>();
    }
}
