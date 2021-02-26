package com.example.hotelSpring.service;

import com.example.hotelSpring.entity.Reservation;
import com.example.hotelSpring.entity.ReservationStatus;
import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.repository.OrderDAO;
import com.example.hotelSpring.repository.RoomDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    RoomDAO roomDAO;

    public Model prepareOrderBlank(Model model, Integer roomId, String start, String end) {
        model.addAttribute("room", roomDAO.findById(roomId).get());
        model.addAttribute("startRent", start);
        model.addAttribute("endRent", end);
        return model;
    }

    public Reservation saveOrder(String start, String end, String capacity, User user) {
        Reservation reservation = new Reservation();
        reservation.setStartRent(LocalDate.parse(start));
        reservation.setEndRent(LocalDate.parse(end));
        reservation.setCapacity(Integer.parseInt(capacity));
        reservation.setStatus(ReservationStatus.BOOKED);
        reservation.setUser(user);
        return orderDAO.save(reservation);
    }

    public Reservation save(Reservation reservation) {
        return orderDAO.save(reservation);
    }

    public List<Reservation> findAllByUser(User user) {
        return orderDAO.findOrdersByUser(user);
    }

    public List<Reservation> findAll() {
        return (List<Reservation>) orderDAO.findAll();
    }
}
