package br.inatel.thisismeapi.services;

import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.entities.SubQuest;

import java.util.List;

public interface SubQuestsService {

    List<SubQuest> createSubQuestByQuest(Quest quest, String email);

    List<SubQuest> findAllSubQuestsToday(String email);

    List<SubQuest> findAllSubQuestsWeekly(String email);

    List<SubQuest> findAllSubQuestsFromNextWeek(String email);
}
