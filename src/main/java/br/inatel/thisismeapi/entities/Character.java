package br.inatel.thisismeapi.entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Character {

    @Id
    private String id;

    @Indexed(name = "unique_email_character", unique = true)
    private String email;

    private Long numberClothes;

    private String characterName;

    private Long xp;

    private Long level;

    @DBRef
    private List<Quest> quests;

    public Character() {
        this.xp = 0L;
        this.level = 0L;
        this.numberClothes = 0L;
        quests = new ArrayList<>();
    }

    public Character(String email, String characterName) {
        this.email = email;
        this.characterName = characterName;
        this.numberClothes = 0L;
        this.xp = 0L;
        this.level = 0L;
        quests = new ArrayList<>();
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Long getNumberClothes() {
        return numberClothes;
    }

    public void setNumberClothes(Long numberClothes) {
        this.numberClothes = numberClothes;
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
