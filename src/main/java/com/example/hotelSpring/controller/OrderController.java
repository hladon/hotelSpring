package com.example.hotelSpring.controller;

import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.service.OrderService;
import com.example.hotelSpring.service.UserPrincipalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/admin/orders")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminRoomPage(Model model){
        model.addAttribute("orders",orderService.findAll());
        return "userOrders";
    }

    @PostMapping(path = "/order")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> createOrder(@RequestParam String startRent,
                                              @RequestParam String endRent,
                                              @RequestParam String capacity){
        orderService.save(startRent,endRent,capacity,getUser());
        return new ResponseEntity<>("Order created! ", HttpStatus.OK);
    }

    private User getUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipalImpl userPrincipal=(UserPrincipalImpl)auth.getPrincipal();
        return userPrincipal.getUser();
    }
}
