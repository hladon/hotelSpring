package com.example.hotelSpring.repository;

import com.example.hotelSpring.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDAO extends CrudRepository<Room,Integer> {
}
