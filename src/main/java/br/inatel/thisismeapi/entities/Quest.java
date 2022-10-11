package br.inatel.thisismeapi.entities;

import br.inatel.thisismeapi.Models.Day;
import br.inatel.thisismeapi.enums.QuestStatus;
import br.inatel.thisismeapi.Models.Week;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document
public class Quest implements Serializable {

    @Id
    private String id;

    private QuestStatus status;

    private String hexColor;

    private String name;

    private String desc;

    private boolean isRepeatEveryDay;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate endDate;

    // TODO criar entidade skill
    private String skill;

    private boolean repeatIntervalTime;

    private Day timeDefault;

    private Week week;

    public Quest() {
        this.status = QuestStatus.IN_PROGRESS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
    }

//    public Character getCharacter() {
//        return character;
//    }
//
//    public void setCharacter(Character character) {
//        this.character = character;
//    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
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

    public boolean isRepeatEveryDay() {
        return isRepeatEveryDay;
    }

    public void setRepeatEveryDay(boolean repeatEveryDay) {
        isRepeatEveryDay = repeatEveryDay;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public boolean isRepeatIntervalTime() {
        return repeatIntervalTime;
    }

    public void setRepeatIntervalTime(boolean repeatIntervalTime) {
        this.repeatIntervalTime = repeatIntervalTime;
    }

    public Day getTimeDefault() {
        return timeDefault;
    }

    public void setTimeDefault(Day timeDefault) {
        this.timeDefault = timeDefault;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }
}
