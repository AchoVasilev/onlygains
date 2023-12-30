package com.project.infrastructure.converters

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.Duration
import java.time.Instant

@Converter
class DurationToIntervalConverter : AttributeConverter<Duration, Instant> {
    override fun convertToDatabaseColumn(attribute: Duration?): Instant? {
        if (attribute == null) {
            return null
        }
        // Convert Duration to a Timestamp for PostgreSQL
        return Instant.ofEpochMilli(attribute.toMillis())
    }


    override fun convertToEntityAttribute(dbData: Instant?): Duration? {
        if (dbData == null) {
            return null
        }
        // Convert Timestamp from PostgreSQL to Duration
        return Duration.ofMillis(dbData.epochSecond)
    }
}