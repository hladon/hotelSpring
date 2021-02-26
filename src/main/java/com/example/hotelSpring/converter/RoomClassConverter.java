package com.example.hotelSpring.converter;

import com.example.hotelSpring.entity.RoomClass;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoomClassConverter implements AttributeConverter<RoomClass, String> {
    @Override
    public String convertToDatabaseColumn(RoomClass roomClass) {
        return roomClass.value();
    }

    @Override
    public RoomClass convertToEntityAttribute(String string) {
        return RoomClass.valueOf(string);
    }
}
