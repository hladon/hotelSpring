package com.example.hotelSpring.repository;

import com.example.hotelSpring.entity.Reservation;
import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDAO extends CrudRepository<Reservation, Integer> {
    List<Reservation> findOrdersByUser(User user);

    @Query("select case  when count (r)>0 then true else false end from Reservation r where r.room=:room" +
            " and r.startRent<:end and r.endRent>:start")
    boolean existsReservation(@Param("room") Room room, @Param("start") LocalDate start, @Param("end")LocalDate end);
    @Transactional
    @Modifying
    @Query("delete from Reservation r where r.reservationId=:id and r.user=:user")
    void deleteByReservationIdAndUser(@Param("id")Integer id,@Param("user")User user);

}
