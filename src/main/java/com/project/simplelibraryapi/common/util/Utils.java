package com.project.simplelibraryapi.common.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    private static final DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static LocalDate parseYYYYMMDD(String date) {
        return LocalDate.parse(date, YYYYMMDD_FORMATTER);
    }
}
