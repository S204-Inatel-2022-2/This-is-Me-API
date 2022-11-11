package br.inatel.thisismeapi.controllers;

import br.inatel.thisismeapi.entities.Skill;
import br.inatel.thisismeapi.services.SkillService;
import br.inatel.thisismeapi.services.impl.SkillServiceImpl;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SkillController.class);

    @Autowired
    private SkillService skillService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Schema(description = "Cria uma nova skill")
    public void createSkill(@RequestBody Skill skill, Authentication authentication) {

        LOGGER.info("m=createSkill, email={}", authentication.getName());
        skillService.createSkill(skill, authentication.getName());
    }

    @GetMapping
    @Schema(description = "Retorna todas as skills do usuário")
    public List<Skill> getAllSkills(Authentication authentication) {

        LOGGER.info("m=getAllSkills, email={}", authentication.getName());
        return skillService.getAllSkills(authentication.getName());
    }
}
