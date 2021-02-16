package com.example.hotelSpring.entity;


import lombok.Data;
import lombok.Getter;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;


public interface RoomAndOrder  {
//    private int roomId;
//    private String roomNameEn;
//    private String roomNameUa;
//    private int price;
//    private int capacity;
//    private RoomClass roomClass;
//    private ReservationStatus status;
//
//    public RoomAndOrder(int roomId, String roomNameEn, String roomNameUa, int price, int capacity, RoomClass roomClass, ReservationStatus status) {
//        this.roomId = roomId;
//        this.roomNameEn = roomNameEn;
//        this.roomNameUa = roomNameUa;
//        this.price = price;
//        this.capacity = capacity;
//        this.roomClass = roomClass;
//        this.status = status;
//    }
    int getRoomId();
    String getRoomNameEn();
    String getRoomNameUa();
    int getPrice();
    int getCapacity();
    RoomClass getCategory();
    ReservationStatus getStatus();
}
