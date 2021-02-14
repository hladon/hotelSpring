package com.example.hotelSpring.entity;

public enum Locale {
    EN("en"),
    UA("ua");
    private String value;

    public String value() {
        return value;
    }

    Locale(String value) {
        this.value = value;
    }
}
