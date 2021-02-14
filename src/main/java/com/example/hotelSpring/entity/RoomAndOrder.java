package com.example.hotelSpring.entity;


public interface RoomAndOrder {

    ReservationStatus getStatus();

    int getId();

    String getRoomNameEn();

    String getRoomNameUa();

    int getPrice();

    int getCapacity();

    RoomClass getRoomClass();
}
