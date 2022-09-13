package br.inatel.thisismeapi.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Character {

    @Id
    private String id;

    private String characterName;

    private Long xp;

    private Long level;

    public Character() {
    }

    public Character(String characterName) {
        this.characterName = characterName;
        this.xp = 0L;
        this.level = 0L;
    }

    public String getId() {
        return id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
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
