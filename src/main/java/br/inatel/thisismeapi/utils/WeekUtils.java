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
}
