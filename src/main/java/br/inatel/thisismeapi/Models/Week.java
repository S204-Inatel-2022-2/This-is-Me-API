package br.inatel.thisismeapi.Models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Week implements Serializable {

    private Day sunday;
    private Day monday;
    private Day tuesday;
    private Day wednesday;
    private Day thursday;
    private Day friday;
    private Day saturday;

    public Day getSunday() {
        return sunday;
    }

    public void setSunday(Day sunday) {
        this.sunday = sunday;
    }

    public Day getMonday() {
        return monday;
    }

    public void setMonday(Day monday) {
        this.monday = monday;
    }

    public Day getTuesday() {
        return tuesday;
    }

    public void setTuesday(Day tuesday) {
        this.tuesday = tuesday;
    }

    public Day getWednesday() {
        return wednesday;
    }

    public void setWednesday(Day wednesday) {
        this.wednesday = wednesday;
    }

    public Day getThursday() {
        return thursday;
    }

    public void setThursday(Day thursday) {
        this.thursday = thursday;
    }

    public Day getFriday() {
        return friday;
    }

    public void setFriday(Day friday) {
        this.friday = friday;
    }

    public Day getSaturday() {
        return saturday;
    }

    public void setSaturday(Day saturday) {
        this.saturday = saturday;
    }

    @Schema(hidden = true)
    public Day getDayOfDayOfWeek(){

        if(LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY)
            return this.sunday;
        if(LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY)
            return this.monday;
        if(LocalDate.now().getDayOfWeek() == DayOfWeek.TUESDAY)
            return this.tuesday;
        if(LocalDate.now().getDayOfWeek() == DayOfWeek.WEDNESDAY)
            return this.wednesday;
        if(LocalDate.now().getDayOfWeek() == DayOfWeek.THURSDAY)
            return this.thursday;
        if(LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY)
            return this.friday;
        if(LocalDate.now().getDayOfWeek() == DayOfWeek.SATURDAY)
            return this.saturday;

        return null;
    }
}
