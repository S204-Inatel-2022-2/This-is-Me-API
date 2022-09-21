package br.inatel.thisismeapi.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        this.xp = 0L;
        this.level = 0L;
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

    public void addXp(Long xp) {
        this.xp += xp;
    }

    public Long getLevel() {
        return level;
    }

    public void upLevel() {
        this.level++;
    }


    public String toStringJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(this);
    }
}
