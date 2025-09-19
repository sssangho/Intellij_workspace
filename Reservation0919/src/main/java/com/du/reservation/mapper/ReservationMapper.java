package com.du.reservation.mapper;

import com.du.reservation.model.Reservation;
import com.du.reservation.model.ReservationItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationMapper {

    @Select("SELECT * FROM reservations ORDER BY id DESC")
    List<Reservation> findAll();

    @Insert("INSERT INTO reservations(customer_name, reservation_date) VALUES(#{customerName}, #{reservationDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Reservation reservation);

    @Delete("DELETE FROM reservations WHERE id = #{id}")
    void delete(Long id);

    // 예약 항목 조회 (ReservationItem + join Service)
    @Select("SELECT ri.*, s.id AS service_id, s.name, s.description, s.price " +
            "FROM reservation_items ri " +
            "JOIN services s ON ri.service_id = s.id " +
            "WHERE ri.reservation_id = #{reservationId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "reservationId", column = "reservation_id"),
            @Result(property = "serviceId", column = "service_id"),
            @Result(property = "quantity", column = "quantity"),
            @Result(property = "service.id", column = "service_id"),
            @Result(property = "service.name", column = "name"),
            @Result(property = "service.description", column = "description"),
            @Result(property = "service.price", column = "price")
    })
    List<ReservationItem> findItemsByReservationId(Long reservationId);

    // 예약 항목 추가
    @Insert("INSERT INTO reservation_items(reservation_id, service_id, quantity) VALUES(#{reservationId}, #{serviceId}, #{quantity})")
    void insertItem(ReservationItem item);

    // 예약 항목 삭제
    @Delete("DELETE FROM reservation_items WHERE id = #{id}")
    void deleteItem(Long id);

    @Select("SELECT * FROM reservations WHERE id = #{id}")
    Reservation findById(Long id);

}

