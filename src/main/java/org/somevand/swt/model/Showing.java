package org.somevand.swt.model;

public record Showing(
        int id,
        String title,
        int startDateTime,
        int duration,
        int hallNumber,
        boolean isArchived) {

}
