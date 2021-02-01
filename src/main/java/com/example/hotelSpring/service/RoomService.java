package com.example.hotelSpring.service;


import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.repository.DAO;
import com.example.hotelSpring.repository.RoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoomService {

    @Autowired
    RoomDAO roomDAO;

    public Room save(Room room) {
        return roomDAO.save(room);
    }
    public List<Room> findAll() {
        return (List<Room>)roomDAO.findAll();
    }

}
