package com.example.hotelSpring.converter;

import com.example.hotelSpring.entity.ReservationStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ReservationStatusConverter implements AttributeConverter<ReservationStatus, String> {
    @Override
    public String convertToDatabaseColumn(ReservationStatus reservationStatus) {
        return reservationStatus.value();
    }

    @Override
    public ReservationStatus convertToEntityAttribute(String string) {
        return ReservationStatus.valueOf(string);
    }
}
