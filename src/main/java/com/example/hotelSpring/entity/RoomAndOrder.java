package com.example.hotelSpring.entity;


public interface RoomAndOrder {
    int getRoomId();

    String getRoomNameEn();

    String getRoomNameUa();

    int getPrice();

    int getCapacity();

    RoomClass getCategory();

    ReservationStatus getStatus();
}
