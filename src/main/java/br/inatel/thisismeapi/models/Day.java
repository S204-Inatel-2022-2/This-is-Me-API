package br.inatel.thisismeapi.models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class Day implements Serializable {

    private DayOfWeek dayOfWeek;

    @Schema(example = "00:00")
    private String startTime;

    @Schema(example = "00:00")
    private String endTime;

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
    public Long getIntervalInMin() {

        if (startTime != null && endTime != null)
            return Duration.between(
                    LocalTime.parse(this.startTime),
                    LocalTime.parse(this.endTime)).toMinutes();

        return null;
    }

    @Schema(hidden = true)
    public Long calculateXp() {

        long interval = this.getIntervalInMin();
        return Math.round(interval / 5.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        return dayOfWeek == day.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return dayOfWeek.hashCode();
    }
}
