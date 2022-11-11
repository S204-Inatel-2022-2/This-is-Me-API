package br.inatel.thisismeapi.controllers;


import br.inatel.thisismeapi.controllers.dtos.responses.CharacterInfoResponseDTO;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.services.CharacterService;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Retorna as informações da home do personagem do usuário logado")
    public ResponseEntity<CharacterInfoResponseDTO> getCharacter(Authentication authentication) {

        String email = authentication.getName();
        LOGGER.info("m=getCharacter, email={}", email);

        Character character = characterService.findCharacterByEmail(email);

        return ResponseEntity.ok().body(new CharacterInfoResponseDTO(character));
    }

    @GetMapping("/get-character-all-infos")
    @Schema(description = "Retorna todas as informações do personagem")
    public ResponseEntity<Character> getCharacterAllInfos(Authentication authentication) {

        String email = authentication.getName();
        LOGGER.info("m=getCharacter, email={}", email);

        Character character = characterService.findCharacterByEmail(email);

        return ResponseEntity.ok().body(character);
    }

    @PostMapping("/set-clothes")
    @Schema(description = "Altera a roupa do personagem")
    public ResponseEntity<CharacterInfoResponseDTO> setClothes(Long number, Authentication authentication) {

        String email = authentication.getName();
        LOGGER.info("m=setClothes, email={}, clothesNumber={}", email, number);

        Character character = characterService.setClothes(email, number);

        return ResponseEntity.ok().body(new CharacterInfoResponseDTO(character));
    }
}
