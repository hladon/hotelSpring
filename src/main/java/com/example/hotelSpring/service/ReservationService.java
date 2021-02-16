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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private static final Logger LOGGER =  LoggerFactory.getLogger(ReservationService.class);
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    RoomDAO roomDAO;

    public Model prepareOrderBlank(Model model, Integer roomId,String start,String end){
        model.addAttribute("room",roomDAO.findById(roomId).get());
        model.addAttribute("startRent",start);
        model.addAttribute("endRent",end);
        return model;
    }
    public ResponseEntity<String> save(Reservation reservation){
        reservation.setStatus(ReservationStatus.BOOKED);
        try {
            orderDAO.save(reservation);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>("Internal error, order not saved", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("Order created! ", HttpStatus.OK);
    }

    public List<Reservation> findAllByUser(User user){
        return orderDAO.findOrdersByUser(user);
    }
    public List<Reservation> findAll(){
        return (List<Reservation>) orderDAO.findAll();
    }
}
