package com.example.hotelSpring.controller;

import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/registration")
    public ResponseEntity<String> addRoom(@RequestBody User user) {
        return userService.save(user);
    }

}
