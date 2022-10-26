package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.models.Card;
import br.inatel.thisismeapi.models.Day;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.Quest;
import br.inatel.thisismeapi.repositories.CharacterRepository;
import br.inatel.thisismeapi.repositories.QuestRepository;
import br.inatel.thisismeapi.services.QuestService;
import br.inatel.thisismeapi.utils.WeekUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        quest.setEmail(email);
        Quest saved = questRepository.save(quest);

        character.getQuests().add(saved);
        characterRepository.save(character);
        return saved;
    }

    @Override
    public List<Quest> getAllQuest(String email) {
        Character character = userService.findCharacterByEmail(email);

        return character.getQuests();
    }

    @Override
    public List<Quest> getQuestToday(String email) {

        return questRepository.findAllQuestsOfTheDay(email, LocalDate.now(), DayOfWeek.from(LocalDate.now()));
    }

    @Override
    public List<Quest> getQuestWeek(String email) {

        return questRepository.findAllQuestsWeek(email, WeekUtils.getActualSundayByDate(LocalDate.now()));
    }

    @Override
    public List<Quest> getQuestNextWeek(String email) {

        return questRepository.findAllQuestsWeek(email, WeekUtils.getNextSundayByDate(LocalDate.now()));
    }

    public List<Card> getCardsTodayByQuestList(List<Quest> quests) {
        List<Card> cards = new ArrayList<>();

        quests.forEach(quest -> {
            Card card = new Card();
            card.setQuestId(quest.getQuestId());
            card.setName(quest.getName());
            card.setSkill(quest.getSkill());
            Day day = quest.getDayByDayOfWeek(DayOfWeek.from(LocalDate.now()));
            card.setStartTime(day.getStartTime());
            card.setEndTime(day.getEndTime());
            card.setDuration(day.getIntervalInMin());
            card.setXp(day.calculateXp());
            cards.add(card);
        });

        return cards;
    }

    public List<Card> getCardsWeekByQuestList(List<Quest> quests) {
        List<Card> cards = new ArrayList<>();

        quests.forEach(quest -> {
            List<Day> week = quest.getWeek();

            Collections.sort(week, new Comparator<Day>() {
                @Override
                public int compare(Day day1, Day day2) {
                    if (day1.getDayOfWeek() == DayOfWeek.SUNDAY)
                        return -1;
                    if (day2.getDayOfWeek() == DayOfWeek.SUNDAY)
                        return 1;

                    return day1.getDayOfWeek().compareTo(day2.getDayOfWeek());
                }
            });

            // Se não é a ultima semana adiciona tudo
            if (!WeekUtils.verifyIsLastWeek(quest.getEndDate())) {
                quest.getWeek().forEach(day -> {
                    cards.add(createInstanceOfCardByQuestAndDay(quest, day));
                });

            } else { // Se é ultima semana adiciona apenas até o dia da semana do endDate
                for (int i = 0; i < week.size(); i++) {
                    Day day = week.get(i);
                    if (day.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        cards.add(createInstanceOfCardByQuestAndDay(quest, day));
                    } else {
                        if (day.getDayOfWeek().getValue() > DayOfWeek.from(quest.getEndDate()).getValue()) {
                            break;
                        }
                        cards.add(createInstanceOfCardByQuestAndDay(quest, day));
                    }
                }
            }
        });

        return cards;
    }

    private Card createInstanceOfCardByQuestAndDay(Quest quest, Day day) {
        Card card = new Card();
        card.setQuestId(quest.getQuestId());
        card.setName(quest.getName());
        card.setSkill(quest.getSkill());
        card.setStartTime(day.getStartTime());
        card.setEndTime(day.getEndTime());
        card.setDuration(day.getIntervalInMin());
        card.setXp(day.calculateXp());
        return card;
    }

    private void sortWeekByDayOfWeek(List<Day> week) {
        Collections.sort(week, new Comparator<Day>() {
            @Override
            public int compare(Day day1, Day day2) {
                if (day1.getDayOfWeek() == DayOfWeek.SUNDAY)
                    return -1;
                if (day2.getDayOfWeek() == DayOfWeek.SUNDAY)
                    return 1;

                return day1.getDayOfWeek().compareTo(day2.getDayOfWeek());
            }
        });
    }
}
