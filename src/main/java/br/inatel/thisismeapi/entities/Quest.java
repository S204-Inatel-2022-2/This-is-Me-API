package br.inatel.thisismeapi.entities;

import br.inatel.thisismeapi.Models.Day;
import br.inatel.thisismeapi.enums.QuestStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class Quest implements Serializable {

    @Id
    @Schema(hidden = true)
    @Indexed(name = "quest_id", unique = true)
    private String questId;

    private String email;

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

    private List<Day> week;

    public Quest() {
    }

    public Quest(QuestStatus status, String hexColor, String name, String desc, boolean isRepeatEveryDay, LocalDate startDate, LocalDate endDate, String skill, List<Day> week) {
        this.status = status;
        this.hexColor = hexColor;
        this.name = name;
        this.desc = desc;
        this.isRepeatEveryDay = isRepeatEveryDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.skill = skill;
        this.week = week;
    }

    public String getQuestId() {
        return questId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Day> getWeek() {
        return week;
    }

    public void setWeek(List<Day> week) {
        this.week = week;
    }

    public void addDayToWeek(Day day) {
        this.week.add(day);
    }

    public Day getDayByDayOfWeek(DayOfWeek dayOfWeek) {
        Optional<Day> dayOptional = this.week.stream().filter(day -> day.getDayOfWeek() == dayOfWeek).findFirst();
        if (dayOptional.isEmpty())
            return null;
        return dayOptional.get();
    }
}
