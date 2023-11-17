package com.snayder.dsclients.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {
    public static String localdateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }
}
