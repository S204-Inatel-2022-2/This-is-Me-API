package br.inatel.thisismeapi.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class Day implements Serializable {

    @Schema(example = "false")
    private boolean active = false;

    private DayOfWeek dayOfWeek;

    @Schema(example = "00:00")
    private String startTime;

    @Schema(example = "00:00")
    private String endTime;


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }


    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Schema(hidden = true)
    public Long getIntervalInMin(){

        if(startTime != null && endTime != null && active)
            return Duration.between(
                LocalTime.parse(this.startTime),
                LocalTime.parse(this.endTime)).toMinutes();

        return null;
    }
}
