package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.entities.SubQuest;
import br.inatel.thisismeapi.enums.QuestStatus;
import br.inatel.thisismeapi.exceptions.OnCreateDataException;
import br.inatel.thisismeapi.exceptions.QuestValidationsException;
import br.inatel.thisismeapi.repositories.QuestRepository;
import br.inatel.thisismeapi.services.CharacterService;
import br.inatel.thisismeapi.services.QuestService;
import br.inatel.thisismeapi.services.SubQuestsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class QuestServiceImpl implements QuestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestServiceImpl.class);

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private SubQuestsService subQuestsService;


    @Override
    @Transactional
    public Quest createNewQuest(Quest quest, String email) {

        LOGGER.info("m=createNewQuest, email={}, questName={}", email, quest.getName());
        this.validateQuest(quest);

        Character character = characterService.findCharacterByEmail(email);
        quest.setEmail(email);
        quest.setStatus(QuestStatus.IN_PROGRESS);
        Quest savedQuest = questRepository.save(quest);

        List<SubQuest> subQuestList = subQuestsService.createSubQuestByQuest(savedQuest, email);

        quest.setFinalized(0L);
        quest.setTotal((long) subQuestList.size());

        savedQuest = questRepository.save(quest);
        character.getQuests().add(savedQuest);
        characterService.updateCharacter(character);
        return savedQuest;
    }

    @Override
    public List<Quest> getQuestToday(String email) {

        LOGGER.info("m=getQuestToday, email={}", email);
        return questRepository.findAllQuestsByDate(email, LocalDate.now());
    }

    private Boolean validateQuest(Quest quest) {

        LOGGER.info("m=validateQuest");


        if (quest.getName().isBlank())
            throw new QuestValidationsException("Nome da quest não pode ser deixado em branco!");

        if (quest.getStartDate() == null || quest.getEndDate() == null)
            throw new QuestValidationsException("Periodo não pode ser nulo!");

        if (quest.getHexColor().isBlank())
            throw new QuestValidationsException("Cor não pode ser nula!");

        if (quest.getStartDate().isBefore(LocalDate.now()))
            throw new QuestValidationsException("Data de inicio precisa ser maior ou igual a data do dia atual!");

        if (quest.getStartDate().isAfter(quest.getEndDate()))
            throw new QuestValidationsException("Data de inicio precisa ser maior que a data final!");

        if (quest.getWeek().isEmpty())
            throw new QuestValidationsException("Não pode criar uma tarefa com a semana vazia, por favor, selecione pelo menos um dia da semana!");

        return true;
    }
}
