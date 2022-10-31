package br.inatel.thisismeapi.services;

import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.models.Card;

import java.util.List;

public interface QuestService {

    Quest createNewQuest(Quest quest, String email);

    List<Quest> getAllQuest(String email);

    List<Quest> getQuestToday(String email);

    List<Quest> getQuestWeek(String email);

    List<Quest> getQuestNextWeek(String email);
    List<Card> getCardsTodayByQuestList(List<Quest> quests);
}
