package com.example.hotelSpring.entity;

public enum Locale {
    en("en"),
    ua("ua");
    private String value;

    public String value() {
        return value;
    }

    Locale(String value) {
        this.value = value;
    }
}
