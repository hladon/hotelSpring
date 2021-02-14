package com.example.hotelSpring.service;


import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.entity.RoomAndOrder;
import com.example.hotelSpring.repository.RoomDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class RoomService {

    @Autowired
    RoomDAO roomDAO;

    private final int PAGE_SIZE=5;
    public Room save(Room room) {
        return roomDAO.save(room);
    }

    public Page<Room> findAll() {
        Pageable firstPageWithTwoElements = PageRequest.of(0, 5, Sort.by("price").descending());
        Page<Room> res=roomDAO.findAll(firstPageWithTwoElements);
        return res;
    }
    public Model setInputDates(Model model){
        LocalDate tm=LocalDate.now();
        model.addAttribute("curDate", tm);
        model.addAttribute("nextDay", tm.plusDays(1));
        model.addAttribute("maxReservation", tm.plusYears(1));
        model.addAttribute("maxReservationEnd", tm.plusYears(1).plusDays(1));
        return model;
    }

    public Model setPagination(Model model, Optional<Integer> page, Optional<String> sort) {
        String sortType=sort.orElse("price");
        Page<Room> roomPage = roomDAO.findAll(
                PageRequest.of(page.orElse(1) - 1, PAGE_SIZE, Sort.by(sortType).descending()));
        model.addAttribute("rooms", roomPage);
        model.addAttribute("sortType",sortType);

        return setPagesNumber(roomPage,model);
    }

    public Model setPagination(Model model, Optional<Integer> page, Optional<String> sort,
                               Optional<String> startRent,Optional<String> endRent,Optional<String> capacity) {
        String sortType=sort.orElse("price");
        Integer cap=Integer.parseInt(capacity.orElse("1"));
        Date startRentDate=Date.valueOf(startRent.get());
        Date endRentDate=Date.valueOf(endRent.get());

        List<RoomAndOrder> roomPage = roomDAO.findRoomReservation(startRentDate,endRentDate,sortType,cap);
        model.addAttribute("rooms", roomPage);
        model.addAttribute("sortType",sortType);

        return model;
    }

    private Pageable createPages(Optional<Integer> page, Optional<String> sort){
        String sortType=sort.orElse("price");
        return PageRequest.of(page.orElse(1) - 1, PAGE_SIZE, Sort.by(sortType).descending());
    }

    private Model setPagesNumber(Page roomPage,Model model){
        int totalPages = roomPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return model;
    }
}
