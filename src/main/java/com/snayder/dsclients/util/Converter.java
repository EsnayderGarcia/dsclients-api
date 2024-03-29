package com.snayder.dsclients.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Converter {
    public static String localdateToString(LocalDate localDate) {
        if (Objects.isNull(localDate))
            return "";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formatter);
    }
}
