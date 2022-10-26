package br.inatel.thisismeapi.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WeekUtils {

    public static Boolean verifyIsLastWeek(LocalDate endDate) {
        LocalDate sundayOfLastWeek;
        if (DayOfWeek.from(endDate) != DayOfWeek.SUNDAY) {
            sundayOfLastWeek = endDate.minusDays(DayOfWeek.from(endDate).getValue());
        } else {
            sundayOfLastWeek = endDate;
        }

        return sundayOfLastWeek.isEqual(LocalDate.now()) || sundayOfLastWeek.isBefore(LocalDate.now());
    }

    public static LocalDate getActualSundayByDate(LocalDate date) {

        int intervalToSunday = DayOfWeek.SUNDAY.getValue() - date.getDayOfWeek().getValue();

        if(intervalToSunday == 0)
            return date;

        LocalDate result = date.minusDays(DayOfWeek.from(date).getValue());
        return result;
    }

    public static LocalDate getNextSundayByDate(LocalDate date) {

        int intervalToSunday = DayOfWeek.SUNDAY.getValue() - date.getDayOfWeek().getValue();

        if(intervalToSunday == 0)
            return date.plusDays(DayOfWeek.SUNDAY.getValue());

        return date.plusDays(intervalToSunday);
    }
}
