package br.inatel.thisismeapi.services.impl;

import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.repositories.CharacterRepository;
import br.inatel.thisismeapi.services.CharacterService;
import br.inatel.thisismeapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    UserService userService;

    @Override
    public Character setClothes(String email, Long number) {
        Character character = userService.findCharacterByEmail(email);
        character.setClothes(number);
        return characterRepository.save(character);
    }
}
