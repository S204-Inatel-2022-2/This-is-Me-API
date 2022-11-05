package br.inatel.thisismeapi.controllers.dtos.responses;

import br.inatel.thisismeapi.entities.SubQuest;
import br.inatel.thisismeapi.utils.TimeUtils;

import java.time.format.DateTimeFormatter;

public class CardResponseDTO {

    private static DateTimeFormatter FMT = DateTimeFormatter.ofPattern("HH:mm");
    private String questId;

    private String subQuestId;

    private String name;

    private String skill;

    private String startTime;

    private String endTime;

    private String duration;

    private Long xp;

    private Long total;

    private Long finalizeds;

    public CardResponseDTO(SubQuest subQuest) {
        this.questId = subQuest.getQuest().getQuestId();
        this.subQuestId = subQuest.getSubQuestId();
        this.name = subQuest.getQuest().getName();
        this.skill = subQuest.getQuest().getSkill();
        this.startTime = FMT.format(subQuest.getStart());
        this.endTime = FMT.format(subQuest.getEnd());
        this.duration = TimeUtils.getTimeInTextFormat(subQuest.getDurationInMin());
        this.xp = subQuest.getXp();
        this.total = subQuest.getQuest().getTotal();
        this.finalizeds = subQuest.getQuest().getFinalized();
    }

    public String getQuestId() {
        return questId;
    }

    public void setQuestId(String questId) {
        this.questId = questId;
    }

    public String getSubQuestId() {
        return subQuestId;
    }

    public void setSubQuestId(String subQuestId) {
        this.subQuestId = subQuestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Long getXp() {
        return xp;
    }

    public void setXp(Long xp) {
        this.xp = xp;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getFinalizeds() {
        return finalizeds;
    }

    public void setFinalizeds(Long finalizeds) {
        this.finalizeds = finalizeds;
    }
}
