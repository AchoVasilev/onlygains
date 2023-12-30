package com.project.infrastructure.converters

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.util.Arrays

@Converter
class StringToListConverter : AttributeConverter<List<String>, String> {
    override fun convertToDatabaseColumn(list: List<String>?): String? {
        if (list == null) return ""
        return java.lang.String.join(",", list)
    }

    override fun convertToEntityAttribute(joined: String?): List<String> {
        if (joined == null) return ArrayList()
        return joined.split(",")
    }
}
