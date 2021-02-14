package com.example.hotelSpring.service;

import com.example.hotelSpring.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ViewService {

    public Model setInputDates(Model model){
        LocalDate tm=LocalDate.now();
        model.addAttribute("curDate", tm);
        model.addAttribute("nextDay", tm.plusDays(1));
        model.addAttribute("maxReservation", tm.plusYears(1));
        model.addAttribute("maxReservationEnd", tm.plusYears(1).plusDays(1));
        return model;
    }


}
