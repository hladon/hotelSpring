package com.example.hotelSpring.service;

import com.example.hotelSpring.entity.Reservation;
import com.example.hotelSpring.entity.ReservationStatus;
import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.repository.OrderDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);
    @Autowired
    OrderDAO orderDAO;

    public void userRemove(Integer id, User user) {
        orderDAO.deleteByReservationIdAndUser(id, user);
    }

    public void adminRemove(Integer id) {
        orderDAO.deleteById(id);
    }

    public Reservation saveOrder(LocalDate start, LocalDate end, Integer capacity, User user) {
        Reservation reservation = new Reservation();
        reservation.setStartRent(start);
        reservation.setEndRent(end);
        reservation.setCapacity(capacity);
        reservation.setStatus(ReservationStatus.BOOKED);
        reservation.setUser(user);
        return orderDAO.save(reservation);
    }

    public Reservation createReservation(LocalDate start, LocalDate end, Integer roomId, User user) {
        Reservation reservation = new Reservation();
        reservation.setStartRent(start);
        reservation.setEndRent(end);
        reservation.setStatus(ReservationStatus.RESERVED);
        reservation.setUser(user);
        Room room = new Room();
        room.setRoomId(roomId);
        reservation.setRoom(room);
        if (orderDAO.existsReservation(room, start, end))
            throw new IllegalStateException("Room is reserved");
        return orderDAO.save(reservation);
    }

    public Reservation setRoomToReservation( Integer roomId,Integer reservationId){
        Reservation reservation = orderDAO.findById(reservationId).orElse(new Reservation());
        reservation.setReservationId(reservationId);
        reservation.setStatus(ReservationStatus.RESERVED);
        Room room = new Room();
        room.setRoomId(roomId);
        reservation.setRoom(room);
        return save(reservation);
    }


    public Reservation save(Reservation reservation) {
        if (reservation.getRoom() != null &&
                orderDAO.existsReservation(reservation.getRoom(), reservation.getStartRent(),reservation.getEndRent())){
            throw new IllegalStateException("Room is reserved");
        }
        return orderDAO.save(reservation);
    }

    public List<Reservation> findAllByUser(User user) {
        return orderDAO.findOrdersByUser(user);
    }

    public List<Reservation> findAll() {
        return (List<Reservation>) orderDAO.findAll();
    }


}
