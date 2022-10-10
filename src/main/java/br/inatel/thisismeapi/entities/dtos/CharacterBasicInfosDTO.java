package br.inatel.thisismeapi.entities.dtos;

import br.inatel.thisismeapi.entities.Character;

import java.io.Serializable;

public class CharacterBasicInfosDTO implements Serializable {

    private String id;
    private String characterName;
    private String sex;
    private Long xp;
    private Long level;

    public CharacterBasicInfosDTO(Character character) {
        this.id = character.getId();
        this.characterName = character.getCharacterName();
        this.sex = character.getSex();
        this.xp = character.getXp();
        this.level = character.getLevel();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getXp() {
        return xp;
    }

    public void setXp(Long xp) {
        this.xp = xp;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}
