package br.inatel.thisismeapi.repositories;

import br.inatel.thisismeapi.entities.SubQuest;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SubQuestRepository extends MongoRepository<SubQuest, String> {

    @Aggregation(pipeline = {
            "{'$match': {" +
                    "'email': ?0, " +
                    "'start': {$gte: ?1}, " +
                    "'end': {$lte: ?2} " +
                    "}}"
    })
    List<SubQuest> findAllSubQuestsByRangeDate(String email, LocalDateTime start, LocalDateTime end);

    @Aggregation(pipeline = {
            "{'$match': {" +
                    "'email': ?0, " +
                    "'end': {$lte: ?1}, " +
                    "'check': false" +
                    "}}"
    })
    List<SubQuest> findAllSubQuestNotCheckUntilDate(String email, LocalDateTime start);
}
