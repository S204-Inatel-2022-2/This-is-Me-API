package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.controllers.dtos.responses.CardResponseDTO;
import br.inatel.thisismeapi.entities.SubQuest;
import br.inatel.thisismeapi.services.SubQuestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/weekly-cards")
    public List<CardResponseDTO> getAllSubQuestCurrentWeekAsCards(Authentication authentication) {

        LOGGER.info("m=getAllSubQuestCurrentWeekAsCards, email={}", authentication.getName());
        List<SubQuest> subQuestList = subQuestsService.findAllSubQuestsWeekly(authentication.getName());

        return subQuestList.stream().map(CardResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/next-week-cards")
    public List<CardResponseDTO> getAllSubQuestNextWeekAsCards(Authentication authentication) {

        LOGGER.info("m=getAllSubQuestNextWeekAsCards, email={}", authentication.getName());
        List<SubQuest> subQuestList = subQuestsService.findAllSubQuestsFromNextWeek(authentication.getName());

        return subQuestList.stream().map(CardResponseDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/late-cards")
    public List<CardResponseDTO> getAllSubQuestLateAsCards(Authentication authentication) {

        LOGGER.info("m=getAllSubQuestLateAsCards, email={}", authentication.getName());
        List<SubQuest> subQuestList = subQuestsService.findAllSubQuestsLate(authentication.getName());

        return subQuestList.stream().map(CardResponseDTO::new).collect(Collectors.toList());
    }

    @DeleteMapping("/delete")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteSubQuestById(String subQuestId, Authentication authentication) {

        LOGGER.info("m=deleteSubQuestById, email={}", authentication.getName());
        subQuestsService.deleteSubQuestById(subQuestId);
    }
}
