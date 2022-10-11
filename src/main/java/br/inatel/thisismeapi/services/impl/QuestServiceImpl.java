package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.Models.Day;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.entities.User;
import br.inatel.thisismeapi.repositories.CharacterRepository;
import br.inatel.thisismeapi.repositories.QuestRepository;
import br.inatel.thisismeapi.repositories.UserRepository;
import br.inatel.thisismeapi.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    private QuestRepository questRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CharacterRepository characterRepository;


    @Override
    public Quest createNewQuest(Quest quest, String email) {
        Character character = userService.findCharacterByEmail(email);

        Quest questSaved = questRepository.save(quest);

        List<Quest> quests = character.getQuests();
        quests.add(questSaved);
        characterRepository.save(character);
        return questSaved;
    }

    @Override
    public List<Quest> getAllQuest(String email) {
        Character character = userService.findCharacterByEmail(email);

        return character.getQuests();
    }

    @Override
    public List<Quest> getQuestToday(String email) {

        Character character = userService.findCharacterByEmail(email);
        List<Quest> quests = character.getQuests();
        List<Quest> todayList = new ArrayList<>();

        quests.forEach(quest -> {
            if(this.verifyTodayQuest(quest))
                todayList.add(quest);
        });

        return todayList;
    }

    private boolean verifyTodayQuest(Quest quest){
        LocalDate today = LocalDate.now();
        if(today.isBefore(quest.getStartDate()) || today.isAfter(quest.getEndDate()))
            return false;

        Day day = quest.getWeek().getDayOfDayOfWeek();

        return day.isActive();
    }
}
