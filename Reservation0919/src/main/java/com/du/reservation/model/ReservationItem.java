package com.du.reservation.model;

import lombok.Data;

@Data
public class ReservationItem {
    private Long id;
    private Long reservationId;
    private Long serviceId;
    private int quantity;  // 인원 수 또는 예약 수량

    private MyService service;  // Join용 서비스 객체
}
