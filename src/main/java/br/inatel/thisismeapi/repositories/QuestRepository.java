package br.inatel.thisismeapi.repositories;

import br.inatel.thisismeapi.entities.Quest;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface QuestRepository extends MongoRepository<Quest, String> {

    @Aggregation(pipeline = {
            "{'$match': {" +
                    "'email': ?0, " +
                    "'startDate': {$lte: ?1}, " +
                    "'endDate': {$gte: ?1}" +
                    "}}"
    })
    List<Quest> findAllQuestsByDate(String email, LocalDate day);
}
