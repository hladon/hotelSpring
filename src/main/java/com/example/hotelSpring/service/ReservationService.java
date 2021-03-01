package com.example.hotelSpring.service;

import com.example.hotelSpring.entity.Reservation;
import com.example.hotelSpring.entity.ReservationStatus;
import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.repository.OrderDAO;
import com.example.hotelSpring.repository.RoomDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    RoomDAO roomDAO;


    public Reservation saveOrder(String start, String end, String capacity, User user) {
        Reservation reservation = new Reservation();
        reservation.setStartRent(LocalDate.parse(start));
        reservation.setEndRent(LocalDate.parse(end));
        reservation.setCapacity(Integer.parseInt(capacity));
        reservation.setStatus(ReservationStatus.BOOKED);
        reservation.setUser(user);
        return orderDAO.save(reservation);
    }

    @Transactional
    public Reservation createReservation(Integer roomId, LocalDate start, LocalDate end, User user) {
        Reservation reservation = new Reservation();
        reservation.setStartRent(start);
        reservation.setEndRent(end);
        reservation.setStatus(ReservationStatus.RESERVED);
        reservation.setUser(user);
        Room room = new Room();
        room.setRoomId(roomId);
        reservation.setRoom(room);
        if (orderDAO.existsReservation(room, Date.valueOf(start), Date.valueOf(end)))
            throw new IllegalStateException("Room is reserved");
        return orderDAO.save(reservation);
    }

    @Transactional
    public Reservation save(Reservation reservation) {
        if (reservation.getRoom() != null && orderDAO.existsReservation(reservation.getRoom(),
                Date.valueOf(reservation.getStartRent()),
                Date.valueOf(reservation.getEndRent())))
            throw new IllegalStateException("Room is reserved");
        return orderDAO.save(reservation);
    }

    public List<Reservation> findAllByUser(User user) {
        return orderDAO.findOrdersByUser(user);
    }

    public List<Reservation> findAll() {
        return (List<Reservation>) orderDAO.findAll();
    }
}
