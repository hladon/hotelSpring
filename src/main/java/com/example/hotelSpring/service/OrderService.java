package com.example.hotelSpring.service;

import com.example.hotelSpring.entity.Reservation;
import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.repository.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDAO orderDAO;

    public Reservation save(String startRent, String endRent, String capacity, User user){
        Reservation order=new Reservation();
        order.setCapacity(Integer.parseInt(capacity));
        order.setStartRent(LocalDate.parse(startRent));
        order.setEndRent(LocalDate.parse(endRent));
        order.setUser(user);
        return orderDAO.save(order);
    }

    public List<Reservation> findAllByUser(User user){
        return orderDAO.findOrdersByUser(user);
    }
    public List<Reservation> findAll(){
        return (List<Reservation>) orderDAO.findAll();
    }
}
