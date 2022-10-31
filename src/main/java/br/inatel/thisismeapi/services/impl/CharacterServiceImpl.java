package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.exceptions.UnregisteredUserException;
import br.inatel.thisismeapi.exceptions.mongo.UniqueViolationConstraintException;
import br.inatel.thisismeapi.repositories.CharacterRepository;
import br.inatel.thisismeapi.services.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterServiceImpl.class);

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public Character findCharacterByEmail(String email) {
        Optional<Character> characterOptional = characterRepository.findCharacterByEmail(email);

        if (characterOptional.isEmpty()) {
            LOGGER.error("m=setClothes, email={}, msg=Personagem não encontrado", email);
            throw new UnregisteredUserException("Personagem com email [" + email + "] não encontrado!");
        }

        return characterOptional.get();
    }

    @Override
    public Character saveNewCharacter(Character character) {
        try {
            return characterRepository.save(character);
        }catch(DuplicateKeyException e){
            throw new UniqueViolationConstraintException("Já Existe uma conta cadastrada com esse e-mail!");
        }
    }

    @Override
    public Character updateCharacter(Character character) {
        if (character.getId() == null)
            throw new UnregisteredUserException("Necessário que o usuário tenha um personagem para utilizá-lo!");
        return characterRepository.save(character);
    }

    @Override
    public Character setClothes(String email, Long number) {

        Character character = this.findCharacterByEmail(email);

        character.setClothes(number);
        return characterRepository.save(character);
    }
}
