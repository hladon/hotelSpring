package com.example.hotelSpring.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity=User.class)
    @JoinColumn(name="fk_user_id")
    private User user;
    @ManyToOne(fetch = FetchType.EAGER,targetEntity=Room.class)
    @JoinColumn(name="fk_room_id")
    private Room room;
    @Column(name = "start_rent")
    private LocalDate startRent;
    @Column(name = "end_rent")
    private LocalDate endRent;
    private int capacity;
    private ReservationStatus status;

}
