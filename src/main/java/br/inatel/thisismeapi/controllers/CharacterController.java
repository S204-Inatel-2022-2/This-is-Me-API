package br.inatel.thisismeapi.controllers;


import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.dtos.CharacterBasicInfosDTO;
import br.inatel.thisismeapi.services.CharacterService;
import br.inatel.thisismeapi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
public class CharacterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CharacterService characterService;


    @GetMapping("/get-character")
    public ResponseEntity<CharacterBasicInfosDTO> getCharacter(Authentication authentication) {

        LOGGER.info("m=getCharacter, email={}", authentication.getName());
        Character character = userService.findCharacterByEmail(authentication.getName());
        CharacterBasicInfosDTO characterBasicInfosDTO = new CharacterBasicInfosDTO(character);
        return ResponseEntity.ok().body(characterBasicInfosDTO);
    }

    @PostMapping("/set-clothes")
    public ResponseEntity<CharacterBasicInfosDTO> setClothes(Long number, Authentication authentication) {

        LOGGER.info("m=setClothes, email={}", authentication.getName());
        Character character = characterService.setClothes(authentication.getName(), number);
        CharacterBasicInfosDTO characterBasicInfosDTO = new CharacterBasicInfosDTO(character);
        return ResponseEntity.ok().body(characterBasicInfosDTO);
    }

}
