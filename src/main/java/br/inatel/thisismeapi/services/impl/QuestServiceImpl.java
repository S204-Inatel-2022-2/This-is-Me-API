package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.repositories.QuestRepository;
import br.inatel.thisismeapi.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    QuestRepository questRepository;


    @Override
    public Quest CreateNewQuest(Quest quest) {
        questRepository.save(quest);
        return null;
    }
}
