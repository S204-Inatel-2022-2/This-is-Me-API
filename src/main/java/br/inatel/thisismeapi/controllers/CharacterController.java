package br.inatel.thisismeapi.controllers;


import br.inatel.thisismeapi.controllers.dtos.responses.CharacterInfoResponseDTO;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.services.CharacterService;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterController.class);

    @Autowired
    private CharacterService characterService;


    @GetMapping("/get-character")
    public ResponseEntity<CharacterInfoResponseDTO> getCharacter(Authentication authentication) {

        String email = authentication.getName();
        LOGGER.info("m=getCharacter, email={}", email);

        Character character = characterService.findCharacterByEmail(email);

        return ResponseEntity.ok().body(new CharacterInfoResponseDTO(character));
    }

    @PostMapping("/set-clothes")
    public ResponseEntity<CharacterInfoResponseDTO> setClothes(Long number, Authentication authentication) {

        String email = authentication.getName();
        LOGGER.info("m=setClothes, email={}, clotheNumber={}", email, number);

        Character character = characterService.setClothes(email, number);

        return ResponseEntity.ok().body(new CharacterInfoResponseDTO(character));
    }
}
