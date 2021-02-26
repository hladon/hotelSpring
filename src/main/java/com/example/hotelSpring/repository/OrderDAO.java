package com.example.hotelSpring.repository;

import com.example.hotelSpring.entity.Reservation;
import com.example.hotelSpring.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends CrudRepository<Reservation, Integer> {
    List<Reservation> findOrdersByUser(User user);

}
