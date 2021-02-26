package com.example.hotelSpring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;
    @Column(name = "room_name_en")
    private String roomNameEn;
    @Column(name = "room_name_ua")
    private String roomNameUa;
    @Column(name = "price")
    private int price;
    @Column(name = "capacity")
    private int capacity;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private RoomClass category;
}
