package com.example.hotelSpring.unitTest;

import com.example.hotelSpring.entity.Reservation;
import com.example.hotelSpring.entity.ReservationStatus;
import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.entity.User;
import com.example.hotelSpring.repository.OrderDAO;
import com.example.hotelSpring.repository.RoomDAO;
import com.example.hotelSpring.service.ReservationService;
import com.example.hotelSpring.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {
    @MockBean
    private OrderDAO orderDAO;
    @Autowired
    private ReservationService reservationService;

    @Test
    public void saveOrderTest() {
        Reservation reservation = new Reservation();
        LocalDate start=LocalDate.now();
        reservation.setStartRent(start);
        LocalDate end=LocalDate.now().plusDays(1);
        reservation.setEndRent(end);
        Integer capacity=1;
        reservation.setCapacity(capacity);
        reservation.setStatus(ReservationStatus.BOOKED);
        User user =new User();
        user.setUserId(1);
        reservation.setUser(user);
        given(this.orderDAO.save(reservation)).willReturn(reservation);
        assertEquals(reservation,reservationService.saveOrder(start,end,capacity,user));
        reservation.setStatus(ReservationStatus.RESERVED);
        assertFalse(reservation.equals(reservationService.saveOrder(start,end,capacity,user)));
    }

    public void createReservationTest(){
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        LocalDate start=LocalDate.now();
        reservation.setStartRent(start);
        LocalDate end=LocalDate.now().plusDays(1);
        reservation.setEndRent(end);
        Room room=new Room();
        Integer roomid=1;
        room.setRoomId(roomid);
        reservation.setRoom(room);
        reservation.setStatus(ReservationStatus.RESERVED);
        User user =new User();
        user.setUserId(1);
        reservation.setUser(user);
        given(this.orderDAO.save(reservation)).willReturn(reservation);
        given(this.orderDAO.existsById(1)).willReturn(true);
        assertEquals(reservation,reservationService.createReservation(start,end,roomid,user));
        reservation.setStatus(ReservationStatus.BOOKED);
        assertFalse(reservation.equals(reservationService.createReservation(start,end,roomid,user)));
    }


}
