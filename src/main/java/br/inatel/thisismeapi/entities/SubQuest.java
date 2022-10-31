package br.inatel.thisismeapi.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class SubQuest {

    @Id
    @Schema(hidden = true)
    @Indexed(name = "sub_quest_id", unique = true)
    private String subQuestId;

    private String email;

    private Boolean check;

    private DayOfWeek dayOfWeek;

    private LocalDateTime start;

    private LocalDateTime end;

    private Long xp;

    public String getSubQuestId() {
        return subQuestId;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Long getXp() {
        return xp;
    }

}
