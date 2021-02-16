package com.example.hotelSpring.repository;

import com.example.hotelSpring.entity.Room;
import com.example.hotelSpring.entity.RoomAndOrder;
import com.example.hotelSpring.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.sql.Date;
import java.util.List;

@Repository
public interface RoomDAO extends PagingAndSortingRepository<Room,Integer> {
    String SELECT_RESERVATION="SELECT r.room_id as \"roomId\", r.room_name_en as \"roomNameEn\"," +
            " r.room_name_ua as \"roomNameUa\",r.price as \"price\"," +
            "r.capacity as \"capacity\", r.category \"category\"," +
            "res.status as \"status\" FROM room r LEFT JOIN (SELECT * FROM reservation WHERE start_rent<= ?1\n" +
            "AND end_rent>=?2) as res\n" +
            "ON room_id=fk_room_id WHERE r.capacity>=?3 ORDER BY ?4 desc ";

    List<Room> findAllByPrice(int price, Pageable pageable);
    List<Room> findAllByCapacity(int capacity, Pageable pageable);
    Room findByRoomId(Integer id);
    @Query(value = SELECT_RESERVATION,countQuery = "SELECT count(*) FROM Room ",nativeQuery = true)
    Page<RoomAndOrder> findRoomReservation(Date endRent, Date startRent,  Integer cap,String sort, Pageable pageable);
}
