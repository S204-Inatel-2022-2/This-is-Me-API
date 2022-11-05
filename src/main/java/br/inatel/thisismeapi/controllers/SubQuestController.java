package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.dtos.responses.CardResponseDTO;
import br.inatel.thisismeapi.entities.SubQuest;
import br.inatel.thisismeapi.services.SubQuestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subQuest")
public class SubQuestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubQuestController.class);
    @Autowired
    private SubQuestsService subQuestsService;

    @GetMapping("/today-cards")
    public List<CardResponseDTO> getAllSubQuestCurrentDayAsCards(Authentication authentication) {

        LOGGER.info("m=getAllSubQuestCurrentDayAsCards, email={}", authentication.getName());
        List<SubQuest> subQuestList = subQuestsService.findAllSubQuestsToday(authentication.getName());

        return subQuestList.stream().map(CardResponseDTO::new).collect(Collectors.toList());
    }
}
