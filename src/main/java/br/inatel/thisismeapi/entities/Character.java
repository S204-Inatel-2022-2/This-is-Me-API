package br.inatel.thisismeapi.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Character {

    @Id
    private String id;

    private String sex;

    private String characterName;

    private Long xp;

    private Long level;

    private List<Quest> quests;

    public Character() {
        this.sex = "indefinido";
        this.xp = 0L;
        this.level = 0L;
        quests = new ArrayList<>();
    }

    public Character(String characterName) {
        this.characterName = characterName;
        this.xp = 0L;
        this.level = 0L;
        this.sex = "indefinido";
        quests = new ArrayList<>();
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public String toStringJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(this);
    }
}
