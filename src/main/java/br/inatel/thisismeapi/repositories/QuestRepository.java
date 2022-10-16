package br.inatel.thisismeapi.repositories;

import br.inatel.thisismeapi.entities.Quest;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface QuestRepository extends MongoRepository<Quest, String> {


//    @Aggregation(pipeline = {
//            "{'$match': {$and: [{'email': ?0}, {'startDate': {$lte: ?1}}, {'endDate': {$gte: ?1}}]}}"
//    })
//    List<Quest> findAllQuestsOfTheDay(String email, LocalDate day);

    @Aggregation(pipeline = {
            "{'$match': {$and: [" +
                    "{'email': ?0}, " +
                    "{'startDate': " +
                    "{$lte: ?1}}, " +
                    "{'endDate': {$gte: ?1}}, " +
                    "{'week': {$elemMatch: {'dayOfWeek': ?2}}}]}}"
    })
    List<Quest> findAllQuestsOfTheDay(String email, LocalDate day, DayOfWeek dayOfWeek);

    @Aggregation(pipeline = {
            "{'$match': {$and: [" +
                    "{'email': ?0}, " +
                    "{'startDate': " +
                    "{$lte: ?1}}, " +
                    "{'endDate': {$gte: ?1}}" +
                    "]}}"
    })
    List<Quest> findAllQuestsWeek(String email, LocalDate day);

}
