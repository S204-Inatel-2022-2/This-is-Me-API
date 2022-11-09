package br.inatel.thisismeapi.controllers.dtos.responses;

import br.inatel.thisismeapi.controllers.dtos.responses.converter.DayOfWeekBrazilian;
import br.inatel.thisismeapi.models.Day;

import java.io.Serializable;

public class DayResponseDTO implements Serializable {

    private String day;

    public DayResponseDTO(Day day) {

        this.day = DayOfWeekBrazilian.convert(day.getDayOfWeekCustom().name()) + ": " + day.getStartTime() + " - " + day.getEndTime();
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
