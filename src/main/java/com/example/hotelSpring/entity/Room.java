package com.example.hotelSpring.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "room")
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "locale")
    private Locale locale;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "price")
    private int price;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "room_class")
    private String roomClass;

}
