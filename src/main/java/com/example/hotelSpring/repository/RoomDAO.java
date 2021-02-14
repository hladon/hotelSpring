package com.example.hotelSpring.repository;

import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.entity.RoomAndOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface RoomDAO extends PagingAndSortingRepository<Room,Integer> {
    String SELECT_RESERVATION="SELECT room.*,status FROM room left join (select * from reservation where start_rent< ?1\n" +
            "and end_rent>?2) as res\n" +
            "ON room_id=fk_room_id WHERE room.capacity>=?4 ORDER BY ?3 desc";
    List<Room> findAllByPrice(int price, Pageable pageable);
    List<Room> findAllByCapacity(int capacity, Pageable pageable);
    @Query(value = SELECT_RESERVATION,countQuery = "SELECT count(*) FROM Room ",nativeQuery = true)
    List<RoomAndOrder> findRoomReservation(Date endRent, Date startRent,String sort,Integer cap);
}
