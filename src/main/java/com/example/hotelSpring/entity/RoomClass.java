package com.example.hotelSpring.entity;

public enum RoomClass {
    DELUXE("DELUXE"), STANDARD("STANDARD"), SUITE("SUITE");

    private String value;

    public String value() {
        return value;
    }

    RoomClass(String value) {
        this.value = value;
    }
}
