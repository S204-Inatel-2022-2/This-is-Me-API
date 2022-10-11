package br.inatel.thisismeapi.entities.dtos;

import br.inatel.thisismeapi.Models.Day;
import br.inatel.thisismeapi.Models.QuestStatus;
import br.inatel.thisismeapi.Models.Week;
import br.inatel.thisismeapi.entities.Character;
import br.inatel.thisismeapi.entities.Quest;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.time.LocalDate;

public class QuestInputDTO implements Serializable {


    private QuestStatus status;

    private String hexColor;

    private String name;

    private String desc;

    private boolean isRepeatAllDay;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate endDate;

    // TODO criar entidade skill
    private String skill;

    private boolean repeatIntervalTime;

    private Day timeDefault;

    private Week week;

    public QuestInputDTO(QuestStatus status, String hexColor, String name, String desc, boolean isRepeatAllDay, LocalDate startDate, LocalDate endDate, String skill, boolean repeatIntervalTime, Day timeDefault, Week week) {
        this.status = status;
        this.hexColor = hexColor;
        this.name = name;
        this.desc = desc;
        this.isRepeatAllDay = isRepeatAllDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.skill = skill;
        this.repeatIntervalTime = repeatIntervalTime;
        this.timeDefault = timeDefault;
        this.week = week;
    }


    public QuestStatus getStatus() {
        return status;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
    }

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

    public boolean isRepeatAllDay() {
        return isRepeatAllDay;
    }

    public void setRepeatAllDay(boolean repeatAllDay) {
        isRepeatAllDay = repeatAllDay;
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

    public Quest getQuest() {
        Quest quest = new Quest();
        quest.setStatus(this.status);
        quest.setHexColor(this.hexColor);
        quest.setName(this.name);
        quest.setDesc(this.desc);
        quest.setRepeatAllDay(this.isRepeatAllDay);
        quest.setStartDate(this.startDate);
        quest.setEndDate(this.endDate);
        quest.setSkill(this.skill);
        quest.setRepeatIntervalTime(this.repeatIntervalTime);
        quest.setTimeDefault(this.timeDefault);
        quest.setWeek(this.week);
        return quest;
    }
}
