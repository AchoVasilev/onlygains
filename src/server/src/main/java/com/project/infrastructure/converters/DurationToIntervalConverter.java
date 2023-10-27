package com.project.infrastructure.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Duration;
import java.time.Instant;

@Converter
public class DurationToIntervalConverter implements AttributeConverter<Duration, Instant> {
    @Override
    public Instant convertToDatabaseColumn(Duration attribute) {
        if (attribute == null) {
            return null;
        }
        // Convert Duration to a Timestamp for PostgreSQL
        return Instant.ofEpochMilli(attribute.toMillis());
    }


    @Override
    public Duration convertToEntityAttribute(Instant dbData) {
        if (dbData == null) {
            return null;
        }
        // Convert Timestamp from PostgreSQL to Duration
        return Duration.ofMillis(dbData.getEpochSecond());
    }
}