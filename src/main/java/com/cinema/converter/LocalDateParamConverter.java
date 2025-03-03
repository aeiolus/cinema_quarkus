package com.cinema.converter;

import jakarta.ws.rs.ext.ParamConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateParamConverter implements ParamConverter<LocalDate> {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;
    
    @Override
    public LocalDate fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        try {
            return LocalDate.parse(value, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd", e);
        }
    }
    
    @Override
    public String toString(LocalDate value) {
        if (value == null) {
            return null;
        }
        return value.format(FORMATTER);
    }
}
