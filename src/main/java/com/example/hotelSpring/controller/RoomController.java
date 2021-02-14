package com.example.hotelSpring.controller;

import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.service.RoomService;
import com.example.hotelSpring.service.UserPrincipalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;


@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping(value = "/admin/rooms/addRoom", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> addRoom(@RequestBody Room room) {
        roomService.save(room);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);

    }

    @GetMapping("/admin/rooms")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminOrderPage(Model model) {
        model.addAttribute("rooms", roomService.findAll());
        return "roomAdmin";
    }

    @GetMapping("/")
    public String mainPage(HttpServletRequest request, Model model,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("sort") Optional<String> sort,
                           @RequestParam(value = "startRent", required = false) Optional<String> startRent,
                           @RequestParam(value = "endRent", required = false) Optional<String> endRent,
                           @RequestParam(value = "capacity", required = false) Optional<String> capacity
    ) {

        Locale loc = RequestContextUtils.getLocale(request);
        roomService.setInputDates(model);
        if (endRent.isEmpty()||startRent.isEmpty()||capacity.isEmpty()){
            roomService.setPagination(model, page, sort);
        }else {
            roomService.setPagination(model, page, sort,startRent,endRent,capacity);
        }


        model.addAttribute("locale", loc);

        return "hotelRooms";
    }

}
