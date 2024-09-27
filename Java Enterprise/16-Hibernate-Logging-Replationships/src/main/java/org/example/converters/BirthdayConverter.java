package org.example.converters;

import jakarta.persistence.AttributeConverter;
import org.example.entities.Birthday;

import java.sql.Date;
import java.util.Optional;

// Converts sql type date to class Birthday; class has custom method getAge() that calculates age in years.

public class BirthdayConverter implements AttributeConverter<Birthday, Date> {
    @Override
    public Date convertToDatabaseColumn(Birthday birthday) {
        return Optional
                .ofNullable(birthday)
                .map(Birthday::birthDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public Birthday convertToEntityAttribute(Date date) {
        return Optional
                .ofNullable(date)
                .map(Date::toLocalDate)
                .map(Birthday::new)
                .orElse(null);
    }
}
