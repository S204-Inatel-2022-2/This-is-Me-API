package br.inatel.thisismeapi.controllers.dtos.responses;

import br.inatel.thisismeapi.entities.Quest;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class QuestResponseDTO implements Serializable {

    private static DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private String id;
    private String name;

    private String desc;

    private String hexColor;

    private String skill;

    private String subQuests;

    private String status;

    private String period;

    private String xp;

    private List<DayResponseDTO> week;

    public QuestResponseDTO(Quest quest) {
        this.id = quest.getQuestId();
        this.name = quest.getName();
        this.desc = quest.getDesc();
        this.hexColor = quest.getHexColor();
        this.skill = quest.getSkill();
        this.subQuests = quest.getFinalized() + "/" + quest.getTotal();
        this.status = quest.getStatus().toString();
        this.period = FMT.format(quest.getStartDate()) + " - " + FMT.format(quest.getEndDate());
        this.xp = quest.getXpGained() + "/" + quest.getTotalXp();
        this.week = quest.getWeek().stream().map(DayResponseDTO::new).toList();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSubQuests() {
        return subQuests;
    }

    public void setSubQuests(String subQuests) {
        this.subQuests = subQuests;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getXp() {
        return xp;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }

//    public List<String> getWeek() {
//        return week;
//    }
//
//    public void setWeek(List<String> week) {
//        this.week = week;
//    }

    public List<DayResponseDTO> getWeek() {
        return week;
    }

    public void setWeek(List<DayResponseDTO> week) {
        this.week = week;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
