package br.inatel.thisismeapi.Models;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

public class Week implements Serializable {

    private List<Day> days;

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public void addDay(Day day){
        this.days.add(day);
    }
}
