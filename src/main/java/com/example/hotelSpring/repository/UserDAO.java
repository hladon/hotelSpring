package com.example.hotelSpring.repository;

import com.example.hotelSpring.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends CrudRepository<User,Integer> {
    User findByUserName(String userName);
}
