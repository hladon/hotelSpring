package com.example.hotelSpring.controller;

import com.example.hotelSpring.entity.Reservation;
import com.example.hotelSpring.entity.ReservationStatus;
import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.service.ReservationService;
import com.example.hotelSpring.service.UserPrincipalImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Locale;


@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/admin/orders")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminRoomPage(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "adminReservation";
    }

    @GetMapping("/user/order")
    @PreAuthorize("hasAuthority('USER')")
    public String userReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAllByUser(getUser()));
        return "userReservation";
    }

    @PostMapping(path = "/reservation")
    @PreAuthorize("hasAuthority('USER')")
    public RedirectView createReservation(@ModelAttribute Reservation reservation,
                                          @RequestParam("start") String start,
                                          @RequestParam("end") String end) {
        reservation.setStartRent(LocalDate.parse(start));
        reservation.setEndRent(LocalDate.parse(end));
        reservation.setStatus(ReservationStatus.RESERVED);
        reservationService.save(reservation);
        return new RedirectView("/");
    }

    @PostMapping(path = "/order")
    @PreAuthorize("hasAuthority('USER')")
    public RedirectView createOrder(@RequestParam("startRent") String start,
                                    @RequestParam("endRent") String end,
                                    @RequestParam("capacity") String capacity) {
        reservationService.saveOrder(start, end, capacity, getUser());
        return new RedirectView("/user/order");
    }


    @GetMapping(path = "/order")
    @PreAuthorize("hasAuthority('USER')")
    public String createOrder(Model model, HttpServletRequest request,
                              @RequestParam("room") Integer roomId,
                              @RequestParam("start") String start,
                              @RequestParam("end") String end) {
        reservationService.prepareOrderBlank(model, roomId, start, end);
        Locale loc = RequestContextUtils.getLocale(request);
        model.addAttribute("locale", loc);
        return "order";
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipalImpl userPrincipal = (UserPrincipalImpl) auth.getPrincipal();
        return userPrincipal.getUser();
    }
}
