package com.example.hotelSpring.entity;

public enum ReservationStatus {
    AVAILABLE("AVAILABLE"),
    BOOKED("BOOKED"),
    RESERVED("RESERVED"),
    NOT_AVAILABLE("NOT_AVAILABLE");
    private String value;

    public String value() {
        return value;
    }

    ReservationStatus(String value) {
        this.value = value;
    }
}
