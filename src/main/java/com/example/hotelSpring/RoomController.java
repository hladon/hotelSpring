package com.example.hotelSpring;

import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping(value ="/admin/rooms/addRoom", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addRoom(@RequestBody Room room){
        roomService.save(room);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }
}
