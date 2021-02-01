package com.example.hotelSpring;

import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;

@Controller
public class ViewController  {

    @Autowired
    private RoomService roomService;

    @GetMapping("/admin/addRoom")
    public String greeting() {
        return "error500";
    }

    @GetMapping("/")
    public String mainPage(Model model){
        model.addAttribute("rooms",roomService.findAll());
        return "hotelRooms";
    }
}
