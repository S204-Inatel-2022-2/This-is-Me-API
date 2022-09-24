package br.inatel.thisismeapi.repositories;

import br.inatel.thisismeapi.entities.Character;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CharacterRepository extends MongoRepository<Character, String> {
    Optional<Character> findByCharacterName(String characterName);
}