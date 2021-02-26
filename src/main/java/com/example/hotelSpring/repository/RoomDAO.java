package com.example.hotelSpring.repository;

import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.entity.RoomAndOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface RoomDAO extends PagingAndSortingRepository<Room, Integer> {
    String COUNT_QUERY="SELECT count(*) FROM room r LEFT JOIN (SELECT * FROM reservation WHERE start_rent<= ?1" +
            "            AND end_rent>=?2) as res" +
            "            ON room_id=fk_room_id WHERE r.capacity>=?3 ";
    String SELECT_RESERVATION = "SELECT r.room_id as \"roomId\", r.room_name_en as \"roomNameEn\"," +
            " r.room_name_ua as \"roomNameUa\",r.price as \"price\"," +
            "r.capacity as \"capacity\", r.category \"category\"," +
            "res.status as \"status\" FROM room r LEFT JOIN (SELECT * FROM reservation WHERE start_rent<= ?1\n" +
            "AND end_rent>=?2) as res\n" +
            "ON room_id=fk_room_id WHERE r.capacity>=?3  ";
    String SELECT_RESERVATION_STATUS = "SELECT r.room_id as \"roomId\", r.room_name_en as \"roomNameEn\"," +
            " r.room_name_ua as \"roomNameUa\",r.price as \"price\"," +
            "r.capacity as \"capacity\", r.category \"category\"," +
            "res.status as \"status\" FROM room r LEFT JOIN (SELECT * FROM reservation WHERE start_rent<= ?1\n" +
            "AND end_rent>=?2) as res\n" +
            "ON room_id=fk_room_id WHERE r.capacity>=?3 ORDER BY res.status ";

    @Query(value = SELECT_RESERVATION, countQuery = COUNT_QUERY, nativeQuery = true)
    Page<RoomAndOrder> findRoomReservation(Date endRent, Date startRent, Integer cap, String sort, Pageable pageable);

    @Query(value = SELECT_RESERVATION_STATUS, countQuery = COUNT_QUERY, nativeQuery = true)
    Page<RoomAndOrder> findRoomReservationStatus(Date endRent, Date startRent, Integer cap, String sort, Pageable pageable);
}
