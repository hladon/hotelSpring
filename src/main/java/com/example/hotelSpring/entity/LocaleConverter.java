package com.example.hotelSpring.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocaleConverter implements AttributeConverter<Locale, String> {

    @Override
    public String convertToDatabaseColumn(Locale locale) {
        if (locale == null) {
            return null;
        }
        return locale.value();
    }

    @Override
    public Locale convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Locale.valueOf(code);
    }
}
