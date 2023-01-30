package org.somevand.swt.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public record Showing(
        int id,
        String title,
        int startDateTime,
        int duration,
        int hallNumber,
        boolean isArchived) {

    public Date startDateTimeAsDate() {
        return Date.from(Instant.ofEpochSecond(startDateTime()).atZone(ZoneId.systemDefault()).toInstant());
    }


}
