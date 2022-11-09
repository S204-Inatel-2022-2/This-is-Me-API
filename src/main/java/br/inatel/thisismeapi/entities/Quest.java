package br.inatel.thisismeapi.entities;

import br.inatel.thisismeapi.enums.QuestStatus;
import br.inatel.thisismeapi.models.Day;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document
public class Quest implements Serializable {

    @Id
    @Schema(hidden = true)
    private String questId;

    @Schema(hidden = true)
    private String email;

    @Schema(hidden = true)
    private QuestStatus status;

    private String hexColor;

    @NotNull
    private String name;

    private String desc;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate endDate;

    // TODO criar entidade skill
    private String skill;

    private List<Day> week;

    @Schema(hidden = true)
    private Long total;

    @Schema(hidden = true)
    private Long finalized;

    public Quest() {
        this.week = new ArrayList<>();
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


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getFinalized() {
        return finalized;
    }

    public void setFinalized(Long finalized) {
        this.finalized = finalized;
    }
}
